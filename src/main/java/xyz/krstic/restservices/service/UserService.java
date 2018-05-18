package xyz.krstic.restservices.service;

import java.util.List;

import xyz.krstic.restservices.exception.CustomBadRequestException;
import xyz.krstic.restservices.exception.CustomInternalServerError;
import xyz.krstic.restservices.exception.CustomNoContentException;
import xyz.krstic.restservices.vo.User;

public interface UserService {

	List<User> getAll() throws CustomNoContentException, CustomInternalServerError;
	
	List<User> getByIds(List<Integer> ids) throws CustomBadRequestException, CustomNoContentException, CustomInternalServerError;
	User getById(Integer id) throws CustomBadRequestException, CustomNoContentException, CustomInternalServerError;
	
}