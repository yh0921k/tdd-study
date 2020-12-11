package tdd.domains.member.application;

import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tdd.domains.member.domain.MemberEntity;
import tdd.domains.member.domain.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberRemoveService {

  private final MemberRepository memberRepository;

  @Transactional
  public ResponseEntity remove(Long memberIndex) {
    MemberEntity memberEntity = memberRepository
        .findById(memberIndex)
        .orElseThrow(() -> new IllegalArgumentException("Invalid Member Index"));

    memberRepository.delete(memberEntity);
    return ResponseEntity.ok().build();
  }
}
