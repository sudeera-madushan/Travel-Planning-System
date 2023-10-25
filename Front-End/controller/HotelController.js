/**
 * author : Sudeera Madushan
 * date : 10/25/2023
 * project : Front-End
 */

$(document).ready(function() {
    // $("#newHotelContainer").hide()
    $("#hotelListContainer").hide()
    // $("#newVehicleContainer").hide()
    // getAllVehicles()
});

$('#btnCreateHotel').click(function () {
    const formData = new FormData();
    let imageArr=[];
    let roomArr=[];
    for (let i = 1; i < $('#hotelImageContainer').children().length-1; i++) {
        // formData.append("images", base64ToFile($('#hotelImageContainer').children().eq(i).children('img').eq(0).attr('src')))
        imageArr.unshift($('#hotelImageContainer').children().eq(i).children('img').eq(0).attr('src').split(',')[1])
    }
    let hotel = {
        id: null,
        name: $('#hotelName').val(),
        category: $('#hotelCategory').find("option:selected").text(),
        location: $('#hotelLocation').val(),
        // location: $("input[name='fuel-type']:checked").val(),
        // email: $('#vehicleType').find("option:selected").text(),
        email: $('#hotelEmail').val(),
        // mapLocation: $('#isAutoGear').is(":checked")?"AUTO":"MANUAL",
        mapLocation: $('#hotelLocationMap').val(),
        contactNoOne: $('#hotelContactOne').val(),
        contactNoTwo: $('#hotelContactTwo').val(),
        petIsAllowed: $('#petIsAllowed').is(":checked"),
        hotelFee: parseFloat($('#hotelFee').val()),
        cancellationCriteriaIsFree: $('#cancellationCriteriaIsFree').is(":checked"),
        cancellationFee: parseFloat($('#cancellationFee').val()),
        packageCategoryId: "",
        images:imageArr,
        roomTypes:[]
    };
    const json = JSON.stringify(hotel);
    const blob = new Blob([json], {
        type: 'application/json'
    });

    formData.append("hotel", blob);
    // formData.append('driver_license_image_front', $('#driverLicenseFrontImage')[0].files[0]);


    $.ajax({
        url: 'http://localhost:8094/travel/api/v1/hotel/save',
        type: 'POST',
        cache: false,
        enctype: 'multipart/form-formData',
        data: formData,
        contentType: false,
        processData: false,
        success: function (data) {
            showToast("Success","Vehicle \"" + data.object.name +"\"' Save Successfully !");
            console.log(data)
        },
        error: function (error) {
            console.log(error)
        }
    });
})