package xyz.krstic.restservices.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import xyz.krstic.restservices.exception.CustomBadRequestException;
import xyz.krstic.restservices.exception.CustomInternalServerError;
import xyz.krstic.restservices.exception.CustomNoContentException;
import xyz.krstic.restservices.service.UserService;
import xyz.krstic.restservices.vo.ExceptionResponse;
import xyz.krstic.restservices.vo.User;

@RestController
public class UserController {

	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	private UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@SuppressWarnings("rawtypes")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = User.class, responseContainer = "List"),
			@ApiResponse(code = 204, message = "No Content", response = ExceptionResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ExceptionResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ExceptionResponse.class)
	})
	@RequestMapping(path = "/users", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity getAllUsers(WebRequest request) throws CustomNoContentException, CustomInternalServerError {
		List<User> users = userService.getAll();
		
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
	@SuppressWarnings("rawtypes")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = User.class, responseContainer = "List"),
			@ApiResponse(code = 204, message = "No Content", response = ExceptionResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ExceptionResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ExceptionResponse.class)
	})
	@RequestMapping(path = "/user", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity getUserById(@RequestParam(required = true) List<Integer> ids, WebRequest request) throws CustomNoContentException, CustomBadRequestException, CustomInternalServerError {
		List<User> users = userService.getByIds(ids);
		
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
	@SuppressWarnings("rawtypes")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = User.class, responseContainer = "List"),
			@ApiResponse(code = 204, message = "No Content", response = ExceptionResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ExceptionResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ExceptionResponse.class)
	})
	@RequestMapping(path = "/user/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity getUserById(@PathVariable(required = true) Integer id, WebRequest request) throws CustomNoContentException, CustomBadRequestException, CustomInternalServerError {
		User user = userService.getById(id);
		
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
}