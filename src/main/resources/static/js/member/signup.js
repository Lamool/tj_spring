console.log('signup.js');

// 1. 회원가입
function doSignup() {
    console.log('doSignup()');

    // 1. 입력값 가져오기
    let id = document.querySelector('#id').value;
    let pw = document.querySelector('#pw').value;
    let name = document.querySelector('#name').value;
    let email = document.querySelector('#email').value;
    let phone = document.querySelector('#phone').value;

    // 2. 객체
    let info = {
        id : id,            // ((필드명과 같아야 한다. dto랑 똑같게. >>id :))
        pw : pw,
        name : name,
        email : email,
        phone : phone
    };
    console.log(info);

    // 3. ajax (jquery 라이브러리 필요), ajax는 비동기 통신
    $.ajax({
        async : false,               // async : true 비동기(기본값), false : 동기식  ((순차 처리 할지 말지 / true는 기본값이라 안 넣어줘도 된다))
        method : 'post',            // HTTP POST
        url : "/member/signup",     // HTTP URL
        data : info,                // HTTP 보낼 데이터
        success : (result) => {     console.log(result);    // HTTP ((통신 성공시 응답)) 받을 데이터    
                                                    // ((result (RestController라서 responsebody가 들어가있으니까 JSON 형식으로) true 혹은 false를 줌))
                                                    // ((boolean / return memberService.mSignup(memberDto);))
            // 4. 결과에 따른 처리
            if (result) {
                alert('회원가입성공');
                location.href="/member/login";      // 페이지 전환
            } else {
                alert('회원가입실패');
            }
        }   // success end
    });  // ajax end

    //alert('ajax 처리 이후');
    // ((ajax 멀티스레드라(?) async가 true면 요청한 거 받아오는 사이에 밑에 alert('ajax 처리 이후');를 먼저 실행시켜버림))
    // ((async가 false면 기다리고(?)))
    // async : true, alert('ajax 처리 이후'); -> alert('회원가입성공');
    // async : false, alert('회원가입성공'); -> alert('ajax 처리 이후');

}   // doSignup end


/*
    onclick="함수()" : 마우스로 클릭 했을때 작동하는 이벤트
    onkeyup="함수()" : 키보드에서 키를 누르고 떼었을때 작동하는 이벤트
*/

// 2. 아이디 유효성검사
function idCheck(){ console.log('idcheck()')
    // 1. 입력된 값 가져오기
    let id = document.querySelector('#id').value;   console.log( id );
    let idCheckBox = document.querySelector('.idCheckBox');
    // 2. 정규표현식 : 영대소문자와 숫자 조합의 5~30글자 까지 허용
    let idReg =  /^[a-zA-Z0-9]{5,30}$/
    // 3. 정규표현식 검사.
    console.log( idReg.test( id ) )
    if( idReg.test(id) ){
        // 아이디 중복검사 REST API 통신
        $.ajax({
            async : false,              // 비동기true vs 동기false
            method : "get",             // HTTP method
            url : "/member/idcheck",    // HTTP url
            data : { id : id } ,        // HTTP 전송할 DATA
            success : (result)=>{       // HTTP 응답받을 DATA
                if( result ){
                    idCheckBox.innerHTML = `사용중인 아이디`
                }else{
                    idCheckBox.innerHTML = `사용가능한 아이디 입니다.`
                }
            } // success method end
        }) // ajax end
    }else{
        idCheckBox.innerHTML = `영대소문자 와 숫자 조합의 5~30 글자 사이 가능합니다.`
    }
} // method end


// 3. 패스워드 유효성검사
function pwCheck(){ console.log("pwCheck()");
    // 1.
    let pw = document.querySelector('#pw').value;
    let pwConfirm = document.querySelector('#pwConfirm').value;
    let pwCheckBox = document.querySelector('.pwCheckBox');
    // 2. 정규표현식
    let pwReg = /^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z0-9]{5,30}$/
    // 3. 정규표현식 검사
    if( pwReg.test(pw) ){ // 비밀번호 정규표현식 검사
        if( pwReg.test(pwConfirm) ){ // 비밀번호 확인 , 정규표현식 검사
            if( pw == pwConfirm ){ // 두 비밀번호 일치 여부
                pwCheckBox.innerHTML = '통과';
                return;
            }else{
                pwCheckBox.innerHTML = '두 비밀번호가 일치하지 않습니다.'
                return;
            }
        }
    }
    pwCheckBox.innerHTML =`영대소문자 와 숫자 조합의 5~30 글자 사이 가능합니다.`
} // method end


// 4. 이름 유효성검사
function nameCheck(){
    let name = document.querySelector('#name').value;
    let nameCheckBox = document.querySelector('.nameCheckBox')
    let nameReg = /^[가-힣]{2,20}$/
    if( nameReg.test( name ) ){
        nameCheckBox.innerHTML ='사용가능한 이름입니다.';
    }else{
        nameCheckBox.innerHTML ='한글 2~20글자 사이 입력해주세요.';
    }
}


// 5. 전화번호 유효성검사.
function phoneCheck(){
    let phone = document.querySelector('#phone').value;
    let phoneCheckBox = document.querySelector('.phoneCheckBox')
    // 2. 정규표현식 : 000-0000-0000 또는 00-000-0000
    let phoneReg = /^([0-9]{2,3})+[-]+([0-9]{3,4})+[-]([0-9]{4})$/
    if( phoneReg.test(phone) ){
        // 중복검사 생략
        phoneCheckBox.innerHTML = '사용가능한 전화번호입니다.'
    }else{
        phoneCheckBox.innerHTML = '000-0000-0000 또는 00-000-0000 형식으로 입력해주세요.'
    }
}


// 6. 이메일 유효성검사
function emailCheck(){
    let email = document.querySelector('#email').value;
    let emailCheckBox = document.querySelector('.emailCheckBox')
    // 2. 정규표현식
        // kgs2072@ : [a-zA-Z0-9_-]+@  : @ 앞에 패턴 1개이상 존재한다.
        // naver : [a-zA-Z0-9_-]
        // .com : \.[a-zA-Z]+
            // . 정규표현식에 사용되는 패턴 vs \. 문자 (점)
    let emailReg = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+\.[a-zA-Z]+$/
    if( emailReg.test(email) ){
        // 이메일 중복검사 생략
        // 이메일 인증검사
        emailCheckBox.innerHTML ='사용가능한 이메일입니다.';
    }else{
        emailCheckBox.innerHTML ='id@도메인주소 형식으로 입력해주세요.';
    }
}
