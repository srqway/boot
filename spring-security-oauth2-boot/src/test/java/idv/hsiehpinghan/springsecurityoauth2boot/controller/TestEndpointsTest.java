package idv.hsiehpinghan.springsecurityoauth2boot.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import idv.hsiehpinghan.springsecurityoauth2boot.SpringSecurityOauth2BootApplication;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = SpringSecurityOauth2BootApplication.class)
public class TestEndpointsTest {
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void getOrder() {
//		mockMvc.perform(get("/basic/save").param("string", STRING)).andExpect(status().is3xxRedirection())
//		.andExpect(header().string("Location", expected));
		
	    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
	    params.add("grant_type", "password");
	    params.add("client_id", "fooClientIdPassword");
	    params.add("username", "user");
	    params.add("password", "user");
	    
//		ResultActions result 
//	      = mockMvc.perform(post("/oauth/token")
//	        .params(params)
//	        .with(httpBasic("fooClientIdPassword","secret"))
//	        .accept("application/json;charset=UTF-8"))
//	        .andExpect(status().isOk())
//	        .andExpect(content().contentType("application/json;charset=UTF-8"));
	 
//	    String resultString = result.andReturn().getResponse().getContentAsString();
	    
	}
	
//	private String obtainAccessToken(String username, String password) throws Exception {
//		  
//	    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//	    params.add("grant_type", "password");
//	    params.add("client_id", "fooClientIdPassword");
//	    params.add("username", username);
//	    params.add("password", password);
//	 
//	    ResultActions result 
//	      = mockMvc.perform(post("/oauth/token")
//	        .params(params)
//	        .with(httpBasic("fooClientIdPassword","secret"))
//	        .accept("application/json;charset=UTF-8"))
//	        .andExpect(status().isOk())
//	        .andExpect(content().contentType("application/json;charset=UTF-8"));
//	 
//	    String resultString = result.andReturn().getResponse().getContentAsString();
//	 
//	    JacksonJsonParser jsonParser = new JacksonJsonParser();
//	    return jsonParser.parseMap(resultString).get("access_token").toString();
//	}
}
