package idv.hsiehpinghan.springrestdocsmockmvcboot.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import idv.hsiehpinghan.springrestdocsmockmvcboot.constant.Constant;
import idv.hsiehpinghan.springrestdocsmockmvcboot.criteria.CrudCreateCriteria;


@SpringBootTest
@RunWith(SpringRunner.class)
public class CrudControllerTest {
	private static final Integer ID = 0;
	private static final String STRING = "string_0";
	
	private MockMvc mockMvc;
	@Rule
	public final JUnitRestDocumentation jUnitRestDocumentation = new JUnitRestDocumentation(
			"target/generated-snippets");
	@Autowired
	private WebApplicationContext webApplicationContext;
	@Autowired
	private ObjectMapper objectMapper;

//	@Before
//	public void before() {
//
//		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
//				.apply(documentationConfiguration(jUnitRestDocumentation))
//				.alwaysDo(
//						document("{method-name}", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())))
//				.build();
//		
////		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
////				.apply(documentationConfiguration(this.restDocumentation).uris()
////						.withScheme("https")
////						.withHost("example.com")
////						.withPort(443))
////				.build();
//		
////		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
////				.apply(documentationConfiguration(this.restDocumentation)
////						.snippets().withTemplateFormat(TemplateFormats.markdown()))
////				.build();
//		
////		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
////				.apply(documentationConfiguration(this.restDocumentation).snippets()
////						.withDefaults(curlRequest()))
////				.build();
//	}

	@Before
	public void before() {
		// @formatter:off
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.apply(documentationConfiguration(jUnitRestDocumentation))
//				.alwaysDo(
//						document("{ClassName}/{method-name}", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())))
				.build();
		// @formatter:on
	}

	@Test
	public void create() throws Exception {
		CrudCreateCriteria criteria = new CrudCreateCriteria(ID, STRING);
		// @formatter:off
		mockMvc
			.perform(
				post(Constant.CRUDS_PATH)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(criteria))
			)
			.andExpect(status().isCreated())
			.andDo(
				document(
					"{ClassName}/{method-name}", 
					requestFields(
						fieldWithPath("id").description("The id of the input"),
						fieldWithPath("string").description("The string of the input")
		  	        )
				)
			);
		// @formatter:on
	}

	@Test
	public void readById() throws Exception {
		// @formatter:off
		mockMvc
			.perform(
				get(Constant.CRUDS_PATH + "/{id}", ID)
			)
			.andExpect(status().isOk())
			.andDo(
				document(
					"{ClassName}/{method-name}", 
					pathParameters(
						parameterWithName("id").description("The id of the input")
		  	        )
				)
			);
		// @formatter:on
	}

//	@Test
//	public void crudDeleteExample() throws Exception {
//	    this.mockMvc.perform(delete("/crud/{id}", 10)).andExpect(status().isOk())
//	      .andDo(document("crud-delete-example", 
//	      pathParameters(
//	        parameterWithName("id").description("The id of the input to delete")
//	      )));
//	}
	
//	@Test
//	public void create() throws Exception {
//		Integer id = 0;
//		String string = "string_0";
//		CrudCreateCriteria criteria = new CrudCreateCriteria(id, string);
//		this.mockMvc
//		.perform(post("/crud/create").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(criteria)))
////				.contentType(MediaTypes.HAL_JSON_VALUE).content(this.objectMapper.writeValueAsString(criteria)))
//		.andExpect(status().isOk())
//		.andDo(document("{ClassName}/{methodName}"));
//		
////		this.mockMvc
////				.perform(post("/crud/create").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(criteria)))
//////						.contentType(MediaTypes.HAL_JSON_VALUE).content(this.objectMapper.writeValueAsString(criteria)))
////				.andExpect(status().isOk())
////				.andDo(document("{ClassName}/{methodName}"));
//		
////		.andDo(document("index", responseFields(
////				fieldWithPath("contact.email")
////						.type(JsonFieldType.STRING) 
////						.description("The user's email address"))));
//		
////		.andDo(document("{ClassName}/{methodName}", links(halLinks(),
////				linkWithRel("_self").ignored().optional().description("Link to the alpha resource"), 
////				linkWithRel("curies").ignored().optional().description("Link to the bravo resource"))));
//		
////				.andDo(document("{method-name}", preprocessRequest(prettyPrint()),
////						preprocessResponse(prettyPrint()),
////						requestFields(fieldWithPath("id").description("The id of the input"),
////								fieldWithPath("string").description("The string of the input"))));
//		
////		this.mockMvc.perform(get("/user/5").accept(MediaType.APPLICATION_JSON))
////		.andExpect(status().isOk())
////		.andDo(document("index", responseFields( 
////				fieldWithPath("contact").description("The user's contact details"), 
////				fieldWithPath("contact.email").description("The user's email address")))); 
//		
////		FieldDescriptor[] book = new FieldDescriptor[] {
////				fieldWithPath("title").description("Title of the book"),
////				fieldWithPath("author").description("Author of the book") };
//		
////		this.mockMvc.perform(get("/books/1").accept(MediaType.APPLICATION_JSON))
////		.andExpect(status().isOk())
////		.andDo(document("book", responseFields(book))); 
//		
////		this.mockMvc.perform(get("/books").accept(MediaType.APPLICATION_JSON))
////		.andExpect(status().isOk())
////		.andDo(document("book", responseFields(
////				fieldWithPath("[]").description("An array of books")) 
////				.andWithPrefix("[].", book))); 
//		
////		this.mockMvc.perform(get("/users?page=2&per_page=100")) 
////		.andExpect(status().isOk())
////		.andDo(document("users", requestParameters( 
////				parameterWithName("page").description("The page to retrieve"), 
////				parameterWithName("per_page").description("Entries per page") 
////		)));
//		
////		this.mockMvc.perform(get("/locations/{latitude}/{longitude}", 51.5072, 0.1275)) 
////		.andExpect(status().isOk())
////		.andDo(document("locations", pathParameters( 
////				parameterWithName("latitude").description("The location's latitude"), 
////				parameterWithName("longitude").description("The location's longitude") 
////		)));
//		
////		this.mockMvc.perform(fileUpload("/upload").file("file", "example".getBytes())) 
////		.andExpect(status().isOk())
////		.andDo(document("upload", requestParts( 
////				partWithName("file").description("The file to upload")) 
////	));
//		
////		this.mockMvc
////		.perform(get("/people").header("Authorization", "Basic dXNlcjpzZWNyZXQ=")) 
////		.andExpect(status().isOk())
////		.andDo(document("headers",
////				requestHeaders( 
////						headerWithName("Authorization").description(
////								"Basic auth credentials")), 
////				responseHeaders( 
////						headerWithName("X-RateLimit-Limit").description(
////								"The total number of requests permitted per period"),
////						headerWithName("X-RateLimit-Remaining").description(
////								"Remaining requests permitted in current period"),
////						headerWithName("X-RateLimit-Reset").description(
////								"Time at which the rate limit period will reset"))));
//	
////		protected final LinksSnippet pagingLinks = links(
////				linkWithRel("first").optional().description("The first page of results"),
////				linkWithRel("last").optional().description("The last page of results"),
////				linkWithRel("next").optional().description("The next page of results"),
////				linkWithRel("prev").optional().description("The previous page of results"));
////		
////		this.mockMvc.perform(get("/").accept(MediaType.APPLICATION_JSON))
////		.andExpect(status().isOk())
////		.andDo(document("example", this.pagingLinks.and( 
////				linkWithRel("alpha").description("Link to the alpha resource"),
////				linkWithRel("bravo").description("Link to the bravo resource"))));
//		
////		static class UserInput {
////
////			@NotNull
////			@Size(min = 1)
////			String name;
////
////			@NotNull
////			@Size(min = 8)
////			String password;
////
////		}
////		public void example() {
////			ConstraintDescriptions userConstraints = new ConstraintDescriptions(UserInput.class); 
////			List<String> descriptions = userConstraints.descriptionsForProperty("name"); 
////		}
//		
////		.andDo(document("create-user", requestFields(
////				attributes(key("title").value("Fields for user creation")), 
////				fieldWithPath("name").description("The user's name")
////						.attributes(key("constraints")
////								.value("Must not be null. Must not be empty")), 
////				fieldWithPath("email").description("The user's email address")
////						.attributes(key("constraints")
////								.value("Must be a valid email address"))))); 
//		
//	}

//	@Test
//	public void deleteId() {
//		throw new RuntimeException("Test not implemented");
//	}
//
//	@Test
//	public void read() {
//		throw new RuntimeException("Test not implemented");
//	}
//
//	@Test
//	public void readId() {
//		throw new RuntimeException("Test not implemented");
//	}
//
//	@Test
//	public void update() {
//		throw new RuntimeException("Test not implemented");
//	}
}
