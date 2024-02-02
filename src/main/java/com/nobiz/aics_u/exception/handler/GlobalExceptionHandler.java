package com.nobiz.aics_u.exception.handler;

import com.nobiz.aics_u.exception.ApiException;
import com.nobiz.aics_u.exception.CustomRuntimeException;
import com.nobiz.aics_u.exception.NoRowsAffectedException;
import com.nobiz.aics_u.exception.UniqueNamingExistsException;
import com.nobiz.aics_u.model.dto.ErrorResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.postgresql.util.PSQLException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.FileNotFoundException;
import java.util.Date;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {


	/**
	 * Valid 어노테이션에서 걸려지는 exception 처리
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> argumentNotValidException(MethodArgumentNotValidException ex) {
		BindingResult bindingResult = ex.getBindingResult();

		StringBuilder builder = new StringBuilder();
		for (FieldError fieldError : bindingResult.getFieldErrors()) {
//			builder.append("[");
//			builder.append(fieldError.getField());
//			builder.append("](은)는 ");
			builder.append(fieldError.getDefaultMessage());
			builder.append(" 입력된 값: [");
			builder.append(fieldError.getRejectedValue());
			builder.append("]\n");
		}

		ErrorResponseDto errorResponseDTO = new ErrorResponseDto(new Date(), HttpStatus.BAD_REQUEST.value(), builder.toString());
		return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
	}


	/**
	 * 데이터 등록, 수정, 삭제 시 영향 받은 row 수가 없을 경우
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(NoRowsAffectedException.class)
	public ResponseEntity<?> noRowsAffectedException(NoRowsAffectedException ex) {
		ErrorResponseDto errorResponseDTO = new ErrorResponseDto(new Date(), HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
		return new ResponseEntity<>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * 유니크 명칭에 중복해서 들어갈 경우
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(UniqueNamingExistsException.class)
	public ResponseEntity<?> UniqueNamingExistsException(UniqueNamingExistsException ex) {
		ErrorResponseDto errorResponseDTO = new ErrorResponseDto(new Date(), HttpStatus.BAD_REQUEST.value(), ex.getMessage());
		return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(PSQLException.class)
	public ResponseEntity<?> dbPSQLException(PSQLException ex) {
		log.error(ex.getMessage());
		ErrorResponseDto errorResponseDTO = new ErrorResponseDto(new Date(),
				HttpStatus.INTERNAL_SERVER_ERROR.value(),
				"알 수 없는 서버 내부의 오류입니다. 이 문제가 지속되면 관리자에게 문의주세요.");
		return new ResponseEntity<>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<?> authException(Exception ex) {
		ErrorResponseDto errorResponseDTO = new ErrorResponseDto(new Date(), HttpStatus.FORBIDDEN.value(), ex.getMessage());
		return new ResponseEntity<>(errorResponseDTO, HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(FileNotFoundException.class)
	public ResponseEntity<?> fileNotFoundException(FileNotFoundException ex) {
		ErrorResponseDto errorResponseDTO = new ErrorResponseDto(new Date(), HttpStatus.NOT_FOUND.value(), ex.getMessage());
		return new ResponseEntity<>(errorResponseDTO, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(TypeMismatchException.class)
	public ResponseEntity<?> typeMismatchException(TypeMismatchException ex) {
		ErrorResponseDto errorResponseDTO = new ErrorResponseDto(new Date(), HttpStatus.BAD_REQUEST.value(), ex.getMessage());
		return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<?> usernameNotFoundException(UsernameNotFoundException ex) {
		ErrorResponseDto errorResponseDTO = new ErrorResponseDto(new Date(), HttpStatus.NOT_FOUND.value(), ex.getMessage());
		return new ResponseEntity<>(errorResponseDTO, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(CustomRuntimeException.class)
	public ResponseEntity<?> runtimeException(RuntimeException ex) {
		ErrorResponseDto errorResponseDTO = new ErrorResponseDto(new Date(), HttpStatus.BAD_REQUEST.value(), ex.getMessage());
		return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(ApiException.class)
	public ResponseEntity<?> apiException(ApiException ex) {
		HttpStatusCode statusCode = ex.getStatus();
		String message = ex.getBody();
		return ResponseEntity.status(statusCode).body(message);
	}
}
