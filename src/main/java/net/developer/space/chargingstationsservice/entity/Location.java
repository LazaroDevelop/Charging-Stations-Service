package net.developer.space.chargingstationsservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Lazaro Noel Guerra Medina
 * @since 17/04/2024
 * @version 1.0.0
 * @implNote Location entity that represents the geolocation of a Charging Station
 */


@Entity
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "location")
public class Location {
    /**
     * Primary key and unique identifier of the table "{@code location}"
     */
    @Id
    @Setter
    @Getter
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    /**
     * Current Address of the Charging Station
     */
    @Getter
    @Setter
    private String address;
    /**
     * Geolocation latitude of the Charging Station
     */
    @Getter
    @Setter
    private double latitude;
    /**
     * Geolocation longitude of the Charging Station
     */
    @Getter
    @Setter
    private double longitude;
    /**
     * Foreign key of Charging station {@code One to One} relationship
     */
    @Getter
    @Setter
    @OneToOne(mappedBy = "location")
    private ChargingStationEntity chargingStationEntity;
}
