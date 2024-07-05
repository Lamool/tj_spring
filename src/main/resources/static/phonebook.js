console.log('phonebook.js 열림');

// let phonebookDB = [];
// 1. 등록함수 : 등록버튼을 클릭했을 때
function postPhone() {
    // 1. 입력받은 값 호출 해서 변수에 저장
    let name = document.querySelector('#name').value;
    let phone = document.querySelector('#phone').value;

    // 2. 객체화
    let phoneDto = { name : name, phone : phone }

    // 3. 객체를 배열 저장
    //phonebookDB.push(phoneDto);

    // 4. 안내 / 새로고침
    //alert('save');
    //getPhone();

    // 2. html에 jquery 라이브러리 가져왔으면 agax 함수 사용 가능.
        // 2-1. ajax 들어갈 옵션 객체 정의
    let option = {
        url : "http://localhost:8080/phone/create",   // 통신할 경로 --> spring의 controller      // ((http://localhost:8080 생략 가능))
        method : "post", // HTTP가 지원하는 함수중 사용할 함수명(post, get, put, delete)
        data : JSON.stringify(phoneDto),    // 통신할 경로에 보낼 데이터 --> spring의 controller에게 보낼 데이터
        contentType : "application/json",       // data 옵션에 있는 타입
        success : function response(result) {   // 통신을 성공했을 대 응답받을 함수
            console.log(JSON.parse(result));
            if(result) {
                alert('save');
                getPhone();
            } else {
                alert('fail)')
            }
        }
    }

    // 2-2. ajax함수 호출, $ : jquery 문법
    $.ajax(option);
}

// 2. 출력함수 : 등록처리가 되었을 때, js열렸을 때 최초 1번
getPhone();
function getPhone() {
    // 1. 어디에
    let phoneListBox = document.querySelector('#phoneListBox');

    // 2. 무엇을       // JAVA : ->  , js : =>
    let html = '';

    let option = {
        url : "http://localhost:8080/phone/read",       // 누구에게
        method : "get",     // "어떠한 방식으로
        // data : // 무엇을 보내고 (매개변수 없음)      // 무엇을 받을지
        success : function response(result) {
            console.log(result);

            result.forEach ( phone => {
            //        html += `<div><span>유재석</span> <span> 010-4444-5555 </span></div>`
                html += `<div>
                        <span> ${phone.name} </span>
                        <span> ${phone.phone} </span>
                    </div>`
                            // 3. 출력
            })
                phoneListBox.innerHTML = html;

        }


    }
    $.ajax(option);


}
