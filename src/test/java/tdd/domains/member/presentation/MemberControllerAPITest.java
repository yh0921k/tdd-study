package tdd.domains.member.presentation;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import tdd.commons.test.AbstractControllerTest;
import tdd.domains.member.application.dto.MemberInfoRequest;

//@Transactional
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
@Slf4j
public class MemberControllerAPITest extends AbstractControllerTest {

  private Long userIndex;

  @Test
  @Order(1)
  @DisplayName("사용자 등록 및 로그인")
  void signIn() throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();
    // 아래 방법 대신 사용 가능(방법은 다름)
    // ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();

    MemberInfoRequest memberInfoRequest = new MemberInfoRequest();
    String requestBody = objectMapper.writeValueAsString(memberInfoRequest);

    String data =
        "{\"id\":\"1\","
            + "\"email\":\"yh0921k@naver.com\","
            + "\"password\":\"test\","
            + "\"name\":\"Kim\","
            + "\"age\":\"29\"}";

    MvcResult mvcResult =
        mockMvc
            .perform(
                MockMvcRequestBuilders.post("/member")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(data)
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn();

    userIndex =
        Long.valueOf(
            JsonPath.read(mvcResult.getResponse().getContentAsString(), "$.id").toString());
    log.info(">> [SignIn] User Index : " + userIndex);
  }

  @Test()
  @Order(2)
  @DisplayName("사용자 조회")
  void getMember() throws Exception {
    log.info(">> [GetMember] User Index : " + userIndex);
    mockMvc
        .perform(
            MockMvcRequestBuilders.get("/member/{memberNumber}", userIndex)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name").value("Kim"));
    // $[0].name 형태는 언제 쓰는지?
  }

  @Test
  @Order(3)
  @DisplayName("사용자 정보 업데이트")
  void updateMember() throws Exception {

    String data = "{\"password\":\"testtest\"," + "\"name\":\"Yong\"}";

    mockMvc
        .perform(
            MockMvcRequestBuilders.put("/member/{memberNumber}", userIndex)
                .contentType(MediaType.APPLICATION_JSON)
                .content(data))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name").value("Yong"))
        .andExpect(jsonPath("$.id").value(userIndex))
        .andExpect(jsonPath("$.password").value("testtest"));
  }

  @Test
  @Order(4)
  @DisplayName("사용자 삭제")
  void removeMember() throws Exception {
    mockMvc
        .perform(MockMvcRequestBuilders.delete("/member/{memberNumber}", userIndex))
        .andExpect(status().isOk());
  }
}
