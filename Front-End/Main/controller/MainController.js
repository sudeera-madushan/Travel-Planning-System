/**
 * author : Sudeera Madushan
 * date : 10/29/2023
 * project : Front-End
 */

$(document).ready(function() {
    // $('#area-list-container').hide();
    $('#area-detail-page').hide();
    getAllAreas();

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
    $('#areaDetailsMapLocation').empty()
    $('#areaDetailsMapLocation').append(area.areaLocation)
}