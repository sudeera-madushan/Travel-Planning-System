/**
 * author : Sudeera Madushan
 * date : 10/29/2023
 * project : Front-End
 */

$(document).ready(function() {
    getAllAreas();
});

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
    console.log()

    data.object.map((value, index) => {
        let data=``;
        console.log(value);
        // if (index%3===0) {
        //     data = `<div class="card-columns row">`;
        // }
        data=` <div class="card col m-4 p-0" >
                    <a href="#">
                        <img class="card-img-top" src="data:image/jpg;base64, ${value.images.length>0?value.images[0]:""}" alt="Card image cap">
                        <div class="card-body">
                            <h5 class="card-title">${value.description}</h5>
                            <p class="card-text">
                          
                            </p>
                            <p class="card-text"><small class="text-muted"><i class="fas fa-eye"></i>1000<i class="far fa-user"></i>admin<i class="fas fa-calendar-alt"></i>Jan 20, 2018</small></p>
                        </div>
                    </a>
                </div>`;
        $('#areaCardContainer').append(data)
    })
}