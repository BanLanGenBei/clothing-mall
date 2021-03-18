$(function () {
    $('#opensearch').on('click', function () {
        $(this).css('display', 'none')
        $('#closesearch').css('display', 'inline')
        $('.spice-sub-menu').css('display', 'block')
    })
    $('#closesearch').on('click', function () {
        $(this).css('display', 'none')
        $('#opensearch').css('display', 'inline')
        $('.spice-sub-menu').css('display', 'none')
    })
    window.onscroll = function () {
        if (document.documentElement.scrollTop > 0) {
            document.querySelector('.spice-header .spice-container.header-transparent').style = 'background-color:#1b1b1b'
        } else if (document.documentElement.scrollTop == 0) {
            document.querySelector('.spice-header .spice-container.header-transparent').style = 'background: linear-gradient(180deg,#23201e 0,rgba(35,32,30,0))'
            document.querySelector('.spice-header .spice-container.header-transparent').style = 'transition: all .2s ease-out;'
        }
    };
    $('#phone').click(function () {
        $('#left_phone').show()
        $('#left_pwd').hide()
    });
    $('#pwd').click(function () {
        $('#left_pwd').show()
        $('#left_phone').hide()
    });


//注册
    $("#sub").click(function () {
        $.ajax({
            url: 'http://localhost:3000/clothing-mall/login/registeredUser',//等同于表单的action属性
            type: 'post',
            data: {
                email: $('#emailRegister').val(),
                // 验证码
                mailVerification :$('#userCode').val(),
                password: $('#userPwd').val()
            },
            dataType: 'json', //返回的数据是JSON格式
            success: function (results) {
                console(results);
                var data = results.data
                console(data);
                if (results.message == 'USER_ALREADY_EXISTS') {
                    $('#errUserName').html('邮箱已经被使用！')
                }else if(results.message == 'MAILBOX_FORMAT_ERROR'){
                    $('#errUserName').html('邮箱格式不正确！')
                }else if(results.message == 'PARAM_ERROR'){
                    $('#errUserName').html('邮箱不能为空!')
                }else if(results.message == 'VERIFICATION_CODE_IS_EMPTY'){
                    $('#errUserPwd').html('验证码不能为空!')
                }else if(results.message == 'VERIFICATION_CODE_IS_ERROR'){
                    $('#errUserPwd').html('验证码错误!')
                }else if(results.message == 'PASSWORD_IS_EMPTY'){
                    $('#errUserPwd1').html('密码不能为空!')
                } else {
                    window.location.href = "login.html"
                    $('#errUserName').html('注册成功')
                }
            }
        })
    });
})

//注册发送验证码
$("#code").click(function (){
    $.ajax({
        url: 'http://localhost:3000/clothing-mall/login/sendMailVerification',//等同于表单的action属性
        type: 'post',
        data: {
            email: $('#emailRegister').val(),
        },
        dataType: 'json', //返回的数据是JSON格式
        success: function (results) {
            location.href = 'http://localhost:3000/clothing-mall/pageJump/information'

        }
    })


});

//登录
function login() {
    $.ajax({
        url: 'http://localhost:3000/clothing-mall/login/login',//等同于表单的action属性
        type: 'post',
        data: {
            email: $('#email').val(),
            password: $('#password').val()
        },
        dataType: 'json', //返回的数据是JSON格式
        success: function (results) {
            console.log(results);
            if (results.code === "-9") {
                alert("邮箱不能为空!")
            } else if (results.code === "-5") {
                alert("邮箱格式不正确!")
            }else if (results.code === "-8") {
                alert("密码不能为空!")
            }else if (results.code === "1002") {
                alert("用户不存在,请先注册!")
            }else if (results.code === "1003") {
                alert("密码错误,请重新输入密码!")
            }else{
                sessionStorage.setItem('auth',results.message);
                location.href = 'http://localhost:3000/clothing-mall/pageJump/information'
            }
        }
    })
}
function checkEmail() {
    $.ajax({
        url: 'http://localhost:3000/clothing-mall/login/checkEmail',//等同于表单的action属性
        type: 'get',
        data: {
            //将用户输入的邮箱传到后端
            email: $('#emailRegister').val(),
        },
        dataType: 'json', //返回的数据是JSON格式
        success: function (results) {
            console.log(results);
            if (results.message == 'USER_ALREADY_EXISTS') {
                $('#errUserName').html('邮箱已经被使用！')
            }else if(results.message == 'MAILBOX_FORMAT_ERROR'){
                $('#errUserName').html('邮箱格式不正确！')
            }else if(results.message == 'PARAM_ERROR'){
                $('#errUserName').html('邮箱不能为空!')
            } else {
                $('#errUserName').html('')
            }
        }
    })
}
