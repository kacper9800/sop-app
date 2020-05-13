package pl.sop.dto;

import pl.sop.dao.entities.User;

import java.util.Date;

public class EventDTO {
    private Long id;
    private String name;
    private String description;
    private Integer workingHours;
    private Date startDate;
    private Date stopDate;
    private boolean allDay;
    private Long userId;

    public EventDTO() {
    }

    public EventDTO(Long id, String name, String description, Integer workingHours, Date startDate, Date stopDate, boolean allDay, Long userId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.workingHours = workingHours;
        this.startDate = startDate;
        this.stopDate = stopDate;
        this.allDay = allDay;
        this.userId = userId;
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

    public Integer getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(Integer workingHours) {
        this.workingHours = workingHours;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
