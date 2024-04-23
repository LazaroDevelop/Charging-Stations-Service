package net.developer.space.chargingstationsservice;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import net.developer.space.chargingstationsservice.repository.ChargingStationRepository;
import net.developer.space.chargingstationsservice.service.ChargingStationService;

@SpringBootTest
@Import(CacheConfig.class)
class ChargingStationsServiceApplicationTests {

	@Mock
	ChargingStationRepository repository;

	@InjectMocks
	ChargingStationService service;

	@Autowired
	private CacheManager cacheManager;

	@BeforeEach
	public void setUp(){
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testFindAllChargingStations() {

		Location location1 = new Location();
		location1.setId("id_location1");
		location1.setAddress("2nd Street #21");
		location1.setLatitude(23.49502);
		location1.setLongitude(45.39302);

		ChargingStationEntity entity1 = new ChargingStationEntity();
		entity1.setId("id1");
		entity1.setLocation(location1);
		entity1.setChargerType(ChargerType.AC);
		entity1.setNumberOfChargingPoints(4);
		entity1.setStatus(Status.AVAILABLE);

		Location location2 = new Location();
		location2.setId("id_location2");
		location2.setAddress("3rd Street #32");
		location2.setLatitude(32.3234);
		location2.setLongitude(21.23382);

		ChargingStationEntity entity2 = new ChargingStationEntity();
		entity2.setId("id2");
		entity2.setLocation(location2);
		entity2.setChargerType(ChargerType.DC_FAST_CHARGE);
		entity2.setNumberOfChargingPoints(2);
		entity2.setStatus(Status.IN_USE);

		List<ChargingStationEntity> mockCharging = new ArrayList<>();
		mockCharging.add(entity1);
		mockCharging.add(entity2);

		when(this.repository.findAll()).thenReturn(mockCharging);

		List<ChargingStationDto> mockDtos = this.service.findAll();

		assertEquals(2, mockDtos.size());

		assertEquals("id1", mockDtos.get(0).getId());
		assertEquals(location1, mockDtos.get(0).getLocation());
		assertEquals(ChargerType.AC, mockDtos.get(0).getChargerType());
		assertEquals(4, mockDtos.get(0).getNumberOfChargingPoints());
		assertEquals(Status.AVAILABLE, mockDtos.get(0).getStatus());

		assertEquals("id2", mockDtos.get(1).getId());
		assertEquals(location2, mockDtos.get(1).getLocation());
		assertEquals(ChargerType.DC_FAST_CHARGE, mockDtos.get(1).getChargerType());
		assertEquals(2, mockDtos.get(1).getNumberOfChargingPoints());
		assertEquals(Status.IN_USE, mockDtos.get(1).getStatus());
	}

	@Test
	void createNewChargingStation(){
		Location location1 = new Location();
		location1.setId("id_location1");
		location1.setAddress("2nd Street #21");
		location1.setLatitude(23.49502);
		location1.setLongitude(45.39302);

		ChargingStationEntity entity1 = new ChargingStationEntity();
		entity1.setId("id1");
		entity1.setLocation(location1);
		entity1.setChargerType(ChargerType.AC);
		entity1.setNumberOfChargingPoints(4);
		entity1.setStatus(Status.AVAILABLE);

		ChargingStationDto dto = new ChargingStationDto();
		dto.setId("id1");
		dto.setLocation(location1);
		dto.setChargerType(ChargerType.AC);
		dto.setNumberOfChargingPoints(4);
		dto.setStatus(Status.AVAILABLE);

		when(this.repository.save(any(ChargingStationEntity.class))).thenReturn(entity1);

		ChargingStationDto mockDto = this.service.createChargingStation(dto);

		assertNotNull(mockDto);
		assertEquals("id1", mockDto.getId());
		assertEquals(location1, mockDto.getLocation());
		assertEquals(ChargerType.AC, mockDto.getChargerType());
		assertEquals(4, mockDto.getNumberOfChargingPoints());
		assertEquals(Status.AVAILABLE, mockDto.getStatus());
	}
	
	@Test
	void findChargingStationById(){
		Location location = new Location();
		location.setId("id_location2");
		location.setAddress("3rd Street #32");
		location.setLatitude(32.3234);
		location.setLongitude(21.23382);

		ChargingStationEntity entity = new ChargingStationEntity();
		entity.setId("id2");
		entity.setLocation(location);
		entity.setChargerType(ChargerType.DC_FAST_CHARGE);
		entity.setNumberOfChargingPoints(2);
		entity.setStatus(Status.IN_USE);

		Optional<ChargingStationEntity> optionalEntity = Optional.of(entity);

		when(this.repository.findById(anyString())).thenReturn(optionalEntity);

		ChargingStationDto mockDto = this.service.findChargingStationById("id2");

		assertNotNull(mockDto);
		assertEquals("id2", mockDto.getId());
		assertEquals(location, mockDto.getLocation());
		assertEquals(ChargerType.DC_FAST_CHARGE, mockDto.getChargerType());
		assertEquals(2, mockDto.getNumberOfChargingPoints());
		assertEquals(Status.IN_USE, mockDto.getStatus());
	}

	@Test
	void updateChargingStation(){
		Location location = new Location();
		location.setId("id_location2");
		location.setAddress("3rd Street #32");
		location.setLatitude(32.3234);
		location.setLongitude(21.23382);

		ChargingStationEntity entity = new ChargingStationEntity();
		entity.setId("id2");
		entity.setLocation(location);
		entity.setChargerType(ChargerType.DC_FAST_CHARGE);
		entity.setNumberOfChargingPoints(2);
		entity.setStatus(Status.IN_USE);

		ChargingStationDto dto = new ChargingStationDto();
		dto.setId("id2");
		dto.setLocation(location);
		dto.setChargerType(ChargerType.DC_FAST_CHARGE);
		dto.setNumberOfChargingPoints(2);
		dto.setStatus(Status.IN_USE);

		Optional<ChargingStationEntity> eOptionaln = Optional.of(entity);

		when(this.repository.findById("id2")).thenReturn(eOptionaln);
		when(this.repository.save(any(ChargingStationEntity.class))).thenReturn(entity);

		ChargingStationDto mockDto = this.service.updateChargingStation(dto.getId(), dto);

		assertNotNull(mockDto);
		assertEquals("id2", mockDto.getId());
		assertEquals(location, mockDto.getLocation());
		assertEquals(ChargerType.DC_FAST_CHARGE, mockDto.getChargerType());
		assertEquals(Status.IN_USE, mockDto.getStatus());
		assertEquals(2, mockDto.getNumberOfChargingPoints());
	}

	@Test
	void findChargingStationByLocation(){
		Location location = new Location();
		location.setId("location_id1");
		location.setAddress("2nd Avenew #32 Founders Street");
		location.setLatitude(23.4231);
		location.setLongitude(56.3232);

		ChargingStationEntity entity = new ChargingStationEntity();
		entity.setId("entity_id1");
		entity.setLocation(location);
		entity.setChargerType(ChargerType.DC_FAST_CHARGE);
		entity.setStatus(Status.AVAILABLE);
		entity.setNumberOfChargingPoints(8);

		when(this.repository.findByLocation(any(Location.class))).thenReturn(entity);

		ChargingStationDto mockDto = this.service.findChargingStationByLocation(location);

		assertNotNull(mockDto);
		assertEquals("entity_id1", mockDto.getId());
		assertEquals(location, mockDto.getLocation());
		assertEquals(8, mockDto.getNumberOfChargingPoints());
		assertNotEquals(ChargerType.AC, mockDto.getChargerType());
		assertNotEquals(Status.IN_USE, mockDto.getStatus());
	}

	@Test
	void checkChargingStationStatus(){
		ChargingStationEntity entity = new ChargingStationEntity();
		entity.setId("entity_id1");
		entity.setStatus(Status.AVAILABLE);

		Optional<ChargingStationEntity> optional = Optional.of(entity);

		when(this.repository.findById("entity_id1")).thenReturn(optional);

		assertEquals(Status.AVAILABLE, this.service.checkChargingStationStatus("entity_id1"));
		assertNotEquals(Status.IN_USE, this.service.checkChargingStationStatus("entity_id1"));
	}

	@Test
	void testRedisCache(){
		String cacheName = "charging_station";
		String key = "test_key";
		String value = "test_value";

		Cache cache = cacheManager.getCache(cacheName);

		cache.put(key, value);
	
		String cachedValue = cache.get(key, String.class);

		assertEquals(value, cachedValue);
	}

}
