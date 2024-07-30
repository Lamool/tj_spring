console.log('board().js');


// 1. 게시물 전체 출력
    // 매개변수
    // 1. page : 보고자 하는 현재 페이지번호 , 초기값 : 1 , 첫페이지를 1페이지로 하기 위해서
    // 2. bcno : 보고자 하는 카테고리 번호  , 초기값 : 0 , 전체보기를 0 으로 하기 위해서
doBoardFindAll(1, 0);
function doBoardFindAll(page, bcno) {     console.log("boardPrint()");
    let boardPageDto = { } // ajax로부터 응답받은 객체를 저장하는 변수
    $.ajax({
        async : false,      // false동기화 vs true비동기화( innerHTML 후 에 응답 온다.)
        method : 'get',
        url : '/board/find/all',
        data : { page : page, bcno : bcno },
        success : (result) => {     console.log(result);
                // 응답 데이터의 타입이 Array , Object 인지 확인 필요.
                boardPageDto = result;
        }
    })  // ajax end

    // 1. 어디에
    let bTbody = document.querySelector('.boardBody');

    // 2. 무엇을
    let html = ``;

    // 페이징 전 내가
    // result.forEach( (b) => html += `
    //                                 <tr> <td> ${b.bno} </td> <td> <a href="/board/view?bno=${b.bno}">${b.btitle}</a> </td> <td> ${b.id} </td> <td> ${b.bdate} </td> <td> ${b.bview} </td> </tr>
    //                                `
    // )

    let list = boardPageDto.data;
        list.forEach( b =>{
            html += `<tr>
                    <th> ${ b.bno } </th>
                    <th> <a href="/board/view?bno=${ b.bno }">${ b.btitle }</a>
                    </th> <th> ${ b.id } </th>
                    <th> ${ b.bdate } </th>
                    <th> ${ b.bview }</th>
                    </tr>`
        })
    // 3. 출력
    boardBody.innerHTML = html;


    // 4. 페이지네이션( 페이지버튼 ) 구성
    // 4-1 어디에
    let pagination = document.querySelector('.pagination')

    // 4-2 무엇을
    let pageHTML = ``;
    // 이전버튼 , page : 현재 함수의 매개변수이면서 현재페이지번호 의미 , 현재페이지 - 1 , 만약에 현재페이지-1 했을때 1보다 작으면 1 고정 하고 아니면 -1차감
    pageHTML += `<li class="page-item"><a class="page-link" onclick="doBoardFindAll( ${ page-1 < 1 ? 1 : page-1 } )">이전</a></li>`;
    // 페이지버튼
        // 페이지 마다 시작 버튼 번호 : startBtn
    let startBtn = boardPageDto.startBtn;
        // 페이지 마다 끝 버튼 번호 : endBtn
    let endBtn = boardPageDto.endBtn;
        // 최대 페이지수 : totalPage
    let totalPage = boardPageDto.totalPage;
        // + current는 startBtn 부터 endBtn 까지 반복
        // current == page ? 'active' : '' : current번째 값이 현재 페이지이면 active 클래스 대입
    for( let current = startBtn ; current <= endBtn ; current++ ){
        pageHTML += `<li class="page-item">
                        <a class="page-link ${ current == page ? 'active' : '' }" onclick="doBoardFindAll(${current})">${current}</a>
                    </li>`;
    }
    // 다음버튼 , page : 현재 함수의 매개변수 이면서 현재페이지번호 의미 , 현재페이지 + 1 , 만약에 현재페이지+1 했을때 최대페이지수 보다 커지면 최대페이지수 고정 아니면 +1 증가
    pageHTML += `<li class="page-item">
                    <a class="page-link" onclick="doBoardFindAll( ${ page+1 > totalPage ? totalPage : page+1 } )">다음</a>
                </li>`;

    // 4-3 출력
    pagination.innerHTML = pageHTML;

             
}