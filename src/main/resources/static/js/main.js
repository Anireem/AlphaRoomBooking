async function doRequest(url, method = 'GET', data = null) {
    try {
        const headers = {}
        let body
        if (data) {
            headers['Content-Type'] = 'application/json'
            body = JSON.stringify(data)
        }
        const response = await fetch(url, {
            method,
            headers,
            body
        })
        return await response.json()
    } catch (e) {
    }
}


function getFormData(form){

    var unindexed_array = form.serializeArray();
    var indexed_array = {};

    $.map(unindexed_array, function(n, i){
        indexed_array[n['name']] = n['value'];
    });

    return indexed_array;
}

function getInputValue(id) {
    var els = document.forms["user"].getElementsByTagName("input")
    for (let i = 0; i < els.length; i++) {
        if (els[i].id == id) {
            return els[i].value
        }
    }
}

async function removeBooking(id) {
    bookingsURL = 'http://' + window.location.host + '/bookings/';
    bookingURL = bookingsURL + id + '/delete';
    promice = doRequest(bookingURL, 'POST')
    let requestResult = await promice;
    window.location.replace(bookingsURL)
}

async function removeRoom(id) {
    roomsURL = 'http://' + window.location.host + '/rooms/';
    roomURL = roomsURL + id + '/delete';
    promice = doRequest(roomURL, 'POST')
    let requestResult = await promice;
    window.location.replace(roomsURL)
}

async function removeUser(id) {
    usersURL = 'http://' + window.location.host + '/users/';
    userURL = usersURL + id + '/delete';
    promice = doRequest(userURL, 'POST')
    let requestResult = await promice;
    window.location.replace(usersURL)
}

async function saveUser() {
    var id = getInputValue("id")
    var username = getInputValue("username")
    var password = getInputValue("password")
    var roles = getInputValue("roles")
    console.log("test")
}
