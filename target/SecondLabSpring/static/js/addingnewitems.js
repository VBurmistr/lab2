function sendAddRequest(form){
    let xhr = new XMLHttpRequest();
    let formData = new FormData(form);
    xhr.open('POST',form.getAttribute("action"))
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send(JSON.stringify(Object.fromEntries(formData)));
    xhr.onload = function() {
        if (this.status === 200) {
            form.reset();
            return true;
        }else{
            alert(JSON.parse(this.response).responseBody.msg)
            return false;
        }
    }
}

function sendAddSmartRequest(form){
    const data = new URLSearchParams();
    for (const pair of new FormData(form)) {
        data.append(pair[0], pair[1]);
    }
    const url = form.getAttribute("action");
    fetch(url, {
        method : "POST",
        body: data,
        headers:{'Content-Type': 'application/x-www-form-urlencoded'}
    }).then((response) =>
        response.json()
    ).then((jsonResp)=>{
        if(jsonResp!==undefined){
            if(jsonResp.success){
                window.location.replace(getContextPath()+"/searchbooks");
            }else{
                alert(jsonResp.responseBody.msg)
            }
        }else{
        }
    })
}

function getContextPath() {
    return window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
}

