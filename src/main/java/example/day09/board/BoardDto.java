package example.day09.board;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter @ToString
public class BoardDto {
    // 매개변수
    private int bno;
    private String bdate;
    private String btitle;
    private int bview;
    private String bcontent;
    private String bpassword;

}
