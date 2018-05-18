package xyz.krstic.restservices.service.impl;

import java.net.URI;
import java.util.List;

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
import xyz.krstic.restservices.exception.CustomInternalServerError;
import xyz.krstic.restservices.exception.CustomNoContentException;
import xyz.krstic.restservices.service.PostService;
import xyz.krstic.restservices.vo.Post;

@Service
public class PostServiceImpl implements PostService {
	
	private static final Logger log = LoggerFactory.getLogger(PostServiceImpl.class);
	private static final Integer TIMEOUT = 15;
	
	private ProjectConfig config;
	private final RestTemplate restTemplate;
	
	@Autowired
	public PostServiceImpl(ProjectConfig config, RestTemplateBuilder restTemplateBuilder) {
		this.config = config;
		
		restTemplateBuilder.setConnectTimeout(1000 * TIMEOUT);
		restTemplateBuilder.setReadTimeout(1000 * TIMEOUT);
		this.restTemplate = restTemplateBuilder.build();
	}

	@Override
	public List<Post> getAll() throws CustomNoContentException, CustomInternalServerError {
		try {
			URI url = UriComponentsBuilder.fromUriString(config.getExternalRestService().getJsonPlaceholder().concat("/posts"))
					.build()
					.encode()
					.toUri();
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			
			HttpEntity<?> entity = new HttpEntity<>(null, headers);
			
			ResponseEntity<List<Post>> result = restTemplate.exchange(url, HttpMethod.GET, entity, new ParameterizedTypeReference<List<Post>>() {});
			
			if (result.getBody() == null || result.getBody().isEmpty()) {
				throw new CustomNoContentException("Posts not founded.");
			} else {
				return result.getBody();
			}
		} catch (HttpStatusCodeException e) {
			throw new CustomInternalServerError("JsonPlaceholder rest exception: " + e.getMessage());
		}
	}

}
