package tdd.domains.member.application.dto;

import lombok.Getter;

@Getter
public class MemberInfoRequest {
  private String email;
  private String password;
  private String name;
  private int age;
}
