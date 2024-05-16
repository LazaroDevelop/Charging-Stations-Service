package net.developer.space.chargingstationsservice;


import net.developer.space.chargingstationsservice.controller.ChargingStationController;
import net.developer.space.chargingstationsservice.dto.ChargingStationDto;
import net.developer.space.chargingstationsservice.entity.Location;
import net.developer.space.chargingstationsservice.entity.enums.ChargerType;
import net.developer.space.chargingstationsservice.entity.enums.Status;
import net.developer.space.chargingstationsservice.service.ChargingStationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(ChargingStationController.class)
class ChargingStationsControllerMvcTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    ChargingStationService service;

    @Test
    void testFetchAllEndpoint() throws Exception {
        ChargingStationDto dto = new ChargingStationDto();
        dto.setId(1L);
        dto.setChargerType(ChargerType.AC);
        dto.setStatus(Status.AVAILABLE);
        dto.setAddress("3rd Avenue");
        dto.setLongitude(23.4321);
        dto.setLatitude(32.2134);
        dto.setNumberOfChargingPoints(8);

        List<ChargingStationDto> dtos = Arrays.asList(dto);

        given(this.service.findAll()).willReturn(dtos);

        mvc.perform(get("/v1/api/charging-stations/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is((dto.getId().intValue()))));
    }

    @Test
    void testFindChargingStationById() throws Exception {
        ChargingStationDto dto = new ChargingStationDto();
        dto.setId(1L);
        dto.setChargerType(ChargerType.AC);
        dto.setStatus(Status.AVAILABLE);
        dto.setAddress("1st Avenue");
        dto.setLatitude(23.4232);
        dto.setLongitude(32.1234);
        dto.setNumberOfChargingPoints(8);

        given(this.service.findChargingStationById(anyLong())).willReturn(dto);

        mvc.perform(get("/v1/api/charging-stations/find-by-id/{id}", dto.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
