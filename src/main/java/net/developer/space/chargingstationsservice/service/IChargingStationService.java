package net.developer.space.chargingstationsservice.service;

import java.util.List;

import net.developer.space.chargingstationsservice.dto.ChargingStationDto;
import net.developer.space.chargingstationsservice.entity.Location;
import net.developer.space.chargingstationsservice.entity.enums.Status;

public interface IChargingStationService {
    List<ChargingStationDto> findAll();
    ChargingStationDto createChargingStation(ChargingStationDto cDto);
    ChargingStationDto findChargingStationById(String id);
    ChargingStationDto updateChargingStation(String id, ChargingStationDto cDto);
    void deleteChargingStation(String id);
    ChargingStationDto findChargingStationByLocation(Location location);
    Status checkChargingStationStatus(String id);
}
