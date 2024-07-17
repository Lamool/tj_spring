package web.model.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter @ToString
public class MemberDto {
    private int no;             // 회원 번호
    private String id;          // 회원 아이디
    private String pw;          // 회원 비밀번호
    private String name;        // 회원 이름
    private String email;       // 회원 이메일
    private String phone;       // 회원 핸드폰 번호

}
