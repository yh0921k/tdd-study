package tdd.domains.member.application;

import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tdd.domains.member.application.dto.MemberInfoResponse;
import tdd.domains.member.domain.MemberEntity;
import tdd.domains.member.domain.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberFindService {

  private final MemberRepository memberRepository;

  @Transactional
  public ResponseEntity<MemberInfoResponse> getMember(Long memberNumber) {
    MemberEntity memberEntity =
        memberRepository
            .findById(memberNumber)
            .orElseThrow(() -> new IllegalArgumentException("Invalid Member Number"));

    return ResponseEntity.ok(
        MemberInfoResponse.builder()
            .id(memberEntity.getId())
            .email(memberEntity.getEmail())
            .password(memberEntity.getPassword())
            .name(memberEntity.getName())
            .age(memberEntity.getAge())
            .build());
  }
}
