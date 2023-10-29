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
        console.log(index,index%3===0);
        if (index%3===0) {
            data = `<div class="card-columns row">`;
        }
        in
                
              // </div>`
    })
}