/**
 * author : Sudeera Madushan
 * date : 10/25/2023
 * project : Front-End
 */
let hotelList=[];
$(document).ready(function() {
    $("#newHotelContainer").hide()
    // $("#hotelListContainer").hide()
    // $("#newVehicleContainer").hide()
    getAllHotels()
});

$('#btnCreateHotel').click(function () {
    const formData = new FormData();
    let imageArr=[];
    let roomArr=[];
    for (let i = 1; i < $('#hotelImageContainer').children().length-1; i++) {
        formData.append("images", base64ToFile($('#hotelImageContainer').children().eq(i).children('img').eq(0).attr('src')))
        // imageArr.unshift(
        //     {hotelImages:{
        //         type: "image/jpeg",
        //         imageData:$('#hotelImageContainer').children().eq(i).children('img').eq(0).attr('src')}})
    }

    for (let i = 1; i < $('#hotelRoomContainer').children().length; i++) {
            roomArr.unshift({
                type: $('#hotelRoomContainer').children().eq(i).children().eq(0).val()
                // imageData: $('#hotelRoomContainer').children().eq(i).children().eq(1).children().eq(0).attr('src')
            });
        formData.append("roomType",base64ToFile($('#hotelRoomContainer').children().eq(i).children().eq(1).children().eq(0).attr('src')))
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
        roomTypes: roomArr
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
            showToast("Success","Vehicle \"" + data +"\"' Save Successfully !");
            console.log(data)
        },
        error: function (error) {
            console.log(error)
        }
    });
})


const loadDataToHotelTable = () => {

    $('#hotel-table-body').empty()
    hotelList.map((value, index) => {
        console.log(value.name ,  value.hotelImages.length)
        let data=`
      <tr>
        <td id="${value.id}">
          <div class="d-flex align-items-center">
            <img src="data:image/jpg;base64, ${value.hotelImages.length!==0?value.hotelImages[0].image:null}"
                 alt=""
                 style="width: 45px; height: 45px"
                 class="rounded-circle"/>
            <div class="ms-3">
              <p class="fw-bold mb-1">${value.name}</p>
              <p class="text-muted mb-0" style="font-size: 12px">${value.}
                <a href="fsa l; afasj afaaj;a a;jf;a" class="bg-info badge " target="_blank">map</a>
              </p>
            </div>
          </div>
        </td>
        <td>Luxury</td>
        <td>
          <a href="tel:+9488787554545" class="m-0">+9488787554545</a>
          <a href="tel:+9488787554545" class="m-0">+9488787554545</a>
        </td>
        <td>Allow</td>
        <td><i class="bg-danger badge">pay</i> 500$</td>
        <td>
          <img src="data:image/jpg;base64, "
               alt=""
               style="width: 60px; height: 40px"/>
          <img src="data:image/jpg;base64, "
               alt=""
               style="width: 60px; height: 40px"/>
        </td>
        <td>
          <button type="button" class="btn bg-success  badge ">Rooms</button>
          <button type="button" class="btn btn-link badge bg-secondary btn-sm btn-rounded">
            Edit
          </button>
        </td>
      </tr>`

        $('#hotel-table-body').append(data);
    })
}
let getAllHotels=()=> {
    // let token = localStorage.getItem('token');
    $.ajax({
        url: 'http://localhost:8094/travel/api/v1/hotel',
        type: 'GET',
        // headers: {
        //     "Authorization": `Bearer ${token}`
        // },
        success: function (data) {
            console.log(data.object)
            hotelList = [];
            hotelList = data.object;
            loadDataToHotelTable()
        },
        error: function (error) {
            console.log(error)
        }
    })
}