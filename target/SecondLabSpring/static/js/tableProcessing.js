function tablePaginationRerender(totalpages, currentpage) {
    let paginationElement = document.querySelector(".tablePagination");
    paginationElement.innerHTML = "";
    let pageButtonHolderPrevious = document.createElement("li");
    pageButtonHolderPrevious.appendChild(createButton(
        currentpage === 1,
        'page-link link-secondary',
        'clearTableBody();fillTableOnPage(' + (currentpage - 1) + ');',
        'Previous'))
    paginationElement.appendChild(pageButtonHolderPrevious)

    for (let i = (currentpage - 2 > 0 ? currentpage - 2 : 1); i <= (currentpage + 2 > totalpages ? totalpages : currentpage + 2); i++) {
        let pageButtonHolder = document.createElement("li");
        pageButtonHolder.setAttribute("class", "page-item");
        pageButtonHolder.appendChild(createButton(
            currentpage === i,
            'page-link link-secondary',
            'clearTableBody();fillTableOnPage(this.innerText);',
            i))
        paginationElement.appendChild(pageButtonHolder)
    }

    let pageButtonHolderNext = document.createElement("li");
    pageButtonHolderNext.appendChild(createButton(
        currentpage === totalpages,
        'page-link link-secondary',
        'clearTableBody();fillTableOnPage(' + (currentpage + 1) + ');',
        'Next'))
    paginationElement.appendChild(pageButtonHolderNext)

    let pageButtonHolderLast = document.createElement("li");
    pageButtonHolderLast.appendChild(createButton(
        currentpage === totalpages,
        'page-link link-secondary',
        'clearTableBody();fillTableOnPage(' + totalpages + ');',
        'Last'))
    paginationElement.appendChild(pageButtonHolderLast)

}

function fillTableOnPage(page) {
    let form = document.querySelector('.searchBox');
    const data = new URLSearchParams();
    for (const pair of new FormData(form)) {
        data.append(pair[0], pair[1]);
    }
    data.set('page',page);
    let xhr = new XMLHttpRequest();
    xhr.open("GET", getContextPath()+"/book/getall/?"+data, true);
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');



    //
    // let rawObj = {};
    // if (form !== undefined || form !== null) {
    //     let formData = new FormData(form);
    //     rawObj = Object.fromEntries(formData);
    // }
    // rawObj.page = page;



    //xhr.send(JSON.stringify(rawObj));

    xhr.send();

    xhr.onload = function () {
        let data = JSON.parse(this.responseText);
        tablePaginationRerender(data.totalPages, data.currentPage);
        let table = document.querySelector(".table_body");
        for (let i = 0; i < data.books.length; i++) {
            let cellNum = 0
            let row = table.insertRow(-1);
            row.insertCell(cellNum).appendChild(document.createTextNode(((data.currentPage - 1) * 10) + i + 1));
            row.insertCell(++cellNum).appendChild(document.createTextNode(data.books[i].title));
            row.insertCell(++cellNum).appendChild(document.createTextNode(data.books[i].author.firstName + " " + data.books[i].author.lastName));
            row.insertCell(++cellNum).appendChild(document.createTextNode(data.books[i].category.categoryName));
            row.insertCell(++cellNum).appendChild(document.createTextNode(data.books[i].language.languageName));
            row.insertCell(++cellNum).appendChild(document.createTextNode(data.books[i].publisher.publisherName));
            row.insertCell(++cellNum).appendChild(
                createLink(
                    false,
                    'link-primary',
                    data.books[i].prequel.title == null ? '' : data.books[i].prequel.title,
                    getContextPath()+'/editbook/' + data.books[i].prequel.id
            ));
            row.insertCell(++cellNum).appendChild(
                createLink(
                    false,
                    'link-primary',
                    'Edit',
                    getContextPath()+'/editbook/' + data.books[i].id)
            );
            row.insertCell(++cellNum).appendChild(
                createButton(
                    false,
                    'btn btn-primary',
                    'removeBookWithId(this.parentElement.closest("tr"),' + data.books[i].id + ');',
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

function createLink(disabled, classAtr, innerText, href) {
    let link = document.createElement("a");
    link.href = href;
    if (disabled) {
        link.setAttribute("disabled", disabled);
    }
    link.setAttribute("class", disabled ? classAtr + " disabled" : classAtr);
    link.innerText = innerText;
    return link;
}

function clearTableBody() {
    var tableBody = document.querySelector(".table_body");
    tableBody.innerHTML = "";
}

function removeBookWithId(row, id) {
    let xhr = new XMLHttpRequest();
    xhr.open('POST', getContextPath()+"/book/remove/")
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.send("id=" + id);
    xhr.onload = function () {
        if (this.status === 200) {
            row.remove();
        } else {
            alert("Something wrong")
        }
    }
}

window.addEventListener('load', function () {
    fillTableOnPage(1);
})
function getContextPath() {
    return window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
}