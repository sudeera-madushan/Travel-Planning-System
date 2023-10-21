/**
 * author : Sudeera Madushan
 * date : 10/20/2023
 * project : Front-End
 */
import {Guide} from "../model/Guide.js";
let guideList=[];
let nowUpdatingGuide;
$('#btnNewGuide').click(function () {
    showNewGuide();
    clearGuideFields();
});
$('#btnGuideList').click(function () {
    showGuideList()

});
$('#btn-home').click(function () {
    showHome()

});
let showHome= () => {
    $('#btn-home').addClass('navbar-a-focus')
    $('#btnGuideList').removeClass('navbar-a-focus')
    $('#btnNewGuide').removeClass('navbar-a-focus')

    $('#guide-list').hide();
    $('#new-guide').hide();

}

let showNewGuide= () => {
    $('#btnNewGuide').addClass('navbar-a-focus')
    $('#btnGuideList').removeClass('navbar-a-focus')
    $('#btn-home').removeClass('navbar-a-focus')
    $('#new-guide').show();
    $('#guide-list').hide();
    $('#btnUpdateGuide').hide();
    $('#btnDeleteGuide').hide();
    $('#btnCancelUpdateGuide').hide();
    $('#btnCreateGuide').show();
}
let showGuideList= () => {
    $('#btnNewGuide').removeClass('navbar-a-focus')
    $('#btn-home').removeClass('navbar-a-focus')
    $('#btnGuideList').addClass('navbar-a-focus')
    getAllGuides();
    $('#new-guide').hide();
    $('#guide-list').show();
}
let base64ToFile= (imageDataUrl) => {
    var dataUrlParts = imageDataUrl.split(",");
    var contentType = dataUrlParts[0].split(":")[1].split(";")[0];
    var byteCharacters = atob(dataUrlParts[1]);
    var byteNumbers = new Array(byteCharacters.length);
    for (var i = 0; i < byteCharacters.length; i++) {
        byteNumbers[i] = byteCharacters.charCodeAt(i);
    }
    var byteArray = new Uint8Array(byteNumbers);
    return new Blob([byteArray], { type: contentType });
}
$('#btnUpdateGuide').click(function () {
    let guide = {
        id: nowUpdatingGuide.id,
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
    formData.append('image', base64ToFile($('#guideFileUploadImage').attr('src')) );
    formData.append('nic_image_front', base64ToFile($('#guideNfFileUploadImage').attr('src')));
    formData.append('nic_image_back', base64ToFile($('#guideNbFileUploadImage').attr('src')));
    formData.append('guide_id_image_front', base64ToFile($('#guideIfFileUploadImage').attr('src')));
    formData.append('guide_id_image_back', base64ToFile($('#guideIfFileUploadImage').attr('src')));
    $.ajax({
        url: 'http://localhost:8092/travel/api/v1/guide',
        type: 'PATCH',
        data: formData,
        cache: false,
        enctype: 'multipart/form-formData',
        contentType: false,
        processData: false,
        success: function (data) {
            console.log(data);
            showToast("Success","Guide \"" + data.object.name+"\"' Update Successfully !")
            showGuideList();
        },
        error: function (error) {
            console.error(error);
        }
    });
})
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
            showToast("Success","Guide \"" + data.object.name +"\"' Save Successfully !")
            getAllGuides();
        },
        error: function (error) {
            console.error(error);
        }
    });
});
let showToast= (type,name) => {
    $('#notification-header').text(type);
    $('#notification-time').text("");
    $('#notification-desc').text(name);
    $('.toast').toast('show')
}
let getAllGuides= () => {
    $.ajax({
        url: 'http://localhost:8092/travel/api/v1/guide/getAll',
        type: 'GET',
        processData: false,
        contentType: false,
        cache: false,
        success: function (data) {
            $('#guide-table-body').empty();
            guideList=[];
            guideList=data.object;
            loadDataToGuideTable()
        },
        error: function (error) {
            console.error(error);
        }
    });
}
let loadDataToGuideTable= () => {
    
    guideList.map((value, index) => {
        let data=`<tr>
        <td id="${value.id}">
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
let clearGuideFields= () => {
        $('#address').val("");
        $('#name').val("");
        $('#contact').val("");
        $('#age').val("");
        $('#experience').val("");
        $('#manDayValue').val("");
        $('#remarks').val("");
        removeUpload();
}
$('#guide-table-body').on('click','button',function () {
    let guideId = event.target.parentElement.parentElement.children[0].id;
    guideList.map((value, index) => {
        if (value.id === guideId) {
            loadEditeGuide(value);
        }
    })
})

let loadEditeGuide=(guide)=>{
    nowUpdatingGuide=guide;
    
 
    $('#address').val(guide.address);
    $('#name').val(guide.name);
    $('#contact').val(guide.contact);
    $('#age').val(guide.age);
    $('#experience').val(guide.experience);
    $('#manDayValue').val(guide.manDayValue);
    $('#remarks').val(guide.remarks);

    if (guide.gender==="male"){
        $('#male').prop('checked', true)
    }else {
        $('#female').prop('checked', true)
    }
    
    $('#guideImageWrap').hide();
    $('#guideFileUploadImage').attr('src', "data:image/jpg;base64,"+ guide.image);
    $('#guideFileUploadContent').show();
    
    $('#guideNfImageWrap').hide();
    $('#guideNfFileUploadImage').attr('src', "data:image/jpg;base64,"+ guide.nicImageFront);
    $('#guideNfFileUploadContent').show();
    
    $('#guideNbImageWrap').hide();
    $('#guideNbFileUploadImage').attr('src', "data:image/jpg;base64,"+ guide.nicImageBack);
    $('#guideNbFileUploadContent').show();
    
    $('#guideIfImageWrap').hide();
    $('#guideIfFileUploadImage').attr('src', "data:image/jpg;base64,"+ guide.nicImageFront);
    $('#guideIfFileUploadContent').show();
    
    $('#guideIbImageWrap').hide();
    $('#guideIbFileUploadImage').attr('src', "data:image/jpg;base64,"+ guide.nicImageBack);
    $('#guideIbFileUploadContent').show();

    $('#btnCreateGuide').hide();
    $('#btnUpdateGuide').show();
    $('#btnDeleteGuide').show();
    $('#btnCancelUpdateGuide').show();
    $('#new-guide').show();
    $('#guide-list').hide();
}

$('#btnDeleteGuide').click(function () {
    $('#conformation-alert').modal('show')
})

$('#btnCancelUpdateGuide').click(function () {
    showGuideList();
})

$('#conformation-ok-btn').click(function () {
    let params={
        id:nowUpdatingGuide.id
    }
    $.ajax({
        url: 'http://localhost:8092/travel/api/v1/guide'+ '?' + $.param(params),
        type: 'DELETE',
        processData: false,
        contentType: false,
        cache: false,
        success: function (data) {
            showGuideList()
            showToast("Success","Guide \"" + nowUpdatingGuide.name +"\"' Delete Successfully !")
            $('#conformation-alert').modal('hide');
        },
        error: function (error) {
            console.error(error);
        }
    });
})
$('#conformation-close').click(function () {
    $('#conformation-alert').modal('hide');
})
$('#conformation-close-btn').click(function () {
    $('#conformation-alert').modal('hide')
})
showHome();