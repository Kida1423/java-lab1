package kz.iitu.spring_lab_01.web;

import kz.iitu.spring_lab_01.config.AppProperties;
import kz.iitu.spring_lab_01.config.EnvironmentBanner;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/lab3")
public class Lab3Controller {

	private final AppProperties props;
	private final EnvironmentBanner banner;
	private final Environment environment;

	public Lab3Controller(AppProperties props, EnvironmentBanner banner, Environment environment) {
		this.props = props;
		this.banner = banner;
		this.environment = environment;
	}

	@GetMapping("/config")
	public Map<String, Object> config() {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("owner", props.owner());
		body.put("group", props.group());
		body.put("mailFrom", props.mail().from());
		body.put("mailRetryCount", props.mail().retryCount());
		body.put("mailTimeout", props.mail().timeout().toString());
		body.put("mailEnabled", props.mail().enabled());
		body.put("paginationDefaultSize", props.pagination().defaultSize());
		body.put("paginationMaxSize", props.pagination().maxSize());
		body.put("serverPort", environment.getProperty("server.port"));
		body.put("activeProfiles", Arrays.asList(environment.getActiveProfiles()));
		body.put("banner", banner.describe());
		return body;
	}
}
