package net.developer.space.chargingstationsservice.dto;

import lombok.*;
import net.developer.space.chargingstationsservice.entity.ChargingStationEntity;
import net.developer.space.chargingstationsservice.entity.Location;
import net.developer.space.chargingstationsservice.entity.enums.ChargerType;
import net.developer.space.chargingstationsservice.entity.enums.Status;


@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ChargingStationDto {
    private String id;
    private Status status;
    private Location location;
    private ChargerType chargerType;
    private int numberOfChargingPoints;

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
