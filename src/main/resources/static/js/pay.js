function pay(){
    $.ajax({
        url:'http://localhost:3000/pay',
        type:'get', 
        data:{
            paytotal:$('.allMoney').html()
        },
        dataType:'json',
        success:function(res){
            //跳转到支付宝的支付页面
            location.href=res.data
        }
    })
}

window.onload = function () {
    //跳转页面
    $('.container').on('click', '.return', function () {
        $(window).attr('location', 'car.html')
    })

    $('.ads').on('click','.payMoney',function(){
        $('.PM').css('display','block')
    })

    //点击确定后才可以点击提交订单按钮
    $('.PM').on('click','.btnOK',function(){
       
        $('.subOrder').removeAttr('disabled')
    })

    //下拉列表
    if(!sessionStorage['auth']){
        location.href='http://localhost:3000/login'
    }
    var arrow = document.getElementsByClassName('jlt3')[0]
    arrow.onclick = function () {
        $('.hid').addClass('toblock')
    }
    var lis = document.getElementsByClassName('hidLi')
    var length = lis.length
    for (var i = 0; i < length; i++) {
        lis[i].onclick = function () {
            var PStime = this.innerHTML

            $('.hid').removeClass('toblock')
            $('.wk').html(PStime)

        }
    }

    var arrow = document.getElementsByClassName('jlt31')[0]
    arrow.onclick = function () {
        $('.hid1').addClass('toblock')
    }
    var lis = document.getElementsByClassName('hidLi1')
    var length = lis.length
    for (var i = 0; i < length; i++) {
        lis[i].onclick = function () {
            var PStime = this.innerHTML
            $('.wk2').html(PStime)
            $('.hid1').removeClass('toblock')
        }
    }


    //点击加号伸缩
    $('.img1').click(function () {
        $('.inner1').fadeToggle()
        if($('.img1').attr('src')=='images/add.png'){
            $('.img1').attr('src','images/sub.png')
        }else{
            $('.img1').attr('src','images/add.png')
        }
    })
    $('.img2').click(function () {
        $('.inner2').fadeToggle()
        if($('.img2').attr('src')=='images/add.png'){
            $('.img2').attr('src','images/sub.png')
        }else{
            $('.img2').attr('src','images/add.png')
        }
    })
    $('.img3').click(function () {
        $('.inner3').fadeToggle()
        if($('.img3').attr('src')=='images/add.png'){
            $('.img3').attr('src','images/sub.png')
        }else{
            $('.img3').attr('src','images/add.png')
        }
    })
    $('.img4').click(function () {
        $('.inner4').fadeToggle()
        if($('.img4').attr('src')=='images/add.png'){
            $('.img4').attr('src','images/sub.png')
        }else{
            $('.img4').attr('src','images/add.png')
        }
    })





    //随机生成数据
    var Random = Mock.Random
    Random.extend({
        constellation: function (date) {
            var constellations = ['smallclothes1.jpg', 'smallclothes2.jpg', 'list-2-470X470.png', 'list-3-316X316.png', '七夕情人节特别款棉布短款连衣裙16200.jpg']
            return this.pick(constellations)
        }
    })
     //1.配置Mock，拦截Ajax请求
     Mock.mock('http://localhost:5500/api/getAllUsers', function () {
        var result = []
        for (var i = 0; i < 2; i++) {
            var rndUser = Mock.mock({
                'headPic': Random.constellation('300*300'),
                'experince': Random.cparagraph(1, 2),
                'price': Random.float(2000, 30000, 2, 2),
            })
            result.push(rndUser)
        }
        return result
    })
    //2.发出Ajax请求，获取接口数据
    $.ajax({
        url: 'http://localhost:5500/api/getAllUsers',
        type: 'get',
        dataType: 'json',
        success: function (data) {
            var strHTML = ''
            var sum = 0
            for (var i = 0; i < data.length; i++) {
                strHTML += `
            <table>
                <tr>
                    <td>
                        <img src="images/${data[i].headPic}">
                    </td>
                    <td>
                        <div class="detail">
                            <div class="detail1">花朵蕾丝褶裥衬衫</div>
                            <div class="detail2">款号# 633308 ZAFI4 9200</div>
                            <div class="detail2">款式： 象牙白</div>
                            <div class="detail4">尺码:36</div>
                            <div class="detail5">有货</div>
                        </div>
                    </td>
                    <td>
                        <span class="num">数量:1</span>
                        <span class="price"><p>￥<span>${data[i].price}</span></p></span>
                    </td>
                </tr>
            </table>
           `

                sum = sum + data[i].price
            }
           
            $('.DTadd').html(strHTML)
            $('.allMoney').html(sum.toFixed(2))
         
        },
        error: function () {
            console.log('请求失败！')
        }
    })





}
