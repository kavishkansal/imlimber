package Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.UNAUTHORIZED)
public class UnAuthorizedException extends RuntimeException{
	public UnAuthorizedException(String message, Throwable cause){
		super(message,cause);
	}
}