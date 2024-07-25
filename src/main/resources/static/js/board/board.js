console.log('board().js');

// 1. 글 전체 출력
boardPrint();
function boardPrint() {     console.log("boardPrint()");
    $.ajax({
        async : false,
        method : 'get',
        url : '/board/print',
        success : (result) => {     console.log(result);
            // 1. 어디에
            let bTbody = document.querySelector('#bTbody');

            // 2. 무엇을
            let html = ``;

            result.forEach( (b) => html += `
                                            <tr> <td> ${b.bno} </td> <td> <a href="/board/view?bno=${b.bno}">${b.btitle}</a> </td> <td> ${b.name} </td> <td> ${b.bdate} </td> <td> ${b.bview} </td> </tr>
                                           `
            )

            console.log(html);

            // 3. 출력
            bTbody.innerHTML = html;
        },
        error : (e) => {
            console.log(e);
        }

    });
}