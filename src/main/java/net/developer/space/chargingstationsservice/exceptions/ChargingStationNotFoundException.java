package net.developer.space.chargingstationsservice.exceptions;

public class ChargingStationNotFoundException extends RuntimeException {
    public static final String STATION_EXCEPTION_MESSAGE = "Error, charging station with id: %s not found";
    public static final String LOCATION_EXCEPTION_MESSAGE = "Error, can't find any charging station with this location";
    public ChargingStationNotFoundException(String message){
        super(message);
    }
}
