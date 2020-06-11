function ClearShortAnsEditModal() {
    document.getElementById("inputName-2").value = "";
    document.getElementById("inputTitle-2").value = "";
    document.getElementById("bookmark-1").value = "";
}
function EditShortAnsWidget() {
    let obj;
    let widgetName = $('#inputName-2').val(),title = $('#inputTitle-2').val();
    let bookmark = $('#bookmark-1').val();
    if (bookmark==null) bookmark="";
    let url = "http://localhost:8080/widget/"+SingleWidgetIdToBeEdited+"/edit";
    ClearShortAnsEditModal();
    fetch(url,{
        method:'POST',
        headers:{'content-type' : 'application/json'},
        body : JSON.stringify({
            'widgetId': SingleWidgetIdToBeEdited,
            'widgetName': widgetName,
            'widgetType':"Short Answer",
            'widgetVersion': 1,
            'templateId': "shortAns",
            'userCreated':"",
            'data': {
                'title': title,
                'bookmark': bookmark
            }
        })
    }).then(res => res.json()).
    then(data => obj=data).
    then(()=>console.log(obj)).
    then(()=>window.location.href = "http://localhost:8080/widget/" + obj["widgetId"]);
    return false;
}