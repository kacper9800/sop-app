package pl.sop.dto;

import java.util.Date;

public class WorkScheduleDTO {
    private Long id;
    private String name;
    private String description;
    private Date startDate;
    private Date stopDate;
    private Integer breaks;
    private Date startHour;
    private Date stopHour;
    private Long[] events;
    private Long[] users;
    private Long[] locations;


//    private String additionalInfo;
//    private Long collegeId;
//    private String collegeName;
//    private Long facultyId;
//    private String facultyName;
//    private Long instituteId;
//    private String instituteName;
//    private Long departmentId;
//    private String departmentName;
//    private Long userId;
//    private String userName;

    private boolean active;
    private boolean deleted;

    public WorkScheduleDTO() {
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

//    public String getAdditionalInfo() {
//        return additionalInfo;
//    }
//
//    public void setAdditionalInfo(String additionalInfo) {
//        this.additionalInfo = additionalInfo;
//    }
//
//    public Long getCollegeId() {
//        return collegeId;
//    }
//
//    public void setCollegeId(Long collegeId) {
//        this.collegeId = collegeId;
//    }
//
//    public String getCollegeName() {
//        return collegeName;
//    }
//
//    public void setCollegeName(String collegeName) {
//        this.collegeName = collegeName;
//    }
//
//    public Long getFacultyId() {
//        return facultyId;
//    }
//
//    public void setFacultyId(Long facultyId) {
//        this.facultyId = facultyId;
//    }
//
//    public String getFacultyName() {
//        return facultyName;
//    }
//
//    public void setFacultyName(String facultyName) {
//        this.facultyName = facultyName;
//    }
//
//    public Long getInstituteId() {
//        return instituteId;
//    }
//
//    public void setInstituteId(Long instituteId) {
//        this.instituteId = instituteId;
//    }
//
//    public String getInstituteName() {
//        return instituteName;
//    }
//
//    public void setInstituteName(String instituteName) {
//        this.instituteName = instituteName;
//    }
//
//    public Long getDepartmentId() {
//        return departmentId;
//    }
//
//    public void setDepartmentId(Long departmentId) {
//        this.departmentId = departmentId;
//    }
//
//    public String getDepartmentName() {
//        return departmentName;
//    }
//
//    public void setDepartmentName(String departmentName) {
//        this.departmentName = departmentName;
//    }
//
//    public Long getUserId() {
//        return userId;
//    }
//
//    public void setUserId(Long userId) {
//        this.userId = userId;
//    }
//
//    public String getUserName() {
//        return userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
