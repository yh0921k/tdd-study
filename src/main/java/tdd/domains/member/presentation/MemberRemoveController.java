package tdd.domains.member.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import tdd.domains.member.application.MemberRemoveService;

@RestController
@RequiredArgsConstructor
public class MemberRemoveController {

  private final MemberRemoveService memberRemoveService;

  @DeleteMapping("/member/{memberNumber}")
  public ResponseEntity remove(@PathVariable Long memberNumber) {
    return memberRemoveService.remove(memberNumber);
  }
}
