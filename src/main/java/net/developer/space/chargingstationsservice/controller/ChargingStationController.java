package net.developer.space.chargingstationsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;

@CrossOrigin
@RestController
@RequestMapping("/v1/api/charging-stations")
@Api(value = "Charging Station controller to manage all operations")
public class ChargingStationController {

    @Autowired
    private IChargingStationService service;

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
        if(chargingStationDtos == null){
            return ResponseEntity.notFound().build();
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
    public ResponseEntity<ChargingStationDto> findChargingStationById(@PathVariable("id") String id) {
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
    public ResponseEntity<ChargingStationDto> updateChargingStation(@PathVariable String id,
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
    public ResponseEntity<String> deleteChargingStation(@PathVariable("id") String id){
        this.service.deleteChargingStation(id);
        return ResponseEntity.ok(String.format("Delete charging station with id: %s", id));
    }

    @GetMapping("/find-by-location")
    @ApiOperation(value = "Find a specific Charging Station by location", response = ChargingStationDto.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully searched"),
        @ApiResponse(code = 404, message = "Requested Resource not found")
    })
    public ResponseEntity<ChargingStationDto> findChargingStationByLocation(@RequestParam Location location) {
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
    public ResponseEntity<Status> checkChargingStationStatus(@PathVariable("id") String id) {
        if(this.service.checkChargingStationStatus(id) != null){
            return new ResponseEntity<Status>(this.service.checkChargingStationStatus(id), HttpStatus.OK);
        }
        return new ResponseEntity<>(this.service.checkChargingStationStatus(id), HttpStatus.NOT_FOUND);
    }
}
