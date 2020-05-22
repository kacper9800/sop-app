package pl.sop.dao.entities;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import pl.sop.dao.entities.BasicEntity;
import pl.sop.dao.entities.Location;
import pl.sop.dao.entities.User;
import pl.sop.dao.entities.organizationStructure.College;
import pl.sop.dao.entities.organizationStructure.Department;
import pl.sop.dao.entities.organizationStructure.Faculty;
import pl.sop.dao.entities.organizationStructure.Institute;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Duration;
import java.util.Date;

@Entity
@Table(name = "events")
public class Event extends BasicEntity implements Serializable {

    @NotBlank
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "duration")
    private String duration;

    @OneToOne
    @Fetch(FetchMode.JOIN)
    private Location location;

    @OneToOne
    @Fetch(FetchMode.JOIN)
    private User instructor;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "stop_date")
    private Date stopDate;

    @Column(name = "all_day")
    private boolean allDay;

//    @Column(name = "repeat")
//    private String repeat;

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

    @Column(name = "deleted")
    private Boolean deleted;

    @Column(name = "active")
    private Boolean active;

//    @Column(name = "instructor_id")
//    private Long instructorId;

    public Event() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getDuration() {
        return duration;
    }

    public void setDuration(String  duration) {
        this.duration = duration;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public User getInstructor() {
        return instructor;
    }

    public void setInstructor(User instructor) {
        this.instructor = instructor;
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

    public boolean isAllDay() {
        return allDay;
    }

    public void setAllDay(boolean allDay) {
        this.allDay = allDay;
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

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

//    public Long getInstructorId() {
//        return instructorId;
//    }
//
//    public void setInstructorId(Long instructorId) {
//        this.instructorId = instructorId;
//    }
}
