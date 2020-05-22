package pl.sop.dao.entities;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import pl.sop.dao.entities.organizationStructure.College;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Entity
public class Location extends BasicEntity implements Serializable {

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "address")
    private String address;

    @Column(name = "floor")
    private Integer floor;

    @Column(name = "room")
    private String room;

    @OneToOne
    @Fetch(FetchMode.JOIN)
    private College college;

    @Column(name = "type")
    private Integer type;

    @Column(name = "parent_id")
    private Long parentId;

}
