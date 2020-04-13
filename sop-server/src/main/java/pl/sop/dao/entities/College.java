/*
 * System Obsługi Praktyk
 * Kacper Rzymkiewicz #2020
 */

package pl.sop.dao.entities;

import javax.persistence.*;

@Entity
public class College {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "short_name", nullable = false)
    private String short_name;

    @Column(name = "voivodeship_id", nullable = false)
    private Long voivodeship_id;
}
