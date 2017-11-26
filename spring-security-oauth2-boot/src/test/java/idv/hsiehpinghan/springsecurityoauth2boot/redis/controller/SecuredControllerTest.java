package idv.hsiehpinghan.springsecurityoauth2boot.redis.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import idv.hsiehpinghan.springsecurityoauth2boot.redis.model.OauthTokenModel;

@RunWith(SpringRunner.class)
@ActiveProfiles({ "redis_token_store_authorization_server", "redis_token_store_resource_server" })
// @SpringBootTest
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SecuredControllerTest {
	@LocalServerPort
	private int port;
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private RemoteTokenServices remoteTokenServices;

	@Test
	public void userMethod() {
		String username = "user";
		String password = "user";
		String method = "userMethod";
		OauthTokenModel oauthTokenModel = getOauthTokenModel(username, password);
		String access_token = oauthTokenModel.getAccess_token();
		Assert.assertNotNull(access_token);
		ResponseEntity<String> responseEntity = getResponseEntity(access_token, method);
		Assert.assertEquals("user method", responseEntity.getBody());
	}

	@Test
	public void adminMethod() {
		String username = "admin";
		String password = "admin";
		String method = "adminMethod";
		OauthTokenModel oauthTokenModel = getOauthTokenModel(username, password);
		String access_token = oauthTokenModel.getAccess_token();
		Assert.assertNotNull(access_token);
		ResponseEntity<String> responseEntity = getResponseEntity(access_token, method);
		Assert.assertEquals("admin method", responseEntity.getBody());
	}

	@Test
	public void otherMethod() {
		String username = "other";
		String password = "other";
		String method = "otherMethod";
		OauthTokenModel oauthTokenModel = getOauthTokenModel(username, password);
		String access_token = oauthTokenModel.getAccess_token();
		Assert.assertNotNull(access_token);
		ResponseEntity<String> responseEntity = getResponseEntity(access_token, method);
		Assert.assertEquals("other otherMethod", responseEntity.getBody());
	}

	@Test(expected = HttpClientErrorException.class)
	public void userUsingAdminMethod() {
		String username = "user";
		String password = "user";
		String method = "adminMethod";
		OauthTokenModel oauthTokenModel = getOauthTokenModel(username, password);
		String access_token = oauthTokenModel.getAccess_token();
		Assert.assertNotNull(access_token);
		getResponseEntity(access_token, method);
	}

	private ResponseEntity<String> getResponseEntity(String access_token, String method) {
		String url = String.format("http://localhost:%d/api/%s", port, method);
		MultiValueMap<String, String> headers = new HttpHeaders();
		headers.add("content-type", "application/x-www-form-urlencoded");
		headers.add("Authorization", String.format("Bearer %s", access_token));
		HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
		modifySetting();
		ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
		return responseEntity;
	}

	private void modifySetting() {
		String checkTokenEndpointUrl = String.format("http://localhost:%d/oauth/check_token/", port);
		remoteTokenServices.setCheckTokenEndpointUrl(checkTokenEndpointUrl);
	}

	private OauthTokenModel getOauthTokenModel(String username, String password) {
		String url = String.format("http://localhost:%d/oauth/token", port);
		MultiValueMap<String, String> headers = new HttpHeaders();
		headers.add("content-type", "application/x-www-form-urlencoded");
		HttpEntity<String> requestEntity = new HttpEntity<String>(
				String.format("grant_type=password&username=%s&password=%s&client_id=trusted-app&client_secret=secret",
						username, password),
				headers);
		ResponseEntity<OauthTokenModel> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity,
				OauthTokenModel.class);
		OauthTokenModel oauthTokenModel = responseEntity.getBody();
		return oauthTokenModel;
	}

}
