/*
 * package com.assignment.mcb.controller;
 * 
 * import static org.junit.jupiter.api.Assertions.*;
 * 
 * import java.io.IOException;
 * 
 * import org.json.JSONObject; import org.junit.jupiter.api.BeforeEach; import
 * org.junit.jupiter.api.Test; import
 * org.junit.jupiter.api.extension.ExtendWith; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.boot.json.JsonParseException; import
 * org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
 * import org.springframework.boot.test.context.SpringBootTest; import
 * org.springframework.http.HttpStatus; import
 * org.springframework.http.MediaType; import
 * org.springframework.http.ResponseEntity; import
 * org.springframework.security.core.context.SecurityContextHolder; import
 * org.springframework.test.context.junit.jupiter.SpringExtension; import
 * org.springframework.test.web.servlet.MockMvc; import
 * org.springframework.test.web.servlet.MvcResult; import
 * org.springframework.test.web.servlet.request.MockMvcRequestBuilders; import
 * org.springframework.web.bind.annotation.PostMapping; import
 * org.springframework.web.bind.annotation.RequestParam; import
 * org.springframework.web.multipart.MultipartFile;
 * 
 * import com.assignment.mcb.dto.JwtRequest; import
 * com.assignment.mcb.dto.ResponseMessage; import
 * com.assignment.mcb.enums.DocumentType; import
 * com.fasterxml.jackson.core.JsonProcessingException; import
 * com.fasterxml.jackson.databind.JsonMappingException; import
 * com.fasterxml.jackson.databind.ObjectMapper;
 * 
 * @ExtendWith(SpringExtension.class)
 * 
 * @SpringBootTest
 * 
 * @AutoConfigureMockMvc public class FileControllerTest {
 * 
 * private String accessToken = "Bearer ";
 * 
 * @Autowired private MockMvc mockMvc;
 * 
 * @BeforeEach public void setup() throws Exception { accessToken = accessToken
 * + getToken(); }
 * 
 * public String getToken() throws Exception { String uri = "/authenticate";
 * JwtRequest request = new JwtRequest(); request.setUsername("gaura");
 * request.setPassword("password"); String inputJson = mapToJson(request);
 * MvcResult mvcResult = mockMvc.perform(
 * MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE
 * ).content(inputJson)) .andReturn(); String content =
 * mvcResult.getResponse().getContentAsString(); JSONObject jsonObj = new
 * JSONObject(content);
 * 
 * return jsonObj.getString("token"); }
 * 
 * private String mapToJson(Object obj) throws JsonProcessingException {
 * ObjectMapper objectMapper = new ObjectMapper();
 * objectMapper.findAndRegisterModules(); return
 * objectMapper.writeValueAsString(obj); }
 * 
 * @Test public void uploadFileTest() { String uri = "/upload"; MvcResult
 * mvcResult = null; try { mvcResult =
 * mockMvc.perform(MockMvcRequestBuilders.post(uri).accept(MediaType.
 * MULTIPART_FORM_DATA) .header("Authorization", accessToken)).andReturn(); int
 * status = mvcResult.getResponse().getStatus(); assertEquals(404, status);
 * String content = mvcResult.getResponse().getContentAsString(); String
 * expectedJSON = "No Teacher Present with this ID"; assertEquals(expectedJSON,
 * content); } catch (Exception e) { e.printStackTrace(); }
 * 
 * }
 * 
 * }
 */