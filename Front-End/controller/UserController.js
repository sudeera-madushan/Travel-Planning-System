/**
 * author : Sudeera Madushan
 * date : 11/3/2023
 * project : Front-End
 */


let userList=[]
$(document).ready(function() {
    $("#newUserContainer").hide()
    $("#userListContainer").hide()
});
$('#btnUserList').click(function (){
    $('#newUserContainer').hide()
    $('#userListContainer').show()
    $('#placeListContainer').hide()
    $('#newPlaceContainer').hide()


    $('#hotel-Section').hide();
    $('#guideSection').hide();
    $('#vehicleSection').hide();
    $('#header-title').text("User List")
    getAllPlaces();
})
$('#btnCreateUser').click(function (){
    $('#userListContainer').hide()
    $('#newUserContainer').show()
    $('#placeListContainer').hide()
    $('#newPlaceContainer').hide()
    $('#hotel-Section').hide();
    $('#guideSection').hide();
    $('#vehicleSection').hide();
    $('#header-title').text("Create User")
})
const clearUserFields = () => {
    $('#userUsername').val("")
    $('#userPassword').val("")
};
const hideCreateUser = () => {
    $('#newUserContainer').hide();
    $('#userListContainer').show();
};
$('#btnCreateUserOk').click(function () {
    let role=[];
    if ($('#userRoleGuideAdmin').is(":checked")){
        role.unshift("ROLE_GUIDE")
    }
    if ($('#userRoleHotelAdmin').is(":checked")){
        role.unshift("ROLE_HOTEL")
    }
    if ($('#userRoleMainTravelAdmin').is(":checked")){
        role.unshift("ROLE_BOOKING")
    }
    if ($('#userRoleVehicleAdmin').is(":checked")){
        role.unshift("ROLE_VEHICLE")
    }
    if ($('#userRoleUserAdmin').is(":checked")){
        role.unshift("ROLE_ADMIN")
    }
    let user = {
        id: null,
        username: $('#userUsername').val(),
        password: $('#userPassword').val(),
        role: role
    }
    $.ajax({
        type: "POST",
        url: "http://localhost:8091/travel/api/v1/user/admin",
        data: JSON.stringify(user),
        contentType: "application/json",
        headers: {
            "Authorization": `Bearer ${token}`
        },
        success: function(response) {
            showToast("Success","Vehicle \"" + response.object.username +"\"' Save Successfully !");
            clearUserFields();
            hideCreateUser()
            getAllUsers();
        },
        error: function(err) {
            console.log(err)
            alert("user save unsuccessfully !");
        }
    });

})

const loadDataToUserTable = () => {
    $('#user-table-body').empty()
    let row=``;
    userList.map((value, index) => {
        let role = value.role;
        let data=`
      <tr id="${value.id}">
        <td>${value.username}</td>
        <td>${$.inArray("ROLE_HOTEL",role) !==-1}</td>
        <td>${$.inArray("ROLE_VEHICLE",role) !==-1}</td>
        <td>${$.inArray("ROLE_GUIDE",role) !==-1}</td>
        <td>${$.inArray("ROLE_BOOKING",role) !==-1}</td>
        <td>${$.inArray("ROLE_ADMIN",role) !==-1}</td>
        <td>
          <button type="button" class="btn btn-link badge bg-secondary btn-sm btn-rounded" onclick="">
            Edit
          </button>
        </td>
      </tr>`

        $('#user-table-body').append(data);
    })
};
let getAllUsers=()=> {
    // let token = localStorage.getItem('token');
    $.ajax({
        url: 'http://localhost:8091/travel/api/v1/user/all',
        type: 'GET',
        headers: {
            "Authorization": `Bearer ${token}`
        },
        success: function (data) {
            console.log(data)
            userList=data.object;
            loadDataToUserTable()
        },
        error: function (error) {
            console.log(error)
        }
    })
}