console.log('myinfo.js');

// 1. 내정보 호출
doMyInfo();
function doMyInfo() {
    $.ajax({
        method : 'get',
        url : "/member/my/info",
        // 내가 한 거
        // success : function(data) {      console.log(data);
        //     document.querySelector('#id').innerHTML = data.id;
        //     document.querySelector('#name').innerHTML = data.name;
        //     document.querySelector('#email').innerHTML = data.email;
        //     document.querySelector('#phone').innerHTML = data.phone;
        // }

        success : result => {
            console.log(result);    // ((값이 뭐가 들어오는지 확인))

            if (result == '') {
                alert('로그인하고 오세요');
                location.href="/member/login";
            }

            // 1. 어디에
            let myInfoBox = document.querySelector('#myInfoBox');

            // 2. 무엇을
            let html = '';
            html += `<div> NO : ${result.no} </div>`
            html += `<div> ID : ${result.id} </div>`
            html += `<div> Name : ${result.name} </div>`
            html += `<div> Phone : ${result.phone} </div>`
            html += `<div> Email : ${result.email} </div>`

            // 3. 출력
            myInfoBox.innerHTML = html;

        }
    });
}


