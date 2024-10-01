package net.developer.space.chargingstationsservice.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.developer.space.chargingstationsservice.entity.enums.ChargerType;
import net.developer.space.chargingstationsservice.entity.enums.Status;

import java.io.Serializable;

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
public class ChargingStationEntity implements Serializable {

    private static final long serialVersionUID = 2L;
    /**
     * Primary key and unique identifier of the "{@code charging_station}" table
     */
    @Id
    @Getter
    @Setter
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Location of the Charging Station that allows make geolocation
     */
    @Getter
    @Setter
    @Embedded
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

    public ChargingStationEntity(Location location, ChargerType chargerType, int numberOfChargingPoints, Status status){
        this.location = location;
        this.chargerType = chargerType;
        this.numberOfChargingPoints = numberOfChargingPoints;
        this.status = status;
    }
}
