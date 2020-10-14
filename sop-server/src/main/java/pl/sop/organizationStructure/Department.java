package pl.sop.organizationStructure;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Set;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import pl.sop.entities.ActivationKey;
import pl.sop.entities.BasicEntity;
import pl.sop.entities.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@Table(name = "departments")
public class Department extends BasicEntity implements Serializable {


    @Column(name = "name", nullable = false)
    private String name;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<User> users;

    @JsonIgnore
    @OneToMany(mappedBy="department")
    private Set<ActivationKey> activationKeys;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    private Institute institute;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<ActivationKey> getActivationKeys() {
        return activationKeys;
    }

    public void setActivationKeys(Set<ActivationKey> activationKeys) {
        this.activationKeys = activationKeys;
    }

    public Institute getInstitute() {
        return institute;
    }

    public void setInstitute(Institute institute) {
        this.institute = institute;
    }
}
