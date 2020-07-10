function copyFormUrl(FormId) {
    var inp = document.createElement('input');

    document.body.appendChild(inp);
    inp.value = "http://localhost:8080/form/"+FormId;

    inp.select();
    document.execCommand("copy");
    inp.remove();
}

function deleteForm(FormId) {
    let url = "http://localhost:8080/form/"+FormId+"/delete";

    fetch(url,{
        method:'POST',
        headers:{'content-type' : 'application/json'}
    }).then(res=>res.toString())
    .then(()=>window.location.href = "http://localhost:8080/");
}
