var allCitys = [['南京', '苏州', '常州'], ['哈尔滨', '牡丹江', '大庆'], ['开封', '洛阳', '郑州']]
window.onload = function () {
    if (!sessionStorage['auth']) {
        location.href = 'http://localhost:3000/login'
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
    var p1 = document.querySelector('#p1')
    p1.onclick = function () {
        $("#myModal").modal({
            backdrop: "static"
        })
    }
    var domywork2 = document.querySelector('#domywork2')
    domywork2.onclick = function () {

    }
    var domywork1 = document.querySelector('#domywork1')
    domywork1.onclick = function () {
        var main = document.getElementsByTagName('main')[0]
        var div1 = document.createElement('div')
        div1.className = 'd2'
        var div2 = document.createElement('div')
        div2.className = 'd3'
        div2.innerHTML = '<p class="p2">邵先生</p><p class="p2">江苏省 南京市</p><p class="p2">朝阳路1号</p><p class="p2">+86 15004598856</p><p class="p2">默认地址</p><hr><p class="pf">编辑</p><p class="pr">删除</p>'
        main.appendChild(div1)
        div1.appendChild(div2)
    }

    var selectSheng = document.querySelector('#selectSheng')
    var selectShi = document.querySelector('#selectShi')
    selectSheng.onchange = function () {
        selectShi.options.length = 0
        var index = this.selectedIndex
        var citys = allCitys[index]
        for (var i = 0; i < citys.length; i++) {
            var city = new Option(citys[i], citys[i])
            selectShi.options.add(city)
        }
    }
    var sS = document.querySelector('#sS')
    var sShi = document.querySelector('#sShi')
    sS.onchange = function () {
        sShi.options.length = 0
        var index = this.selectedIndex
        var citys = allCitys[index]
        for (var i = 0; i < citys.length; i++) {
            var city = new Option(citys[i], citys[i])
            sShi.options.add(city)
        }
    }
}
$(function () {
    $('main').on('click', ".pr", function () {
        $(this).parent().parent().remove()
    })
    $('main').on('click', '.pf', function () {
        $("#myModal2").modal({
            backdrop: "static"
        })
    })
})