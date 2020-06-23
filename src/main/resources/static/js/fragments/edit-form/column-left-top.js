function viewForm() {
    let url = "http://localhost:8080/form/"+formid+'/preview';
    window.location.href = url;
}
function publishForm() {
    let url = "http://localhost:8080/form/"+formid+'/publish';
    fetch(url,{
        method:'POST',
        headers:{'content-type':'application/json'}
    }).then(res => res.toString())
        .then(()=>window.location.href = "http://localhost:8080/");
}