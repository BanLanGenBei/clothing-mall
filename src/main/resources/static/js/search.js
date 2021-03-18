$(function () {
    var getkey = decodeURI(location.search).slice(1).split('=')[1]
    if (sessionStorage['auth']) {
        document.querySelector('#myprofile').style.display = 'inline-block'
        document.querySelector('.e-header-login').style.display = 'none'
    }
    $.ajax({
        url: 'http://localhost:3000/clothing-mall/pageJump/search/tosearch',
        type: 'get',
        data: {
            searchkey: getkey
        },
        dataType: 'json',
        success: function (res) {
            console.log(res)
            var strHtml = ``
            document.querySelector('.search-result-text p').innerHTML = `找到${res.data.length}个结果`;
            document.querySelector('.search-result-text h3').innerHTML = `"${getkey}"`;
           document.querySelector('.search-nav-left-num')  .innerHTML=`(${res.data.length})`;
            for (let i = 0; i < res.data.length; i++) {
            var thisclass='search-list-div-i'
                  if(sessionStorage['auth']){
                    $.ajax({
                        url: 'http://localhost:3000/clothing-mall/pageJump/search/iscollect',
                        headers:{Authorization:sessionStorage.getItem('auth')},
                        type:'get',
                        async:false,
                        data:{
                            productid:res.data[i].productId
                        },
                        dataType:'json',
                        success:function(res){
                            if(res.data==1){
                                thisclass='search-list-div-i-collect'
                            }
                        }
                    })
                }
                strHtml += `        <div class="search-list-div">
                                    <i class="${thisclass}"></i>
                                    <span class="productid">${res.data[i].productId}</span>
                                    <a class=" search-list-a">
                                        <div class="search-list-a-div">
                                            <img alt="双面GG马海毛羊毛混纺开衫" class=" "
                                           sizes="(max-width: 767px) 158px, (max-width: 1023px) 235px, 470px"
                                
                                           alt="双面GG马海毛羊毛混纺开衫" 
                                           srcset='${res.data[i].picname} 158w,
                                           ${res.data[i].picname} 316w,
                                           ${res.data[i].picname} 540w,
                                           ${res.data[i].picname} 235w,
                                           ${res.data[i].picname} 470w' 
                                                      src="${res.data[i].picname}" />
                                                      
                                        </div>
                                    </a>
                                    </div>`
            }
            $('.search-list').html(strHtml) 

        }
    })
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
    $('.search-list').on('click', 'img', function () {
         location.href='http://localhost:3000/clothing-mall/pageJump/detail?product='+$(this).parents().parents().prev().html()
    })
    $('.search-list').on('click', 'i', function () {
      
        var collectflag=''
        if ($(this).hasClass('search-list-div-i')) {
            collectflag='tocollect'
            this.className = 'search-list-div-i-collect'
        } else {
            collectflag='todelete'
            this.className = 'search-list-div-i'
        }
        if(!sessionStorage['auth']){
            location.href='http://localhost:3000/clothing-mall/pageJump/login'
         }
         else{
            $.ajax({
                url: 'http://localhost:3000/clothing-mall/pageJump/search/'+collectflag,
                headers:{Authorization:sessionStorage.getItem('auth')},
                type:'get',
                data:{
                    productid:$(this).next().html()
                },
                dataType:'json',
                success:function(res){
    
                }
            })
         }

    })
})
