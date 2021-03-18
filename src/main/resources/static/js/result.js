
$(function () {
    // if(!sessionStorage['nowuser']){
    //     location.href = 'login.html'
    // }
    $("#myheader").load("header.html")
    $("#myfooter").load("footer.html")
})



function openDialog(){
    document.getElementById('light').style.display='block';
    document.getElementById('fade').style.display='block'
}
function closeDialog(){
    document.getElementById('light').style.display='none';
    document.getElementById('fade').style.display='none'
}

