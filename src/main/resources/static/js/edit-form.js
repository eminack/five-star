function CopyFormUrl() {
    var inp = document.createElement('input');
    document.body.appendChild(inp);
    inp.value = "http://localhost:8080/form/"+formid;
    inp.select();
    document.execCommand("copy");
    inp.remove();
}


