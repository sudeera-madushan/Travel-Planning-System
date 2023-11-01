/**
 * author : Sudeera Madushan
 * date : 10/29/2023
 * project : Front-End
 */
var regexFullName = /^[a-zA-Z0-9_ ]+$/;
var regexEmail = /^[\w-]+(\.[\w-]+)*@([\w-]+\.)+[a-zA-Z]{2,7}$/;
var regexAddress = /^[a-zA-Z0-9\s\.,#-]+$/;
var regexContact = /^(?:\+94|0)[1-9]\d{8}$/;
var regexPassword = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
var regexAge = /^(1[8-9]|[2-9]\d|\d{3,})$/;
var regexNIC = /^\d{9}[vVxX]$|^\d{12}$/;
var regexUsername = /^[a-zA-Z0-9_]{5,20}$/;
let token;
$(document).ready(function() {
    $('#area-list-container').hide();
    $('#area-detail-page').hide();
    $('#hotel-detail-page').hide();
    $('#hotel-list-container').hide();
    $('#user-data').hide();

    $('#register-section').hide();
    // $('#home-section').hide()
    $('#home-register').hide()
    // $('#home-login').hide()
    // $('#register-section').show()
    // getAllAreas();
    // getAllHotel();


    autoLogin()
});
$('#btnRegister').click(function () {
    $('#home-section').hide()
    $('#register-section').show()
    $('#home-register').show()
    $('#home-login').hide()
})
$('#btnLogin').click(function () {
    $('#home-section').hide()
    $('#home-register').hide()
    $('#home-login').show()
    $('#register-section').show()
})
$('#btnLoginUser').click(function (){
        var formData = {
            username: $("#loginUserName").val(),
            password: $("#loginPassword").val()
        };
        $.ajax({
            type: "POST",
            url: "http://localhost:8091/travel/api/v1/auth",
            data: JSON.stringify(formData),
            contentType: "application/json",
            success: function(response) {
                if (response.status === 200) {
                    console.log(response)
                    response.object.role.map((value, index) => {
                        if (value === "ROLE_USER") {
                            $('#home-section').show();
                            $('#user-data').show();
                            $('#user-register').hide();
                            $('#usernameLabel').empty();
                            $('#register-section').hide();
                            localStorage.setItem('token', response.object.token);
                        }
                    });
                }
            },
            error: function(err) {
                $("#loginUserName").removeClass('border-success')
                $("#loginPassword").removeClass('border-success')
                $('#loginUserName').addClass('border-danger')
                $('#loginPassword').addClass('border-danger')
                alert("incorrect username or password !");
            }
        });
})
$('#btnRegisterUser').click(function (){
    if (validFields([
        {data:$("#registerFullName"),regex:regexFullName},
        {data:$("#registerEmail"),regex:regexEmail},
        {data:$("#registerUserName"),regex:regexUsername},
        {data:$("#registerContact"),regex:regexContact},
        {data:$("#registerPassword"),regex:regexPassword},
        {data:$("#registerPasswordConform"),regex:regexPassword},
        {data:$("#registerAge"),regex:regexAge},
        {data:$("#registerNicPassportNo"),regex:regexNIC},
        {data:$("#registerAddress"),regex:regexAddress}
    ])){
        if ($("#registerPassword").val()===$("#registerPasswordConform").val()){
            if ($('#nicFrontFileUploadImage').attr('src')==='#'||$('#nicBackFileUploadImage').attr('src')==='#'){
                $("#nicFrontImageWrap").removeClass('border-success')
                $("#nicBackImageWrap").removeClass('border-success')
                $('#nicFrontImageWrap').addClass('border-danger')
                $('#nicBackImageWrap').addClass('border-danger')
            }else {
                $('#nicFrontImageWrap').removeClass('border-danger')
                $('#nicBackImageWrap').removeClass('border-danger')
                $("#nicFrontImageWrap").addClass('border-success')
                $("#nicBackImageWrap").addClass('border-success')

                registerUser();
            }
        }else {
            $("#registerPassword").removeClass('border-success')
            $("#registerPassword").addClass('border-danger')
            $("#registerPasswordConform").removeClass('border-success')
            $("#registerPasswordConform").addClass('border-danger')
        }
    }
})
let autoLogin=()=>{
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
                        if (value === "ROLE_USER") {
                            $('#home-section').show();
                            $('#user-data').show();
                            $('#user-register').hide();
                        }
                    })
                }else {
                    $("#login").show();
                }
            },
            error: function (error) {
                $("#login").show();
            }
        });
    }
}
let registerUser=()=>{
    let user={
        username:$('#registerUserName').val(),
        password:$('#registerPassword').val(),
        age:parseInt($('#registerAge').val()),
        fullName:$('#registerFullName').val(),
        gender:$('input[name="gender"]:checked').val(),
        email:$('#registerEmail').val(),
        contactNo:$('#registerContact').val(),
        address:$('#registerAddress').val(),
        nicOrPassportNo:$('#registerNicPassportNo').val(),
    }
    const json = JSON.stringify(user);
    const blob = new Blob([json], {
        type: 'application/json'
    });

    const formData = new FormData();
    formData.append("customer", blob);
    formData.append('nic_or_passport_image_front', base64ToFile($('#nicFrontFileUploadImage').attr('src')) );
    formData.append('nic_or_passport_image_back', base64ToFile($('#nicBackFileUploadImage').attr('src')) );

    $.ajax({
        url: 'http://localhost:8091/travel/api/v1/customer/save',
        type: 'POST',
        data: formData,
        enctype: 'multipart/form-formData',
        contentType: false,
        processData: false,
        success: function (data) {
            $('#home-section').hide()
            $('#home-register').hide()
            $('#home-login').show()
            $('#register-section').show()
        },
        error: function (error) {
            if (error.responseJSON.statusCode === 400) {
                alert("Username Already entered");
                $("#registerUserName").removeClass('border-success')
                $("#registerUserName").addClass('border-danger')

            }else {
                console.log(error.responseJSON)
            }
        }
    });
}
let base64ToFile= (imageDataUrl) => {
    let dataUrlParts = imageDataUrl.split(",");
    let contentType = dataUrlParts[0].split(":")[1].split(";")[0];
    let byteCharacters = atob(dataUrlParts[1]);
    let byteNumbers = new Array(byteCharacters.length);
    for (let i = 0; i < byteCharacters.length; i++) {
        byteNumbers[i] = byteCharacters.charCodeAt(i);
    }
    let byteArray = new Uint8Array(byteNumbers);
    return new Blob([byteArray], { type: contentType });
}
let validFields=(arr) => {
    let data=[]
    for (let i = 0; i < arr.length; i++) {
        if (arr[i].regex.test(arr[i].data.val())) {
            arr[i].data.removeClass('border-danger')
            arr[i].data.addClass('border-success')
        }else {
            arr[i].data.removeClass('border-success')
            arr[i].data.addClass('border-danger')
            return false;
        }
    }
    return data;
}
$('#areaDetailImageSlidePrev').click(function () {
    console.log(this.parentElement.children[1])
    console.log($('.carousel-item.active').index())
    var $activeItem = $('.carousel-item.active');

// Get the next item
    var $prevItem = $activeItem.prev('.carousel-item');

// Remove "active" class from the current active item
    $activeItem.removeClass('active');
    console.log($prevItem.index())
    if ($prevItem.index()===-1){
        $('.carousel-item:last').addClass('active')
    }else {
        $prevItem.addClass('active');
    }
// Add "active" class to the next item
})
let getAllAreas=()=>{
    $.ajax({
        url: 'http://localhost:8095/travel/api/v1/area',
        type: 'GET',
        // headers: {
        //     "Authorization": `Bearer ${token}`
        // },
        success: function (data) {
            loadAreaCards(data);
        },
        error: function (error) {
            console.log(error)
        }
    })
}

let loadAreaCards=(data)=>{

    data.object.map((value, index) => {
        let data=``;
        data=` <div class="card col m-4 p-0" id="${value.id}">
                    <a href="#">
                        <img class="card-img-top" src="data:image/jpg;base64, ${value.image}" alt="Card image cap">
                        <div class="card-body">
                            <h5 class="card-title">${value.name}</h5>
<!--                            <p class="card-text">-->
<!--                          -->
<!--                            </p>-->
                            <p class="card-text">
                                <small class="text-muted">
                                    <i class="fas fa-eye"></i>
                                    1000
                                    <i class="bx bx-user"></i>
                                    admin
                                    <i class="fas fa-calendar-alt"></i>
                                    Read More
                                </small>
                            </p>
                        </div>
                    </a>
                </div>`;
        $('#areaCardContainer').append(data)
    })
}

let showMoreArea=(id) => {
    let params = {
        id: id
    }
    $.ajax({
        url: 'http://localhost:8095/travel/api/v1/area/get' + '?' + $.param(params),
        type: 'GET',
        // headers: {
        //     "Authorization": `Bearer ${token}`
        // },
        success: function (data) {
            loadAreaDetails(data.object);
            loadAreaDetailsNearestPlaces(data.object)
        },
        error: function (error) {
            console.log(error)
        }
    })
    console.log(id);
}
$('#areaCardContainer').on('click','.card',function () {
    showMoreArea($(this).attr('id'))
    $('#area-list-container').hide();
    $('#area-detail-page').show();
})
// $('#areaDetailImageSlidePrev').click(function () {
// $('.carousel').carousel('next')
//
// })

let loadAreaDetails=(area)=>{
    $('#areaDetailsName').empty()
    $('#areaDetailsName').append(area.name)
    $('#areaDetailsDescription').empty()
    $('#areaDetailsDescription').append(area.description)
    // $('#areaDetailsMapLocation').empty()
    $('#areaDetailsMapLocation').attr('src', area.areaLocation)
    let params = {
        id: area.id
    }
    $.ajax({
        url: 'http://localhost:8095/travel/api/v1/areaImage/all' + '?' + $.param(params),
        type: 'GET',
        // headers: {
        //     "Authorization": `Bearer ${token}`
        // },
        success: function (data) {
            loadAreaDetailsImgList(data.object)
        },
        error: function (error) {
            console.log(error)
        }
    })


}
let loadAreaDetailsNearestPlaces=(area)=>{
    let params = {
        id: area.id
    }
    $.ajax({
        url: 'http://localhost:8095/travel/api/v1/area/nears' + '?' + $.param(params),
        type: 'GET',
        // headers: {
        //     "Authorization": `Bearer ${token}`
        // },
        success: function (data) {
            data.object.map((value, index) => {

                let code = `<div class="card bg-dark text-white mb-2" id="${value.id}">
                                    <img class="card-img" src="data:image/jpg;base64, ${value.image}" alt="Card image">
                                    <div class="card-img-overlay d-flex justify-content-center align-items-center " style="background: rgba(151, 12, 200, 0.25)">
                                        <h5 class="card-title">${value.name}</h5>
                                    </div>
                                </div>`;

                $('#areaDetailsNearPlace').empty();
                $('#areaDetailsNearPlace').append(code);
            })

        },
        error: function (error) {
            console.log(error)
        }
    })
}
let loadAreaDetailsImgList=(arr) => {
    let data=`
                   <ol class="carousel-indicators">`;
    arr.map((value, index) => {
        data = data + `<li data-target="#carouselExampleIndicators" data-slide-to="${index}"
                        class="${index === 0 ? 'active' : ''} bx bx-circle " ></li>`;
    })
        data=data+`</ol>
                   <div class="carousel-inner">`;
    arr.map((value, index) => {
                     data=data+`
                     <div class="carousel-item ${index === 0 ? 'active' : ''}">
                       <img class="d-block" src="data:image/jpg;base64, ${value.image}" width="800px" height="500px">
                     </div>`;
    })
        data=data+`</div>`;
    $('#carouselExampleIndicators').empty();
    $('#carouselExampleIndicators').append(data);
}

$('#areaDetailsNearPlace').on('click','.card',function () {
    showMoreArea($(this).attr('id'));
})

let getAllHotel=()=>{
    $.ajax({
        url: 'http://localhost:8094/travel/api/v1/hotel',
        type: 'GET',
        // headers: {
        //     "Authorization": `Bearer ${token}`
        // },
        success: function (data) {
            loadHotelCards(data);
        },
        error: function (error) {
            console.log(error)
        }
    })
}

let loadHotelCards=(data)=>{

    $('#hotelCardContainer').empty()
    data.object.map((value, index) => {
        console.log(value)
        let data=``;
        data=` <div class="card col m-4 p-0" id="${value.id}">
                    <a href="#">
                        <img class="card-img-top" src="data:image/jpg;base64, ${value.hotelImages[0].image}" alt="Card image cap">
                        <div class="card-body">
                            <h5 class="card-title">${value.name}</h5>
<!--                            <p class="card-text">-->
<!--                          -->
<!--                            </p>-->
                            <p class="card-text">
                                <small class="text-muted">
                                    <i class="fas fa-eye"></i>
                                    1000
                                    <i class="bx bx-user"></i>
                                    admin
                                    <i class="fas fa-calendar-alt"></i>
                                    Read More
                                </small>
                            </p>
                        </div>
                    </a>
                </div>`;
        $('#hotelCardContainer').append(data)
    })
}
$('#packages').on('click','.card',function () {
    console.log($(this).attr('id'))
    let name;
    if ($(this).attr('id')==="regularPackage") {
        name="Regular"
    }else if ($(this).attr('id')==="midLevelPackage") {
        name="Mid-level"
    }else if ($(this).attr('id')==="luxuryPackage") {
        name="Luxury"
    }else if ($(this).attr('id')==="superLuxuryPackage") {
        name="Super Luxury"
    }
    console.log(name)
    let params = {
        name:name
    }
    $.ajax({
        type: "GET",
        url: "http://localhost:8095/travel/api/v1/package/find" + '?' + $.param(params),
        headers: {
            "Authorization": `Bearer ${token}`
        },
        success:function(response) {
            console.log(response.object)
        },
        error: function (error) {
            $("#login").show();
        }
    });

})
$('#hotelCardContainer').on('click','.card',function () {
    showMoreHotel($(this).attr('id'))
    $('#hotel-list-container').hide();
    $('#hotel-detail-page').show();
})
let showMoreHotel=(id) => {
    let params = {
        id: id
    }
    $.ajax({
        url: 'http://localhost:8094/travel/api/v1/hotel/get' + '?' + $.param(params),
        type: 'GET',
        // headers: {
        //     "Authorization": `Bearer ${token}`
        // },
        success: function (data) {
            loadHotelDetails(data.object);
            loadHotelDetailsNearestPlaces(data.object.mapLocation)
            loadHotelDetailsNearestHotels(data.object)
        },
        error: function (error) {
            console.log(error)
        }
    })
    console.log(id);
}
let loadHotelDetails=(hotel)=>{
    $('#hotelDetailsName').empty()
    $('#hotelDetailsName').append(hotel.name)
    $('#hotelDetailsDescription').empty()
    $('#hotelDetailsDescription').append(hotel.remarks)
    // $('#areaDetailsMapLocation').empty()
    $('#hotelDetailsMapLocation').attr('src', hotel.mapLocation)

    let data=`
                   <ol class="carousel-indicators">`;
    hotel.hotelImages.map((value, index) => {
        data = data + `<li data-target="#carouselExampleIndicators" data-slide-to="${index}"
                        class="${index === 0 ? 'active' : ''} bx bx-circle " ></li>`;
    })
    data=data+`</ol>
                   <div class="carousel-inner">`;
    hotel.hotelImages.map((value, index) => {
        data=data+`
                     <div class="carousel-item ${index === 0 ? 'active' : ''}">
                       <img class="d-block" src="data:image/jpg;base64, ${value.image}" width="800px" height="500px">
                     </div>`;
    })
    data=data+`</div>`;
    $('#hotel-carouselExampleIndicators').empty();
    $('#hotel-carouselExampleIndicators').append(data);


}

let loadHotelDetailsNearestPlaces=(area)=>{
    let params = {
        src: area
    }
    $.ajax({
        url: 'http://localhost:8095/travel/api/v1/area/src' + '?' + $.param(params),
        type: 'GET',
        // headers: {
        //     "Authorization": `Bearer ${token}`
        // },
        success: function (data) {

            $('#hotelDetailsNearPlace').empty();
            data.object.map((value, index) => {

                let code = `<div class="card bg-dark text-white mb-2" id="${value.id}">
                                    <img class="card-img" src="data:image/jpg;base64, ${value.image}" alt="Card image">
                                    <div class="card-img-overlay d-flex justify-content-center align-items-center " style="background: rgba(151, 12, 200, 0.25)">
                                        <h5 class="card-title">${value.name}</h5>
                                    </div>
                                </div>`;

                $('#hotelDetailsNearPlace').append(code);
            })

        },
        error: function (error) {
            console.log(error)
        }
    })
}

let loadHotelDetailsNearestHotels=(hotel)=>{
    let params = {
        id: hotel.id
    }
    $.ajax({
        url: 'http://localhost:8094/travel/api/v1/hotel/nears' + '?' + $.param(params),
        type: 'GET',
        // headers: {
        //     "Authorization": `Bearer ${token}`
        // },
        success: function (data) {

            $('#hotelDetailsNearHotel').empty();
            data.object.map((value, index) => {

                let code = `<div class="card bg-dark text-white mb-2" id="${value.id}">
                                    <img class="card-img" src="data:image/jpg;base64, ${value.hotelImages[0].image}" alt="Card image">
                                    <div class="card-img-overlay d-flex justify-content-center align-items-center " style="background: rgba(151, 12, 200, 0.25)">
                                        <h5 class="card-title">${value.name}</h5>
                                    </div>
                                </div>`;

                $('#hotelDetailsNearHotel').append(code);
            })

        },
        error: function (error) {
            console.log(error)
        }
    })
}