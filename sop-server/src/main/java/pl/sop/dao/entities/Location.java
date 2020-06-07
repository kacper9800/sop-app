package pl.sop.dao.entities;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import pl.sop.dao.entities.organizationStructure.College;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "locations")
public class Location extends BasicEntity implements Serializable {

    @Column(name = "name")
    private String name;

//    @Column(name = "description")
//    private String description;

    @Column(name = "address")
    private String address;

    @Column(name = "floor")
    private Integer floor;

    @Column(name = "room_number")
    private String room;

//    @OneToOne
//    @Fetch(FetchMode.JOIN)
//    private College college;

    @Column(name = "type")
    private Integer type;

    @Column(name = "parent_id")
    private Long parentId;

    public Location() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

//    public College getCollege() {
//        return college;
//    }
//
//    public void setCollege(College college) {
//        this.college = college;
//    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}
