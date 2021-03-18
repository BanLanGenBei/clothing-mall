$(function(){
    $('#exit-text').on('click',function(){
        sessionStorage.removeItem('auth')
        document.querySelector('#myprofile').style.display = 'none'
        document.querySelector('.e-header-login').style.display = 'inline-block'
    })
    $('.spice-nav-menu').on('click',function(){
          location.href='http://localhost:3000/search?search='+$(this).children().text() 
    })
})

