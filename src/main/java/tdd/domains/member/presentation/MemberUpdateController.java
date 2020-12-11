package tdd.domains.member.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tdd.domains.member.application.MemberUpdateService;
import tdd.domains.member.application.dto.MemberInfoRequest;

@RestController
@RequiredArgsConstructor
public class MemberUpdateController {

  private final MemberUpdateService memberUpdateService;

  @PutMapping("/member/{memberNumber}")
  public ResponseEntity update(@PathVariable Long memberNumber,
      @RequestBody MemberInfoRequest memberInfoRequest) {
    return memberUpdateService.update(memberNumber, memberInfoRequest);
  }
}
