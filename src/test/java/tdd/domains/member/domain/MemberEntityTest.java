package tdd.domains.member.domain;

import static org.assertj.core.api.Assertions.assertThat;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(
    properties = {
      "testName=Kim",
      "testAge=29",
      "spring.config.location=classpath:/secret.yml,application.yml"
    })
@Slf4j
public class MemberEntityTest {

  @Value("${testName}")
  private String testName;

  @Value("${testAge}")
  private int testAge;

  @Value("${testEmail}")
  private String testEmail;

  @Value("${testPassword}")
  private String testPassword;

  @Test
  void getMember() {
    MemberEntity memberEntity =
        MemberEntity.builder()
            .email(testEmail)
            .password(testPassword)
            .name(testName)
            .age(testAge)
            .build();

    MemberEntity comp =
        MemberEntity.builder()
            .email("yh0921k@naver.com")
            .password("1111")
            .name("Kim")
            .age(29)
            .build();

    log.info(">> " + memberEntity.toString());
    log.info(">> " + comp.toString());
    assertThat(memberEntity).isEqualToComparingFieldByField(comp);
  }
}
