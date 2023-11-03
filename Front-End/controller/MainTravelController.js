/**
 * author : Sudeera Madushan
 * date : 11/3/2023
 * project : Front-End
 */
let placeList=[]

$('#btnPlaceList').click(function (){
    console.log("ok")
    getAllPlaces();
})
const showPlaceList = () => {
    getAllPlaces()
};
let getAllPlaces=()=>{
    $.ajax({
        url: 'http://localhost:8095/travel/api/v1/area',
        type: 'GET',
        // headers: {
        //     "Authorization": `Bearer ${token}`
        // },
        success: function (data) {
            placeList=data.object
            loadPlacesTable();
        },
        error: function (error) {
            console.log(error)
        }
    })
}
const loadPlacesTable = () => {
    $('#hotel-table-body').empty()
    placeList.map((value, index) => {
    let data=`
      <tr>
        <td id="${value.id}">
          <div class="d-flex align-items-center">
            <img src="data:image/jpg;base64, ${value.image}"
                 alt=""
                 style="width: 45px; height: 45px"
                 class="rounded-circle"/>
            <div class="ms-3">
              <p class="fw-bold mb-1">${value.name}</p>
              <p class="text-muted mb-0" style="font-size: 12px">
                <a href="${value.mapLocation}" class="bg-info badge " target="_blank">map</a>
              </p>
            </div>
          </div>
        </td>
        <td>`

        let params = {
            id: value.id
        }
        $.ajax({
            url: 'http://localhost:8095/travel/api/v1/areaImage/all' + '?' + $.param(params),
            type: 'GET',
            headers: {
                "Authorization": `Bearer ${token}`
            },
            success: function (data) {
                for (let i = 1; i < data.object; i++) {
                    data = data + `<img src="data:image/jpg;base64, ${data.object[i]}"
               alt="hotel image"
               style="width: 60px; height: 40px"/>`

                }
            },
            error: function (error) {
                console.log(error)
            }
        })

    data=data+`</td>
        <td>
          <button type="button" class="btn btn-link badge bg-secondary btn-sm btn-rounded" onclick="">
            Edit
          </button>
        </td>
      </tr>`

    $('#place-table-body').append(data);
})
}
$('#btnCreatePlaceOk').click(function () {
    console.log();
    const formData = new FormData();
    for (let i = 2; i < $('#placeImageContainer').children().length-1; i++) {
        formData.append("images", base64ToFile($('#placeImageContainer').children().eq(1).children('img').eq(0).attr('src')))
    }
    formData.append("name",$('#placeName').val());
    formData.append("description", $('#placeDescription').val());
    formData.append("areaLocation", getIFrameSrcPlace($('#areaLocation').val()));
    formData.append("image", base64ToFile($('#placeImageContainer').children().eq(1).children('img').eq(0).attr('src')));


    $.ajax({
        url: 'http://localhost:8095/travel/api/v1/area',
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
            showToast("Success","Place \"" + data.object.name +"\"' Save Successfully !");
            clearPlaceFields();
            showPlaceList();
        },
        error: function (error) {
            console.log(error)
        }
    });
})
let clearPlaceFields=() => {
    $('#placeName').val("");
    $('#placeDescription').val("")
    $('#areaLocation').val("")
    $('#hotelRemarks').val("")
    let length = $('#placeImageContainer').children().length-1;
    for (let i = 1; i < length; i++) {
        $('#placeImageContainer').children().eq(1).remove();
    }
}
let getIFrameSrcPlace=(data)=>{
    var srcRegex = /src="(.*?)"/;
    var match = srcRegex.exec(data);
    if (match && match[1]) {
        return match[1];
    } else {
        console.log('src attribute not found');
        return null;
    }
}