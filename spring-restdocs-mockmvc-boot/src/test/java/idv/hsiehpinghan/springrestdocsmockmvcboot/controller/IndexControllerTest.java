package idv.hsiehpinghan.springrestdocsmockmvcboot.controller;

import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.linkWithRel;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.subsectionWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

//@SpringBootTest
//@RunWith(SpringRunner.class)
public class IndexControllerTest {
//	private MockMvc mockMvc;
//	@Rule
//	public final JUnitRestDocumentation jUnitRestDocumentation = new JUnitRestDocumentation(
//			"target/generated-snippets");
//	@Autowired
//	private WebApplicationContext webApplicationContext;
//
//	@Before
//	public void before() {
//		// @formatter:off
//		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
//				.apply(documentationConfiguration(jUnitRestDocumentation))
//				.alwaysDo(
//						document("{ClassName}/{method-name}", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())))
//				.build();
//		// @formatter:on
//	}
//
//	@Test
//	public void index() throws Exception {
//		// @formatter:off
//		this.mockMvc
//		.perform(get("/api/1.0"))
//		.andExpect(status().isOk())
//		.andDo(
//			document(
//				"index",
//				links(linkWithRel("/api/1.0/cruds").description("the crud resource")),
//				responseFields(subsectionWithPath("_links").description("Links to other resources")),
//				responseHeaders(headerWithName("Content-Type").description("The Content-Type of the payload"))
//			)
//		); 
//		// @formatter:on
//	}

}
