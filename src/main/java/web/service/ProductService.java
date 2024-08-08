package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.model.dao.ProductDao;
import web.model.dto.ProductDto;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired private FileService fileService;
    @Autowired private ProductDao productDao;

    // 1. 제품등록
    public boolean pRegister(ProductDto productDto) {
        System.out.println("ProductService.pRegister");
        System.out.println("productDto = " + productDto);
        
        // [1] 여러 개 첨부파일 업로드 하기
        List<String> fileNames = new ArrayList<>();

        // (1) 첨부파일 개수만큼 반복문을 돌린다.
        productDto.getFiles().forEach( file -> {
                // (2) 각 첨부파일 하나씩 업로드 메소드에 대입한다.
                String fileName = fileService.fileUpload(file);
                if (fileName != null) {
                    // (3) 업로드 된 파일명을 리스트에 담는다
                    // (업로드된 파일명을 DB에 저장하기 위해서)
                    fileNames.add(fileName);
                }   // if end
            }   // for 실행문 end
        );  // forEach end
        // [2] - DB
        productDto.setFileNames(fileNames);
        return productDao.pRegister(productDto);
    }   // pRegister end

    // 2. 제품 전체 출력 ( 1개 : dto, 여러 개 : List<Dto> )
    public List<ProductDto> getProductFindAll() {
        return productDao.getProductFindAll();
    }


}   // class end
