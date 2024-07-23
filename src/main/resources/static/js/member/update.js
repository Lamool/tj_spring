console.log('update.js');

// 1. 내 회원 정보 호출
doUpdatePrint();
function doUpdatePrint() {
    $.ajax({
        async : false,
        method : 'get',
        url : '/member/update/print',
        success : result => {     console.log(result);
            console.log(result.no);

            if ('' == result) {     // ==를 써야 하는데 =를 썼더니 대입이 돼서 계속 undefined가 뜸
                alert('로그인하고 오세요.');
                location.href="/member/login";
            }

            // if (7 == result.no) {
            //     alert('!!!');
            // }

            // 1. 어디에
            let updateBox = document.querySelector('#updateBox');

            // 2. 무엇을 
            let html = '';
            console.log( result.id );
            
            html += `<div> NO : ${result.no} </div>`
            html += `<div> ID : ${result.id} </div>`
            html += `<div> 기존 비밀번호 : <input type="password" class="pw" /> </div>`
            html += `<div> 새로운 비밀번호 : <input type="text" class="newPw" /> </div>`
            html += `<div> Name : <input type="text" value="${result.name}" class="name" /> </div>`
            html += `<div> Phone : <input type="text" value="${result.phone}" class="phone" /> </div>`
            html += `<div> Email : ${result.email} </div>`
            html += `<button type="button" onclick="doUpdate()">수정하기</button>`

            // 3. 출력
            updateBox.innerHTML = html;
        }

    });

}


// 2. 회원 정보 수정
function doUpdate() {   console.log("doUpdate()");
    let pw = document.querySelector('.pw');
    let newPw = document.querySelector('.newPw');
    let name = document.querySelector('.name');
    let phone = document.querySelector('.phone');

    $.ajax({
        async : false,
        method : 'put',
        url : '/member/update',
        data : { pw : pw, pw : newPw, name : name, phone : phone },
        success : result => {       console.log(result);
            if (result) {
                alert('회원 정보 수정 성공');
            } else {
                alert('회원 정보 수정 실패');
            }

        }

    });

}

