package com.study.SpringSecurity.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ReqSugninDto {
    @NotBlank(message = "사용자 이름을 입력하세요.") // NotBlank: 아무것도 입력 안했을때(빈값 체크)
    private  String username;
    @NotBlank(message = "비밀번호를 입력하세요.")
    private String password;
}
