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
public class MemberUpdateService {

  private final MemberRepository memberRepository;

  @Transactional
  public ResponseEntity<MemberInfoResponse> update(
      Long memberIndex, MemberInfoRequest memberInfoRequest) {
    MemberEntity memberEntity =
        memberRepository
            .findById(memberIndex)
            .orElseThrow(() -> new IllegalArgumentException("Invalid Member Index"));

    memberEntity.update(
        memberInfoRequest.getEmail(),
        memberInfoRequest.getPassword(),
        memberInfoRequest.getName(),
        memberInfoRequest.getAge());

    return ResponseEntity.ok()
        .body(
            MemberInfoResponse.builder()
                .id(memberEntity.getId())
                .email(memberEntity.getEmail())
                .password(memberEntity.getPassword())
                .name(memberEntity.getName())
                .age(memberEntity.getAge())
                .build());
  }
}
