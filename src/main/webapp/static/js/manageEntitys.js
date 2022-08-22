window.addEventListener('load', function () {
    fillTable();
})

function fillTable() {
    let table = document.querySelector(".table");
    let tableName = table.getAttribute("id");
    let xhr = new XMLHttpRequest();
    xhr.open("GET", getContextPath() + "/"+tableName+"/getall/", true);
    xhr.send();

    xhr.onload = function () {
        let data = JSON.parse(this.responseText);
        if (!data.success) {
            throw 'response status: success=false';
        }
        let responseBody = data.responseBody;
        for (let i = 0; i < responseBody.length; i++) {
            let cellNum = 0
            let row = table.insertRow(-1);
            row.insertCell(cellNum).appendChild(document.createTextNode(i+1));
            row.insertCell(++cellNum).appendChild(document.createTextNode(getValuseWithoutId(responseBody[i])));

            row.insertCell(++cellNum).appendChild(
                createButton(
                    false,
                    'btn btn-primary',
                    'removeItemFromTableWithId(this.parentElement.closest("tr"),' + responseBody[i].id + ','+'\"'+tableName+'\"'+');',
                    'Remove'));
        }
    };
}

function createButton(disabled, classAtr, func, innerText) {
    let button = document.createElement("button");
    if (disabled) {
        button.setAttribute("disabled", disabled);
    }
    button.setAttribute("class", disabled ? classAtr + " disabled" : classAtr);
    button.setAttribute("onclick", func);
    button.innerText = innerText;
    return button;
}

function removeItemFromTableWithId(row, id,entity) {
    fetch(getContextPath() + "/"+entity+"/remove/" + id, {
        method: 'GET'
    }).then((response) =>
        response.json()
    ).then((jsonResp) => {
        if (jsonResp !== undefined) {
            if (jsonResp.success) {
                row.remove();
            } else {
                alert(jsonResp.responseBody.msg)
            }
        } else {
        }
    })
}

function getValuseWithoutId(item){
    let arrValues = [];
    for (let key in item) {
        if (key !== 'id') {
            arrValues.push(item[key])
        }
    }
    return arrValues.join(' ');
}

function getContextPath() {
    return window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));
}