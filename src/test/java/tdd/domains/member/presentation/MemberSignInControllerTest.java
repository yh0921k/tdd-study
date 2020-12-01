package tdd.domains.member.presentation;

import static org.junit.jupiter.api.Assertions.*;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@RequiredArgsConstructor
@SpringBootTest
class MemberSignInControllerTest {

  private final MockMvc mockMvc;

  @Test
  void signIn() {}
}