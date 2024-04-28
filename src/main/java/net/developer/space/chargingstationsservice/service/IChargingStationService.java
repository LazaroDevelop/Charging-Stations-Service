package net.developer.space.chargingstationsservice.service;

import java.util.List;

import net.developer.space.chargingstationsservice.dto.ChargingStationDto;
import net.developer.space.chargingstationsservice.entity.Location;
import net.developer.space.chargingstationsservice.entity.enums.Status;

public interface IChargingStationService {
    /**
     * This function returns a list of ChargingStationDto objects representing all charging stations.
     * 
     * @return A list of ChargingStationDto objects is being returned.
     */
    List<ChargingStationDto> findAll();
    /**
     * The function creates a new charging station using the provided ChargingStationDto object.
     * 
     * @param cDto The parameter `cDto` is of type `ChargingStationDto`, which is likely a Data
     * Transfer Object (DTO) used for transferring data related to a charging station. The method
     * `createChargingStation` takes a `ChargingStationDto` object as input and is expected to return a
     * @return The method createChargingStation is returning a ChargingStationDto object.
     */
    ChargingStationDto createChargingStation(ChargingStationDto cDto);
    /**
     * This function finds a charging station by its ID and returns a ChargingStationDto object.
     * 
     * @param id The method `findChargingStationById` takes a `String` parameter `id`, which is used to
     * search for a charging station based on its unique identifier. The method returns a
     * `ChargingStationDto` object that represents the charging station found with the provided `id`.
     * @return An object of type ChargingStationDto with the information of the charging station
     * identified by the given id.
     */
    ChargingStationDto findChargingStationById(Long id);
    /**
     * The function `updateChargingStation` updates a charging station with the provided ID using the
     * information in the `ChargingStationDto` object.
     * 
     * @param id The `id` parameter is a unique identifier for the charging station that you want to
     * update. It is used to specify which charging station should be updated in the system.
     * @param cDto The `cDto` parameter in the `updateChargingStation` method is of type
     * `ChargingStationDto`, which likely represents a Data Transfer Object (DTO) containing
     * information about a charging station. This parameter is used to update the details of a charging
     * station identified by the `id` parameter
     * @return The method `updateChargingStation` is returning a `ChargingStationDto` object.
     */
    ChargingStationDto updateChargingStation(Long id, ChargingStationDto cDto);
    /**
     * The function `deleteChargingStation` in Java is used to remove a charging station based on its
     * ID.
     * 
     * @param id The `deleteChargingStation` function takes a parameter `id`, which is a string
     * representing the unique identifier of the charging station that needs to be deleted.
     */
    void deleteChargingStation(Long id);
    /**
     * This function finds a charging station based on a given location.
     * 
     * @param location The `location` parameter is an object that represents the geographical location
     * of a charging station. It likely contains information such as latitude and longitude coordinates
     * to specify the exact location of the charging station.
     * @return A ChargingStationDto object is being returned.
     */
    ChargingStationDto findChargingStationByLocation(Location location);
    /**
     * The function `checkChargingStationStatus` in Java is used to retrieve the status of a charging
     * station identified by its ID.
     * 
     * @param id The `id` parameter in the `ChargingStationStatus` function is a unique identifier that
     * is used to specify which charging station you want to check the status of. This identifier helps
     * the function to locate and retrieve the status information for the specific charging station
     * identified by the `id`.
     * @return The method `ChargingStationStatus(String id)` is likely returning the status of a
     * charging station with the specified ID. The return value could be information such as whether
     * the station is currently in use, available, offline, or any other relevant status.
     */
    Status checkChargingStationStatus(Long id);
}
