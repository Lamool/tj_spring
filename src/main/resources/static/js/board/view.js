console.log("view.js()");

let urlParams = new URL(location.href).searchParams;
let bno = parseInt(urlParams.get("bno"));

// 1. 글 상세 페이지 출력
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
                    <div> 작성자 : ${r.name} , 조회수 : ${r.bview} , 작성일 : ${r.bdate}  </div>
                    <div> 제목 : ${r.btitle} </div>
                    <div> 내용 : ${r.bcontent} </div>
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