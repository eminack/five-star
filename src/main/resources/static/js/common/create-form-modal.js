function CreateForm() {
    let obj;
    let name = $('#message-text').val();
    document.getElementById("message-text").value = "";
    let url = "http://localhost:8080/form";
    console.log(url);
    fetch(url,
        {
            method:'POST',
            headers:{'content-type' : 'application/json'},
            body:JSON.stringify({
                "formName":name,
                "userCreated":"xyz"
            })
        }).
    then(results=>results.json()).
    then(data => obj=data).
    then(()=>console.log(obj)).then(()=>window.location.href = "http://localhost:8080/form/" + obj["formId"] + "/edit");
    return false;
}