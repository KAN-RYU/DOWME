// Your web app's Firebase configuration
const firebaseConfig = {
    apiKey: "AIzaSyC3QLKNxaohqnrWbSRVf7YYYtFzAjRyVYE",
    authDomain: "dowme-dad58.firebaseapp.com",
    projectId: "dowme-dad58",
    storageBucket: "dowme-dad58.appspot.com",
    messagingSenderId: "911730688701",
    appId: "1:911730688701:web:cc4bc7f75b94a23e75add4"
};

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
                firebase.initializeApp(firebaseConfig);

                const messaging = firebase.messaging();
                messaging.requestPermission()
                    .then(function () {
                        return messaging.getToken();
                    })
                    .then(async function (token) {
                        await fetch('/register', { method: 'post', body: token })
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
                    .catch(function(err) {
                        console.log(err);
                    })
            })
    }
}

function submitSchedule() {
    let table = document.getElementById("lectureItems");
    let rows = table.children
    for (let i = 0; i < rows.length; i++) {
        let row = rows[i];
        let name = (row.children[0].children[0]).value;
        let mon = (row.children[1].children[0]).checked;
        let tue = (row.children[2].children[0]).checked;
        let wed = (row.children[3].children[0]).checked;
        let thu = (row.children[4].children[0]).checked;
        let fri = (row.children[5].children[0]).checked;
        let time = (row.children[6].children[0]).value;
        console.log(time);
    }

    initServiceWorker();
}