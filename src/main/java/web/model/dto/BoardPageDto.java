package web.model.dto;

import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString
public class BoardPageDto {
    // 검색, 카테고리별 페이징 처리에 다양한 매개변수가 필요하므로 집합 Dto
    private int page;   // 1. 현재 페이지 번호
        // (지역/매개) 변수와 필드 차이점 : 초기값의 차이
    private int totalBoardSize;     // 2. 전체 게시물 수
    private int totalPage;          // 3. 전체 페이지 수
    private List<BoardDto> data;    // 4. 조회된 게시물 정보 목록
    private int startBtn;           // 5. 페이지별 시작버튼 번호
    private int endBtn;             // 6. 페이지별 끝버튼 번호
    private int bcno;               // 7. 현재 카테고리 번호
    // + 검색 필드
    private String searchKey;       // 8. 검색 조회시 사용되는 필드명
    private String searchKeyword;   // 9. 검색 조회시 사용되는 필드의 값
}
