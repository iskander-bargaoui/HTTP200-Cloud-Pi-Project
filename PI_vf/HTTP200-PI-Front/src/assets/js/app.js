/* Theme Name: Alita - A Responsive Multipurpose HTML Templates
   Author: Shreethemes
   Version: 1.0.0
   Created: March 2018
   File Description:Main JS file of the template
*/
(function ($) {
    'use strict';
    // Navbat Toggle
        var scroll = $(window).scrollTop();

        $('.navbar-toggle').on('click', function (event) {
            $(this).toggleClass('open');
            $('#navigation').slideToggle(400);
        });

        $('.navigation-menu>li').slice(-2).addClass('last-elements');

        $('.menu-arrow,.submenu-arrow').on('click', function (e) {
            if ($(window).width() < 992) {
                e.preventDefault();
                $(this).parent('li').toggleClass('open').find('.submenu:first').toggleClass('open');
            }
        });

        
        $(".navigation-menu a").each(function () {
            if (this.href == window.location.href) {
                $(this).parent().addClass("active"); // add active to li of the current link
                $(this).parent().parent().parent().addClass("active"); // add active class to an anchor
                $(this).parent().parent().parent().parent().parent().addClass("active"); // add active class to an anchor
            }
        });
    // Smooth scroll
        $('.navbar-nav a, .mouse-down a').on('click', function(event) {
            var $anchor = $(this);
            $('html, body').stop().animate({
                scrollTop: $($anchor.attr('href')).offset().top - 0
            }, 1500, 'easeInOutExpo');
            event.preventDefault();
        });

    //SCROLLSPY
        $(".navbar-nav").scrollspy({
            offset: 20
        });

    //sticky header on scroll
        $(window).scroll(function() {    
        var scroll = $(window).scrollTop();

            if (scroll >= 50) {
                $(".navbar-sticky").addClass("small");
            } else {
                $(".navbar-sticky").removeClass("small");
            }
        });

        $(window).scroll(function() {
            var scroll = $(window).scrollTop();

            if (scroll >= 50) {
                $(".sticky").addClass("nav-sticky");
            } else {
                $(".sticky").removeClass("nav-sticky");
            }
        });

    // Magnific Popup
        $('.mfp-image').magnificPopup({
            type: 'image',
            closeOnContentClick: true,
            mainClass: 'mfp-fade',
            gallery: {
                enabled: true,
                navigateByImgClick: true,
                preload: [0, 1]
            }
        });

        $(function () {
            $('[data-toggle="tooltip"]').tooltip()
        })

    // Loader 
    $(window).on('load', function() {
        $('#status').fadeOut();
        $('#preloader').delay(1000).fadeOut('slow');
        $('body').delay(1000).css({
            'overflow': 'visible'
        });
    });
        $(window).on('load', function() {
            $('#status').fadeOut();
            $('#preloader').delay(350).fadeOut('slow');
            $('body').delay(350).css({
                'overflow': 'visible'
            });
        });

        // BACK TO TOP
        $(window).scroll(function(){
            if ($(this).scrollTop() > 100) {
                $('.back-to-top').fadeIn();
            } else {
                $('.back-to-top').fadeOut();
            }
        }); 
        $('.back-to-top').click(function(){
            $("html, body").animate({ scrollTop: 0 }, 1000);
            return false;
        });
})(jQuery) 
