package dw.example.application.exception;

import java.time.LocalDateTime;

import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dw.example.api.CustomResponse;
import dw.example.api.CustomResponseStatus;

//Return our response object in case of exception
public class ExceptionMapper implements javax.ws.rs.ext.ExceptionMapper<Exception>{
	private static final Logger log = LoggerFactory.getLogger(ExceptionMapper.class);

	@Override
	public Response toResponse(Exception exception) {
		log.error("Unknown exception",exception);
		return Response
				.status(Response.Status.INTERNAL_SERVER_ERROR)
				.entity(new CustomResponse(new CustomResponseStatus(9,exception.toString(),LocalDateTime.now())))
				.build();
	}
}
