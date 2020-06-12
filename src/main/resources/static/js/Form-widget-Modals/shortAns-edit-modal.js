function ClearShortAnsEditModal(){
    document.getElementById("edit-inputName-1").value = "";
    document.getElementById("edit-inputTitle-1").value = "";
    document.getElementById("edit-bookmark").value = "";
}
function EditShortAnsWidgetForForm() {
    let obj;
    let widgetName = $('#edit-inputName-1').val(),title = $('#edit-inputTitle-1').val();
    let bookmark = $('#edit-bookmark').val();
    if (bookmark==null) bookmark="";
    ClearShortAnsEditModal();
    let url = "http://localhost:8080/form/"+formid+"/widget/"+WidgetIdToBeEdited+"/edit";
    console.log(url);
    fetch(url,{
        method:'POST',
        headers:{'content-type' : 'application/json'},
        body : JSON.stringify({
            'widgetId':WidgetIdToBeEdited,
            'widgetName': widgetName,
            'widgetType':"Short Answer",
            'widgetVersion': 1,
            'templateId': "shortAns",
            'data': {
                'title': title,
                'bookmark': bookmark
            }
        })
    }).then(res => res.json()).
    then(data => obj=data).
    then(()=>console.log(obj)).
    then(()=>window.location.href = "http://localhost:8080/form/" + obj["formId"]+"/edit");
    return false;
}