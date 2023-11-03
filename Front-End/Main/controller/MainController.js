import {Booking} from "../model/Booking.js";

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
let booking;
let nowArea;
let nowHotel;
let nowVehicle;
$(document).ready(function() {
    $('#area-list-container').hide();
    $('#area-detail-page').hide();
    $('#hotel-detail-page').hide();
    $('#hotel-list-container').hide();
    $('#user-data').hide();

    $('#register-section').hide();
    // $('#home-section').hide()
    $('#home-register').hide()
    $('#vehicle-detail-page').hide()
    $('#vehicle-list-container').hide()
    $('#booking-planing-page').hide()
    $('#booking-planing-page-2').hide()
    // $('#home-login').hide()
    // $('#register-section').show()
    // getAllAreas();
    // getAllHotel();
    // getAllVehiclesByCategory()

    // getAllHotel()
    autoLogin()
    $('#bookingStartDate').on("change",function () {
        let currentDate = new Date();
        currentDate.setDate(currentDate.getDate() + 1);
        let startDate=new Date($('#bookingStartDate').val())
        if (currentDate>startDate){
            startDate.setDate(currentDate.getDate() + 1);
            $('#bookingStartDate').val(startDate.toISOString().split('T')[0])
        }
        startDate=new Date($('#bookingStartDate').val())
        let date = new Date($('#bookingEndDate').val());
        date.setDate(date.getDate()+1)
        if (startDate>date){
            startDate.getDate(startDate.getDate() + 1)
            $('#bookingEndDate').val(startDate.toISOString().split('T')[0])
        }

        setTravelDates();
    });
    $('#bookingEndDate').on("change",function (){
        let currentDate = new Date();
        currentDate.setDate(currentDate.getDate() + 1);
        let startDate=new Date($('#bookingStartDate').val())
        if (currentDate>startDate){
            startDate.setDate(currentDate.getDate() + 1);
            $('#bookingStartDate').val(startDate.toISOString().split('T')[0])
        }
        startDate=new Date($('#bookingStartDate').val())
        let date = new Date($('#bookingEndDate').val());
        startDate.setDate(startDate.getDate()+1)
        if (startDate>date){
            startDate.getDate(startDate.getDate() + 1)
            $('#bookingEndDate').val(startDate.toISOString().split('T')[0])
        }
        setTravelDates();
    });
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
    $('#areaCardContainer').empty()
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
        headers: {
            "Authorization": `Bearer ${token}`
        },
        success: function (data) {
            nowArea=data.object;
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
        headers: {
            "Authorization": `Bearer ${token}`
        },
        success: function (data) {
            loadAreaDetailsImgList(data.object)
        },
        error: function (error) {
            console.log(error)
        }
    })

    let $btnAddToTravelPlace = $('#btnAddToTravelPlace');
    for (let i = 0; i < booking.areaList.length; i++) {
        console.log(nowArea.id===booking.areaList[i].area.id)
        if (nowArea.id===booking.areaList[i].area.id){
            $btnAddToTravelPlace.prop("disabled",true);
            $btnAddToTravelPlace.empty()
            $btnAddToTravelPlace.append(`<i class='bx bx-check fs-3'></i> <span> Added</span>`)
            return;
        }

    }
    $btnAddToTravelPlace.prop("disabled",false);
    $btnAddToTravelPlace.empty()
    $btnAddToTravelPlace.append(`<i class='bx bx-cart-add fs-3'></i> <span> Add To Travel</span>`)
}
let loadAreaDetailsNearestPlaces=(area)=>{
    let params = {
        id: area.id
    }
    $.ajax({
        url: 'http://localhost:8095/travel/api/v1/area/nears' + '?' + $.param(params),
        type: 'GET',
        headers: {
            "Authorization": `Bearer ${token}`
        },
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
    let params = {
        // id: booking.packageCategoryId
        id: "6529a319cb7d004ca5a58f39"
    }
    $.ajax({
        url: 'http://localhost:8094/travel/api/v1/hotel/category' + '?' + $.param(params),
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
            console.log(response.object.id);
            booking=new Booking()
            booking.areaList=[];
            booking.hotelList=[];
            booking.packageCategoryId=response.object.id;
            $('#area-list-container').show();
            $('#home-section').hide()
            // setTravelDates();
            getAllAreas();
        },
        error: function (error) {
            console.log(error)
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
            nowHotel=data.object;
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

    let $btnAddToTravelHotel = $('#btnAddToTravelHotel');
    $('#hotelDetailOption').empty()
    $('#hotelOptionsList').empty()
    let table=`<table class="table"><tr>`;
    let tData=`<tbody><tr>`;
    hotel.options.map((value, index) => {
        $('#hotelDetailOption').append(`<option value="${value.id}">${value.name}</option>`)
        table=table+`<th scope="col">${value.name}</th>`
        tData=tData+`<td>${value.charge}</td>`
    })
    table=table+`</tr>`+tData+`</tr></tbody>`;
    $('#hotelOptionsList').append(table)

    for (let i = 0; i < booking.hotelList.length; i++) {
        console.log(nowHotel.id===booking.hotelList[i].hotel.id)
        if (nowHotel.id===booking.hotelList[i].hotel.id){
            $btnAddToTravelHotel.prop("disabled",true);
            $btnAddToTravelHotel.empty()
            $btnAddToTravelHotel.append(`<i class='bx bx-check fs-3'></i> <span> Added</span>`)
            return;
        }

    }
    $btnAddToTravelHotel.prop("disabled",false);
    $btnAddToTravelHotel.empty()
    $btnAddToTravelHotel.append(`<i class='bx bx-cart-add fs-3'></i> <span> Add To Travel</span>`)

}

let loadHotelDetailsNearestPlaces=(area)=>{
    let params = {
        src: area
    }
    $.ajax({
        url: 'http://localhost:8095/travel/api/v1/area/src' + '?' + $.param(params),
        type: 'GET',
        headers: {
            "Authorization": `Bearer ${token}`
        },
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

$('#btnAddToTravelPlace').click(function () {

    $('#area-detail-page').hide();
    $('#area-list-container').show();
    booking.areaList.unshift({index:booking.areaList.length,area:nowArea})
})
$('#btnAddToTravelPlaceCancel').click(function () {
    $('#area-detail-page').hide();
    $('#area-list-container').show();
})


$('#btnNextToHotelList').click(function () {
    if (booking.areaList.length>0) {
        $('#area-list-container').hide();
        $('#hotel-list-container').show();
        getAllHotel();
    }else {
        alert("Please select place first !");
    }
})

$('#btnAddToTravelHotel').click(function () {

    $('#hotel-detail-page').hide();
    $('#hotel-list-container').show();
    let optionList=nowHotel.options;
    nowHotel.options=[];
    optionList.map((value, index) => {
        if ($('#hotelDetailOption').val() === value.id) {
            nowHotel.options=value;
        }
    })
    booking.totalHeadCount=parseInt($('#hotelPassengers').val())
    booking.noOfChildren=parseInt($('#hotelChildren').val())
    booking.hotelList.unshift({index:booking.areaList.length+booking.hotelList.length,hotel:nowHotel})
})
$('#btnAddToTravelHotelCancel').click(function () {
    $('#hotel-detail-page').hide();
    $('#hotel-list-container').show();
})

let getAllVehiclesByCategory=()=>{
    let params = {
        id: booking.packageCategoryId,
        seat: booking.totalHeadCount
    }
    $.ajax({
        url: 'http://localhost:8093/travel/api/v1/vehicle/cate&seat' + '?' + $.param(params),
        type: 'GET',
        // headers: {
        //     "Authorization": `Bearer ${token}`
        // },
        success: function (data) {
            loadVehicleCards(data);
        },
        error: function (error) {
            console.log(error)
        }
    })
}

let loadVehicleCards=(data)=>{

    $('#vehicleListContainer').empty()
    data.object.map((value, index) => {
        console.log(value)
        let data=``;
        data=` <div class="card col m-4 p-0" id="${value.id}">
                    <a href="#">
                        <img class="card-img-top" src="data:image/jpg;base64, ${value.vehicleImage.frontView}" alt="Card image cap">
                        <div class="card-body">
                            <h5 class="card-title">${value.brand}</h5>
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
        $('#vehicleCardContainer').append(data)
    })
}
$('#vehicleCardContainer').on('click','.card',function () {
    showMoreVehicle($(this).attr('id'))
    $('#vehicle-list-container').hide();
    $('#vehicle-detail-page').show();
})
let showMoreVehicle=(id) => {
    let params = {
        id: id
    }
    $.ajax({
        url: 'http://localhost:8093/travel/api/v1/vehicle/get' + '?' + $.param(params),
        type: 'GET',
        // headers: {
        //     "Authorization": `Bearer ${token}`
        // },
        success: function (data) {
            nowVehicle=data.object;
            loadVehicleDetails(data.object);
            // loadHotelDetailsNearestPlaces(data.object.mapLocation)
            // loadHotelDetailsNearestHotels(data.object)
        },
        error: function (error) {
            console.log(error)
        }
    })
    console.log(id);
}
let loadVehicleDetails=(vehicle)=>{
    console.log(999)
    $('#vehicleDetailsName').empty()
    $('#vehicleDetailsName').append(vehicle.name)
    $('#vehicleDetailsDescription').empty()
    $('#vehicleDetailsDescription').append(vehicle.remarks)
    // $('#areaDetailsMapLocation').empty()
    // $('#hotelDetailsMapLocation').attr('src', vehicle.mapLocation)

    let data=`
                   <ol class="carousel-indicators">
                   <li data-target="#carouselExampleIndicators" data-slide-to="0"
                        class="active bx bx-circle " ></li>
                        <li data-target="#carouselExampleIndicators" data-slide-to="1"
                        class="bx bx-circle " ></li>
                        <li data-target="#carouselExampleIndicators" data-slide-to="2"
                        class=" bx bx-circle " ></li>
                        <li data-target="#carouselExampleIndicators" data-slide-to="3"
                        class=" bx bx-circle " ></li>
                        <li data-target="#carouselExampleIndicators" data-slide-to="4"
                        class=" bx bx-circle " ></li>
                        </ol>
                   <div class="carousel-inner">
                   <div class="carousel-item active">
                       <img class="d-block" src="data:image/jpg;base64, ${vehicle.vehicleImage.frontView}" width="800px" height="500px">
                     </div>
                   <div class="carousel-item">
                       <img class="d-block" src="data:image/jpg;base64, ${vehicle.vehicleImage.rearView}" width="800px" height="500px">
                     </div>
                   <div class="carousel-item">
                       <img class="d-block" src="data:image/jpg;base64, ${vehicle.vehicleImage.sideView}" width="800px" height="500px">
                     </div>
                   <div class="carousel-item">
                       <img class="d-block" src="data:image/jpg;base64, ${vehicle.vehicleImage.frontInterior}" width="800px" height="500px">
                     </div>
                   <div class="carousel-item">
                       <img class="d-block" src="data:image/jpg;base64, ${vehicle.vehicleImage.rearInterior}" width="800px" height="500px">
                     </div>
                     </div>`;


    $('#vehicle-carouselExampleIndicators').empty();
    $('#vehicle-carouselExampleIndicators').append(data);

    // let $btnAddToTravelHotel = $('#btnAddToTravelHotel');
    // console.log(booking)
    // for (let i = 0; i < booking.hotelList.length; i++) {
    //     console.log(nowHotel.id===booking.hotelList[i].hotel.id)
    //     if (nowHotel.id===booking.hotelList[i].hotel.id){
    //         $btnAddToTravelHotel.prop("disabled",true);
    //         $btnAddToTravelHotel.empty()
    //         $btnAddToTravelHotel.append(`<i class='bx bx-check fs-3'></i> <span> Added</span>`)
    //         return;
    //     }
    //
    // }
    // $btnAddToTravelHotel.prop("disabled",false);
    // $btnAddToTravelHotel.empty()
    // $btnAddToTravelHotel.append(`<i class='bx bx-cart-add fs-3'></i> <span> Add To Travel</span>`)

}
$('#btnAddToTravelVehicleCancel').click(function () {
    $('#vehicle-detail-page').hide();
    $('#vehicle-list-container').show();
})

$('#btnAddToTravelVehicle').click(function () {

    $('#vehicle-detail-page').hide();
    $('#booking-planing-page').show();
    $('#booking-planing-page-2').show();
    booking.vehicle=nowVehicle;
    loadTravelPlaningDetails();
})

$('#btnAddRoutPlanTravel').click(function () {
    let data=`<div class="row border-secondary-subtle border rounded p-2 mt-2">
            <div class="col">
                <div class="md-form md-outline input-with-post-icon datepicker">
                    <label for="bookingStartDate" class="text-primary">Date</label>
                    <input placeholder="Select date" type="date" class="form-control m-0">
                </div>
            </div>
            <div class="col">
                <label for="bookingStartDate" class="text-primary">Go to</label>
                <select class="form-select " aria-label="Default select example">
                    <option selected>Open this select menu</option>
                    <option value="1">One</option>
                    <option value="2">Two</option>
                    <option value="3">Three</option>
                </select>
            </div>
            <button class="btn-sm btn btn-primary" style="width: 5rem">add</button>
            <div class="col">
                <label for="bookingStartDate" class="text-primary">End</label>
                <select class="form-select " aria-label="Default select example">
                    <option selected>Open this select menu</option>
                    <option value="1">One</option>
                    <option value="2">Two</option>
                    <option value="3">Three</option>
                </select>
            </div>
        </div>`
    $('#booking-planing-page').append(data)
    initDatePicker($('#booking-planing-page .row:last-child'));
    setPlaningSelectFields()
})

$('#booking-planing-page').on('click','button',() => {
    if (event.target.classList.contains('btn-primary')){
        let data=`<div class="col-2">
                <label for="bookingStartDate" class="text-primary">Go to</label>
                <select class="form-select " aria-label="Default select example">
                    <option selected>Open this select menu</option>
                    <option value="1">One</option>
                    <option value="2">Two</option>
                    <option value="3">Three</option>
                </select>
            </div>`;
        let tempContainer = document.createElement('div');
        tempContainer.innerHTML = data;

        // Insert the new element before the clicked button's parent
        event.target.parentElement.insertBefore(tempContainer.firstChild, event.target);
        setPlaningSelectFields()
    }
})


$('#btnBookNowPlanTravel').click(function () {
    let params = {
        token: token
    }
    if (token) {
        $.ajax({
            type: "GET",
            url: "http://localhost:8091/travel/api/v1/customer/token" + '?' + $.param(params),
            headers: {
                "Authorization": `Bearer ${token}`
            },
            success: function (response) {
                booking.customerId=response.object.id;
            },
            error: function (error) {
                $("#login").show();
            }
        });
    }

    setTravelDates()
    let children = $('#booking-planing-page').children();
    for (let i = 6; i < children.length; i++) {
        let date = new Date(children.eq(i).children().eq(0).children().eq(0).children().eq(1).val());
        booking.dates.map((value, index) => {
            let bDate = new Date(value.date)
            if (date.getDate() === bDate.getDate()) {
                for (let x = 1; x < children.eq(i).children().length - 2; x++) {
                    let s = children.eq(i).children().eq(x).find(":selected").val();
                    value.routes.unshift({
                        type: s.split("/")[1],
                        id: s.split("/")[0],
                    })

                }
            }
            let val = children.eq(i).children().eq(children.eq(i).children().length-1).children().eq(1).val();
            value.end = {
                type: val.split("/")[1],
                id: val.split("/")[0],
            }
        })
    }

    let dto={
        startDate:$('#bookingStartDate').val(),
        endDate:$('#bookingEndDate').val(),
        countOfDays:booking.dates.length,
        noOfChildren:$('#hotelChildrenTravelPlan').val(),
        totalHeadCount:$('#hotelPassengersTravelPlan').val(),
        areaList:booking.areaList,
        dates:booking.dates,
        needGuide:false,
        guideId:null,
        withPets:false,
        packageValue:booking.packageValue,
        paidValue:0,
        customerId:booking.customerId,
        packageCategoryId:booking.packageCategoryId,
        vehicle:booking.vehicle.id
    }
    $.ajax({
        type: "POST",
        url: "http://localhost:8095/travel/api/v1/booking/save",
        data: JSON.stringify(dto),
        headers: {
            "Authorization": `Bearer ${token}`
        },
        contentType: "application/json",
        success: function(response) {
            console.log(response)
        },
        error: function(err) {
            console.log(err)
        }
    });
    // $('#btnBookNowPlanTravel').prop("disabled",true);
})
$('#btnSubmitSlitPlanTravel').click(function () {
    $('#btnSubmitSlitPlanTravel').prop("disabled",true);
})

$('#btnNextToVehicleList').click(function () {
    if (booking.hotelList.length>0) {
        $('#hotel-list-container').hide();
        $('#vehicle-list-container').show();
        getAllVehiclesByCategory();
    }else {
        alert("Please select Hotel first !");
    }
})
$('#hotelDetailOptionTravelPlan').on('change', function() {
    $('#hotelDetailOptionTravelPlan').val()
    booking.options.map((value, index) => {

    })
})
let loadTravelPlaningDetails=() => {
    $('#statesTravelPlaning').empty()
    $('#statesTravelPlaning').append("Draft")
    $('#hotelPassengersTravelPlan').val(booking.totalHeadCount)
    $('#hotelChildrenTravelPlan').val(booking.noOfChildren)
    $('#travelPlanPlacesContainer').empty()
    $('#travelPlanHotelsContainer').empty()
    $('#travelPlanPlacesContainer').append(`<h4 class="header text-primary">Places</h4>`);
    $('#travelPlanHotelsContainer').append(`<h4 class="header text-primary">Hotels</h4>`);
    let options = booking.hotelList[0].hotel.options;
    $('#hotelDetailOptionTravelPlan').empty();
    $('#hotelDetailOptionTravelPlan').append(`<option value="${options.id}">${options.name}</option>`)
    $('#hotelChargeTravelPlan').val(options.charge)
    // booking.hotelList[0].hotel.options.map((value, index) => {
    // })
    booking.areaList.map((value, index) => {
        let data=`<div class="col">
                <div class="card border-primary mb-4" >
                    <div class="card-body text-primary">
                        <h5 class="card-title fs-6">${value.area.name}<button class="btn btn-danger btn-sm">Remove</button></h5>
                    </div>
                </div>
            </div>`
        $('#travelPlanPlacesContainer').append(data)
    })
    booking.hotelList.map((value, index) => {
        let data=`<div class="col">
                <div class="card border-primary mb-4" >
                    <div class="card-body text-primary">
                        <h5 class="card-title fs-6">${value.hotel.name}<button class="btn btn-danger btn-sm">Remove</button></h5>
                    </div>
                </div>
            </div>`
        $('#travelPlanHotelsContainer').append(data)
    })
    setPlaningSelectFields()

    setDate()
}

let setPlaningSelectFields=() =>{
    let optionArr=[];
    booking.areaList.map((value, index) => {
        optionArr.unshift({
            id:value.area.id,
            name:value.area.name,
            type:"AREA",
        })
    })
    booking.hotelList.map((value, index) => {
        optionArr.unshift({
            id:value.hotel.id,
            name:value.hotel.name,
            type:"HOTEL",
        })
    })
    let data=``
    optionArr.map((value, index) => {
        data=data+`<option value="${value.id+"/"+value.type}">${value.name}</option>`
    })
    const $selectElements = $("#planingSection select");
    $selectElements.each(function(index, element) {
        if (element.id !== "hotelDetailOptionTravelPlan") {
            $(element).empty();
            $(element).append(data);
        }
    });
}

let setDate=() =>{
    var currentDate = new Date();

    currentDate.setDate(currentDate.getDate() + 1);
    var twoDaysLater = currentDate.toISOString().split('T')[0];
    $('#bookingStartDate').val(twoDaysLater);
    currentDate.setDate(currentDate.getDate() + 1);
    var nextDay = currentDate.toISOString().split('T')[0];
    $('#bookingEndDate').val(nextDay);

}

let setTravelDates=() =>{
    let dates=[];
    let start = new Date($('#bookingStartDate').val());
    let end = new Date($('#bookingEndDate').val());
    let ok=true;
    dates.unshift({
        date:start.toISOString().split('T')[0],
        routes:[],
        end:null,
        ok:false
    })
    while (ok){
        start.setDate(start.getDate()+1);
        if (end > start) {
            dates.unshift({
                date:start.toISOString().split('T')[0],
                routes:[],
                end:null,
                ok:false
            })
        }else {
            ok=false;
        }
    }
    dates.reverse();
    booking.dates=dates;
}
let checkDate=() =>{
    let date = new Date(event.target.value);
    booking.dates.forEach((value, index) => {
        let temp = new Date(value.date);
        if (temp === date) {
            if (!value.ok){
                value.ok=false;
            }else {

            }
        }
    })

}

$("input[placeholder='Select date'][type='date'].form-control.m-0").on('change', function() {
    if (event.target.id!=='bookingStartDate') {
        if (event.target.id!=='bookingEndDate') {
            checkDate()
        }
    }
});

function initDatePicker($element) {
    $element.find("input[type='date']").on('change', function() {
        checkDate()
    });
}

$('#btnCheckPlanTravel').click(function () {
    setTravelDates()
    let children = $('#booking-planing-page').children();
    for (let i = 6; i < children.length; i++) {
        let date = new Date(children.eq(i).children().eq(0).children().eq(0).children().eq(1).val());
        booking.dates.map((value, index) => {
            let bDate = new Date(value.date)
            console.log(date.getDate()+"--"+bDate.getDate())
            if (date.getDate() === bDate.getDate()) {
                console.log("OOOO")
                for (let x = 1; x < children.eq(i).children().length - 2; x++) {
                    let s = children.eq(i).children().eq(x).find(":selected").val();
                    value.routes.unshift({
                        type: s.split("/")[1],
                        id: s.split("/")[0],
                    })

                }
            }
            let val = children.eq(i).children().eq(children.eq(i).children().length-1).children().eq(1).val();
            value.end = {
                type: val.split("/")[1],
                id: val.split("/")[0],
            }
        })
    }
    console.log(booking.vehicle);
    let dto={
        startDate:$('#bookingStartDate').val(),
        endDate:$('#bookingEndDate').val(),
        countOfDays:booking.dates.length,
        noOfChildren:$('#hotelChildrenTravelPlan').val(),
        totalHeadCount:$('#hotelPassengersTravelPlan').val(),
        areaList:booking.areaList,
        dates:booking.dates,
        vehicleId:booking.vehicle.id,

    }
    let params = {
        token: token
    }
    $.ajax({
        type: "POST",
        url: "http://localhost:8095/travel/api/v1/booking/distance"+ '?' + $.param(params),
        data: JSON.stringify(dto),
        headers: {
            "Authorization": `Bearer ${token}`
        },
        contentType: "application/json",
        success: function(response) {
            let distance= response.object[0].toFixed(2);
            $('#lblTotalDistance').empty()
            $('#lblTotalDistance').append("Total Distance : "+distance+"km")
            let cost= response.object[1].toFixed(2);
            let total=response.object[1]+parseFloat($('#hotelChargeTravelPlan').val());
            $('#lblVehicleCost').empty()
            $('#lblVehicleCost').append("Vehicle Cost : Rs"+cost);
            $('#lblTotalCost').empty()
            $('#lblTotalCost').append("Total Cost : Rs"+total.toFixed(2));
            booking.packageValue=total;
        },
        error: function(err) {
            console.log(err)
        }
    });
})