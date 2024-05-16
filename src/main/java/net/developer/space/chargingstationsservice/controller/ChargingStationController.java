package net.developer.space.chargingstationsservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import net.developer.space.chargingstationsservice.dto.ChargingStationDto;
import net.developer.space.chargingstationsservice.entity.Location;
import net.developer.space.chargingstationsservice.entity.enums.Status;
import net.developer.space.chargingstationsservice.service.IChargingStationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

/**
 * @author Lazaro Noel Guerra Medina
 * @since 17/04/2024
 * @version 1.0.0
 * @implNote Charging Station Controller to manage all operations via http request
 * @apiNote API version 1.0 - Swagger Doc v2
 */
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/v1/api/charging-stations")
@Api(value = "Charging Station controller to manage all operations")
public class ChargingStationController {

    IChargingStationService service;

    @Autowired
    ChargingStationController(IChargingStationService service){
        this.service = service;
    }

    @GetMapping("/all")
    @ApiOperation(value = "Create and Save a new Charging Station item provided", response = ChargingStationDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully fetch all charging station"),
            @ApiResponse(code = 204, message = "Empty result"),
            @ApiResponse(code = 404, message = "Requested Resource not found")
    })
    public ResponseEntity<List<ChargingStationDto>> fetchAll(){
        List<ChargingStationDto> chargingStationDtos = this.service.findAll();
        if(chargingStationDtos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(chargingStationDtos);
    }

    @PostMapping("/create")
    @ApiOperation(value = "Create and Save a new Charging Station item provided", response = ChargingStationDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully saved a charging station"),
            @ApiResponse(code = 404, message = "Requested Resource not found")
    })
    public ResponseEntity<ChargingStationDto> createChargingStation(@RequestBody ChargingStationDto dto) {
        ChargingStationDto dStationDto = this.service.createChargingStation(dto);
        if (dStationDto != null) {
            return ResponseEntity.ok(dStationDto);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/find-by-id/{id}")
    @ApiOperation(value = "Return a Charging Station for provided identifier", response = ChargingStationDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully searched"),
            @ApiResponse(code = 404, message = "Requested Resource not found")
    })
    public ResponseEntity<ChargingStationDto> findChargingStationById(@PathVariable("id")Long id) {
        ChargingStationDto dto = this.service.findChargingStationById(id);
        if (dto != null) {
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/update/{id}")
    @ApiOperation(value = "Update a given Charging Station", response = ChargingStationDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated a existing Charging Station"),
            @ApiResponse(code = 404, message = "Requested Resource not found")
    })
    public ResponseEntity<ChargingStationDto> updateChargingStation(@PathVariable Long id,
                                                                    @RequestBody ChargingStationDto dto) {
        ChargingStationDto newDto = this.service.updateChargingStation(id, dto);
        if (newDto != null) {
            return ResponseEntity.ok(newDto);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Delete a given Charing Station", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully Charging Station eliminated"),
            @ApiResponse(code = 404, message = "Requested Resource not found")
    })
    public ResponseEntity<String> deleteChargingStation(@PathVariable("id") Long id) {
        this.service.deleteChargingStation(id);
        return ResponseEntity.ok(String.format("Delete charging station with id: %s", id));
    }

    @PostMapping("/find-by-location")
    @ApiOperation(value = "Find a specific Charging Station by location", response = ChargingStationDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully searched"),
            @ApiResponse(code = 404, message = "Requested Resource not found")
    })
    public ResponseEntity<ChargingStationDto> findChargingStationByLocation(@RequestBody Location location) {
        log.info("location provided - {}", location);
        ChargingStationDto dto = this.service.findChargingStationByLocation(location);
        if (dto != null)
            return ResponseEntity.ok(dto);
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/check-status/{id}")
    @ApiOperation(value = "Check the status of one charging station for provided id", response = Status.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully status checked"),
            @ApiResponse(code = 404, message = "Requested Resource not found")
    })
    public ResponseEntity<Status> checkChargingStationStatus(@PathVariable("id") Long id) {
        ChargingStationDto dto = service.findChargingStationById(id);
        if (dto != null) {
            return ResponseEntity.ok(dto.getStatus());
        }
        return ResponseEntity.notFound().build();
    }
}
