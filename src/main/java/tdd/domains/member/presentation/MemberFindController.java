package tdd.domains.member.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import tdd.domains.member.application.MemberFindService;
import tdd.domains.member.application.dto.MemberInfoResponse;

@RestController
@RequiredArgsConstructor
public class MemberFindController {
  private final MemberFindService memberFindService;

  @GetMapping("/member/{memberNumber}")
  public ResponseEntity<MemberInfoResponse> getMember(@PathVariable Long memberNumber) {
    return memberFindService.getMember(memberNumber);
  }
}
