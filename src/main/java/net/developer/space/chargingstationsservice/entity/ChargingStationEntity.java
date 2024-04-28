package net.developer.space.chargingstationsservice.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.developer.space.chargingstationsservice.entity.enums.ChargerType;
import net.developer.space.chargingstationsservice.entity.enums.Status;

/**
 * @author Lazaro Noel Guerra Medina
 * @since 17/04/2024
 * @version 1.0.0
 * @implNote Charging Station entity that represents a single instance of Charging Station
 */

@Entity
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "charging_station")
public class ChargingStationEntity {
    /**
     * Primary key and unique identifier of the "{@code charging_station}" table
     */
    @Id
    @Getter
    @Setter
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    /**
     * Location of the Charging Station that allows make geolocation
     */
    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private Location location;

    /**
     * Charger type of the Charging Station
     */
    @Setter
    @Getter
    @Column(name = "charger_type")
    @Enumerated(EnumType.STRING)
    private ChargerType chargerType;

    /**
     * The number of charging points of the Charging Station
     */
    @Setter
    @Getter
    @Column(name = "number_of_charging_points")
    private Integer numberOfChargingPoints;

    /**
     * Current Status of the Charging Station {@code AVAILABLE, IN_USE}
     */
    @Setter
    @Getter
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

}
