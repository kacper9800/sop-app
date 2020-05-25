/*
 * System Obsługi Praktyk
 * Kacper Rzymkiewicz #2020
 */

package pl.sop.dao.entities.organizationStructure;

import pl.sop.dao.entities.BasicEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Institute extends BasicEntity implements Serializable {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "short_name", nullable = false)
    private String short_name;


}