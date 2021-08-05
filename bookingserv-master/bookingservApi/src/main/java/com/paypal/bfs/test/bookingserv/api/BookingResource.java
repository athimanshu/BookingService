package com.paypal.bfs.test.bookingserv.api;

import com.paypal.bfs.test.bookingserv.api.model.Booking;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/v1/bfs/booking")
public interface BookingResource {
    /**
     * Create {@link Booking} resource
     *
     * @param booking the booking object
     * @return the created booking
     */
    @PutMapping("")
    ResponseEntity<Booking> create(@RequestBody Booking booking);

    @GetMapping("{id}")
    ResponseEntity<List<Booking>> retrieve(@PathVariable("id") String id);


}
