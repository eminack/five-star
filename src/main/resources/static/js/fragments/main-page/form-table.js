function copyFormUrl(FormId) {
    console.log(FormId);
    var inp = document.createElement('input');
    document.body.appendChild(inp);
    inp.value = "http://localhost:8080/form/"+FormId;
    inp.select();
    document.execCommand("copy");
    inp.remove();
}
