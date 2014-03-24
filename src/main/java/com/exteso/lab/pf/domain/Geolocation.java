package com.exteso.lab.pf.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * User: celestino
 * Date: 3/24/14
 * Time: 4:07 PM
 */
@Embeddable
public class Geolocation {

    @NotNull
    @Column(precision = 11, scale = 8)
    private BigDecimal latitude;

    @NotNull
    @Column(precision = 11, scale = 8)
    private BigDecimal longitude;

}
