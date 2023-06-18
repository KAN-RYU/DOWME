let url = window.location.origin;

async function searchMeal(menu) {
    const response = await fetch('./meal/search/' + menu, {method: 'GET'})
    return response.json();
}

function searchButton() {
    let searchMenu = document.getElementById("searchMenu").value;
    if (searchMenu.length < 2) return;

    searchMeal(searchMenu).then(data => {
        let topData = data.slice(0,10);
        let mealCon = document.getElementById('meal_container');
        mealCon.innerHTML = '';
        console.log(topData);
        
        //Build card
        for (let i in topData) {
            let card = document.createElement('div');
            card.classList.add('card', 'my-4');

            let cardBody = document.createElement('div');
            cardBody.classList.add('card-body');
            card.append(cardBody);

            let cardTitle = document.createElement('h1');
            cardTitle.classList.add('card-title');
            switch (topData[i].restaurant) {
                case "Student":
                    cardTitle.innerText = "Student Cafeteria";
                    break;
                case "Dining Hall":
                    cardTitle.innerText = "Dining Hall";
                    break;
                case "Dormitory":
                    cardTitle.innerText = "Dormitory Cafeteria " + topData[i].category;
                    break;
            }
            cardBody.append(cardTitle);

            let cardDate = document.createElement('p');
            cardDate.classList.add('card-text');
            let sD = topData[i].date.toString();
            cardDate.innerText = `${sD.slice(0,2)}/${sD.slice(2,4)}/${sD.slice(4,6)} ${topData[i].time}`
            cardBody.append(cardDate);

            let menuList = topData[i].menu.split('/');
            let listGroup = document.createElement('ul');
            listGroup.classList.add('list-group', 'list-group-flush');
            for(let j in menuList) {
                let item = document.createElement('li');
                item.classList.add('list-group-item');
                item.innerText = menuList[j].trim();
                if (menuList[j].toLowerCase().includes(searchMenu.toLowerCase())) {
                    item.classList.add('text-bg-warning');
                }
                listGroup.append(item);
            }
            card.append(listGroup);
            mealCon.append(card);
        }
    })
}
