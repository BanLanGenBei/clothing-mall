$(function () {
   
    if (!sessionStorage['auth']) {
        //如果没有登录跳转至登录页面
        location.href = 'http://localhost:3000/clothing-mall/pageJump/login'
    } else {
        document.querySelector('#myprofile').style.display = 'inline-block'
        document.querySelector('.e-header-login').style.display = 'none'
    }

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
    }
    //跳转到结算页面
    $('.right').on('click', '.btnPay', function () {
        $(window).attr('location', 'pay.html')
    })

    $('.right').on('click', '.goBuy', function () {
        $(window).attr('location', 'index.html')
    })
    //全选按钮
    $('.checkall').click(function () {
        var $check = $('.check')
        var num = 0
        for (var i = 0; i < $check.length; i++) {
            $check[i].checked = this.checked
            if ($check[i].checked) {
                num++
            }
            $check[i].onclick = function () {
                if (!this.checked) {
                    $('.checkall').prop('checked', 'false')
                }
            }
        }
        mytotal()
        $('.number2').html(num)
    })

    function mytotal() {
        var num = 0
        var total = 0
        for (let i = 0; i < $('.check').length; i++) {
            if ($('.check').eq(i).prop('checked')) {
                var count = $('.check').eq(i).parent().next().next().next().children(':first').children(':first').next().val()
                var price = $('.check').eq(i).parent().next().next().next().next().children(':first').children(':first').children(':first').html()
                num++
                total = total + count * price
            }
        }
        $('.rt').html('￥' + total.toFixed(2))
        $('.rtt').html('￥' + total.toFixed(2))
        $('.number2').html(num)
    }
    //统计选中的数量
    $('.left').on('click', "[type='checkbox']", function () {
        mytotal()
    })



    //增加商品的数量
    $('.left').on('click', '.add', function () {
        var count = parseInt($(this).prev().val())
        count++
        var danjia = parseFloat($(this).parent().parent().next().children(":first").children(":first").children(":first").html())
        var allprice = (count * danjia).toFixed(2)
        $(this).prev().val(count)
        $('.rt').html((parseFloat(danjia) + parseFloat($('.rt').html())).toFixed(2))
        $('.rtt').html((parseFloat(danjia) + parseFloat($('.rtt').html())).toFixed(2))
        mytotal()

    })

    //减少商品的数量
    $('.left').on('click', '.sub', function () {
        var count = parseInt($(this).next().val())
        count--
        if (count >= 1) {
            $(this).next().val(count)
            var danjia = parseFloat($(this).parent().parent().next().children(":first").children(":first").children(":first").html())
            var allprice = (count * danjia).toFixed(2)
            $(this).prev().val(count)
            $('.rt').html((parseFloat($('.rt').html()) - parseFloat(danjia)).toFixed(2))
            $('.rtt').html((parseFloat($('.rtt').html()) - parseFloat(danjia)).toFixed(2))
        }
        else {
            $(this).next().val(1)
        }
        mytotal()

    })

    //删除该行
    $('.left').on('click', '.del', function () {
    
        var current = $('.rt').html()
        var count = parseInt($(this).parent().parent().parent().next().children(':first').children(':first').next().val())
        var num = 0
        var $check = $('.check')

        for (var i = 0; i < $check.length; i++) {
            if ($check[i].checked) {
              
                $.ajax({
                    url: 'http://localhost:3000/car/deletecar',
                    headers:{Authorization:sessionStorage.getItem('auth')},
                    data:{
                        productid:$(this).next().html()
                    },
                    type: 'get',
                    dataType: 'json',
                    success:function(result){
        
                    }
                })
                num++
                // $check[i].parentNode.parentNode.parentNode.parentNode.removeChild($check[i].parentNode.parentNode.parentNode)
                // $check[i].parentNode.parentNode.parentNode.removeChild($check[i].parentNode.parentNode)
                $(this).parent().parent().parent().parent().remove()
                $('.rt').html((current - parseFloat($(this).parent().parent().parent().next().next().children(':first').children(':first').children(':first').html()) * count).toFixed(2))
                $('.rtt').html((current - parseFloat($(this).parent().parent().parent().next().next().children(':first').children(':first').children(':first').html()) * count).toFixed(2))
            }
           
        }
        mytotal()
        $('.number2').html(0)
    })



    //随机生成数据
    var Random = Mock.Random
    Random.extend({
        constellation: function (date) {
            var constellations = ['clothes1.jpg', 'clothes2.jpg', 'list-2-470X470.png', 'list-3-316X316.png', '七夕情人节特别款棉布短款连衣裙16200.jpg']
            return this.pick(constellations)
        }
    })
    //2.发出Ajax请求，获取接口数据
    $.ajax({
        url: 'http://localhost:3000/car/mycar',
        headers:{Authorization:sessionStorage.getItem('auth')},
        type: 'get',
        dataType: 'json',
        success:function(data){
            console.log(data)
            var strHTML1 = ''
            var strHTML2 = ''
            var sum = 0
            for (var i = 0; i < data.data.length; i++) {
                strHTML1 +=
                    `<table>
                    <tr>
                        <td>
                            <input type="checkbox" class="check">
                        </td>
                        <td>
                        <img src="${data.data[i].picname}" >
                        </td>
                        <td>
                            <div class="detail">
                                <div class="detail1">${data.data[i].productName}</div>
                                <div class="detail2">款号#6370911000G9022</div>
                                <div class="detail2">款式:白色皮革</div>
                                <div class="detail3">有货</div>
                                <div class="detail4">预计发货后2-4个工作日送达</div>
                                <div>
                                    <li>加入心愿单</li>
                                    <li class="del">删除</li>
                                    <span class="productid">${data.data[i].productId}</span>
                                </div>
                            </div>
                        </td>
                        <td>
                            <div class="num">
                                <button class="sub">-</button>
                                <input type="text" name="" class="text1" value="1" size=5 disabled>
                                <button class="add">+</button>
                            </div>
                        </td>
                        <td>
                            <div class="price"><p>￥<span>${data.data[i].productPrice}</span></p></div>
                        </td>
                    </tr>
                    
                   
                    
                </table>`

                sum = sum + data.data[i].productPrice
            }
         
                strHTML2 = ` 
                

                `
            $('.left').html(strHTML1)
            $('.right').html(strHTML2)
            $('.rt').html('￥' + 0.00.toFixed(2))
            $('.rtt').html('￥' + 0.00.toFixed(2))
        }
    })
    $.ajax({
        url: 'http://localhost:5500/api/getAllUsers',
        type: 'get',
        dataType: 'json',
        success: function (data) {

        },
        error: function () {
            console.log('请求失败！')
        }
    })
})
