package idv.hsiehpinghan.springsecurityoauth2boot.redis.controller;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
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

import idv.hsiehpinghan.springsecurityoauth2boot.model.OauthTokenModel;
import idv.hsiehpinghan.springsecurityoauth2boot.redis.entity.RoleEntity;
import idv.hsiehpinghan.springsecurityoauth2boot.redis.entity.UserEntity;
import idv.hsiehpinghan.springsecurityoauth2boot.redis.service.UserService;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles({ "redis_token_store_authorization_server", "redis_token_store_resource_server" })
public class SecuredControllerTest {
	@LocalServerPort
	private int port;
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private RemoteTokenServices remoteTokenServices;
	@Autowired
	private UserService userService;

	@Test
	public void test00_userMethod() {
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
	public void test01_adminMethod() {
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
	public void test02_otherMethod() {
		String username = "other";
		String password = "other";
		String method = "otherMethod";
		OauthTokenModel oauthTokenModel = getOauthTokenModel(username, password);
		String access_token = oauthTokenModel.getAccess_token();
		Assert.assertNotNull(access_token);
		ResponseEntity<String> responseEntity = getResponseEntity(access_token, method);
		Assert.assertEquals("other otherMethod", responseEntity.getBody());
	}

	@Test
	public void test03_notBannedMethod_notBanned() {
		String username = "bannable_user";
		String password = "bannable_user";
		String method = "notBannedMethod";
		OauthTokenModel oauthTokenModel = getOauthTokenModel(username, password);
		String access_token = oauthTokenModel.getAccess_token();
		Assert.assertNotNull(access_token);
		ResponseEntity<String> responseEntity = getResponseEntity(access_token, method);
		Assert.assertEquals("bannable_user notBannedMethod", responseEntity.getBody());
	}

	@Test(expected = HttpClientErrorException.class)
	public void test04_notBannedMethod_banned() {
		String username = "bannable_user";
		String password = "bannable_user";
		String method = "notBannedMethod";
		addBannedRole(username);
		OauthTokenModel oauthTokenModel = getOauthTokenModel(username, password);
		String access_token = oauthTokenModel.getAccess_token();
		Assert.assertNotNull(access_token);
		getResponseEntity(access_token, method);
	}

	@Test(expected = HttpClientErrorException.class)
	public void test05_userUsingAdminMethod() {
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

	private void addBannedRole(String username) {
		UserEntity userEntity = userService.findOne(username);
		userEntity.getRoles().add(new RoleEntity("ROLE_BANNED", "banned role name", null));
		userService.save(userEntity);
	}
}
