package xyz.krstic.restservices.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import xyz.krstic.restservices.exception.CustomInternalServerError;
import xyz.krstic.restservices.exception.CustomNoContentException;
import xyz.krstic.restservices.service.PostService;
import xyz.krstic.restservices.vo.ExceptionResponse;
import xyz.krstic.restservices.vo.Post;

@RestController
public class PostController {

	private static final Logger log = LoggerFactory.getLogger(PostController.class);
	
	private PostService postService;
	
	@Autowired
	public PostController(PostService postService) {
		this.postService = postService;
	}
	
	@SuppressWarnings("rawtypes")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = Post.class, responseContainer = "List"),
			@ApiResponse(code = 204, message = "No Content", response = ExceptionResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ExceptionResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ExceptionResponse.class)
	})
	@RequestMapping(path = "/posts", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity getAllPosts(WebRequest request) throws CustomNoContentException, CustomInternalServerError {
		List<Post> posts = postService.getAll();
		
		return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
	}
}
