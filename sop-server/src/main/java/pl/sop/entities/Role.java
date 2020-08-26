/*
 * System Obsługi Praktyk
 * Kacper Rzymkiewicz #2020
 */

package pl.sop.entities;

import pl.sop.enums.ERole;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "roles")
public class Role extends BasicEntity implements Serializable {

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;

    public Role() {
    }

    public Role(ERole name) {
        this.name = name;
    }

    public ERole getName() {
        return name;
    }

    public void setName(ERole name) {
        this.name = name;
    }
}
