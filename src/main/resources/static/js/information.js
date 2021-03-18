window.onload=function(){
    if(!sessionStorage['auth']){
        location.href='http://localhost:3000/clothing-mall/pageJump/login'
    }else{
        document.querySelector('#myprofile').style.display = 'inline-block'
        document.querySelector('.e-header-login').style.display = 'none'
    }
    var porder=document.querySelector('#porder')
    porder.onclick=function(){
        window.location.href="http://localhost:3000/order"
 }
    var pprofile=document.querySelector('#pprofile')
    pprofile.onclick=function(){
        window.location.href="http://localhost:3000/profile"
 }
    var paddress=document.querySelector('#paddress')
    paddress.onclick=function(){
        window.location.href="http://localhost:3000/address"
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
window.onscroll=function(){
    if(document.documentElement.scrollTop>0){
       document.querySelector('.spice-header .spice-container.header-transparent').style='background-color:#1b1b1b'
    }else if(document.documentElement.scrollTop==0){
        document.querySelector('.spice-header .spice-container.header-transparent').style='background: linear-gradient(180deg,#23201e 0,rgba(35,32,30,0))'
        document.querySelector('.spice-header .spice-container.header-transparent').style='transition: all .2s ease-out;'
    }
}
}