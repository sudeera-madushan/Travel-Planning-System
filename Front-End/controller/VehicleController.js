/**
 * author : Sudeera Madushan
 * date : 10/23/2023
 * project : Front-End
 */
let vehicleList=[];
let nowUpdatingVehicle;
$(document).ready(function() {
    $("#vehicleListContainer").hide()
    $("#newVehicleContainer").hide()
    // getAllVehicles()
});

$('#btnNewVehicle').click(function () {
    $('#placeListContainer').hide()
    $('#newPlaceContainer').hide()
    $('#newUserContainer').hide()
    $('#userListContainer').hide()
    $('#guideSection').hide();
    $('#hotel-Section').hide();
    $('#vehicleSection').show();
    $('#newVehicleContainer').show()
    $("#vehicleListContainer").hide()
    $('#header-title').empty()
    $("#header-title").append("Create Vehicle")
    $('#btnCreateVehicle').show()
    $('#btnUpdateVehicle').hide();
    $('#btnCancelUpdateVehicle').hide();
    $('#btnDeleteVehicle').hide();
    clearVehicleFields();
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
        feeForDay: parseInt($('#vehicle-fee-for-day').val()),
        feeForKm: parseInt($('#vehicle-fee-for-km').val()),
        packageCategoryId: "",
    };
    const json = JSON.stringify(vehicle);
    const blob = new Blob([json], {
        type: 'application/json'
    });

    const formData = new FormData();
    formData.append("vehicle", blob);
    formData.append('driver_license_image_front', $('#driverLicenseFrontImage')[0].files[0]);
    formData.append('driver_license_image_back', $('#driverLicenseBackImage')[0].files[0]);
    formData.append('side_view', $('#vehicleSideImage')[0].files[0]);
    formData.append('front_view', $('#vehicleFrontImage')[0].files[0]);
    formData.append('rear_view', $('#vehicleRearImage')[0].files[0]);
    formData.append('front_interior', $('#vehicleFrontInteriorImage')[0].files[0]);
    formData.append('rear_interior', $('#vehicleRearInteriorImage')[0].files[0]);
    let token = localStorage.getItem('token');
    $.ajax({
        url: 'http://localhost:8093/travel/api/v1/vehicle/save',
        type: 'POST',
        cache: false,
        enctype: 'multipart/form-formData',
        data: formData,
        contentType: false,
        processData: false,
        headers: {
            "Authorization": `Bearer ${token}`
        },
        success: function (data) {
            showToast("Success","Vehicle \"" + data.object.brand +"\"' Save Successfully !");
            // getAllVehicles();
            clearVehicleFields();
        },
        error: function (error) {
            console.log(error)
        }
    });
})

$('#btnVehicleList').click(function () {
    showVehicleList();

});
let showVehicleList= () => {

    $('#placeListContainer').hide()
    $('#newPlaceContainer').hide()
    $('#newUserContainer').hide()
    $('#userListContainer').hide()
    $('#hotel-Section').hide();
    $('#guideSection').hide();
    $('#vehicleSection').show();
    $('#vehicleListContainer').show()
    $('#newVehicleContainer').hide()

    $('#header-title').empty()
    $("#header-title").append("Vehicle List")
    getAllVehicles()
}
let getAllVehicles=() => {
    let token = localStorage.getItem('token');
    $.ajax({
        url: 'http://localhost:8093/travel/api/v1/vehicle',
        type: 'GET',
        headers: {
            "Authorization": `Bearer ${token}`
        },
        success: function (data) {
            vehicleList=[];
            vehicleList=data.object;
            loadDataToVehicleTable()
        },
        error: function (error) {
            console.log(error)
        }
    })
}
let loadDataToVehicleTable=() => {
    $('#vehicle-table-body').empty()
    vehicleList.map((value, index) => {
        let data=`   <tr>
        <td id="${value.id}">
          <div class="d-flex align-items-center">
            <img src="data:image/jpg;base64, ${value.vehicleImage.frontView}"
                 alt=""
                 style="width: 45px; height: 45px"
                 class="rounded-circle"/>
            <div class="ms-3">
              <p class="fw-bold mb-1">${value.brand}</p>
              <p class="text-muted mb-0">
                <span class="bg-info badge" style="font-size:10px">${value.category}</span>
                <span class="bg-danger badge" style="font-size:10px">${value.fuelType}</span>
                <span class="bg-success badge" style="font-size:10px">${value.vehicleType}</span>
                <span class="bg-primary badge" style="font-size:10px">${value.transmissionType}</span>
                <span class="bg-secondary badge" style="font-size:10px">${value.isHybrid?"Hybrid":"Non-Hybrid"}</span>
              </p>
            </div>
          </div>
        </td>
        <td>${value.fuelUsage}</td>
        <td>${value.seatCapacity}</td>
        <td>
          <img src="data:image/jpg;base64, ${value.vehicleImage.rearView}"
               alt=""
               style="width: 60px; height: 40px"/>
          <img src="data:image/jpg;base64, ${value.vehicleImage.sideView}"
               alt=""
               style="width: 60px; height: 40px"/>
          <img src="data:image/jpg;base64, ${value.vehicleImage.frontInterior}"
               alt=""
               style="width: 60px; height: 40px"/>
          <img src="data:image/jpg;base64, ${value.vehicleImage.rearInterior}"
               alt=""
               style="width: 60px; height: 40px"/>
        </td>
        <td style="font-size:12px">${value.driverName}</td>
        <td style="font-size:12px">${value.driverContact}</td>
        <td>
          <img src="data:image/jpg;base64, ${value.driverLicenseImageFront}"
               alt=""
               style="width: 60px; height: 40px"/>
          <img src="data:image/jpg;base64, ${value.driverLicenseImageBack}"
               alt=""
               style="width: 60px; height: 40px"/>
        </td>
        <td><label style="font-size: 12px">Day Rs:${value.feeForDay} <br/> 1Km Rs:${value.feeForDay}</label></td>
        <td>
          <button type="button" class="btn btn-link badge bg-dark-subtle btn-sm btn-rounded">
            Edit
          </button>
        </td>
      </tr>`;
        $('#vehicle-table-body').append(data);
    })
}

let clearVehicleFields= () => {
    $('#vehicle-brand').val("");
    $("#vehicleCategory").val("Select");
    $("#vehicleType").val("Select");
    $('#vehicle-fuel-usage').val("");
    $('#vehicle-seat-capacity').val("");
    $('#vehicle-driver-name').val("");
    $('#vehicle-driver-contact').val("");
    $('#vehicle-remarks').val("");
    $('#vehicle-fee-for-km').val("");
    $('#vehicle-fee-for-day').val("");
    removeUpload();
}

$('#vehicle-table-body').on('click','button',function () {
    let vehId = event.target.parentElement.parentElement.children[0].id;
    vehicleList.map((value, index) => {
        if (value.id === vehId) {
            loadEditeVehicle(value);
        }
    })
})

let loadEditeVehicle=(vehicle)=>{
    nowUpdatingVehicle=vehicle;
    $('#vehicle-brand').val(vehicle.brand);
    $("#vehicleCategory").val(
        vehicle.category==="Economy"?"1"
            :vehicle.category==="Mid-Range"?"2"
            :vehicle.category==="Luxury"?"3"
            :vehicle.category==="Super Luxury"?"4"
                    :"Select"
    );
    $("#vehicleType").val(
        vehicle.vehicleType==="Car"?"1"
            :vehicle.vehicleType==="Van (7-12 seats)"?"2"
            :vehicle.vehicleType==="Luxury"?"3"
            :vehicle.vehicleType==="Bus (30-40 seats)"?"4"
                    :"Select"
    )
    if (vehicle.fuelType==="Diesel"){
        $('#diesel').prop('checked', true)
    }else {
        $('#petrol').prop('checked', true)
    }
    $('#isAutoGear').prop("checked", vehicle.transmissionType==="AUTO");
    $('#isHybrid').prop("checked", vehicle.isHybrid==="true");
    $('#vehicle-fuel-usage').val(vehicle.fuelUsage);
    $('#vehicle-seat-capacity').val(vehicle.seatCapacity);
    $('#vehicle-driver-name').val(vehicle.driverName);
    $('#vehicle-driver-contact').val(vehicle.driverContact);
    $('#vehicle-remarks').val(vehicle.remarks);
    $('#vehicle-fee-for-day').val(vehicle.feeForDay);
    $('#vehicle-fee-for-km').val(vehicle.feeForKm);

    $('#driverLicenseFrontImageWrap').hide();
    $('#driverLicenseFrontFileUploadImage').attr('src', "data:image/jpg;base64,"+ vehicle.driverLicenseImageFront);
    $('#driverLicenseFrontFileUploadContent').show();

    $('#driverLicenseBackImageWrap').hide();
    $('#driverLicenseBackFileUploadImage').attr('src', "data:image/jpg;base64,"+ vehicle.driverLicenseImageBack);
    $('#driverLicenseBackFileUploadContent').show();

    $('#vehicleFrontImageWrap').hide();
    $('#vehicleFrontFileUploadImage').attr('src', "data:image/jpg;base64,"+ vehicle.vehicleImage.frontView);
    $('#vehicleFrontFileUploadContent').show();

    $('#vehicleRearImageWrap').hide();
    $('#vehicleRearFileUploadImage').attr('src', "data:image/jpg;base64,"+ vehicle.vehicleImage.rearView);
    $('#vehicleRearFileUploadContent').show();

    $('#vehicleSideImageWrap').hide();
    $('#vehicleSideFileUploadImage').attr('src', "data:image/jpg;base64,"+ vehicle.vehicleImage.sideView);
    $('#vehicleSideFileUploadContent').show();

    $('#vehicleFrontInteriorImageWrap').hide();
    $('#vehicleFrontInteriorFileUploadImage').attr('src', "data:image/jpg;base64,"+ vehicle.vehicleImage.frontInterior);
    $('#vehicleFrontInteriorFileUploadContent').show();

    $('#vehicleRearInteriorImageWrap').hide();
    $('#vehicleRearInteriorFileUploadImage').attr('src', "data:image/jpg;base64,"+ vehicle.vehicleImage.rearInterior);
    $('#vehicleRearInteriorFileUploadContent').show();

    $('#btnCreateVehicle').hide();
    $('#btnUpdateVehicle').show();
    $('#btnDeleteVehicle').show();
    $('#btnCancelUpdateVehicle').show();
    $('#newVehicleContainer').show();
    $('#vehicleListContainer').hide();
    $('#header-title').text("Edit Vehicle")
}

$('#btnCancelUpdateVehicle').click(function () {
    clearVehicleFields();
    showVehicleList();
})

$('#btnUpdateVehicle').click(function () {
    let vehicle = {
        id: nowUpdatingVehicle.id,
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
        feeForDay: parseInt($('#vehicle-fee-for-day').val()),
        feeForKm: parseInt($('#vehicle-fee-for-km').val()),
        packageCategoryId: "",
    };
    const json = JSON.stringify(vehicle);
    const blob = new Blob([json], {
        type: 'application/json'
    });

    const formData = new FormData();
    formData.append("vehicle", blob);
    formData.append('driver_license_image_front',base64ToFile($('#driverLicenseFrontFileUploadImage').attr('src')));
    formData.append('driver_license_image_back', base64ToFile($('#driverLicenseBackFileUploadImage').attr('src')));
    formData.append('side_view', base64ToFile($('#vehicleSideFileUploadImage').attr('src')));
    formData.append('front_view',base64ToFile($('#vehicleFrontFileUploadImage').attr('src')));
    formData.append('rear_view', base64ToFile($('#vehicleRearFileUploadImage').attr('src')));
    formData.append('front_interior', base64ToFile($('#vehicleFrontInteriorFileUploadImage').attr('src')));
    formData.append('rear_interior', base64ToFile($('#vehicleRearInteriorFileUploadImage').attr('src')));
    let token = localStorage.getItem('token');
    $.ajax({
        url: 'http://localhost:8093/travel/api/v1/vehicle/save',
        type: 'POST',
        cache: false,
        enctype: 'multipart/form-formData',
        data: formData,
        contentType: false,
        processData: false,
        headers: {
            "Authorization": `Bearer ${token}`
        },
        success: function (data) {
            showToast("Success","Vehicle \"" + data.object.brand +"\"' Update Successfully !");
            // getAllVehicles();
            clearVehicleFields();
            showVehicleList();
        },
        error: function (error) {
            console.log(error)
        }
    });
})

$('#btnDeleteVehicle').click(function () {
    $('#conformation-alert').modal('show')
    $('#model-body').empty();
    $('#model-body').append("Conform Delete Vehicle");
})

$('#conformation-ok-btn').click(function () {
    if ($('#model-body').text().endsWith("Vehicle")) {
        let token = localStorage.getItem('token');
        let params = {
            id: nowUpdatingVehicle.id,
            imageId:nowUpdatingVehicle.vehicleImage.id
        }
        $.ajax({
            url: 'http://localhost:8093/travel/api/v1/vehicle' + '?' + $.param(params),
            type: 'DELETE',
            processData: false,
            contentType: false,
            cache: false,
            headers: {
                "Authorization": `Bearer ${token}`
            },
            success: function (data) {
                console.log(data)
                console.log(nowUpdatingVehicle)
                showToast("Success", "Vehicle \"" + nowUpdatingVehicle.brand + "\"' Delete Successfully !")
                showVehicleList()
                $('#conformation-alert').modal('hide');
            },
            error: function (error) {
                console.error(error);
            }
        });
    }
})