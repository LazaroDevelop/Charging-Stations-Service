package net.developer.space.chargingstationsservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.developer.space.chargingstationsservice.entity.ChargingStationEntity;
import net.developer.space.chargingstationsservice.entity.Location;

/**
 * @author Lazaro Noel Guerra Medina
 * @since 17/04/2024
 * @version 1.0.0
 * @implNote Charging Station repository for sql backend operations
 */

@Repository
public interface IChargingStationRepository extends JpaRepository<ChargingStationEntity, Long> {
    /**
     * Find all Charging Stations in the repository
     * @return a {@link List} of {@link ChargingStationEntity}
     */
    List<ChargingStationEntity> findAll();

    /**
     * Find a Charging Station by Location
     * @param location of the Charging Station entity
     * @return a {@link ChargingStationEntity}
     */
    ChargingStationEntity findByLocation(Location location);

    /**
     * Find a Charging Station by identifier
     * @param id of the Charging Station
     * @return a {@link Optional} of {@link ChargingStationEntity}
     */
    Optional<ChargingStationEntity> findById(Long id);
}
