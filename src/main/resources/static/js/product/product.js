console.log('product.js');

// 모든 제품 호출 함수
doProductFindAll();
function doProductFindAll() {
    // 어디에
    const productListBox = document.querySelector('.productListBox');

    // 무엇을
    let html = ``;

    $.ajax({    // 데이터 요청 ajax
        async : false,
        method : "get",
        url : "/product/find/all",
        success : (result) => {     console.log(result);

            // 여러 개 제품, r : 여러 개 제품들 리스트, product : 각 제품 1개
                // fileNames : 각 제품들의 이미지들 리스트, imgName : 각 이미지 1개

            result.forEach(product => {
                html += `<div class="productBox">`  // 제품 1개당 div 하나씩, div start
                // 여러 개 이미지 파일, 제품마다의 여러 개 이미지들
                product.fileNames.forEach( imgName => {

                    // 업로드된 이미지 파일명을 서버로 요청을 해서 응답 받아 img마크업 src속성에 대입, /업로드된 폴더명
                    // (((((html += `<img style="width: 100px;" src="/upload/${result[0].fileNames[0]}" />`
                    html += `<img style="width:100px;" src="/upload/${imgName}" />`
                });
                html += `</div>`    // 제품 1개당 div end
            });
        },
        error : e => {
            console.log(e);
        }
    })

    // 출력
    productListBox.innerHTML = html;


}   // productFindAll() end
