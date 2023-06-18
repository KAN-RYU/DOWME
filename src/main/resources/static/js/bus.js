let url = window.location.origin

var d1 = new Date();
var currentTime = d1.getHours() * 100 + d1.getMinutes();

function padZero(num) {
    let s = '00' + num;
    return s.slice(-2);
}

async function getBusSoon(time) {
    const response = await fetch(url + '/bus/depart/' + currentTime, {method: 'GET'})
    return response.json();
}

getBusSoon(currentTime).then((data) => {
    let con = document.getElementById("bus_container")
    con.innerHTML = '';
    let currentTimeNode = document.createElement('div');
    currentTimeNode.classList.add('my-4')
    currentTimeNode.innerText = `Current time - ${padZero(parseInt(currentTime/100))}:${padZero(currentTime % 100)}`;
    con.append(currentTimeNode);
    for (let i in data) {
        let busNode = document.createElement('div');
        busNode.classList.add('card', 'my-4');
        let busHeader = document.createElement('div');
        busHeader.classList.add('card-header');
        busHeader.innerText = data[i].busId;
        busNode.append(busHeader);
        let busBody = document.createElement('div');
        busBody.classList.add('card-body');
        let busTime = document.createElement('h');
        busTime.classList.add('card-title');
        busTime.innerText = `${padZero(parseInt(data[i].time/100))}:${padZero(data[i].time % 100)}`;
        busBody.append(busTime);
        busNode.append(busBody);
        con.append(busNode);
    }
})