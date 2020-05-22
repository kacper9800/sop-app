package pl.sop.dao.entities.organizationStructure;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import pl.sop.dao.entities.BasicEntity;
import pl.sop.dao.entities.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Entity
public class Department extends BasicEntity implements Serializable {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "short_name", nullable = false)
    private String short_name;

//    @ManyToOne
//    @Fetch(FetchMode.JOIN)
//    private User user;

}
