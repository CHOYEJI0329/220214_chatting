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