package dw.example.application.exception;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dw.example.api.CustomResponse;
import dw.example.api.CustomResponseStatus;

//Return our response object in case of exception
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException>{
	private static final Logger log = LoggerFactory.getLogger(ConstraintViolationExceptionMapper.class);

	@Override
	public Response toResponse(ConstraintViolationException exception) {
		log.error("Validation error", exception);
		//Concat the list of validation errors
		
		String errors = Optional.ofNullable(exception.getConstraintViolations()).orElseGet(Collections::emptySet)
				.stream()
				.map(ConstraintViolation::getMessage)
				.collect(Collectors.joining("; "));
		
		return Response
				.status(Response.Status.BAD_REQUEST)
				.entity(new CustomResponse(new CustomResponseStatus(2,"Validation error:"+errors,LocalDateTime.now())))
				.build();
	}


	

}
