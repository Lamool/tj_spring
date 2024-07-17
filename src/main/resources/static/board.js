boardReadAll()
// 1. 글추가
function boardWrite(){
    let btitle = document.querySelector("#titleInput").value
    let bcontent = document.querySelector("#contentInput").value
    let bpassword = document.querySelector("#passwordInput").value
    $.ajax({
        url : "/day08/board",
        method : "POST",
        data : {btitle : btitle, bcontent : bcontent, bpassword : bpassword},
        success : response => {
            if (response == true){
                console.log("boardWrite Success")
                document.querySelector("#titleInput").value = ""
                document.querySelector("#contentInput").value = ""
                document.querySelector("#passwordInput").value = ""
                boardReadAll()
            } else {
                console.log("boardWrite Failed")
            }
        }
    })
}
// 2. 전체글출력
function boardReadAll(){
    $.ajax({
        url : "/day08/board",
        method : "GET",
        success : function resp(response){
            console.log("boardReadAll Success")
            let html = ""
            response.forEach(dto => {
                html += `<tr> <td>${dto.bdate}</td> <td onclick="boardView(${dto.bno})">${dto.btitle}</td> <td id=bview${dto.bno}>${dto.bview}</td> </tr>`
            })
            document.querySelector("#tableBody").innerHTML = html
        }
    })
}

// 2-1. 상세글출력
function boardView(bno){
    console.log("boardViewing")
    let bview = document.querySelector(`#bview${bno}`).innerHTML
    console.log("bview : ", bview)
    $.ajax({
        url : "/day08/board/view",
        method : "GET",
        data : {bno : bno, bview : bview},
        success : boardDto => {
            let html = `<h3>상세 페이지</h3>
                        <div>${boardDto.btitle}</div>
                        <div>
                            <span>${boardDto.bview}</span>
                            <span>${boardDto.bdate}</span>
                        </div>
                        <div>${boardDto.bcontent}</div>
                        <button type="button" onclick='boardEdit(${boardDto.bno})' >수정</button>
                        <button type="button" onclick='boardDelete(${boardDto.bno})' >삭제</button>`
            document.querySelector("#viewPage").innerHTML = html;
            boardReadAll()
        }
    })
}
// 3. 글 수정
function boardEdit(bno){
    let password = prompt("비밀번호를 입력해주세요 : ")
    $.ajax({
        url : "/day08/board/pass",
        method : "GET",
        data : {bno : bno, bpassword : password},
        success : result =>{
            console.log("result = " + result)
            if (result){
                let newTitle = prompt("새로운 제목을 입력해주세요 : ")
                let newBoard = prompt("새로운 내용을 입력해주세요 : ")
                $.ajax({
                    url : "/day08/board",
                    method : "PUT",
                    data : {bno : bno, btitle : newTitle, bcontent : newBoard},
                    success : result => {
                        if (result){
                            console.log("BoardEdit Success")
                        }
                        else {console.log("BoardEdit Failed")
                        }
                        boardView(bno);
                    }
                })
            }
        }
    })
}
// 4. 글삭제
function boardDelete(bno){
    let password = prompt("비밀번호를 입력해주세요 : ")
    $.ajax({
        url : "/day08/board/pass",
        method : "GET",
        data : {bno : bno, bpassword : password},
        success : result =>{
            if (result){
                $.ajax({
                    url : "/day08/board",
                    method : "DELETE",
                    data : {bno : bno},
                    success : result => {
                        if (result){
                            console.log("BoardDeletion Success")
                            boardReadAll()
                            document.querySelector("#viewPage").innerHTML =""
                        }
                        else {console.log("BoardDeletion Failed")}
                        
                    }
                })
            }
        }
    })
}

/*
// 3. 글수정
function boardEdit(bno){
    if (boardCheckPassWord(bno) == true) {
        let newTitle = prompt("새로운 제목을 입력해주세요 : ")
        let newBoard = prompt("새로운 내용을 입력해주세요 : ")
        $.ajax({
            url : "/day08/board",
            method : "PUT",
            data : {bno : bno, btitle : newTitle, bcontent : newBoard},
            success : result => {
                if (result){
                    console.log("BoardEdit Success")
                }
                else {console.log("BoardEdit Failed")
                }
                boardView(bno);
            }
        })
    }
}
// 4. 글삭제
function boardDelete(bno){
    console.log("PasswordCheck : " +boardCheckPassWord(bno))
    if (boardCheckPassWord(bno) == true) {
        $.ajax({
            url : "/day08/board",
            method : "PUT",
            data : {bno : bno, bcontent : newboard},
            success : result => {
                if (result){
                    console.log("BoardDeletion Success")
                    boardReadAll()
                }
                else {console.log("BoardDeletion Failed")}
                
            }
        })
    }
}
// 5. 비밀번호확인
function boardCheckPassWord(bno){
    let bpassword = prompt("비밀번호를 입력해주세요 : ")
    $.ajax({
        url : "/day08/board/pass",
        method : "GET",
        data : {bno : bno, bpassword : bpassword},
        success : result =>{
            console.log("result : " + result)
            return result
        }
    })
}*/

/*console.log(date)
console.log(date.getFullYear());
console.log(date.getMonth());
console.log(date.getDate());

console.log(date.getHours());
console.log(date.getMinutes());
console.log(date.getSeconds());

let boardList = [];  // '제목,내용,비밀번호,날짜,조회수'
_allread();
// 1.
function _create(){
    let title = document.querySelector("#titleInput").value;
    let content = document.querySelector("#contentInput").value;
    let password = document.querySelector("#passwordInput").value;
    let date = new Date(); // 현재 날짜,시간 반환해주는 클래스
    console.log(date.getMonth())
    let currentYear = date.getFullYear();
    let currentMonth = date.getMonth()+1 <=10 ? '0' + (date.getMonth()+1) : date.getMonth()+1 // 월 줄맞춤
    let currentDay = date.getDay();
    console.log(currentMonth)
    date = `${currentYear}-${currentMonth}-${currentDay}`
    let view = 0;

    let board = `${title},${content},${password},${date},${view}`; console.log(board);
    document.querySelector("#titleInput").value = '';
    document.querySelector("#contentInput").value = '';
    document.querySelector("#passwordInput").value = '';
    alert("등록 성공");
    _allread()
}

function _allread(){ // 1. 페이지가 열렸을 때 2. 데이터에 변화가 생겼을 때 (HTML실행 -> JS실행)
    // 1. 어디
    let tableBody = document.querySelector("#tableBody")
    let html = ""
    for (let i = 0; i < boardList.length; i++){
        let board = boardList[i]; console.log (board);
        let boardArray = board.split(','); console.log(boardArray)
        console.log(boardArray[0]); console.log(boardArray[3] ); console.log( boardArray[4]);
        html += `<tr> <td> ${i} </td><td onclick="_read(${i})">${boardArray[0]}</td><td>${boardArray[1]}</td><td>${boardArray[3]}</td> <td>${boardArray[4]}</td></tr>`
    }

    tableBody.innerHTML = html;
}

function _read(x){
    let boardArray = boardList[x].split(",");
    let viewPage = document.querySelector('#viewPage');
    let html = `<h3>상세 페이지</h3>
                <div>${boardArray[0]}</div>
                <div>
                    <span>${boardArray[4]}</span>
                    <span>${boardArray[3]}</span>
                </div>
                <div>${boardArray[1]}</div>
                <button type="button" onclick='_update(${x})' >수정</button>
                <button type="button" onclick='_delete(${x})' >삭제</button>`
    viewPage.innerHTML = html;
    _allread()

}

function _update(x){
    if (checkPass(x) == false) return;
    let board = boardList[x].split(',')
    let uptdTitle = prompt('새 제목');
    let uptdContent = prompt('새 내용');
    let uptdBoard = `${uptdTitle},${uptdContent},${board[2]},${board[3]},${board[4]}`;
    boardList[x] = uptdBoard;
    _allread()
    _read(x)
}

function _delete(x){
    if (checkPass(x) == false) return;
    boardList.splice(x, 1);
    _allread();
    document.querySelector('#viewPage').innerHTML = '';
}

function checkPass(x){
    userPass = prompt('비밀번호를 입력하세요')
    if (userPass != boardList[x].split(',')[2]) {alert('비밀번호가 틀립니다'); return false;}
    return true;
}
*/