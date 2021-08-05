package com.paypal.bfs.test.bookingserv.config;

import com.paypal.bfs.test.bookingserv.common.exception.BookingNotFoundException;
import com.paypal.bfs.test.bookingserv.model.ExceptionResponse;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.lang.Nullable;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice
public class BookingExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({Exception.class,RuntimeException.class})
    @Nullable
    public final ResponseEntity<BookingNotFoundException> handleAllException(Exception ex, WebRequest request) throws Exception {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getLocalizedMessage(),request.getDescription(false));
        return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler({BookingNotFoundException.class})
    @Nullable
    public final ResponseEntity<BookingNotFoundException> handleBookingException(Exception ex, WebRequest request) throws Exception {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage(),"Booking not find.");
        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }

//    @ExceptionHandler({HttpRequestMethodNotSupportedException.class, HttpMediaTypeNotSupportedException.class, HttpMediaTypeNotAcceptableException.class, MissingPathVariableException.class, MissingServletRequestParameterException.class, ServletRequestBindingException.class, ConversionNotSupportedException.class, TypeMismatchException.class, HttpMessageNotReadableException.class, HttpMessageNotWritableException.class, MethodArgumentNotValidException.class, MissingServletRequestPartException.class, BindException.class, NoHandlerFoundException.class, AsyncRequestTimeoutException.class})
//    @Nullable
//    public final ResponseEntity<BookingNotFoundException> handleOtherException(Exception ex, WebRequest request) throws Exception {
//        ExceptionResponse exceptionResponse = new ExceptionResponse();
//        return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
}
