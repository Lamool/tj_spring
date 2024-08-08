console.log("register.js");

function onRegister() {     console.log("onRegister()");
    // 대용량 첨부파일 보내기
    // 1. 폼 가져오기
    const productForm = document.querySelector('#productForm')
    // 2. 폼데이터를 바이트(바이나리)로 변환, 첨부파일은 JSON, TEXT (텍스트)형식으로 보낼 수 없다
    const productFormData = new FormData(productForm);
    // 3. ajax 이용한 데이터 전송
    $.ajax({
        async : false,
        method : "post",
        url : "/product/register",
        data : productFormData,
        contentType : false, processData : false,   // ajax에서 멀티파트타입으로 전송 방법   /* ((multipart Type으로 전송하기 위해 false?)) */
        success : r => {    console.log(r); },
        error : e => {  console.log(e);  }

    });

}   // method end

