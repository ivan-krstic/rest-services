package xyz.krstic.restservices.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@ConfigurationProperties(prefix = "project", ignoreUnknownFields = false)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectConfig {

	private ExternalRestService externalRestService;
	
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class ExternalRestService {
		private String jsonPlaceholder;
	}
	
}