/**
 * author : Sudeera Madushan
 * date : 10/20/2023
 * project : Front-End
 */
import {Guide} from "../model/Guide.js";
$('#btnNewGuide').click(function () {
    $('#new-guide').show();
    $('#guide-list').hide();
    $('#notification-header').text("Success");
    $('#notification-time').text("");
    $('#notification-desc').text("Guide Save Successfully !");
    $('.toast').toast('show')


});
$('#btnGuideList').click(function () {
    getAllGuides();
    $('#new-guide').hide();
    $('#guide-list').show();
});

$('#btnCreateGuide').click(function () {
    let guide = {
        id: null,
        name: $('#name').val(),
        address: $('#address').val(),
        gender: $("input[name='gender']:checked").val(),
        contact: $('#contact').val(),
        age: parseInt($('#age').val()),
        experience: parseInt($('#experience').val()),
        manDayValue: parseFloat($('#manDayValue').val()),
        remarks: $('#remarks').val()
    };
    const json = JSON.stringify(guide);
    const blob = new Blob([json], {
        type: 'application/json'
    });
    const formData = new FormData();
    formData.append("guide", blob);
    formData.append('image', $('#guideImage')[0].files[0]);
    formData.append('nic_image_front', $('#guideNfImage')[0].files[0]);
    formData.append('nic_image_back', $('#guideNbImage')[0].files[0]);
    formData.append('guide_id_image_front', $('#guideIfImage')[0].files[0]);
    formData.append('guide_id_image_back', $('#guideIbImage')[0].files[0]);
    $.ajax({
        url: 'http://localhost:8092/travel/api/v1/guide/save',
        type: 'POST',
        data: formData,
        cache: false,
        enctype: 'multipart/form-formData',
        contentType: false,
        processData: false,
        success: function (data) {
            $('#notification-header').text("Success");
            $('#notification-time').text("");
            $('#notification-desc').text("Guide Save Successfully !");
            $('.toast').toast('show')

        },
        error: function (error) {
            console.error(error);
        }
    });
});

let getAllGuides= () => {
    $.ajax({
        url: 'http://localhost:8092/travel/api/v1/guide/getAll',
        type: 'GET',
        processData: false,
        contentType: false,
        cache: false,
        success: function (data) {
            $('#guide-table-body').empty();
            loadDataToGuideTable(data.object);
        },
        error: function (error) {
            console.error(error);
        }
    });
}
let loadDataToGuideTable= (guideList) => {
    
    guideList.map((value, index) => {
        let data=`<tr>
        <td>
          <div class="d-flex align-items-center">
            <img src="data:image/jpg;base64, ${value.image}"
                    alt=""
                    style="width: 45px; height: 45px"
                    class="rounded-circle"/>
            <div class="ms-3">
              <p class="fw-bold mb-1">${value.name}</p>
              <p class="text-muted mb-0">${value.address}</p>
            </div>
          </div>
        </td>
        <td>${value.gender}</td>
        <td>${value.age}</td>
        <td>${value.experience}</td>
        <td>${value.manDayValue}</td>
        <td>${value.contact}</td>
        <td>
          <img src="data:image/jpg;base64, ${value.nicImageFront}"
                 alt=""
                 style="width: 60px; height: 40px"/>
          <img src="data:image/jpg;base64, ${value.nicImageBack}"
                 alt=""
                 style="width: 60px; height: 40px"/>
        </td>
        <td>
          <img src="data:image/jpg;base64, ${value.guideIdImageFront}"
                 alt=""
                 style="width: 60px; height: 40px"/>
          <img src="data:image/jpg;base64, ${value.guideIdImageBack}"
                 alt=""
                 style="width: 60px; height: 40px"/>
        </td>
        <td><label style="font-size: 12px">${value.remarks}</label></td>
        <td>
          <button type="button" class="btn btn-link btn-sm btn-rounded">
            Edit
          </button>
        </td>
      </tr>`;

        $('#guide-table-body').append(data);
    })
}


getAllGuides();