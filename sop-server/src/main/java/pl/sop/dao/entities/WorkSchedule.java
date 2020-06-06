package pl.sop.dao.entities;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import pl.sop.dao.entities.organizationStructure.College;
import pl.sop.dao.entities.organizationStructure.Department;
import pl.sop.dao.entities.organizationStructure.Faculty;
import pl.sop.dao.entities.organizationStructure.Institute;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class WorkSchedule extends BasicEntity implements Serializable {

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "stop_date")
    private Date stopDate;

    @Column(name = "breaks_duration")
    private String breaks_duration;

    @Column(name = "additional_info")
    private String additionalInfo;

    @OneToOne
    @Fetch(FetchMode.JOIN)
    private College college;

    @OneToOne
    @Fetch(FetchMode.JOIN)
    private Faculty faculty;

    @OneToOne
    @Fetch(FetchMode.JOIN)
    private Institute institute;

    @OneToOne
    @Fetch(FetchMode.JOIN)
    private Department department;

    @OneToOne
    @Fetch(FetchMode.JOIN)
    private User user;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinTable(name = "work_schedule_locations", joinColumns = {
            @JoinColumn(name = "work_schedule_id", nullable = false)}, inverseJoinColumns = {
            @JoinColumn(name = "location_id", nullable = false)
    })
    @Fetch(FetchMode.JOIN)
    private Set<Location> locations;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinTable(name = "work_schedule_events", joinColumns = {
            @JoinColumn(name = "work_schedule_id", nullable = false)}, inverseJoinColumns = {
            @JoinColumn(name = "event_id", nullable = false)
    })
    @Fetch(FetchMode.JOIN)
    private Set<Event> events;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinTable(name = "work_schedule_users", joinColumns = {
            @JoinColumn(name = "work_schedule_id", nullable = false)}, inverseJoinColumns = {
            @JoinColumn(name = "user_id", nullable = false)})
    @Fetch(FetchMode.JOIN)
    private Set<User> users;


    public WorkSchedule() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getStopDate() {
        return stopDate;
    }

    public void setStopDate(Date stopDate) {
        this.stopDate = stopDate;
    }

    public String getBreaks_duration() {
        return breaks_duration;
    }

    public void setBreaks_duration(String breaks_duration) {
        this.breaks_duration = breaks_duration;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public College getCollege() {
        return college;
    }

    public void setCollege(College college) {
        this.college = college;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public Institute getInstitute() {
        return institute;
    }

    public void setInstitute(Institute institute) {
        this.institute = institute;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Location> getLocations() {
        return locations;
    }

    public void setLocations(Set<Location> locations) {
        this.locations = locations;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    public void addEvent(Event event) {
        if (this.events == null) {
            this.events = new HashSet<>();
        }
        this.events.add(event);
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public void addUser(User user) {
        if (this.users == null) {
            this.users = new HashSet<>();
        }
        this.users.add(user);
    }
}
