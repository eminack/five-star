/**
 * function to read fields from create-form-modal & send a POST request to backend
 * @returns {boolean} 'false' so as to hide the modal after submit is pressed
 */
function CreateForm() {
    let obj;
    let name = $('#message-text').val();
    let url = "http://localhost:8080/form";

    document.getElementById("message-text").value = "";

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