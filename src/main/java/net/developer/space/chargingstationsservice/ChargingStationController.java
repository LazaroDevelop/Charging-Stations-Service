package net.developer.space.chargingstationsservice;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("v1/api/charging-stations")
public class ChargingStationController {

    @Autowired
    private IChargingStationService service;

    @PostMapping("/create")
    public ResponseEntity<ChargingStationDto> createChargingStation(@RequestBody ChargingStationDto dto) {
        ChargingStationDto dStationDto = this.service.createChargingStation(dto);
        if (dStationDto != null) {
            return ResponseEntity.ok(dStationDto);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<ChargingStationDto> findChargingStationById(@PathVariable("id") String id) {
        ChargingStationDto dto = this.service.findChargingStationById(id);
        if (dto != null) {
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ChargingStationDto> updateChargingStation(@PathVariable String id,
            @RequestBody ChargingStationDto dto) {
        ChargingStationDto newDto = this.service.updateChargingStation(id, dto);
        if (newDto != null) {
            return ResponseEntity.ok(newDto);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(/"delete/{id}")
    public ResponseEntity<String> deleteChargingStation(@PathVariable("id") String id){
        this.service.deleteChargingStation(id);
        return ResponseEntity.ok(String.format("Delete charging station with id: %s", id));
    }

    @GetMapping("/find-by-location")
    public ResponseEntity<ChargingStationDto> findChargingStationByLocation(@RequestParam Location location) {
        ChargingStationDto dto = this.service.findChargingStationByLocation(location);
        if (dto != null)
            return ResponseEntity.ok(dto);
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/check-status/{id}")
    public ResponseEntity<Status> getMethodName(@PathVariable("id") String id) {
        Status sts = this.service.checkChargingStationStatus(id);
        if(sts != null){
            return ResponseEntity.ok(sts);
        }
        return ResponseEntity.notFound().build();
    }
}
