function findID(){
    let findID = document.getElementById('findID');
    let findPW = document.getElementById('findPW');
    findID.classList.add('active');
    findID.style.backgroundColor = "red";
    findID.style.color = "white";
    findPW.classList.remove('active');
    findPW.style.color = "black";
    findPW.style.backgroundColor = "transparent";
    document.getElementById('findIDForm').style.display = "block";
    document.getElementById('findPWForm').style.display = "none";
}
function findPW(){
    let findID = document.getElementById('findID');
    let findPW = document.getElementById('findPW');
    findPW.classList.add('active');
    findPW.style.backgroundColor = "red";
    findPW.style.color = "white";
    findID.classList.remove('active');
    findID.style.color = "black";
    findID.style.backgroundColor = "transparent";
    document.getElementById('findPWForm').style.display = "block";
    document.getElementById('findIDForm').style.display = "none";
}

function nicknameChange(){
    let nickname = document.getElementById("userNickname").value.trim();
    let param = {nickname: nickname};
    let nicknameCheck = document.getElementById("nickNameCheck");
    let nickNameFlag = document.getElementById("nickNameFlag");

    if(!nickname){
        nicknameCheck.style.color = "red";
        nicknameCheck.innerHTML = "필수 입력 사항입니다.";
        nickNameFlag.value = "false";
    }else {
        $.ajax({
            type: "POST",
            url: "/api/nicknameChange",
            contentType: 'application/json',
            async: false,
            dataType: "json",
            data: JSON.stringify(param),
            success: function (result) {
                if (result) {
                    nicknameCheck.style.color = "red";
                    nicknameCheck.innerHTML = "이미 존재하는 닉네임입니다.";
                    nickNameFlag.value = "false";
                } else {
                    nicknameCheck.style.color = "green";
                    nicknameCheck.innerHTML = "사용 가능한 닉네임입니다.";
                    nickNameFlag.value = "true";
                }
            },
            error: function (error) {
                console.log("데이터 검색에 실패했습니다.");
                console.log(error);
            }
        });
    }
}

function idChange(){
    let userID = document.getElementById("userID").value.trim();
    let param = {id: userID};
    let idCheck = document.getElementById("idCheck");
    let idFlag = document.getElementById("idFlag");

    if(!userID){
        idCheck.style.color = "red";
        idCheck.innerHTML = "필수 입력 사항입니다.";
        idFlag.value = "false";
    }else {
        $.ajax({
            type: "POST",
            url: "/api/idChange",
            contentType: 'application/json',
            async: false,
            dataType: "json",
            data: JSON.stringify(param),
            success: function (result) {
                if (result) {
                    idCheck.style.color = "red";
                    idCheck.innerHTML = "이미 존재하는 아이디입니다.";
                    idFlag.value = "false";
                } else {
                    idCheck.style.color = "green";
                    idCheck.innerHTML = "사용 가능한 아이디입니다.";
                    idFlag.value = "true";
                }
            },
            error: function (error) {
                console.log("데이터 검색에 실패했습니다.");
                console.log(error);
            }
        });
    }
}

function pwCheck(){
    let pw = document.getElementById("userPW").value;
    let checkUserPW = document.getElementById("checkUserPW").value;
    let pwCheck = document.getElementById("pwCheck");
    let checkPwFlag = document.getElementById("checkPwFlag");

    if(pw != checkUserPW){
        pwCheck.style.color = "red";
        pwCheck.innerHTML = "패스워드와 패스워드 확인이 같지 않습니다.";
        checkPwFlag.value = "false";
    }else{
        pwCheck.style.display = "none";
        checkPwFlag.value = "true";
    }
}

function done() {

    let userName = document.getElementById("userName").value;
    let userGender = document.querySelector('input[name="gender"]:checked');
    let userNickname = document.getElementById("userNickname").value;
    let userBirthday = document.getElementById("userBirthday").value;
    let userEmail = document.getElementById("userEmail").value;
    let userID = document.getElementById("userID").value;
    let userPW = document.getElementById("userPW").value;
    let checkUserPW = document.getElementById("checkUserPW").value;

    let nickNameFlag = document.getElementById("nickNameFlag").value;
    let idFlag = document.getElementById("idFlag").value;
    let checkPwFlag = document.getElementById("checkPwFlag").value;

    if (!userName) {
        document.getElementById("userName").focus();
    } else if (!userGender) {
        document.getElementById("woman").focus();
    } else if (!userNickname || nickNameFlag == "false") {
        document.getElementById("userNickname").focus();
    } else if (!userBirthday) {
        document.getElementById("userBirthday").focus();
    } else if (!userEmail) {
        document.getElementById("userEmail").focus();
    } else if (!userID || idFlag == "false") {
        document.getElementById("userID").focus();
    } else if (!userPW) {
        document.getElementById("userPW").focus();
    } else if (!checkUserPW || checkPwFlag == "false") {
        document.getElementById("checkUserPW").focus();
    }else{
        document.getElementById("registerForm").submit();
    }
}

function login(){
    let id = document.getElementById("id").value;
    let pw = document.getElementById("pw").value;

    let param = {id: id, pw: pw};
    $.ajax({
        type: "POST",
        url: "/api/login",
        contentType: 'application/json',
        async: false,
        dataType: "json",
        data: JSON.stringify(param),
        success: function (result) {
            if (result) {
                alert("바로 홈으로 이동(아직 만들기 전)")
            } else {
                alert("로그인 실패");
            }
        },
        error: function (error) {
            console.log("데이터 검색에 실패했습니다.");
            console.log(error);
        }
    });

}

function findUserId(){
    let userName = document.getElementById("userName").value;
    let userBirthday = document.getElementById("userBirthday").value;
    let param = {name: userName, birthday: userBirthday};

    $.ajax({
        type: "POST",
        url: "/api/findId",
        contentType: 'application/json',
        async: false,
        dataType: "json",
        data: JSON.stringify(param),
        success: function (data) {
            if (data.length == 0) {
                document.getElementById("findUserId").innerText = "회원정보가 존재하지 않습니다.";
            } else {
                console.log(data);
                document.getElementById("findUserId").innerText = "회원 ID는 ";
                data.forEach(function(element, index, array){
                    document.getElementById("findUserId").innerText += "'"+ array[index].id.substr(0,1) +
                        "*".repeat(array[index].id.length - 2) + array[index].id.substr(array[index].id.length - 1) + "'";
                });
                document.getElementById("findUserId").innerText += "입니다.";
            }
            $('#findIdModal').show();
        },
        error: function (error) {
            console.log("데이터 검색에 실패했습니다.");
            console.log(error);
        }
    });
}

function close_pop(){
    $('#findIdModal').hide();
}




