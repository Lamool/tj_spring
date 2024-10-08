console.log("write.js");

loginCheck();       // 로그인 상태 체크

// 1. 카테고리 호출, 실행조건 : js 열렸을 때
bcFindAll();
function bcFindAll() {
    $.ajax({
        method : "get",
        url : "/board/category",
        success : (r) => {      console.log(r);
            // 1. 어디에
            let categoryBox = document.querySelector('.categoryBox');
            // 2. 무엇을 (AJAX 이용한 서버로부터 받은 데이터)
            let html = ``;
            // 서버로부터 응답받은 데이터를 타입 확인했더니, List 타입이므로 반복문 사용하자.
                // 언어별 화살표 함수 표현 방식 JAVA : () -> {} vs JS : () => {}
            
            r.forEach( (c) => { html += `<option value="${c.bcno}">${c.bcname}</option>`});

            // 3. 출력
            categoryBox.innerHTML = html;

        },
        error : (e) => {
            console.log(e);
        }
    })
}

// 2. 게시물 쓰기 (첨부파일 전송하지 않는 일반 JSON 타입의 통신)
/*
function doBoardWrite() {
    // 1. 입력받은 값 가져오기
        // select 목록에서 선택한 option의 value 호출
    let bcno = document.querySelector('.categoryBox').value;
    let btitle = document.querySelector('.btitle').value;
    let bcontent = document.querySelector('.bcontent').value;

    // 2. 객체화
    let info = {
        bcno : bcno, btitle : btitle, bcontent, bcontent
    };
    console.log(info);
    
    // 3. AJAX 통신
    $.ajax({
        method : 'post',
        url : "/board/write",
        data : JSON.stringify(info),
        success : (r) => {  console.log(r);
            // 4. 통신 결과에 따른 실행문
            if (r) {
                alert('글쓰기성공');
                location.href="/board";
            } else {
                alert('글쓰기실패');
            }
        },
        error : (e) => {
            console.log(e);
        }
    })

}
*/


// 2. 게시물 쓰기 (첨부파일 전송하는 대용량 form 타입의 통신)
function doBoardWrite() {
    // 1. form 가져오기, form 안에 있는 HTML 모두 한 번에 가져오기
    let boardWriteForm = document.querySelector('.boardWriteForm');
    console.log(boardWriteForm);

    // 2. form HTML 을 바이트로 변환해주는 객체 = new FormData
    let boardWriteFormData = new FormData(boardWriteForm);
    console.log(boardWriteFormData);

    $.ajax({
        method : "post",
        url : "/board/write",
        data : boardWriteFormData,
        contentType : false,    // ((true : 일반 폼(생략 가능), false : 대용량 폼)
        processData : false,
        success : (r) => {  console.log(r);
            // 4. 통신 결과에 따른 실행문
            if (r) {
                alert('글쓰기성공');
                location.href="/board";
            } else {
                alert('글쓰기실패');
            }
        },
        error : (e) => {
            console.log(e);
        }
    })

}


// 3. 썸머노트 실행
$(document).ready(function() {
    // 썸머노트 옵션
    let option = {
        height : 500,   // 에디터 높이
        lang : 'ko-KR'   // 도움말이 한글로 표기
    }

    $('#summernote').summernote(option);
});


// 4. 로그인 체크
function loginCheck() {
    $.ajax ({
        async : false,
        method : "get",
        url : "/member/login/check",
        success : r => {    console.log(r);
            if ('' == r) {
                alert('글쓰기는 로그인 후 가능합니다');
                location.href = '/member/login';
            }
        }

    })
}

