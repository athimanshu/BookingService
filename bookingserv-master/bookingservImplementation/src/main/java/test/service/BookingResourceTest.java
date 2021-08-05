package test.service;

import com.paypal.bfs.test.bookingserv.BookingServApplication;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.HttpClients;
import org.aspectj.lang.annotation.Before;
import com.paypal.bfs.test.bookingserv.api.model.Booking;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BookingServApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookingResourceTest {
    @LocalServerPort
    private int port;
    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();
    Booking booking;
    @Before("")
    public void init(){
        Address address = new Address();
        address.setLine1("221 B");
        address.setLine2("Baker Street");
        address.setCity("London");
        address.setCountry("United Kingdom");
        address.setZipcode("273001");
        booking = new Booking();
        booking.setFirstName("Sherlock");
        booking.setLastName("Holmes");
        booking.setDateOfBirth("06-01-1854");
        booking.setAddress(address);
    }
    @Test
    public void testCreateBooking(){
        HttpResponse httpResponse = null;

        try {
       HttpClient httpClient = HttpClients.createDefault();
        HttpPut httpPut = new HttpPut("v1/bfs/booking");
        org.apache.http.HttpEntity<Booking> entity = new org.apache.http.HttpEntity<Booking>(booking, headers);
        httpPut.setEntity(entity);
        httpPut.setHeader("Accept","application/json");
        httpPut.setHeader("Content-type","application/json");

            httpResponse = httpClient.execute(httpPut);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

        assertEquals(HttpStatus.CREATED,httpResponse.getStatusLine().getStatusCode());



    }

//    @Test
//    public void testGetAllBooking(){
//        HttpEntity<Booking> entity = new HttpEntity<>(booking, headers);
//
//        //make a post call to insert data into db
//        ResponseEntity<Booking> createResponse = restTemaplate.exchange(
//                createURLWithPort("v1/bfs/booking"),
//                HttpMethod.POST, entity, Booking.class);
//        assertNotNull(createResponse.getBody());
//
//        //make a get call
//        ResponseEntity<Booking> getResponse = restTemplate.exchange(
//                createURLWithPort("v1/bfs/booking/"+createResponse.getBody().getId()),
//                HttpMethod.GET, entity, Booking.class);
//
//        assertNotNull(getResponse.getBody());
//        assertEquals(getResponse.getBody().getId(), createResponse.getBody().getId());
//        assertEquals(getResponse.getBody().getFirstName(), createResponse.getBody().getFirstName());
//    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
