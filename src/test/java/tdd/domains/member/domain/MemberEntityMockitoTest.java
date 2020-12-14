package tdd.domains.member.domain;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class MemberEntityMockitoTest {

  @Mock MemberEntity memberEntity;

  @Test
  public void createMockObject() {
    MemberEntity memberEntity = mock(MemberEntity.class);
    assertTrue(memberEntity != null);
  }

  @Test
  public void createMockObjectWithAnnotation() {
    MockitoAnnotations.initMocks(this);
    assertTrue(memberEntity != null);
  }

  @Test
  public void whenTest() {
    MemberEntity memberEntity = mock(MemberEntity.class);
    when(memberEntity.getName()).thenReturn("kyh");
    when(memberEntity.getAge()).thenReturn(29);
    assertTrue("kyh".equals(memberEntity.getName()));
    assertTrue(29 == memberEntity.getAge());
  }

  @Test()
  public void throwTest() {
    MemberEntity memberEntity = mock(MemberEntity.class);
    doThrow(new IllegalArgumentException()).when(memberEntity).setName(eq("kyh"));
    String name = "kyh";
    memberEntity.setName("kyh2");
  }

  @Test
  public void nothingTest() {
    MemberEntity memberEntity = mock(MemberEntity.class);
    doNothing().when(memberEntity).setAge(anyInt());
    memberEntity.setAge(29);
    verify(memberEntity).setAge(anyInt());
  }

  @Test
  public void verifyTest() {
    MemberEntity memberEntity = mock(MemberEntity.class);
    String name = "kyh";
    memberEntity.setName(name);

    // n번 호출 했는지 체크
    verify(memberEntity, times(1)).setName(any(String.class));

    // 호출 안했는지 체크
    verify(memberEntity, never()).getName();
    verify(memberEntity, never()).setName(eq("kyh2"));
    verify(memberEntity, never()).setName(eq("kyh3"));

    // 최소 한 번 이상 호출 했는지 체크
    verify(memberEntity, atLeastOnce()).setName(any(String.class));

    // 두 번 이하로 호출 했는지 체크
    verify(memberEntity, atMost(2)).setName(any(String.class));

    // 두 번 이상 호출 했는지 체크
    //verify(memberEntity, atLeast(2)).setName(any(String.class));

    // 지정된 시간(millisecond) 안으로 메서드를 호출 했는지 체크
    verify(memberEntity, timeout(100)).setName(any(String.class));

    // 지정된 시간안으로 한 번 이상 메서드를 호출 했는지 체크
    verify(memberEntity, timeout(100).atLeast(1)).setName(any(String.class));
  }
}
