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
