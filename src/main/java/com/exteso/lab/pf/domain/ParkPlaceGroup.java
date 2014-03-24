package com.exteso.lab.pf.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.joda.deser.LocalDateDeserializer;
import com.exteso.lab.pf.domain.util.CustomLocalDateSerializer;
import lombok.Data;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * A ParkPlaceGroup.
 */
@Entity
@Table(name = "T_PARKPLACEGROUP")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Data
public class ParkPlaceGroup implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;

    @Size(min = 1, max = 1024)
    private String name;

    @NotNull
    private ValidityInterval validityInterval;

    @NotNull
    private Geolocation geolocation;

    @OneToMany(orphanRemoval=true)
    @JoinColumn()
    private List<ParkPlace> parkPlaces;

    private User administrator;

}
