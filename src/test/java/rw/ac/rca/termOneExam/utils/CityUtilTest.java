package rw.ac.rca.termOneExam.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import rw.ac.rca.termOneExam.domain.City;
import rw.ac.rca.termOneExam.repository.ICityRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@DataJpaTest

@RunWith(SpringRunner.class)
public class CityUtilTest {

    @Autowired
    private ICityRepository iCityRepository;

    @Test
    public void noWeatherMoreThan40() {
        boolean result = false;
        List<City> cities = iCityRepository.findAll();
        for (City city : cities)
            if (city.getWeather() > 40)
                result = true;

        assertEquals(false, result);
    }

    @Test
    public void noWeatherLessThan10() {
        boolean result = false;
        List<City> cities = iCityRepository.findAll();
        for (City city : cities)
            if (city.getWeather() < 10)
                result = true;

        assertEquals(false, result);
    }

    @Test
    public void citiesContainMusanzeAndKigali() {
        assertEquals(true, iCityRepository.existsByName("Musanze") && iCityRepository.existsByName("Kigali"));
    }

    @Test
    public void testMocking() {
        List<City> mockedList = Mockito.mock(ArrayList.class);
        City city = new City("Kigali", 24);
        mockedList.add(city);
        Mockito.verify(mockedList).add(city);

        assertEquals(0, mockedList.size());
    }

    @Test
    public void testSpying() {
        List<City> spyList = Mockito.spy(ArrayList.class);
        City city = new City("Kigali", 24);
        spyList.add(city);
        Mockito.verify(spyList).add(city);

        assertEquals(1, spyList.size());
    }
}
