package net.developer.space.chargingstationsservice.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Lazaro Noel Guerra Medina
 * @since 17/04/2024
 * @version 1.0.0
 * @implNote Location entity that represents the geolocation of a Charging Station
 */


@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Location implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * Current Address of the Charging Station
     */
    private String address;
    /**
     * Geolocation latitude of the Charging Station
     */    
    private double latitude;
    /**
     * Geolocation longitude of the Charging Station
     */
    private double longitude;
}
