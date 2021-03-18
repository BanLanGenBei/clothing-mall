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
    var lis = document.querySelectorAll('.order-ul li')

    for (var i = 0; i < lis.length; i++) {
        lis[i].onclick = function () {
            console.log(lis)
            for (let j = 0; j < lis.length; j++) {
                lis[j].style = 'color:#ccc'
            }
            this.style = 'color:#000'
        }
    }
    var btn=document.getElementById('btn')
    btn.onclick=function(){
        window.location.href="http://localhost:3000/index"
    }
}
$(function () {
    var Random = Mock.Random
    Random.extend({
        constellation: function (date) {
            var constellations = ['clothes1.jpg', 'clothes2.jpg', 'list-2-470X470.png', 'list-3-316X316.png', '七夕情人节特别款棉布短款连衣裙16200.jpg', 'Disney x Gucci双面可穿飞行员夹克28900.jpg', '破洞效果环保水洗有机牛仔裤6900.jpg', '小鹿贴饰T恤5300.jpg']
            return this.pick(constellations)
        }
    })
    //1.配置Mock，拦截Ajax请求
    Mock.mock('http://localhost:5500/api/getAlls', function () {
        var result = []
        for (var i = 0; i <0; i++) {
            var rndUser = Mock.mock({
                'headPic': Random.constellation(),
                'experince': Random.csentence(5, 10),
                'price': Random.float(2000, 30000, 2, 2),
                'created': Random.datetime('yyyy-MM-dd-HH-mm-ss'),
                'address':Random.city(true)
            })
            result.push(rndUser)
        }
        return result
    })
    //2.发出Ajax请求，获取接口数据
    $.ajax({
        url: 'http://localhost:5500/api/getAlls',
        type: 'get',
        dataType: 'json',
        success: function (data) {
            if (data.length<=0) {
                d2.style.display = "block"
                d3.style.display = "none"
            } else {
                d3.style.display = 'block'
                d2.style.display = 'none'
            }
            var strHTML1 = ''
            for (var i = 0; i < data.length; i++) {
                strHTML1 += `
                <div class="row">
                <div class="col-lg-3 col-md-4 col-sm-4 col-xs-5">${data[i].created}</div>
                <div class="col-lg-5 col-md-4 col-sm-4 col-xs-7 p_name">订单编号：#GU202009111462168</div>
                <div class="col-lg-2 col-md-2 col-sm-2 hidden-xs">数量：1</div>
                <div class="col-lg-2 col-md-2 col-sm-2 hidden-xs">￥${data[i].price}</div><br><br>
                <div class="row">
                <div class="col-lg-3 col-md-2 col-sm-3 col-xs-5"><img src="images/${data[i].headPic}" alt="" srcset="" id="img2"></div>
                <div class="col-lg-3 col-md-4 col-sm-3 hidden-xs"><p class="p_name">${data[i].experince}</p><p>款号#5300</p><p>图片款，L码</p></div>
                <div class="col-lg-3 hidden-md hidden-sm hidden-xs p_name">目前位于：${data[i].address}</div>
                <div class="col-lg-1 col-md-2 col-sm-3 col-xs-3">数量：1</div>
                <div class="col-lg-2 col-md-2 col-sm-3 col-xs-4">￥${data[i].price}</div>
            </div><hr>`
            }
            $('.d4').html(strHTML1)
        },
        error: function () {
            console.log('请求失败！')
        }
    })
})

