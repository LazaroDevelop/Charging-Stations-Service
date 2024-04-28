package net.developer.space.chargingstationsservice.dto;

import lombok.*;
import net.developer.space.chargingstationsservice.entity.ChargingStationEntity;
import net.developer.space.chargingstationsservice.entity.Location;
import net.developer.space.chargingstationsservice.entity.enums.ChargerType;
import net.developer.space.chargingstationsservice.entity.enums.Status;

/**
 * @author Lazaro Noel Guerra Medina
 * @since 17/04/2024
 * @version 1.0.0
 * @implNote Charging Station Data Transfer Object (DTO) that represents a single instance of Charging Station
 */

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ChargingStationDto {
    /**
     * Dto field of identifier
     */
    private String id;
    /**
     * Dto field of Status
     */
    private Status status;
    /**
     * Dto field of Location
     */
    private Location location;
    /**
     * Dto field of Charger type
     */
    private ChargerType chargerType;
    /**
     * Dto field of Number of charging points
     */
    private int numberOfChargingPoints;

    /**
     * Static constructor named "{@code of}", that transform a {@link ChargingStationEntity} into {@link ChargingStationDto}
     * @param entity to transform
     * @return a new Instance of {@link ChargingStationDto}
     */
    public static ChargingStationDto of(ChargingStationEntity entity){
        return ChargingStationDto.builder()
            .id(entity.getId()) 
            .location(entity.getLocation())
            .status(entity.getStatus())
            .chargerType(entity.getChargerType())
            .numberOfChargingPoints(entity.getNumberOfChargingPoints())
            .build();
    }
}
