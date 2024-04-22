package net.developer.space.chargingstationsservice.dto;

import lombok.*;
import net.developer.space.chargingstationsservice.entity.Location;
import net.developer.space.chargingstationsservice.entity.enums.ChargerType;
import net.developer.space.chargingstationsservice.entity.enums.Status;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ChargingStationsDto {
    private String id;
    private Status status;
    private Location location;
    private ChargerType chargerType;
    private int numberOfChargingPoints;
}
