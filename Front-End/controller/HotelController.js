/**
 * author : Sudeera Madushan
 * date : 10/25/2023
 * project : Front-End
 */
let hotelList=[];
let nowUpdatingHotel;
$(document).ready(function() {
    $("#newHotelContainer").hide()
    $("#hotelListContainer").hide()
    // $("#newVehicleContainer").hide()

    showHotelList()
});
$('#btnNewHotel').click(function () {
    showCreateHotel();
})

let showHotelList=() => {

    $('#header-title').empty()
    $("#header-title").append("Hotel List")
    $("#hotelListContainer").show();
    $("#newHotelContainer").hide()
    hideNav();
    getAllHotels();

}

$('#btnHotelList').click(function () {
    showHotelList();
})
let showCreateHotel=() => {
    $('#header-title').empty()
    $("#header-title").append("Create Hotel")
    $("#newHotelContainer").show();
    $("#hotelListContainer").hide();
    $('#btnCreateHotel').show();
    $('#btnUpdateHotel').hide();
    $('#btnDeleteHotel').hide();
    $('#btnCancelUpdateHotel').hide();
    hideNav();
}
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

    // for (let i = 1; i < $('#hotelRoomContainer').children().length; i++) {
    //         roomArr.unshift({
    //             type: $('#hotelRoomContainer').children().eq(i).children().eq(0).val()
    //             // imageData: $('#hotelRoomContainer').children().eq(i).children().eq(1).children().eq(0).attr('src')
    //         });
    //     formData.append("roomType",base64ToFile($('#hotelRoomContainer').children().eq(i).children().eq(1).children().eq(0).attr('src')))
    // }

    let hotel = {
        id: null,
        name: $('#hotelName').val(),
        category: $('#hotelCategory').find("option:selected").text(),
        location: $('#hotelLocation').val(),
        email: $('#hotelEmail').val(),
        mapLocation: $('#hotelLocationMap').val(),
        contactNoOne: $('#hotelContactOne').val(),
        contactNoTwo: $('#hotelContactTwo').val(),
        petIsAllowed: $('#petIsAllowed').is(":checked"),
        cancellationCriteriaIsFree: $('#cancellationCriteriaIsFree').is(":checked"),
        cancellationFee: parseFloat($('#cancellationFee').val()),
        packageCategoryId: "",
        optionList: [
            {
            name:"Full Board with A/C Luxury Room Double",
            charge:parseFloat($('#fullBoardWithACLuxuryRoomDoubleCharge').val())
            },{
            name:"Half Board with A/C Luxury Room Double",
            charge:parseFloat($('#halfBoardWithACLuxuryRoomDoubleCharge').val())
            },{
            name:"Full Board with A/C Luxury Room Triple",
            charge:parseFloat($('#fullBoardWithACLuxuryRoomTripleCharge').val())
            },{
            name:"Half Board with A/C Luxury Room Triple",
            charge:parseFloat($('#halfBoardWithACLuxuryRoomTripleCharge').val())
            },
        ]
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
              <p class="text-muted mb-0" style="font-size: 12px">${value.location}
                <a href="${value.mapLocation}" class="bg-info badge " target="_blank">map</a>
                <a href="mailto:"${value.email}" class="bg-success badge " target="_blank">email</a>
              </p>
            </div>
          </div>
        </td>
        <td>${value.category}</td>
        <td>
          <a href="${value.contactNoOne}" class="m-0">${value.contactNoOne}</a>
          <a href="${value.contactNoTwo}" class="m-0">${value.contactNoTwo}</a>
        </td>
        <td>${value.petIsAllowed?"Allow":"Not Allow"}</td>
        <td><i class="bg-danger badge">${value.cancellationCriteriaIsFree?"free":"pay"}</i>${
            !value.cancellationCriteriaIsFree?" "+value.cancellationFee:""}</td>
        <td>`
        for (let i = 1; i < value.hotelImages.length; i++) {
            data=data+`          <img src="data:image/jpg;base64, ${value.hotelImages[i].image}"
               alt="hotel image"
               style="width: 60px; height: 40px"/>`

        }
        data=data+`</td>
        <td>
           <p class="mb-0 mt-0" style="font-size: 12px">Full :${value.optionList[0].charge}</p>
           <p class="mb-0 mt-0" style="font-size: 12px">Half :${value.optionList[1].charge}</p>
        </td>
        <td>
           <p class="mb-0 mt-0" style="font-size: 12px">Full :${value.optionList[2].charge}</p>
           <p class="mb-0 mt-0" style="font-size: 12px">Falf :${value.optionList[3].charge}</p>
        </td>
        <td>
          <button type="button" class="btn btn-link badge bg-secondary btn-sm btn-rounded" onclick="">
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

$('#cancellationCriteriaIsFree').click(function () {

    $("#cancellationFeeEnabel").prop("disabled", $('#cancellationCriteriaIsFree').is(":checked"));
})

$('#hotel-table-body').on('click','button',function () {
    let hotelId = event.target.parentElement.parentElement.children[0].id;
    console.log(hotelId)
    hotelList.map((value, index) => {
        if (value.id === hotelId) {
            loadEditeHotel(value)
        }
    })
})
let loadEditeHotel=(hotel)=> {
    nowUpdatingHotel = hotel;
    $('#hotelName').val(hotel.name);
    $('#hotelLocation').val(hotel.location)
    $('#hotelLocationMap').val(hotel.mapLocation)
    $('#hotelEmail').val(hotel.email)
    console.log(hotel.category==="4 Star")
    $('#hotelCategory').val(
        hotel.category==="2 Star"?"2 Star":
            hotel.category==="3 Star"?"3 Star":
                hotel.category==='4 Star'?'4 Star':
                    hotel.category==="5 Star"?"5 Star":"Select"
    )
    if (hotel.petIsAllowed){
        $('#petIsAllowed').prop('checked', true)
    }else {
        $('#petIsAllowed').prop('checked', false)
    }
    if (hotel.cancellationCriteriaIsFree){
        $('#cancellationCriteriaIsFree').prop('checked', true)
    }else {
        $('#cancellationCriteriaIsFree').prop('checked', false)
    }
    $('#newHotelContainer').show()
    $('#header-title').text("Edit Hotel")
}
