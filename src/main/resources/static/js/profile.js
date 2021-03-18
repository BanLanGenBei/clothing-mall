var year
var month
var day
var currentyear = 1920, currentmonth = 1
var monthday = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]
var allCitys=['北京市','上海市','江苏省','河南省','黑龙江省','吉林省','辽宁省'] 
function setDay() {
    if (currentyear % 4 == 0 && currentyear % 100 != 0 || currentyear % 400 == 0) {
        monthday[1] = 29
    } else {
        monthday[1] = 28
    }
    day.options.length = 0
    var days = monthday[currentmonth - 1]
    for (var i = 1; i <= days; i++) {
        day.options.add(new Option(i, i))
    }
}
window.onload=function(){
    if(!sessionStorage['auth']){
        location.href='http://localhost:3000/login'
    }else{
        document.querySelector('#myprofile').style.display = 'inline-block'
        document.querySelector('.e-header-login').style.display = 'none'
    }
    $('#opensearch').on('click',function(){
        $(this).css('display','none')
        $('#closesearch').css('display','inline')
        $('.spice-sub-menu').css('display','block')
    })
    $('#closesearch').on('click',function(){
        $(this).css('display','none')
        $('#opensearch').css('display','inline')
        $('.spice-sub-menu').css('display','none')
    })
    year = document.getElementById("selyear")
    month = document.getElementById("selMonth")
    day = document.getElementById("selDay")
    for (var i = 1920; i < 2021; i++) {
        year.options.add(new Option(i, i))
    }
    
    for (var i = 1; i < 13; i++) {
        month.options.add(new Option(i, i))
    }
    for (var i = 1; i <= 31; i++) {
        day.options.add(new Option(i, i))
    }
    year.onchange = function () {
        currentyear = year.options[year.selectedIndex].text
        setDay()
    }
    month.onchange = function () {
        currentmonth = month.options[month.selectedIndex].text
        setDay()
    }
    var selectG=document.querySelector('#selectG')
    var selectS=document.querySelector('#selectS')
    selectG.onchange=function(){
       for(var i=0;i<allCitys.length ;i++){
          var city=new Option(allCitys[i])
          selectS.options.add(city)
       }
    }
var modm=document.querySelector('#modm')
    modm.onclick=function(){
        $("#myModal").modal({
            backdrop: "static"
        })
    }
var modp=document.querySelector('#modpass')
    modp.onclick=function(){
        $("#myModal2").modal({
            backdrop: "static"
        })
    }
var domywork1=document.querySelector('#domywork1')
    domywork1.onclick=function(){
        var modpt=document.querySelector('#modpt')
        var modp=document.querySelector('#modp')
        //modp.value=modpt.value
        var nub1 = /^[1][3,4,5,7,8][0-9]{9}$/;
        if (nub1.test(modpt.value) == false) {
            document.querySelector('#nubPwd').innerHTML = ' 手机号码格式不正确！！'
            return false;
        }else{
          modp.value=modpt.value
        return true;  
        }
        
    }
var sp3=document.querySelector('#sp3')
    sp3.onclick=function(){
        $("#myModal3").modal({
            backdrop: "static"
        })
    }
}
$(function(){
    $('#btn2').click(function () {
        $(window).attr('location', 'http://localhost:3000/information')
    })
})

window.onscroll=function(){
    if(document.documentElement.scrollTop>0){
       document.querySelector('.spice-header .spice-container.header-transparent').style='background-color:#1b1b1b'
    }else if(document.documentElement.scrollTop==0){
        document.querySelector('.spice-header .spice-container.header-transparent').style='background: linear-gradient(180deg,#23201e 0,rgba(35,32,30,0))'
        document.querySelector('.spice-header .spice-container.header-transparent').style='transition: all .2s ease-out;'
    }
}