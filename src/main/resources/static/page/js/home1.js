$(function(){
     $("img.lazy").lazyload({effect: "fadeIn", threshold: 200});
 });
(function() {
    var common = {
        touchBnner: function() {
            var swiper = new Swiper('.swiper-container1', {
              pagination: {
                el: '.swiper-pagination',
              },
            });
            var swiper = new Swiper('.swiper-container2', {
                slidesPerView: 3,
                spaceBetween: 10,
                freeMode: true,
                pagination: {
                  clickable: true,
                },
            });
        },
        ctrlWidth: function() {
            var whdef = 100 / 1920;
            var wH = window.innerHeight;
            var wW = window.innerWidth;
            var rem = wW * whdef;
            $(":root").css({ 'font-size': parseInt(document.body.clientWidth / 19) + 'px' });
            $('html').css('font-size', rem + "px");
            //非IE
            $(window).resize(function() {
                var whdef = 100 / 1920;
                common.setTeam();
                var wH = window.innerHeight;
                var wW = window.innerWidth;
                var rem = wW * whdef;
                $('html').css('font-size', rem + "px");
            });
            //IE兼容
            $(window).resize(function() {
                $(":root").css({ 'font-size': parseInt(document.body.clientWidth / 19) + 'px' });
            });
        },
        isIe8: function() {
            var browser = navigator.appName
            var b_version = navigator.appVersion
            var version = b_version.split(";");
            var trim_Version;
            if (version.length >= 2) {
                trim_Version = version[1].replace(/[ ]/g, "");
            }
            return {
                browser: browser,
                trim_Version: trim_Version
            }
        },
        moveBanner: function(datas, datas1, datas2) {
            for (var i = 0; i < datas.length; i++) {
                var data = datas[i];
                var data1 = datas1[i];
                var data2 = datas2[i];
                $('#slide ul li').eq(i).css('z-index', data['z-index']);
                $('.prodct-img img').eq(i).animate({ opacity: '0' }).css('z-index', data1['z-index']).animate({ opacity: '1' }, 1200);
                $('#slide ul li').eq(i).stop().animate(data, 1200);
                $('.prodct-content').eq(i).css('display', data1['display']);
                $('.product-banner').eq(i).stop().animate(data2);
                $('.product-banner').eq(i).css('backgroundColor', data2['background'])
            }
        },
        nextBanner: function(datas, datas1, datas2) {
            var first = datas.shift();
            var first1 = datas1.shift();
            var first2 = datas2.shift();
            datas.push(first);
            datas1.push(first1);
            datas2.push(first2);
            common.moveBanner(datas, datas1, datas2);
        },
        prevBanner: function(datas, datas1, datas2) {
            var last = datas.pop();
            var last1 = datas1.pop();
            var last2 = datas2.pop();
            datas.unshift(last);
            datas2.unshift(last2);
            datas1.unshift(last1);
            common.moveBanner(datas, datas1, datas2);
        },
        autoPlay: function(datas, datas1, datas2) {
            var timer = setInterval(function() {
                common.nextBanner(datas, datas1, datas2);
            }, 5000);
            $('#slide').on({
                mouseenter: function() {
                    $('.arrow').css('display', 'block');
                    clearInterval(timer);
                },
                mouseleave: function() {
                    $('.arrow').css('display', 'none');
                    clearInterval(timer);
                    timer = setInterval(function() {
                        common.nextBanner(datas, datas1, datas2);
                    }, 5000)
                }
            });
        },
        playAnimateBanner: function() {
            var ie8 = common.isIe8();
            if (ie8.browser == "Microsoft Internet Explorer" && ie8.trim_Version == "MSIE8.0") {
                var datas = [
                    { opacity: 1, width: '900px', height: '600px', top: '5px', left: '-75px' },
                    { opacity: 0.6, width: '350px', height: '350px', top: '200px', left: '-450px' },
                    { opacity: 0.6, width: '350px', height: '350px', top: '200px', left: '850px' },
                    // { opacity: 0, width: '350px', height: '350px', top: '200px', left: '850px' },
                ]
            } else {
                var datas = [
                    { 'z-index': 6, opacity: 1, width: '11.5rem', height: '6rem', top: '0.05rem', left: '-2rem' },
                    { 'z-index': 4, opacity: 0.6, width: '3.5rem', height: '3.5rem', top: '2rem', left: '-5.7rem' },
                    { 'z-index': 4, opacity: 0.6, width: '3.5rem', height: '3.5rem', top: '2rem', left: '9.7rem' },
                    // { 'z-index': 3, opacity: 0, width: '3.5rem', height: '3.5rem', top: '2rem', left: '9.7rem' },
                ]
            }
            var datas1 = [
                { display: 'block', 'z-index': 3 },
                { display: 'none', 'z-index': 3 },
                { display: 'none', 'z-index': 3 },
                // { display: 'none', 'z-index': 3 },
            ]
            var datas2 = [
                { height: '0.05rem', background: '#0b91ca' },
                { height: '0.03rem', background: '#e3e3e3' },
                { height: '0.03rem', background: '#e3e3e3' },
                // { height: '0.03rem', background: '#e3e3e3' },
            ]
            common.moveBanner(datas, datas1, datas2);
            common.autoPlay(datas, datas1, datas2);
            $('.prev').on('click', function() { common.nextBanner(datas, datas1, datas2) })
            $('.next').on('click', function() { common.prevBanner(datas, datas1, datas2) });
        },
        slideTop: function() {
            if (document.addEventListener) {
                document.addEventListener('DOMMouseScroll', common.scrollFunc, false);
            }
            console.log($(window).scrollTop())
            if ($(window).scrollTop() > 0) {
                $('.head').addClass('head-hua')
            }
            window.onmousewheel = document.onmousewheel = common.scrollFunc;
        },
        scrollFunc: function(e) {
            if ($(window).scrollTop() > 0) {
                $('.head').addClass('head-hua')
            }
            if ($(window).scrollTop() == 0) {
                $('.head').removeClass('head-hua')
            }
        },
        addAnimate: function() {
            $('.why-l').addClass('animated fadeInLeft');
            $('.why-analysis img').addClass('animated fadeInRight');

            $('.ltp-platform img').addClass('animated fadeInLeft');
            $('.ltp-r').addClass('animated fadeInRight');

            $('.help-l').addClass('animated fadeInLeft');
            $('.ltp-help img').addClass('animated fadeInRight');
        },

        headChange: function() {
            if (window.location.href.indexOf('nlp') != -1) {
                return
            }
            var _touchStatus = false;
            $(".head .nav ul li a").click(function() {
                var that = this;
                var animateTop = $($(that).attr("href")).offset().top - $('.nav').outerHeight(true) + "px";
                _touchStatus = true;
                $("html, body").animate({ scrollTop: animateTop }, 500);
                if ($($(that).attr("href")).offset().top > 0) {
                    $('.head').addClass('head-hua');
                } else {
                    $('.head').removeClass('head-hua');
                }
                setTimeout(function() {
                    window.location.hash = $(that).attr("href");
                }, 500)

                $('.head .nav ul li a').removeClass("nav-active")
                $(this).addClass("nav-active");
                $(window).unbind('scroll', scroll);
                setTimeout(function() {
                    $(window).bind('scroll', scroll);
                }, 500)

                return false; //不要这句会有点卡顿
            });
            var topList = [];
            $('.head .nav ul li a').removeClass("nav-active").eq(0).addClass("nav-active");
            if ($(document).width() > 750) {
                var top1 = $('#myCarousel').offset().top - $(".head").height();
                var top2 = $('#service').offset().top - $(".head").height();
                var top3 = $('#case').offset().top - $(".head").height();
                var top4 = $('#about').offset().top - $(".head").height();
                var top5 = $('#cooperation').offset().top - $('.head').height();
                var top6 = $('#about').offset().top + $('#about').height() - $('#contact').height() - 10 - $(".head").height();
                topList = [0, top2 - 20, top3 - 20, top4 - 20, top5 - 20,top6 - 20];
                $(window).bind('scroll', scroll);
            } else {
                var top1 = $('#myCarousel').offset().top - $(".head").height();
                var top2 = $('#myCarousel').height() - $(".head").height();
                var top3 = $('#myCarousel').height() + $('#service').height() - $(".head").height();
                var top4 = $('#myCarousel').height() + $('#service').height() + $('#case').height() - $(".head").height();
                var top5 = $('#myCarousel').height() + $('#service').height() + $('#case').height() + $('#about').height() - $(".head").height();
                topList = [0, top2 - 50, top3- 50, top4- 50,top5- 50, 10000];
                $("body").on("touchstart", function(e) {
                    // e.preventDefault();
                });
                $("body").on("touchmove", function(e) {
                    // e.preventDefault();
                    $(window).bind('scroll', scroll);
                    _touchStatus = false;
                });
            }
            
            
            function scroll() {
                if (_touchStatus) {return}
                var targetTop = $(this).scrollTop();
                if (targetTop >= topList[0] && targetTop < topList[1]) {
                    $('.head .nav ul li a').removeClass("nav-active").eq(0).addClass("nav-active");
                } else if (targetTop >= topList[1] && targetTop < topList[2]) {
                    $('.head').addClass('head-hua');
                    $('.head .nav ul li a').removeClass("nav-active").eq(1).addClass("nav-active");
                } else if (targetTop >= topList[2] && targetTop < topList[3]) {
                    $('.head').addClass('head-hua');
                    $('.head .nav ul li a').removeClass("nav-active").eq(2).addClass("nav-active");
                } else if (targetTop >= topList[3] && targetTop < topList[4]) {
                    $('.head').addClass('head-hua');
                    $('.head .nav ul li a').removeClass("nav-active").eq(3).addClass("nav-active");
                } else if (targetTop >= topList[4]) {
                    $('.head').addClass('head-hua');
                    $('.head .nav ul li a').removeClass("nav-active").eq(4).addClass("nav-active");
                }
            }

            //Firefox
            if (document.addEventListener) {
                document.addEventListener('DOMMouseScroll', scrollFunc, false);
            }
            //IE及其他浏览器
            window.onmousewheel = document.onmousewheel = scrollFunc;

            function scrollFunc(e) {
                e = e || window.event;
                //IE/Opera/Chrome
                if (e.wheelDelta) {
                    if (parseInt(e.wheelDelta) < 0) {
                        $('.head').addClass('head-hua')
                    } else {
                        if ($(window).scrollTop() == 0) {
                            $('.head').removeClass('head-hua')
                        }
                    }
                } else if (e.detail) { //Firefox
                    if (parseInt(e.detail) < 0) {
                        $('.head').addClass('head-hua')
                    } else {
                        if ($(window).scrollTop() == 0) {
                            $('.head').removeClass('head-hua')
                        }
                    }
                }
            }
            if ($(window).scrollTop() > 0) {
                $('.head').addClass('head-hua');
            }
        },
        getNowYear:function(){
            var _date = new Date();
            var _now_year = _date.getFullYear();
            $('.time-year').html(_now_year)
        },
        setTeam:function(){
            if(window.screen.width <= 760){
                return false;
            }
            var h = $('.fourth-content .team_2 .info').height();
            $('.team_1 .info').css("height",h);
        }
    }
    var tempObj = {
        reader: function() {
            common.touchBnner();
            common.ctrlWidth();
            common.playAnimateBanner();
            
            // common.slideTop();
            common.addAnimate();
            common.headChange();
            common.getNowYear();
            common.setTeam();
        }
    }
    var outputObj = function() {
        tempObj.reader();
        return tempObj;
    }
    return new outputObj();
})()