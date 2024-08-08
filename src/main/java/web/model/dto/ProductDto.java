package web.model.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Builder
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString
public class ProductDto {

    // 제품 정보
    private int pno;
    private String pdate;
    private int pview;
    // 제품 정보
    private String ptitle;
    private String pcontent;
    private int pprice;

    // 여러 개 첨부파일 필드(업로드용도)
    List<MultipartFile> files;
    // 여러 개 파일명 필드
    List<String> fileNames;

}
