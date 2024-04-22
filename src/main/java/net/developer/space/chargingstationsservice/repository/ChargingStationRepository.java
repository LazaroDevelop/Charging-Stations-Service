package net.developer.space.chargingstationsservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.developer.space.chargingstationsservice.entity.ChargingStationEntity;
import net.developer.space.chargingstationsservice.entity.Location;

@Repository
public interface ChargingStationRepository extends JpaRepository<ChargingStationEntity, String> {
    List<ChargingStationEntity> findAll();
    List<ChargingStationEntity> findByLocation(Location location);
    Optional<ChargingStationEntity> findById(String id);
}
