package com.paypal.bfs.test.bookingserv.convertor;

import com.paypal.bfs.test.bookingserv.model.AddressEntity;
import com.paypal.bfs.test.bookingserv.model.BookingEntity;
import com.paypal.bfs.test.bookingserv.api.model.Address;
import com.paypal.bfs.test.bookingserv.api.model.Booking;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public final class EntityToDaoConvertor {

    private EntityToDaoConvertor() {
    }

    public static BookingEntity convertEntityToBookingEntity(Booking booking){
        BookingEntity bookingEntity = new BookingEntity();
        bookingEntity.setFirstName(booking.getFirstName());
        bookingEntity.setLastName(booking.getLastName());
        try {
            System.out.println(booking.getDateOfBirth());
            Date dob = new SimpleDateFormat("dd-MM-yyyy").parse(booking.getDateOfBirth());
            bookingEntity.setDateOfBirth(dob);
        }catch (ParseException e){
           throw new RuntimeException(e.getMessage());
        }
        bookingEntity.setAddressEntity(convertEntityToAddressEntity(booking.getAddress()));
        return bookingEntity;
    }
    public static AddressEntity convertEntityToAddressEntity(Address address){
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setCity(address.getCity());
        addressEntity.setCountry(address.getCountry());
        addressEntity.setState(address.getState());
        addressEntity.setZipcode(address.getZipcode());
        addressEntity.setLine1(address.getLine1());
        addressEntity.setLine2(address.getLine2());
        return addressEntity;
    }
}
