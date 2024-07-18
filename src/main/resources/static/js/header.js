console.log('header.js');

// 1. 로그인 상태 요청
doLoginCheck();
function doLoginCheck() {
    $.ajax({
        method : 'get',
        url : "/member/login/check",
        success : (result) => {
            console.log(result);
            if(result == '') {
                console.log('비로그인');
            } else {
                console.log('로그인');
            }
        }
    })
}

// 2. 로그아웃
function doLogout() {
    $.ajax({
        method : 'get',
        url : "/member/logout",
        success : (result) => {
            console.log(result);
            location.href="/";
        }
    });
}


