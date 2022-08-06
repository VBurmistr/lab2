function sendAddRequest(form){
    let xhr = new XMLHttpRequest();
    let formData = new FormData(form);
    xhr.open('POST',form.getAttribute("action"))
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send(JSON.stringify(Object.fromEntries(formData)));
    xhr.onreadystatechange = function() {
        if (this.status === 200) {
            form.reset();
            return true;
        }else{
            return false;
        }
    }
}
