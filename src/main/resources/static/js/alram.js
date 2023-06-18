// Your web app's Firebase configuration
const firebaseConfig = {
    apiKey: "AIzaSyC3QLKNxaohqnrWbSRVf7YYYtFzAjRyVYE",
    authDomain: "dowme-dad58.firebaseapp.com",
    projectId: "dowme-dad58",
    storageBucket: "dowme-dad58.appspot.com",
    messagingSenderId: "911730688701",
    appId: "1:911730688701:web:cc4bc7f75b94a23e75add4"
};
firebase.initializeApp(firebaseConfig);

let lectureNames = []
let times = []

function addRow() {
    let table = document.getElementById("lectureItems");

    let row = document.createElement('tr');

    let header = document.createElement('th');
    let nameField = document.createElement('input');
    nameField.classList.add('form-control');
    nameField.setAttribute('type', 'text');
    nameField.setAttribute('placeholder', 'Lecture Name');
    nameField.setAttribute('id', 'lectureName');
    header.append(nameField);
    row.append(header);

    let day = ['Mon', 'Tue', 'Wed', 'Thu', 'Fri'];
    for (let i = 0; i < 5; i++) {
        let box = document.createElement('td');
        let checkbox = document.createElement('input');
        checkbox.classList.add('form-check-input');
        checkbox.setAttribute('type', 'checkbox');
        checkbox.setAttribute('id', 'checkbox' + day[i]);
        box.append(checkbox);
        row.append(box);
    }

    let time = document.createElement('td');
    let selection = document.createElement('select');
    selection.classList.add('form-select');
    let timeC = ['09:00', '10:30', '13:00', '14:30', '16:00', '17:30', '19:00'];
    for (let i = 0; i < 7; i++) {
        let option = document.createElement('option');
        option.setAttribute('value', (i + 1).toString());
        option.innerText = timeC[i];
        selection.append(option);
    }
    time.append(selection);
    row.append(time);

    let deleteB = document.createElement('td');
    let button = document.createElement('button');
    button.classList.add('btn', 'btn-danger');
    button.setAttribute('onclick', 'deleteRow(this)');
    button.innerText = '-';
    deleteB.append(button);
    row.append(deleteB);

    table.append(row);

    return row;
}

function deleteRow(current) {
    let row = current.parentElement.parentElement;
    row.remove();
}

async function initServiceWorker() {
    if ('serviceWorker' in navigator) {
        navigator.serviceWorker.register('/firebase-messaging-sw.js')
            .then(registration => {
                const messaging = firebase.messaging();
                messaging.requestPermission()
                    .then(function () {
                        return messaging.getToken();
                    })
                    .then(async function (token) {
                        data = {
                            'token': token,
                            'times': times,
                            'lectureNames': lectureNames
                        }
                        await fetch('/register', {
                            method: 'post',
                            headers: {
                                'Content-Type': 'application/json'
                            },
                            body: JSON.stringify(data)
                        })
                        messaging.onMessage(payload => {
                            const title = payload.notification.title
                            const options = {
                                body: payload.notification.body
                            }
                            navigator.serviceWorker.ready.then(registration => {
                                registration.showNotification(title, options);
                            })
                        })
                    })
                    .catch(function (err) {
                        console.log(err);
                    })
            })
    }
}

function submitSchedule() {
    times = []
    lectureNames = []
    let table = document.getElementById("lectureItems");
    let rows = table.children
    for (let i = 0; i < rows.length; i++) {
        let row = rows[i];
        let name = (row.children[0].children[0]).value;
        let time = (row.children[6].children[0]).value;
        for (let j = 1; j <= 5; j++) {
            if ((row.children[j].children[0]).checked) {
                let realtime = 0;
                switch (time) {
                    case '1':
                        realtime = 900;
                        break;
                    case '2':
                        realtime = 1030;
                        break;
                    case '3':
                        realtime = 1300;
                        break;
                    case '4':
                        realtime = 1430;
                        break;
                    case '5':
                        realtime = 1600;
                        break;
                    case '6':
                        realtime = 1730;
                        break;
                    case '7':
                        realtime = 1900;
                        break;
                }
                realtime = j * 10000 + realtime;
                times.push(realtime);
                lectureNames.push(name);
            }
        }
    }

    initServiceWorker();
}

async function unsubscribe() {
    const messaging = firebase.messaging();
    messaging.requestPermission()
        .then(function () {
            return messaging.getToken();
        })
        .then(async function (token) {
            const response = await fetch('/unsubscribe', { method: 'post', body: token })
            console.log(response);
        })
    return;
}

function realtimeTovalue(realtime) {
    switch (realtime) {
        case 900:
            return 1;
        case 1030:
            return 2;
        case 1300:
            return 3;
        case 1430:
            return 4;
        case 1600:
            return 5;
        case 1730:
            return 6;
        case 1900:
            return 7;
    }
    return 0;
}

function initializeTable() {
    if (Notification.permission === 'default') return;
    const messaging = firebase.messaging();
    messaging.requestPermission()
        .then(function () {
            return messaging.getToken();
        })
        .then(async function (token) {
            const response = await fetch('/notiuser/' + token, { method: 'get' })
            return (response.json());
        })
        .then(function (timeTable) {
            if (timeTable.lectureNames.length === 0) return;
            let table = document.getElementById("lectureItems");

            let row = table.children[0];
            let currentLecture = timeTable.lectureNames[0];
            (row.children[0].children[0]).value = currentLecture;
            (row.children[6].children[0]).value = realtimeTovalue(timeTable.times[0]%10000);

            for (let i = 0; i < timeTable.times.length; i++) {
                if (currentLecture !== timeTable.lectureNames[i]) {
                    row = addRow();
                    currentLecture = timeTable.lectureNames[i];
                    (row.children[0].children[0]).value = currentLecture;
                    (row.children[6].children[0]).value = realtimeTovalue(timeTable.times[i]%10000);
                }
                (row.children[parseInt(timeTable.times[i]/10000)].children[0]).checked = true;
            }

            let name = (row.children[0].children[0]).value;
            let time = (row.children[6].children[0]).value;
            for (let j = 1; j <= 5; j++) {
                if ((row.children[j].children[0]).checked) {
                    let realtime = 0;
                    switch (time) {
                        case '1':
                            realtime = 900;
                            break;
                        case '2':
                            realtime = 1030;
                            break;
                        case '3':
                            realtime = 1300;
                            break;
                        case '4':
                            realtime = 1430;
                            break;
                        case '5':
                            realtime = 1600;
                            break;
                        case '6':
                            realtime = 1730;
                            break;
                        case '7':
                            realtime = 1900;
                            break;
                    }
                    realtime = j * 10000 + realtime;
                    times.push(realtime);
                    lectureNames.push(name);
                }
            }

        })
        .catch(function (err) {
            console.log("Error on initializing");
        })
    return;
}

initializeTable();