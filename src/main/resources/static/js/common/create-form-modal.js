function CreateForm() {
    let obj;
    let name = $('#message-text').val();
    document.getElementById("message-text").value = "";
    let url = "http://localhost:8080/form?name=" + name;
    console.log(url);
    fetch(url,{method:'POST'}).
    then(results=>results.json()).
    then(data => obj=data).
    then(()=>console.log(obj)).then(()=>window.location.href = "http://localhost:8080/form/" + obj["formId"] + "/edit");
    return false;
}