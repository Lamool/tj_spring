console.log("view.js()");

// board.js 에서 view 페이지 이동 코드  <th> <a href="/board/view?bno=${ b.bno }">${ b.btitle }</a>
    // JS 코드가 HTML 를 만들어내고 사용자는 표현된 HTML 에서 a 클릭이벤트
    // <a href="/board/view?bno=3">안녕하세요</a>    ---> /board/view?bno=3

// URL 상의 쿼리스트링 매개변수를 JS에서 꺼내는방법
    // JAVA SPRING 에서 HTTP URL 상의 쿼리스트링 매개변수를 꺼내는 방법
        // @RequestParam 이용한 쿼리스트링 매개변수 매핑
    // JS 에서 HTTP URL 상의 쿼리스트링 매개변수를 꺼내는 방법
        // 1. new URL( location.href )  : 현재 JS가 포함된 URL 경로 의 정보가 담긴 객체 호출
        // 2. .searchParams;            : 현재경로상의 쿼리스트링 매개변수 속성 호출
        // 3. .get( key )               : 쿼리스트링 매개변수의 key에 해당 하는 value 호출

let urlParams = new URL(location.href).searchParams;
let bno = parseInt(urlParams.get("bno"));

// 1. 게시물 개별 조회 처리
boardView();
function boardView() {      console.log('boardView()');
    $.ajax({
        async : false,
        method : 'get',
        data : { bno : bno },
        url : '/board/bview',
        success : (r) => {     console.log(r);
            // 1. 어디에
            let view = document.querySelector('#view');

            // 2. 무엇을
            let html = ``;

            html += `
                    <div> 카테고리 : ${r.bcname} </div>
                    <div> 작성자 : ${r.id} , 조회수 : ${r.bview} , 작성일 : ${r.bdate}  </div>
                    <div> 제목 : ${r.btitle} </div>
                    <div> 내용 : ${r.bcontent} </div>
                    `
                    if (r.bfile == null) {  // 첨부파일이 없을 때
                        html += '<div> 첨부파일 : 없음</div>';
                    } else {    // 첨부파일이 있을 때
                        html += `<div> 첨부파일 : ${r.bfile.split('_')[1]} <a href="/file/download?filename=${r.bfile}">다운로드</a> </div>`;
                    }


                    // if (r.bfile == null) {  // 첨부파일이 없을 때
                    //     document.querySelector('.bFile').innerHTML = '';
                    // } else {    // 첨부파일이 있을 때
                    //     document.querySelector('.bFile').innerHTML = `${r.bfile.split('_')[1]} <a href="/file/download?filename=${r.bfile}">다운로드</a>`;
                    // }

                    

            html += `
                    <div>
                        <button type="button" onclick="location.href='/board/update?bno=${r.bno}'">수정</button>
                        <button type="button" onclick="doBoardDelete(${r.bno})">삭제</button>
                    </div>
                    `




            console.log(html);

            // 3. 출력
            view.innerHTML = html;
        },
        error : (e) => {
            console.log(e);
        }
    });

}



// 2. 댓글 쓰기
function onReplyWrite() {   console.log('onReplyWrite()');
    // 1. 입력받은 댓글내용 가져오기
    let brcontent = document.querySelector('.brcontent').value;

    // 2. 객체화
    let info = {
        brindex : 0,    // 댓글분류, 0이면 상위댓글
        brcontent : brcontent,
        bno : bno       // 현재 보고 있는 게시물 번호 (view.js 상단에서 선언된 변수)
    }    

    $.ajax({    // 왜 ajax를 쓰는가
        async : false,
        method : 'post',
        url : "/board/reply/write",
        data : JSON.stringify(info),    // 왜? JSON.stringify 사용하는지?
        contentType: "application/json",    // 왜? application/json 사용하는지?
            // contentType: "application/x-www-form-urlencoded", : ajax 기본값(생략시)
            // contentType: false,   --> contentType : multipart/form-data 첨부파일(바이너리)
            // contentType: "application/json"
        success : r => {    console.log(r);
            if (r == true) {
                alert('댓글쓰기 성공');
                onReplyPrint();
            } else {
                alert('댓글쓰기 실패 : 로그인 후 댓글쓰기가 가능합니다.');
            }

        }   // success end

    }); // ajax end

}   // onReplyWrite() end



// 3. 댓글 출력
onReplyPrint();
function onReplyPrint() { console.log('onReplyPrint()');
    let rmap = {};

    $.ajax({
        async : false,
        method : 'get',
        url : "/board/reply/print",
        data : { bno : bno },
        success : result => {    console.log(result);
            rmap = result;
        
        }
    })  // ajax end

    // 1. 어디에
    let brpint = document.querySelector('.brprint');
    
    // 2. 무엇을
    let html = ``;

    // let list = rmap.data;
    // console.log(rmap);
    // console.log(rmap.data);
    // console.log(list);
    rmap.forEach( r =>{ console.log(r.brcontent);
        html += `
                <div>
                    <div> ${ r.brcontent } </div>
                    <div> ${ r.brdate } </div>
                    <div> ${ r.name } </div>
                </div>
                `
    })

    // 3. 출력
    brpint.innerHTML = html;
}
