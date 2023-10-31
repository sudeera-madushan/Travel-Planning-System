/**
 * author : Sudeera Madushan
 * date : 10/29/2023
 * project : Front-End
 */

$(document).ready(function() {
    $('#area-list-container').hide();
    $('#area-detail-page').hide();
    // getAllAreas();
    getAllHotel();

});
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

    data.object.map((value, index) => {
        console.log(value)
        $('#hotelCardContainer').empty()
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
