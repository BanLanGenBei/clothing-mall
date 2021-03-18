$(function(){
    // if (!sessionStorage['auth']) {
    //     location.href = 'http://localhost:3000/login'
    // } else {
        document.querySelector('#myprofile').style.display = 'inline-block'
        document.querySelector('.e-header-login').style.display = 'none'
    // }
    $('#collect_products').on('click','#del',function(){
        $.ajax({
            url:'http://localhost:3000/collect/deletecollect',
            headers:{Authorization:sessionStorage.getItem('auth')},
            type:'get',
            data:{
                productid:$(this).next().html()
            },
            dataType:'json',
            success:function(results){

            }
        })
        $(this).parent().remove()
    })
    $('#collect_products').on('click','#pro',function(){
    })
    var Random = Mock.Random
    Random.extend({
        constellation: function(date){
            var pic = ['女士玛丽珍低跟鞋7900.jpg', '破洞效果环保水洗有机牛仔裤6900.jpg', '小鹿贴饰T恤5300.jpg', '饰小猫嵌花海马毛毛衣17200.jpg', '七夕情人节特别款棉布短款连衣裙16200.jpg',
                        '千鸟格印花羊毛真丝迷你裙8500.jpg']
            return this.pick(pic)
        }
    })
    //2.发出Ajax请求，获取接口数据
    $.ajax({
        url: 'http://localhost:3000/collect/tocollect',
        headers:{Authorization:sessionStorage.getItem('auth')},
        type: 'get',
        dataType: 'json',
        success: function (results) {
            data=results.data
            console.log(data)
            var strHTML = ''
            $('.productcount').html(data.length)
            for (var i = 0; i <data.length; i++) {
                strHTML += `<div id="pro" >
                                <img id="pro_size" src="${data[i].picname}" alt=""><br/><br/>
                                <p id="pro_title">gucci${data[i].productName}</p>
                                <p id="price">￥${data[i].productPrice}</p>
                                <div id="del">×</div>
                                <span class="productid">${data[i].productId}</span>
                            </div>`
            }
            $('#collect_products').html(strHTML) 
        }  
    })
})