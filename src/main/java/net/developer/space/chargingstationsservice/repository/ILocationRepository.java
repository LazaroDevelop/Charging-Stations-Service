package net.developer.space.chargingstationsservice.repository;

import net.developer.space.chargingstationsservice.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Lazaro Noel Guerra Medina
 * @since 17/04/2024
 * @version 1.0.0
 * @implNote Location repository for sql backend operations
 */

@Repository
public interface ILocationRepository extends JpaRepository<Location, Long> {
}
