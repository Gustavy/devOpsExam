package rw.ac.rca.termOneExam.service;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import rw.ac.rca.termOneExam.domain.City;
import rw.ac.rca.termOneExam.dto.CreateCityDTO;
import rw.ac.rca.termOneExam.repository.ICityRepository;
@RunWith(MockitoJUnitRunner.class)

public class CityServiceTest {

    @Mock
    private ICityRepository iCityRepositoryMock;

    @InjectMocks
    private CityService cityService;

    @Test
    public void getAllSuccess(){
        when(iCityRepositoryMock.findAll()).thenReturn(Arrays.asList(new City("Karongi",18), new City("Musanze",16)));
        assertEquals("Musanze", cityService.getAll().get(1).getName());
    }

    @Test
    public void getByIdSuccess(){
        when(iCityRepositoryMock.findById(20L)).thenReturn(Optional.of(new City("Burera",17)));
        assertEquals(true, cityService.getById(20).isPresent());
    }

    @Test
    public void getByIdFail(){
        when(iCityRepositoryMock.findById(1L)).thenReturn(null);
        assertNull(cityService.getById(1));
    }

    @Test
    public void addCitySuccess(){
        CreateCityDTO dto = new CreateCityDTO();
        dto.setName("Bujumbura");
        dto.setWeather(22);
        City city = new City(dto.getName(),dto.getWeather());
        when(cityService.save(dto)).thenReturn(city);
        assertEquals("Bujumbura", city.getName());
    }

    @Test
    public void alreadyExistsTrue(){
        when(iCityRepositoryMock.existsByName("Rubavu")).thenReturn(true);
        assertEquals(true, cityService.existsByName("Rubavu"));
    }

    @Test
    public void existsByNameFalse(){
        when(iCityRepositoryMock.existsByName("Tokyo")).thenReturn(false);
        assertFalse(cityService.existsByName("Tokyo"));
    }


}
