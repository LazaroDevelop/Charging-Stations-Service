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
import net.developer.space.chargingstationsservice.repository.ChargingStationRepository;

@Service
public class ChargingStationService implements IChargingStationService {

    @Autowired
    ChargingStationRepository repository;

    @Override
    @Cacheable(value = "all_charging_stations")
    public List<ChargingStationDto> findAll() {
        return repository.findAll().stream()
                .map(i -> ChargingStationDto.of(i))
                .collect(Collectors.toList());
    }

    @Override
    public ChargingStationDto createChargingStation(ChargingStationDto cDto) {
        ChargingStationEntity entity = new ChargingStationEntity();

        entity.setChargerType(cDto.getChargerType());
        entity.setStatus(Status.AVAILABLE);
        entity.setLocation(cDto.getLocation());
        entity.setNumberOfChargingPoints(cDto.getNumberOfChargingPoints());
        ChargingStationEntity newEntity = this.repository.save(entity);

        return ChargingStationDto.builder()
                .id(newEntity.getId())
                .chargerType(newEntity.getChargerType())
                .location(newEntity.getLocation())
                .numberOfChargingPoints(newEntity.getNumberOfChargingPoints())
                .status(newEntity.getStatus())
                .build();
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
    @CachePut(value = "charging_station")
    public ChargingStationDto updateChargingStation(Long id, ChargingStationDto cDto) {
        ChargingStationEntity entity = this.chargingStationRepository.findById(id)
                .orElseThrow(() -> new ChargingStationNotFoundException(
                        String.format(ChargingStationNotFoundException.STATION_EXCEPTION_MESSAGE, id)));
        entity.setLocation(cDto.getLocation());
        entity.setStatus(cDto.getStatus());
        entity.setNumberOfChargingPoints(cDto.getNumberOfChargingPoints());
        entity.setChargerType(cDto.getChargerType());
        return ChargingStationDto.of(this.repository.save(entity));
    }

    @Override
    @CacheEvict(value = "charging_station", key = "#id")
    public void deleteChargingStation(Long id) {
        ChargingStationEntity entity = this.chargingStationRepository.findById(id)
                .orElseThrow(() -> new ChargingStationNotFoundException(
                        String.format(ChargingStationNotFoundException.STATION_EXCEPTION_MESSAGE, id)));
        this.repository.delete(entity);
    }

    @Override
    public ChargingStationDto findChargingStationByLocation(Location location) {
        ChargingStationEntity entity = this.repository.findByLocation(location);
        if (entity != null) {
            return ChargingStationDto.of(entity);
        }
        throw new ChargingStationNotFoundException(ChargingStationNotFoundException.LOCATION_EXCEPTION_MESSAGE);
    }

    @Override
    @Cacheable(value = "charging_station_status")
    public Status checkChargingStationStatus(Long id) {
        ChargingStationEntity entity = this.chargingStationRepository.findById(id)
                .orElseThrow(() -> new ChargingStationNotFoundException(
                        String.format(ChargingStationNotFoundException.STATION_EXCEPTION_MESSAGE, id)));
        return entity.getStatus();
    }

}
