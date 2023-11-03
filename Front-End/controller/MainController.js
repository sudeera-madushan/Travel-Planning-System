/**
 * author : Sudeera Madushan
 * date : 10/20/2023
 * project : Front-End
 */
const mobileScreen = window.matchMedia("(max-width: 990px )");
$(document).ready(function () {
    $(".dashboard-nav-dropdown-toggle").click(function () {
        $(this).closest(".dashboard-nav-dropdown")
            .toggleClass("show")
            .find(".dashboard-nav-dropdown")
            .removeClass("show");
        $(this).parent()
            .siblings()
            .removeClass("show");
    });
    $(".menu-toggle").click(function () {
        if (mobileScreen.matches) {
            $(".dashboard-nav").toggleClass("mobile-show");
        } else {
            $(".dashboard").toggleClass("dashboard-compact");
        }
    });
});


document.addEventListener("DOMContentLoaded", function(event) {

    const showNavbar = (toggleId, navId, bodyId, headerId) =>{
        const toggle = document.getElementById(toggleId),
            nav = document.getElementById(navId),
            bodypd = document.getElementById(bodyId),
            headerpd = document.getElementById(headerId)

        if(toggle && nav && bodypd && headerpd){
            toggle.addEventListener('click', ()=>{
                nav.classList.toggle('shows')
                toggle.classList.toggle('bx-x')
                bodypd.classList.toggle('body-pd')
                headerpd.classList.toggle('body-pd')
            })
        }
    }

    showNavbar('header-toggle','nav-bar','body-pd','header')
    const linkColor = document.querySelectorAll('.nav_link')

    function colorLink(){
        if(linkColor){
            linkColor.forEach(l=> l.classList.remove('active'))
            this.classList.add('active')
        }
    }
    linkColor.forEach(l=> l.addEventListener('click', colorLink))
});


$('#logout').click(function () {
    localStorage.setItem('token', "");
    $("#guide-menu").hide();
    $("#vehicle-menu").hide();
    $("#hotel-menu").hide();
    $("#body-pd").hide();
    $("#login").show();

})

$(document).ready(function() {

    $("#guide-menu").hide();
    $("#vehicle-menu").hide();
    $("#hotel-menu").hide();
    $("#user-menu").hide();
    $("#main-travel-menu").hide();
    // $("#body-pd").hide();
    $("#login").hide();

    $("#guide-header").hide();
    token= localStorage.getItem('token');
    let params = {
        token: token
    }
    if (token){
        $.ajax({
            type: "GET",
            url: "http://localhost:8091/travel/api/v1/auth/log" + '?' + $.param(params),
            headers: {
                "Authorization": `Bearer ${token}`
            },
            success:function(response) {
                if (response.status === 202) {
                    response.object.map((value, index) => {
                        let row;
                        if (value === "ROLE_GUIDE") {
                            $('#guide-menu').show()
                        }
                        if (value === "ROLE_VEHICLE") {
                            $('#vehicle-menu').show()
                        }
                        if (value === "ROLE_HOTEL") {
                            $('#hotel-menu').show()
                        }
                        if (value === "ROLE_ADMIN") {
                            $('#user-menu').show()
                        }
                        if (value === "ROLE_BOOKING") {
                            $('#main-travel-menu').show()
                        }
                    })
                    $("#navbar").show();
                    $("#body-pd").show();
                    $('#dashboard-btn-home').addClass('navbar-a-focus')
                    $("#login").hide();
                    $('#user-name').text(response.description)
                    console.log(response.description)
                }else {
                    $("#login").show();
                }
            },
            error: function (error) {
                $("#login").show();
            }
        });
    }else {
        $("#login").show();
    }
});

function login(data) {
    localStorage.setItem('token', data.token);
    $("#navbar").show();
    let roles=data.role;
    roles.map((value, index) => {
        let row;
        if (value === "ROLE_GUIDE") {
            $('#guide-menu').show()
        }
        if (value === "ROLE_VEHICLE") {
            $('#vehicle-menu').show()
        }
        if (value === "ROLE_HOTEL") {
            $('#hotel-menu').show()
        }
        $('#nav-list').append(row);

    })

    $("#body-pd").show();
    $('#dashboard-btn-home').addClass('navbar-a-focus')
    $("#login").hide();
    $('#user-name').text($('#username').val())
    alert("Login successfully !");
}

$('#btn-login').click(function () {

    var formData = {
        username: $("#username").val(),
        password: $("#password").val()
    };
    $.ajax({
        type: "POST",
        url: "http://localhost:8091/travel/api/v1/auth",
        data: JSON.stringify(formData),
        contentType: "application/json",
        success: function(response) {
            if (response.status === 200) {
                var data = response.object;
                login(data);
            }
        },
        error: function(err) {
            alert("incorrect username or password !");
        }
    });
})




$('.image-upload-wrap').bind('dragover', function () {
    $('.image-upload-wrap').addClass('image-dropping');
});
$('.image-upload-wrap').bind('dragleave', function () {
    $('.image-upload-wrap').removeClass('image-dropping');
});
$('#btn-notification-close').click(function () {
    $('.toast').toast('hide')
})
