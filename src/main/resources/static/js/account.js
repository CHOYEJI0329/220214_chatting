function findID(){
    const findID = document.getElementById('findID');
    const findPW = document.getElementById('findPW');
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
    const findID = document.getElementById('findID');
    const findPW = document.getElementById('findPW');
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
    let param = {nickname: document.getElementById("userNickname").value};
    $.ajax({
        type: "POST",
        url: "/api/nicknameChange",
        contentType: 'application/json',
        async: false,
        dataType: "json",
        data: JSON.stringify(param),
        success: function(result) {
            if(result){
                let nicknameCheck = document.getElementById("nickNameCheck");
                nicknameCheck.style.color = "red";
                nicknameCheck.style.fontSize="13px"
                nicknameCheck.style.paddingLeft="20px"
                nicknameCheck.style.paddingTop="2px"
                nicknameCheck.innerHTML = "이미 존재하는 닉네임입니다.";
            }else {
                let nicknameCheck = document.getElementById("nickNameCheck");
                nicknameCheck.style.color = "green";
                nicknameCheck.style.fontSize="13px"
                nicknameCheck.style.paddingLeft="20px"
                nicknameCheck.style.paddingTop="2px"
                nicknameCheck.innerHTML = "사용 가능한 닉네임입니다.";
            }
        },
        error: function (error){
            console.log("데이터 검색에 실패했습니다.");
            console.log(error);
        }
    });
}

