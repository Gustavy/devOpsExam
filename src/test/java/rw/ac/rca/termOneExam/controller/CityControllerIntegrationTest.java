package rw.ac.rca.termOneExam.controller;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import rw.ac.rca.termOneExam.domain.City;
import rw.ac.rca.termOneExam.dto.CreateCityDTO;
import rw.ac.rca.termOneExam.utils.APICustomResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CityControllerIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getAllSuccess() throws JSONException {
        String response = this.restTemplate.getForObject("/api/cities/all", String.class);
        System.out.println(response);
        JSONAssert.assertEquals("[{\"id\":101,\"name\":\"Kigali\",\"weather\":24.0,\"fahrenheit\":0.0},{\"id\":102,\"name\":\"Musanze\",\"weather\":18.0,\"fahrenheit\":0.0},{\"id\":103,\"name\":\"Rubavu\",\"weather\":20.0,\"fahrenheit\":0.0},{\"id\":104,\"name\":\"Nyagatare\",\"weather\":28.0,\"fahrenheit\":0.0}]\n", response, true);
    }

    @Test
    public void getByIdSuccess() throws JSONException {
        ResponseEntity<City> response = this.restTemplate.getForEntity("/api/cities/id/101", City.class);
        System.out.println(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(101, response.getBody().getId());
        assertEquals("Kigali", response.getBody().getName());
    }

    @Test
    public void getByIdNotFound() {
        ResponseEntity<APICustomResponse> response = this.restTemplate.getForEntity("/api/cities/id/890", APICustomResponse.class);

        assertEquals(404, response.getStatusCodeValue());
        assertEquals("City with id 283 not found", response.getBody().getMessage());
    }

    @Test
    public void addCitySuccess() {

        CreateCityDTO city=new CreateCityDTO();
        city.setName("Kicukiro");
        city.setWeather(22);

        ResponseEntity<City> response = this.restTemplate.postForEntity("/api/cities/add", city, City.class);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals("Kicukiro",response.getBody().getName());
        assertEquals(22, response.getBody().getWeather());
    }

    public void addCityFail(){
        
    }
}
