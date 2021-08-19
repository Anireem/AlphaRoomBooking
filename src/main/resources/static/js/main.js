api = 'http://' + window.location.host + '/api/v1'

tableCreate()
const interval = setInterval(function() {
    if (true) {
        tableCreate()
    }
}, 10000)


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

function getInputValue(id, tagName) {
    var els = document.forms["form"].getElementsByTagName(tagName)
    for (let i = 0; i < els.length; i++) {
        if (els[i].id == id) {

            if (tagName == "select") {

                if (els[i].multiple) {
                    selectedOptions = getSelectedOptions(els[i].options)
                    return selectedOptions
                } else {
                    return els[i].value
                }
            } else {
                if (els[i].type == "checkbox")
                    return els[i].checked;
                else
                    return els[i].value
            }
        }
    }
}

function getSelectedOptions(options) {
    selectedOptions = []
    for (let i = 0; i < options.length; i++) {
        if (options[i].selected)
            selectedOptions.push(options[i].value)
    }
    return selectedOptions
}

// Bookings
bookingURL = 'http://' + window.location.host + '/bookings'
async function removeBooking(id) {
    promice = doRequest(api + '/bookings/' + id, 'DELETE')
    let requestResult = await promice;
    window.location.replace(bookingURL)
}

async function saveBooking() {
    var id = getInputValue("id", "input")
    var room = {"id": getInputValue("roomId", "select")}
    var status = getInputValue("status", "select")
    var start = getInputValue("start", "input")
    var finish = getInputValue("finish", "input")
    var event = getInputValue("event", "input")
    if (id == "")
        id = null
    const booking = {
        "id": id,
        "room": room,
        "status": status,
        "start": start,
        "finish": finish,
        "event": event
    }
    promice = doRequest(api + '/bookings', 'POST', booking)
    let requestResult = await promice
    if (requestResult.message != undefined) {
        alert(requestResult.message)
    }
    window.location.replace(bookingURL)
}

async function agreeBooking(id) {
    promice = doRequest(api + '/bookings/' + id, 'GET')
    let booking = await promice
    booking.status = "AGREED"

    promice = doRequest(api + '/bookings', 'POST', booking)
    let requestResult = await promice
    tableCreate()
}

async function rejectBooking(id) {
    promice = doRequest(api + '/bookings/' + id, 'GET')
    let booking = await promice
    booking.status = "REJECTED"

    promice = doRequest(api + '/bookings', 'POST', booking)
    let requestResult = await promice
    tableCreate()
}

async function tableCreate() {

    promice = doRequest(api + '/approval-bookings/', 'GET')
    let requestResult = await promice;

    var bookingsTable = document.getElementById('bookingsTable');
    while(bookingsTable.hasChildNodes()) {
        bookingsTable.removeChild(bookingsTable.firstChild);
    }
    for (let i = 0; i < requestResult.length; i++) {

        var button = document.createElement('input')
        button.type = 'button';
        button.value = 'Подтвердить';
        button.addEventListener('click', function () {
            agreeBooking(requestResult[i].id)
        }, false);

        var button1 = document.createElement('input')
        button1.type = 'button';
        button1.value = 'Отклонить';
        button1.addEventListener('click', function () {
            rejectBooking(requestResult[i].id)
        }, false);

        var tr = bookingsTable.insertRow()
        var td = tr.insertCell();
        textNode = "id: " + requestResult[i].id + ", комната: " + requestResult[i].room.name
            + ", событие: " + requestResult[i].event
            + ", старт: " + requestResult[i].start
            + ", финиш: " + requestResult[i].finish
            td.appendChild(document.createTextNode(textNode));
        var td = tr.insertCell();
        td.appendChild(button);
        var td = tr.insertCell();
        td.appendChild(button1);
    }
}

// Rooms
roomsUrl = 'http://' + window.location.host + '/rooms'
async function removeRoom(id) {
    promice = doRequest(api + '/rooms/' + id, 'DELETE')
    let requestResult = await promice;
    window.location.replace(roomsUrl)
}

async function saveRoom() {
    var id = getInputValue("id", "input")
    var name = getInputValue("name", "input")
    var numberOfSeats = getInputValue("numberOfSeats", "input")
    var projector = getInputValue("projector", "input")
    var board = getInputValue("board", "input")
    if (id == "")
        id = null
    const user = {
        "id": id,
        "name": name,
        "numberOfSeats": numberOfSeats,
        "projector": projector,
        "board": board
    }
    promice = doRequest(api + '/rooms', 'POST', user)
    let requestResult = await promice
    window.location.replace(roomsUrl)
}

async function openBooking(roomId) {
    if (roomId == 'null')
        alert("Сначала сохраните документ")
    else
        window.location.replace('http://localhost:8080/bookings/new?roomId=' + roomId)
}

// Users
usersURL = 'http://' + window.location.host + '/users'
async function removeUser(id) {
    promice = doRequest(api + '/users/' + id, 'DELETE')
    let requestResult = await promice;
    window.location.replace(usersURL)
}

async function saveUser() {
    var id = getInputValue("id", "input")
    var username = getInputValue("username", "input")
    var password = getInputValue("password", "input")
    var roles = getInputValue("roles", "select")
    const user = {
        "id": id,
        "username": username,
        "password": password,
        "active": true,
        "roles": roles
    }
    promice = doRequest(api + '/users', 'POST', user)
    let requestResult = await promice
    window.location.replace(usersURL)
}
