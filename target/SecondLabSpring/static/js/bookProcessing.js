function bookProcessing(form) {
    let xhr = new XMLHttpRequest();
    let formData = new FormData(form);
    let rawObj = Object.fromEntries(formData);
    let obj = {
        title: rawObj.title,
        author: {id: rawObj.author},
        category: {id: rawObj.category},
        language: {id: rawObj.language},
        publisher: {id: rawObj.publisher},
        prequel: {id: rawObj.prequel === "0" ? null : rawObj.prequel}
    }
    xhr.open('POST', form.getAttribute("action"))
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send(JSON.stringify(obj));
    xhr.onload = function () {
        if (this.status === 200) {
            window.location.replace(getContextPath()+"/searchbooks");
        } else {
            alert("Something wrong")
        }
    }
}


window.addEventListener('load', function () {
    initializeAllSelectors();
})

function initializeAllSelectors() {
    initializeSelector(getContextPath()+'/author/getall/', document.querySelector(".selectAuthor"));
    initializeSelector(getContextPath()+'/language/getall/', document.querySelector(".selectLanguage"));
    initializeSelector(getContextPath()+'/publisher/getall/', document.querySelector(".selectPublisher"));
    initializeSelector(getContextPath()+'/category/getall/', document.querySelector(".selectCategory"));
    initializeSelector(getContextPath()+'/bookbasemodel/getall/', document.querySelector(".selectPrequel"));
}

function initializeSelector(link, selector) {
    let xhr = new XMLHttpRequest();
    xhr.open('GET', link)
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send();
    xhr.onload = function () {
        let data = JSON.parse(this.responseText);
        for (let i = 0; i < Object.keys(data).length; i++) {
            let arrValues = [];
            for (let key in data[i]) {
                if (key !== 'id') {
                    arrValues.push(data[i][key])
                }
            }
            if (selector.querySelector("option[value='" + data[i].id + "']") === null) {
                selector.appendChild(createOption(data[i].id, arrValues.join(' ')))
            }
        }

    }
}

function createOption(value, innerText) {
    let option = document.createElement("option");
    option.value = value;
    option.innerText = innerText;
    return option;
}

function getContextPath() {
    console
    return window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
}