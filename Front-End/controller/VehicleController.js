/**
 * author : Sudeera Madushan
 * date : 10/23/2023
 * project : Front-End
 */

$(document).ready(function() {
    $("#vehicle-list").hide()
    // $("#newVehicleContainer").hide()
});

$('#btnNewVehicle').click(function () {
    $('#newVehicleContainer').show()
})

$('#btnCreateVehicle').click(function () {
    let vehicle = {
        id: null,
        brand: $('#vehicle-brand').val(),
        category: $('#vehicleCategory').find("option:selected").text(),
        fuelType: $("input[name='fuel-type']:checked").val(),
        vehicleType: $('#vehicleType').find("option:selected").text(),
        transmissionType: $('#isAutoGear').is(":checked")?"AUTO":"MANUAL",
        driverName: $('#vehicle-driver-name').val(),
        driverContact: $('#vehicle-driver-contact').val(),
        remarks: $('#vehicle-remarks').val(),
        isHybrid: $('#isHybrid').is(":checked"),
        fuelUsage: parseFloat($('#vehicle-fuel-usage').val()),
        seatCapacity: parseInt($('#vehicle-seat-capacity').val()),
        packageCategoryId: "",
    };
    const json = JSON.stringify(vehicle);
    const blob = new Blob([json], {
        type: 'application/json'
    });

    const formData = new FormData();
    formData.append("vehicle", blob);
    formData.append('driver_license_image_front', $('#driver-license-front-image')[0].files[0]);
    formData.append('driver_license_image_back', $('#driver-license-back-image')[0].files[0]);
    formData.append('front_view', $('#vehicle-front-image')[0].files[0]);
    formData.append('rear_view', $('#vehicle-rear-image')[0].files[0]);
    formData.append('front_interior', $('#vehicle-front-interior-image')[0].files[0]);
    formData.append('rear_interior', $('#vehicle-rear-interior-image')[0].files[0]);
    $.ajax({
        url: 'http://localhost:8093/travel/api/v1/vehicle/save',
        type: 'POST',
        cache: false,
        enctype: 'multipart/form-formData',
        contentType: false,
        processData: false,
        success: function (data) {
            console.log(data)
            // showToast("Success","Guide \"" + data.object.name +"\"' Save Successfully !")
            // getAllGuides();
        },
        error: function (error) {
            console.error(error);
        }
    });
})