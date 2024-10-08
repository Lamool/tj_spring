console.log('todo.js');

//let todoList = ["밥먹기,X"];  // 첫 달에는 JS에서 메모리 관리를 했지만 // 세 번째 달에는 웹서버(SPRING->DB서버) 관리 하기 때문에 필요없다.

// 1. 할 일 등록 함수
function todocreate(){
    console.log('todocreate() load');

    // [1] HTML 입력받은 값 가져오기
        // [1-1] DOM 가져오기
    let todoInput = document.querySelector(`#todoInput`);
    console.log(todoInput);
        // [1-2] 가져온 DOM의 입력값 호출
    let tcontent = todoInput.value;
    console.log(tcontent);
    
    // [2] AJAX(JQUERY 라이브러리) 이용한 웹서버(컨트롤러) 통신해서 요청과 응답 하기
    let ajaxoption = {
        method : 'post',                        // HTTP 메소드 선택(GET, POST, PUT, DELETE)
        url : '/todo/create?tcontent='+tcontent,  // HTTP통신할 경로 작성(http://localhost:8080 IP와 PORT 생략)
        success : function response(result) {   // HTTP 통신 성공시 응답값을 함수의 매개변수로 받는다
            console.log(result);            // 응답 결과 확인
            if (result == true) {
                alert('할 일 등록 성공');   // 성공 안내
                todoInput.value = '';       // 입력상자에 입력된 값 없애기
                todoreadall();              // 등록 성공시 할 일 목록 전체 출력 함수 호출
            } else {
                alert('할 일 등록 실패');   // 실패 안내
            }
        }   // success end
    }   // 옵션 end
    // AJAX는 JQUERY 라이브러리 포함된 함수이다. $는 JQUERY의 문법이다.
    $.ajax(ajaxoption);

}   // todocreate end

// 2. 할 일 목록 전체 출력, 실행조건 : 1. JS열렸을 때 2. 등록/삭제/수정(상태 변경시) 성공시
todoreadall();      // 1. JS 열렸을 때
function todoreadall(){
    // 출력할 데이터 가져오기
    $.ajax({
        method : 'get',
        url : '/todo/readall',
        success : function response (result) {  // 결과받은 데이터의 타입은 Array/list
            console.log(result)
        
            // [1] 어디에
            let todoBox = document.querySelector(`#todoBox`);
            // [2] 무엇을
            let html = ``;
            // [2-1] for (let i = 0; i < 리스트명.length; i++)
            // [2-2] 리스트명.forEach(반복변수명 => {실행문})
            result.forEach(todoDto => {
                html += `<div id =${todoDto.tstate == 0 ? 'whiteBox' : 'blackBox'}>
                            <span> ${todoDto.tcontent} </span>
                            <div>
                                <button type="button" onclick="todoupdate(${todoDto.tno})">변경</button>
                                <button type="button" onclick="tododelete(${todoDto.tno})">삭제</button>
                            </div>
                        </div>`
                        console.log(html);
            });

            // [3] 출력
            todoBox.innerHTML = html;
        }
    })  // ajax end

}

function todoupdate(tno) {
    // $.ajax({옵션속성객체});
    $.ajax({
        method : 'put',
        // url 안에 쿼리스트링 형식으로 데이터 대입
        url : `/todo/update?tno=${tno}`,
        success : function response(result) {
            console.log(result);
            if (result) {
                todoreadall();  // 새로고침
            } else {
                alert('오류 발생 : 관리자에게 문의');
            }
        }   // success end
    }); // ajax end
}   // todoupdate end

function tododelete(tno) {
    $.ajax({
        method : 'delete',
        url : '/todo/delete',
        // url 아닌 data 속성에 {key:value} 형식의 데이터 대입
        data : {'tno' : tno},
        // [화살표함수] success : result => {}
        // [익명함수] success : function(result) {}
        // [일반함수] success : function response(reuslt) {}
        success : result => {
            console.log(result);
            if (result) {
                todoreadall();  // 새로고침
            } else {
                alert('오류 발생 : 관리자에게 문의');
            }
        }   // success end

    });     // ajax end
}   // tododelete end


