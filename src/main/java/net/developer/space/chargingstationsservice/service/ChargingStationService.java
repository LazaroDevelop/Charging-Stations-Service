package net.developer.space.chargingstationsservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import net.developer.space.chargingstationsservice.dto.ChargingStationDto;
import net.developer.space.chargingstationsservice.entity.ChargingStationEntity;
import net.developer.space.chargingstationsservice.entity.Location;
import net.developer.space.chargingstationsservice.entity.enums.Status;
import net.developer.space.chargingstationsservice.exceptions.ChargingStationNotFoundException;
import net.developer.space.chargingstationsservice.repository.IChargingStationRepository;

@Service
public class ChargingStationService implements IChargingStationService {
    IChargingStationRepository chargingStationRepository;

    @Autowired
    ChargingStationService(IChargingStationRepository chargingStationRepository){
        this.chargingStationRepository = chargingStationRepository;
    }

    @Override
    @Cacheable(value = "charging_stations")
    public List<ChargingStationDto> findAll() {
        return chargingStationRepository.findAll().stream()
                .map(ChargingStationDto::of)
                .collect(Collectors.toList());
    }

    @Override
    public ChargingStationDto createChargingStation(ChargingStationDto cDto) {
        Location location = new Location();
        location.setLongitude(cDto.getLongitude());
        location.setLatitude(cDto.getLatitude());
        location.setAddress(cDto.getAddress());

        ChargingStationEntity entity = new ChargingStationEntity();

        entity.setLocation(location);
        entity.setChargerType(cDto.getChargerType());
        entity.setStatus(Status.AVAILABLE);
        entity.setNumberOfChargingPoints(cDto.getNumberOfChargingPoints());
        return ChargingStationDto.of(this.chargingStationRepository.save(entity));
    }

    @Override
    @Cacheable(value = "charging_stations", key = "#id")
    public ChargingStationDto findChargingStationById(Long id) {
        ChargingStationEntity entity = this.chargingStationRepository.findById(id)
                .orElseThrow(() -> new ChargingStationNotFoundException(
                        String.format(ChargingStationNotFoundException.STATION_EXCEPTION_MESSAGE, id)));
        return ChargingStationDto.of(entity);
    }

    @Override
    @CachePut(value = "charging_stations", key = "#id")
    public ChargingStationDto updateChargingStation(Long id, ChargingStationDto cDto) {
        ChargingStationEntity entity = this.chargingStationRepository.findById(id)
                .orElseThrow(() -> new ChargingStationNotFoundException(
                        String.format(ChargingStationNotFoundException.STATION_EXCEPTION_MESSAGE, id)));

        Location location = new Location();
        location.setLongitude(cDto.getLongitude());
        location.setLatitude(cDto.getLatitude());
        location.setAddress(cDto.getAddress());

        entity.setLocation(location);
        entity.setStatus(cDto.getStatus());
        entity.setNumberOfChargingPoints(cDto.getNumberOfChargingPoints());
        entity.setChargerType(cDto.getChargerType());
        return ChargingStationDto.of(this.chargingStationRepository.save(entity));
    }

    @Override
    @CacheEvict(value = "charging_stations", key = "#id")
    public void deleteChargingStation(Long id) {
        ChargingStationEntity entity = this.chargingStationRepository.findById(id)
                .orElseThrow(() -> new ChargingStationNotFoundException(
                        String.format(ChargingStationNotFoundException.STATION_EXCEPTION_MESSAGE, id)));
        this.chargingStationRepository.delete(entity);
    }

    @Override
    public ChargingStationDto findChargingStationByLocation(Location location) {
        return ChargingStationDto.of(chargingStationRepository.findByLocation(location));
    }

}
