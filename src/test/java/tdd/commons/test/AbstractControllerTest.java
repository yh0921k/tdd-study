package tdd.commons.test;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

// 두 가지 방법 존재
// 1. AutoConfiguration
// - 간단하게 테스트 하는 경우 아래와 같이 바로 mockMvc 객체를 생성할 수 있다.

// @SpringBootTest
// @AutoConfigureMockMvc
// @RequiredArgsConstructor
// public abstract class AbstractControllerTest {
//  protected final MockMvc mockMvc;
// }

// 2. Customizing
// - MockMvc를 커스터마이징 하기 위해 사용
// - MockMvcBuilders를 활용해 직접 인스턴스를 생성
// - 아래의 addFilters(...)는 테스트 수행 시에 body의 한글이 깨지는 것을 막기 위함
// - 아래의 alwaysDo(print())는 항상 콘솔에 테스트 결과를 찍는 문장

@SpringBootTest
public abstract class AbstractControllerTest {

  protected MockMvc mockMvc;

  @Autowired
  protected WebApplicationContext webApplicationContext;

  @BeforeEach
  private void setup() {
    mockMvc =
        MockMvcBuilders.webAppContextSetup(webApplicationContext)
            .addFilter(new CharacterEncodingFilter(StandardCharsets.UTF_8.name(), true))
            .alwaysDo(print())
            .build();
  }
}
