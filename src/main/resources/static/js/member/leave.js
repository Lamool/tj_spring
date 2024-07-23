console.log('leave.js');

// 회원 탈퇴 함수
function doLeave() {    console.log(doLeave);
    // 1.
    let pwConfirm = document.querySelector('.pwConfirm').value;
    console.log(pwConfirm);

    // 2. AJAX 이용한 서버에게 탈퇴 통신
    $.ajax({
        method : 'delete',                  // HTTP METHOD : 통신할 때 사용할 method 선택
        url : '/member/leave',
        data : { pwConfirm : pwConfirm },
        success : (result) => {
            if (result) {
                alert('회원탈퇴성공');
                location.href="/";
            } else {
                alert('회원탈퇴실패');
            }
        }
    });     // ajax end
}   // method end