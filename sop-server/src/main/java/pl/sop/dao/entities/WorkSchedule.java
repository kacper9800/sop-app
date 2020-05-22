package pl.sop.dao.entities;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import pl.sop.dao.entities.organizationStructure.College;
import pl.sop.dao.entities.organizationStructure.Department;
import pl.sop.dao.entities.organizationStructure.Faculty;
import pl.sop.dao.entities.organizationStructure.Institute;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.util.Date;

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
}
