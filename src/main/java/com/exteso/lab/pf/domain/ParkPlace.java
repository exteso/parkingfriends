package com.exteso.lab.pf.domain;

import lombok.Data;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * A ParkPlace.
 */
@Entity
@Table(name = "T_PARKPLACE")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Data
public class ParkPlace implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;

    @Size(min = 1, max = 1024)
    private String name;

    private Geolocation geolocation;

    @NotNull
    private ValidityInterval validityInterval;

    @NotNull
    private User owner;

}
