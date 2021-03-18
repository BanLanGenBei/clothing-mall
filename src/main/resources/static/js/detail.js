window.onload = function () {
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
    var getkey = decodeURI(location.search).slice(1).split('=')[1]
    var thisclass = 'search-list-div-i'
    if (sessionStorage['auth']) {
        document.querySelector('#myprofile').style.display = 'inline-block'
        document.querySelector('.e-header-login').style.display = 'none'
        $.ajax({
            url: 'http://localhost:3000/search/iscollect',
            headers: { Authorization: sessionStorage.getItem('auth') },
            type: 'get',
            async: false,
            data: {
                productid: getkey
            },
            dataType: 'json',
            success: function (res) {
                if (res.data == 1) {
                    thisclass = 'search-list-div-i-collect'
                }
            }
        })
    }
    $.ajax({
        url: 'http://localhost:3000/detail/thisdetail',
        type: 'get',
        data: {
            productkey: getkey
        },
        dataType: 'json',
        success: function (res) {
            var strHtml = `
            <div id="left" class="col-lg-7 col-xs-12">
            <div id="breadcrumb">
                <a href="" id="wcloth">女士服饰</a>&nbsp;<span>|</span>&nbsp;
                <span id="sweater">毛衣&开衫</span>
            </div>
            <div id="picture">
                    <img id="pic" src="images/饰小猫嵌花海马毛毛衣17200.jpg" alt="">
                    <img id="pic" src="images/海马毛毛衣1.jpg" alt="">
                    <img id="pic" src="images/海马毛毛衣2.jpg" alt="">
                    <img id="pic" src="images/海马毛毛衣3.jpg" alt="">
                    <img id="pic" src="images/海马毛毛衣4.jpg" alt="">
                    <img id="pic" src="images/海马毛毛衣5.jpg" alt="">
            </div>
        </div>
        <div id="floor">
            <ul id="circle">
                <li><i></i></li>
                <li><i></i></li>
                <li><i></i></li>
                <li><i></i></li>
                <li><i></i></li>
                <li><i></i></li>
            </ul>
        </div>
        <div id="right" class="col-lg-5">
            <div id="product_detail">
                <div id="pro_title">
                    <p id="protit">${res.data[0].productName}</p>
                    <i id="icon-i" class="${thisclass}"></i>
                    <p>￥17200</p>
                    <br/>
                    <hr>
                </div>
                <p id="pnumber">款号636104 XZBLZ 4896</p>
                <br/>
                <div id="pColor">浅蓝色</div>
                <br/>
                <div id="pSize">
                    <select name="" id="pSize">
                        <option value="">请选择合适的尺码</option>
                        <option value="">XS</option>
                        <option value="">S</option>
                        <option value="">M</option>
                        <option value="">XL</option>
                    </select>
                </div>
                 &nbsp; &nbsp; &nbsp;<a class="spice-size-table e-size-table" href="" ><u>尺码表</u></a>
                <br/><br/>
                <button id="p_btn">加入购物袋</button>
            </div>
        </div>`
            $('#detail-main').html(strHtml)
        }
    })
    var allDiv = document.querySelectorAll("#pic");
    var allNavLi = document.querySelectorAll("#circle li");
    function navLiPop() {//滚动到相应位置
        for (var i = 0; i < allNavLi.length; i++) {
            allNavLi[i].index = i;
            allNavLi[i].onclick = function () {
                for (var j = 0; j < allNavLi.length; j++) {
                    window.scrollTo({
                        top: allDiv[this.index].offsetTop,
                        behavior: "smooth"
                    });
                }
            }
        }
    }
    navLiPop();
    $('#detail-main').on('click', '#icon-i', function () {

        var collectflag = ''
        if ($(this).hasClass('search-list-div-i')) {
            collectflag = 'tocollect'
            this.className = 'search-list-div-i-collect'
        } else {
            collectflag = 'todelete'
            this.className = 'search-list-div-i'
        }
        if (!sessionStorage['auth']) {
            location.href = 'http://localhost:3000/login'
        }
        else {
            $.ajax({
                url: 'http://localhost:3000/search/' + collectflag,
                headers: { Authorization: sessionStorage.getItem('auth') },
                type: 'get',
                data: {
                    productid: getkey
                },
                dataType: 'json',
                success: function (res) {
                }
            })
        }
    })
    $('#detail-main').on('click','#p_btn',function(){
        $.ajax({
            url:'http://localhost:3000/detail/tocar',
            headers: { Authorization: sessionStorage.getItem('auth') },
            type:'get',
            data:{
                productid: getkey
            },
            dataType:'json',
            success:function(res){
             console.log(res)   
            }
        })
    })
}
