function ClearShortAnsEditModal(){
    document.getElementById("edit-inputName-1").value = "";
    document.getElementById("edit-inputTitle-1").value = "";
    document.getElementById("edit-bookmark").value = "";
}

/**
 * This method is invoked when user presses submit on shortAns-edit-modal , it reads all the values entered by user
 * and creates a POST request to backend , for updating a particular short Answer widget in form
 */
function EditShortAnsWidgetForForm() {
    let obj;
    let url = "http://localhost:8080/form/"+formid+"/widget/"+WidgetIdToBeEdited+"/edit";
    let widgetName = $('#edit-inputName-1').val(),title = $('#edit-inputTitle-1').val();
    let bookmark = $('#edit-bookmark').val();
    if (bookmark==null) bookmark="";

    ClearShortAnsEditModal();

    fetch(url,{
        method:'POST',
        headers:{'content-type' : 'application/json'},
        body : JSON.stringify({
            'widgetId':WidgetIdToBeEdited,
            'widgetName': widgetName,
            'widgetType':"SHORT_ANSWER",
            'title': title,
            'hint': bookmark
        })
    }).then(res => res.json()).
    then(data => obj=data).
    then(()=>console.log(obj)).
    then(()=>window.location.href = "http://localhost:8080/form/" + obj["formId"]+"/edit");

    return false;
}