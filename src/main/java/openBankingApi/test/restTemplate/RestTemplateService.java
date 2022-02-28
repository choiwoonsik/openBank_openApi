package openBankingApi.test.restTemplate;

import com.sun.jndi.toolkit.url.Uri;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collections;

@Service
public class RestTemplateService {

	public <T> ResponseEntity<T> postRestTemplate(
			String path, MediaType contentType, MediaType acceptType,
			MultiValueMap<String, String> params, Class<T> returnType
	) {
		RestTemplate restTemplate = new RestTemplate();
		String openUrl = "https://testapi.openbanking.or.kr/" + path;

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(contentType);
		headers.setAccept(Collections.singletonList(acceptType));

		HttpEntity formEntity = new HttpEntity<>(params, headers);

		return restTemplate.postForEntity(openUrl, formEntity, returnType);
	}

	public <T> ResponseEntity<T> getRestTemplate(
			String path, MediaType contentType, MediaType acceptType,
			MultiValueMap<String, String> params, Class<T> returnType, String token, String tokenYn
	) {
		RestTemplate restTemplate = new RestTemplate();
		String openUrl = "https://testapi.openbanking.or.kr/" + path;

		HttpHeaders headers = new HttpHeaders();
		if (tokenYn.equals("Y")) {
			headers.set("Authorization", "Bearer " + token);
		}
		headers.setContentType(contentType);
		headers.setAccept(Collections.singletonList(acceptType));
		HttpEntity entity = new HttpEntity(headers);

		String uri = UriComponentsBuilder.fromUriString(openUrl)
				.path(path)
				.queryParams(params)
				.build()
				.toUriString();

		ResponseEntity<T> exchange = restTemplate.exchange(uri, HttpMethod.GET, entity, returnType);
		System.out.println("exchange.getHeaders() = " + exchange.getHeaders());
		System.out.println("exchange.getBody() = " + exchange.getBody());
		return restTemplate.exchange(uri, HttpMethod.GET, entity, returnType);
	}
}
