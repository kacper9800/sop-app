/*
 * System Obsługi Praktyk
 * Kacper Rzymkiewicz #2020
 */

package pl.sop.organizationStructure;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import java.util.Set;
import pl.sop.entities.ActivationKey;
import pl.sop.entities.BasicEntity;

import javax.persistence.*;
import java.io.Serializable;
import pl.sop.entities.Direction;
import pl.sop.entities.User;

@Entity
@Table(name = "institutes")
public class Institute extends BasicEntity implements Serializable {

    @Column(name = "name", nullable = false)
    private String name;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<User> users;

    @JsonIgnore
    @OneToMany(mappedBy="institute")
    private Set<ActivationKey> activationKeys;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    private Faculty faculty;

    @JsonIgnore
    @OneToMany(mappedBy = "institute", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Department> departments;

    @JsonIgnore
    @OneToMany(mappedBy = "institute", orphanRemoval = true)
    private List<Direction> directions;

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

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public Set<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<Department> departments) {
        this.departments = departments;
    }

    public List<Direction> getDirections() {
        return directions;
    }

    public void setDirections(List<Direction> directions) {
        this.directions = directions;
    }
}
