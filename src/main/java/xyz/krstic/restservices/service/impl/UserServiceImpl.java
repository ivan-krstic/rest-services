package xyz.krstic.restservices.service.impl;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import xyz.krstic.restservices.configuration.ProjectConfig;
import xyz.krstic.restservices.exception.CustomBadRequestException;
import xyz.krstic.restservices.exception.CustomInternalServerError;
import xyz.krstic.restservices.exception.CustomNoContentException;
import xyz.krstic.restservices.service.UserService;
import xyz.krstic.restservices.vo.User;

@Service
public class UserServiceImpl implements UserService {
	
	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	private static final Integer TIMEOUT = 15;
	
	private ProjectConfig config;
	private final RestTemplate restTemplate;
	
	@Autowired
	public UserServiceImpl(ProjectConfig config, RestTemplateBuilder restTemplateBuilder) {
		this.config = config;
		
		restTemplateBuilder.setConnectTimeout(1000 * TIMEOUT);
		restTemplateBuilder.setReadTimeout(1000 * TIMEOUT);
		this.restTemplate = restTemplateBuilder.build();
	}

	@Override
	public List<User> getAll() throws CustomNoContentException, CustomInternalServerError {
		try {
			URI url = UriComponentsBuilder.fromUriString(config.getExternalRestService().getJsonPlaceholder().concat("/users"))
					.build()
					.encode()
					.toUri();
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			
			HttpEntity<?> entity = new HttpEntity<>(null, headers);
			
			ResponseEntity<List<User>> result = restTemplate.exchange(url, HttpMethod.GET, entity, new ParameterizedTypeReference<List<User>>() {});
			
			if (result.getBody() == null || result.getBody().isEmpty()) {
				throw new CustomNoContentException("Users not founded.");
			} else {
				return result.getBody();
			}
		} catch (HttpStatusCodeException e) {
			throw new CustomInternalServerError("JsonPlaceholder rest exception: " + e.getMessage());
		}
	}

	@Override
	public List<User> getByIds(List<Integer> ids) throws CustomBadRequestException, CustomNoContentException, CustomInternalServerError {
		if (ids == null || ids.isEmpty()) {
			throw new CustomBadRequestException("Parameter 'id' must exist");
		} else {
			try {
				UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(config.getExternalRestService().getJsonPlaceholder().concat("/users"));
				for (Integer id : ids) {
					uriComponentsBuilder.queryParam("id", id);
				}
				URI url = uriComponentsBuilder
						.build()
						.encode()
						.toUri();
				
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON);
				
				HttpEntity<?> entity = new HttpEntity<>(null, headers);
				
				ResponseEntity<List<User>> result = restTemplate.exchange(url, HttpMethod.GET, entity, new ParameterizedTypeReference<List<User>>() {});
				
				if (result.getBody() == null || result.getBody().isEmpty()) {
					throw new CustomNoContentException("Users not founded.");
				} else {
					return result.getBody();
				}
			} catch (HttpStatusCodeException e) {
				throw new CustomInternalServerError("JsonPlaceholder rest exception: " + e.getMessage());
			}
		}
	}
	
	@Override
	public User getById(Integer id) throws CustomBadRequestException, CustomNoContentException, CustomInternalServerError {
		if (id == null || id == 0) {
			throw new CustomBadRequestException("Parameter 'id' must exist");
		} else {
			try {
				URI url = UriComponentsBuilder.fromUriString(config.getExternalRestService().getJsonPlaceholder().concat("/users"))
						.path("/")
						.path(id.toString())
						.buildAndExpand(id)
						.encode()
						.toUri();
				
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON);
				
				HttpEntity<?> entity = new HttpEntity<>(null, headers);
				
				log.info("url: " + url.toString());
				ResponseEntity<User> result = restTemplate.exchange(url, HttpMethod.GET, entity, User.class);
				
				if (result.getBody() == null) {
					throw new CustomNoContentException("User not founded.");
				} else {
					return result.getBody();
				}
			} catch (HttpStatusCodeException e) {
				throw new CustomInternalServerError("JsonPlaceholder rest exception: " + e.getMessage());
			}
		}
	}

}