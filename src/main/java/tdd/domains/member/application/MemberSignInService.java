package tdd.domains.member.application;

import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tdd.domains.member.application.dto.MemberInfoRequest;
import tdd.domains.member.application.dto.MemberInfoResponse;
import tdd.domains.member.domain.MemberEntity;
import tdd.domains.member.domain.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberSignInService {

  private final MemberRepository memberRepository;

  @Transactional
  public ResponseEntity<MemberInfoResponse> signIn(MemberInfoRequest memberInfoRequest) {
    MemberEntity memberEntity =
        MemberEntity.builder()
            .email(memberInfoRequest.getEmail())
            .password(memberInfoRequest.getPassword())
            .name(memberInfoRequest.getName())
            .age(memberInfoRequest.getAge())
            .build();

    memberRepository.save(memberEntity);
    return ResponseEntity.ok(MemberInfoResponse.builder().id(memberEntity.getId()).build());
  }
}
