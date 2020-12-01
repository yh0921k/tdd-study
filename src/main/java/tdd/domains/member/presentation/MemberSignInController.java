package tdd.domains.member.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tdd.domains.member.application.MemberSignInService;
import tdd.domains.member.application.dto.MemberInfoRequest;
import tdd.domains.member.application.dto.MemberInfoResponse;

@RestController
@RequiredArgsConstructor
public class MemberSignInController {

  private final MemberSignInService memberSignInService;

  @PostMapping("/member")
  public ResponseEntity<MemberInfoResponse> signIn(
      @RequestBody MemberInfoRequest memberInfoRequest) {
    return memberSignInService.signIn(memberInfoRequest);
  }
}
