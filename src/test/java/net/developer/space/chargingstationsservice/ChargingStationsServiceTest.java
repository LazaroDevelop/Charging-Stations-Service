package net.developer.space.chargingstationsservice;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Import;

import net.developer.space.chargingstationsservice.config.CacheConfig;
import net.developer.space.chargingstationsservice.dto.ChargingStationDto;
import net.developer.space.chargingstationsservice.entity.ChargingStationEntity;
import net.developer.space.chargingstationsservice.entity.Location;
import net.developer.space.chargingstationsservice.entity.enums.ChargerType;
import net.developer.space.chargingstationsservice.entity.enums.Status;
import net.developer.space.chargingstationsservice.repository.IChargingStationRepository;
import net.developer.space.chargingstationsservice.service.ChargingStationService;

@Slf4j
@SpringBootTest
@Import(CacheConfig.class)
class ChargingStationsServiceTest {

	@Mock
	IChargingStationRepository chargingStationRepository;

	@InjectMocks
	ChargingStationService service;

	@Autowired
	CacheManager cacheManager;

	@BeforeEach
	public void setUp(){
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testFindAllChargingStations() {

		Location location1 = new Location();
		location1.setAddress("2nd Street #21");
		location1.setLatitude(23.49502);
		location1.setLongitude(45.39302);

		ChargingStationEntity entity1 = new ChargingStationEntity();
		entity1.setId(1L);
		entity1.setLocation(location1);
		entity1.setChargerType(ChargerType.AC);
		entity1.setNumberOfChargingPoints(4);
		entity1.setStatus(Status.AVAILABLE);

		Location location2 = new Location();
		location2.setAddress("3rd Street #32");
		location2.setLatitude(32.3234);
		location2.setLongitude(21.23382);

		ChargingStationEntity entity2 = new ChargingStationEntity();
		entity2.setId(2L);
		entity2.setLocation(location2);
		entity2.setChargerType(ChargerType.DC_FAST_CHARGE);
		entity2.setNumberOfChargingPoints(2);
		entity2.setStatus(Status.IN_USE);

		List<ChargingStationEntity> mockCharging = new ArrayList<>();
		mockCharging.add(entity1);
		mockCharging.add(entity2);

		when(this.chargingStationRepository.findAll()).thenReturn(mockCharging);

		List<ChargingStationDto> mockDtos = this.service.findAll();

		assertEquals(2, mockDtos.size());

		assertEquals(new Long(1), mockDtos.get(0).getId());
		assertEquals(location1.getLongitude(), mockDtos.get(0).getLongitude());
		assertEquals(location1.getLatitude(), mockDtos.get(0).getLatitude());
		assertEquals(location1.getAddress(), mockDtos.get(0).getAddress());
		assertEquals(ChargerType.AC, mockDtos.get(0).getChargerType());
		assertEquals(4, mockDtos.get(0).getNumberOfChargingPoints());
		assertEquals(Status.AVAILABLE, mockDtos.get(0).getStatus());

		assertEquals(new Long(2), mockDtos.get(1).getId());
		assertEquals(location2.getLongitude(), mockDtos.get(1).getLongitude());
		assertEquals(location2.getLatitude(), mockDtos.get(1).getLatitude());
		assertEquals(location2.getAddress(), mockDtos.get(1).getAddress());
		assertEquals(ChargerType.DC_FAST_CHARGE, mockDtos.get(1).getChargerType());
		assertEquals(2, mockDtos.get(1).getNumberOfChargingPoints());
		assertEquals(Status.IN_USE, mockDtos.get(1).getStatus());
	}

	@Test
	void createNewChargingStation(){
		Location location1 = new Location();
		location1.setAddress("2nd Street #21");
		location1.setLatitude(23.49502);
		location1.setLongitude(45.39302);

		ChargingStationEntity entity1 = new ChargingStationEntity();
		entity1.setId(1L);
		entity1.setLocation(location1);
		entity1.setChargerType(ChargerType.AC);
		entity1.setNumberOfChargingPoints(4);
		entity1.setStatus(Status.AVAILABLE);

		ChargingStationDto dto = new ChargingStationDto();
		dto.setId(2L);
		dto.setAddress(location1.getAddress());
		dto.setLongitude(location1.getLongitude());
		dto.setLatitude(location1.getLatitude());
		dto.setChargerType(ChargerType.AC);
		dto.setNumberOfChargingPoints(4);
		dto.setStatus(Status.AVAILABLE);

		when(this.chargingStationRepository.save(any(ChargingStationEntity.class))).thenReturn(entity1);

		ChargingStationDto mockDto = this.service.createChargingStation(dto);

		assertNotNull(mockDto);
		assertEquals(new Long(1), mockDto.getId());
		assertEquals(location1.getAddress(), mockDto.getAddress());
		assertEquals(location1.getLatitude(), mockDto.getLatitude());
		assertEquals(location1.getLongitude(), mockDto.getLongitude());
		assertEquals(ChargerType.AC, mockDto.getChargerType());
		assertEquals(4, mockDto.getNumberOfChargingPoints());
		assertEquals(Status.AVAILABLE, mockDto.getStatus());
	}
	
	@Test
	void findChargingStationById(){
		Location location = new Location();
		location.setAddress("3rd Street #32");
		location.setLatitude(32.3234);
		location.setLongitude(21.23382);

		ChargingStationEntity entity = new ChargingStationEntity();
		entity.setId(1L);
		entity.setLocation(location);
		entity.setChargerType(ChargerType.DC_FAST_CHARGE);
		entity.setNumberOfChargingPoints(2);
		entity.setStatus(Status.IN_USE);

		Optional<ChargingStationEntity> optionalEntity = Optional.of(entity);

		when(this.chargingStationRepository.findById(anyLong())).thenReturn(optionalEntity);

		ChargingStationDto mockDto = this.service.findChargingStationById(1l);

		assertNotNull(mockDto);
		assertEquals(1L, mockDto.getId());
		assertEquals(location.getAddress(), mockDto.getAddress());
		assertEquals(location.getLongitude(), mockDto.getLongitude());
		assertEquals(location.getLatitude(), mockDto.getLatitude());
		assertEquals(ChargerType.DC_FAST_CHARGE, mockDto.getChargerType());
		assertEquals(2, mockDto.getNumberOfChargingPoints());
		assertEquals(Status.IN_USE, mockDto.getStatus());
	}

	@Test
	void updateChargingStation(){
		Location location = new Location();
		location.setAddress("3rd Street #32");
		location.setLatitude(32.3234);
		location.setLongitude(21.23382);

		ChargingStationEntity entity = new ChargingStationEntity();
		entity.setId(1L);
		entity.setLocation(location);
		entity.setChargerType(ChargerType.DC_FAST_CHARGE);
		entity.setNumberOfChargingPoints(2);
		entity.setStatus(Status.IN_USE);

		ChargingStationDto dto = new ChargingStationDto();
		dto.setId(1L);
		dto.setAddress(location.getAddress());
		dto.setLatitude(location.getLatitude());
		dto.setLongitude(location.getLongitude());
		dto.setChargerType(ChargerType.DC_FAST_CHARGE);
		dto.setNumberOfChargingPoints(2);
		dto.setStatus(Status.IN_USE);

		Optional<ChargingStationEntity> eOptionaln = Optional.of(entity);


		when(this.chargingStationRepository.findById(1L)).thenReturn(eOptionaln);
		when(this.chargingStationRepository.save(any(ChargingStationEntity.class))).thenReturn(entity);

		ChargingStationDto mockDto = this.service.updateChargingStation(dto.getId(), dto);

		assertNotNull(mockDto);
		assertEquals(1L, mockDto.getId());
		assertEquals(ChargerType.DC_FAST_CHARGE, mockDto.getChargerType());
		assertEquals(Status.IN_USE, mockDto.getStatus());
		assertEquals(2, mockDto.getNumberOfChargingPoints());
	}

	@Test
	void findChargingStationByLocation(){
		Location location = new Location();
		location.setAddress("2nd Avenew #32 Founders Street");
		location.setLatitude(23.4231);
		location.setLongitude(56.3232);

		ChargingStationEntity entity = new ChargingStationEntity();
		entity.setId(1L);
		entity.setLocation(location);
		entity.setChargerType(ChargerType.DC_FAST_CHARGE);
		entity.setStatus(Status.AVAILABLE);
		entity.setNumberOfChargingPoints(8);

		when(this.chargingStationRepository.findByLocation(any(Location.class))).thenReturn(entity);

		ChargingStationDto mockDto = this.service.findChargingStationByLocation(location);

		assertNotNull(mockDto);
		assertEquals(1L, mockDto.getId());
		assertEquals(location.getAddress(), mockDto.getAddress());
		assertEquals(location.getLongitude(), mockDto.getLongitude());
		assertEquals(location.getLatitude(), mockDto.getLatitude());
		assertEquals(8, mockDto.getNumberOfChargingPoints());
		assertNotEquals(ChargerType.AC, mockDto.getChargerType());
		assertNotEquals(Status.IN_USE, mockDto.getStatus());
	}

	@Test
	void checkChargingStationStatus(){
		ChargingStationEntity entity = new ChargingStationEntity();

		Location location = new Location("2nd Avenue", 23.4323, 43.3212);

		entity.setId(1L);
		entity.setLocation(location);
		entity.setNumberOfChargingPoints(9);
		entity.setChargerType(ChargerType.AC);
		entity.setStatus(Status.AVAILABLE);

		Optional<ChargingStationEntity> optional = Optional.of(entity);

		log.info("entity: {}", entity);
		log.info("optional: {}", optional);

		when(this.chargingStationRepository.findById(anyLong())).thenReturn(optional);

		ChargingStationDto dto = this.service.findChargingStationById(1L);

		assertEquals(Status.AVAILABLE, dto.getStatus());
		assertNotEquals(Status.IN_USE, dto.getStatus());
	}

	@Test
	void testRedisCache(){
		String cacheName = "charging_station";
		String key = "test_key";
		String value = "test_value";

		Cache cache = cacheManager.getCache(cacheName);

		assert cache != null;
		cache.put(key, value);
	
		String cachedValue = cache.get(key, String.class);

		assertEquals(value, cachedValue);
	}
}
