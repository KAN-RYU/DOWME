// Your web app's Firebase configuration
const firebaseConfig = {
    apiKey: "AIzaSyC3QLKNxaohqnrWbSRVf7YYYtFzAjRyVYE",
    authDomain: "dowme-dad58.firebaseapp.com",
    projectId: "dowme-dad58",
    storageBucket: "dowme-dad58.appspot.com",
    messagingSenderId: "911730688701",
    appId: "1:911730688701:web:cc4bc7f75b94a23e75add4"
};

const firebaseModule = (function () {
    async function init() {
        // Your web app's Firebase configuration
        if ('serviceWorker' in navigator) {
            window.addEventListener('load', function() {
                console.log('init1');
                navigator.serviceWorker.register('/firebase-messaging-sw.js')
                    .then(registration => {
                        // Initialize Firebase
                        firebase.initializeApp(firebaseConfig);
                        console.log("init");

                        // Show Notificaiton Dialog
                        const messaging = firebase.messaging();
                        messaging.requestPermission()
                        .then(function() {
                            return messaging.getToken();
                        })
                        .then(async function(token) {
                            await fetch('/register', { method: 'post', body: token })
                            messaging.onMessage(payload => {
                                const title = payload.notification.title
                                const options = {
                                    body : payload.notification.body
                                }
                                navigator.serviceWorker.ready.then(registration => {
                                    registration.showNotification(title, options);
                                })
                            })
                        })
                        .catch(function(err) {
                            console.log("Error Occured");
                        })
                    })
            })
        }
        console.log('done')
    }      

    return {
        init: function () {
            init()
        }
    }
})()

console.log('hi');
firebaseModule.init()