package com.exteso.lab.pf.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.joda.deser.LocalDateDeserializer;
import com.exteso.lab.pf.domain.util.CustomLocalDateSerializer;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

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
public class ParkPlace implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long parkplace_id;

    @Size(min = 1, max = 50)
    private String name;

    @Size(min = 1, max = 50)
    private String description;

    public long getId() {
        return parkplace_id;
    }

    public void setId(long id) {
        this.parkplace_id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ParkPlace parkplace = (ParkPlace) o;

        if (parkplace_id != parkplace.parkplace_id) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (parkplace_id ^ (parkplace_id >>> 32));
    }

    @Override
    public String toString() {
        return "ParkPlace{" +
                "id=" + parkplace_id +
                ", name='" + name + '\'' +
                ", description=" + description +
                '}';
    }
}
