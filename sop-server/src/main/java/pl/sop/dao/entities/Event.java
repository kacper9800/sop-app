package pl.sop.dao.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 20)
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "stop_date")
    private Date stopDate;

//    @Column(name = "college_id")
//    private College college;

    @Column(name = "all_day")
    private boolean allDay;

//    @Column(name = "repeat")
//    private String repeat;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(name = "deleted")
    private Boolean deleted;

    @Column(name = "active")
    private Boolean active;

    public Event() {
    }

    public Event(@NotBlank @Size(max = 20) String name, String description, Date startDate, Date stopDate, boolean allDay, User user) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.stopDate = stopDate;
        this.allDay = allDay;
        this.user = user;
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

    public void setStartDate(Date startdate) {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
