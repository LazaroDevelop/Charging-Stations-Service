package net.developer.space.chargingstationsservice.exceptions;

/**
 * @author Lazaro Noel Guerra Medina
 * @since 17/04/2024
 * @version 1.0.0
 * @implNote Custom exception for Charging stations or Location issues
 */
public class ChargingStationNotFoundException extends RuntimeException {
    /**
     * Custom-formatted message for type exceptions not found for charging stations
     */
    public static final String STATION_EXCEPTION_MESSAGE = "Error, charging station with id: %s not found";
    /**
     * Custom-formatted message for type exceptions not found for localizations
     */
    public static final String LOCATION_EXCEPTION_MESSAGE = "Error, can't find any charging station with this location";
    /**
     * Custom-formatted message for type exceptions not found for localizations by id
     */
    public static final String LOCATION_NOT_FOUND_MESSAGE = "Error, location with id: %s, not found";

    /**
     * Inherit Runtime Exception constructor with message parameter
     * @param message with cause of this exception
     */
    public ChargingStationNotFoundException(String message){
        super(message);
    }
}
