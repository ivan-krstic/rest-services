package xyz.krstic.restservices.service;

import java.util.List;

import xyz.krstic.restservices.exception.CustomInternalServerError;
import xyz.krstic.restservices.exception.CustomNoContentException;
import xyz.krstic.restservices.vo.Post;

public interface PostService {

	List<Post> getAll() throws CustomNoContentException, CustomInternalServerError;
	
}
