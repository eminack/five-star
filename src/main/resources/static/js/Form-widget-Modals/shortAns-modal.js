function ClearShortAnsModal(){
   document.getElementById("inputName-1").value = "";
   document.getElementById("inputTitle-1").value = "";
   document.getElementById("bookmark").value = "";
}
function CreateShortAnsWidgetForForm() {
    let obj;
    let widgetName = $('#inputName-1').val(),title = $('#inputTitle-1').val();
    let bookmark = $('#bookmark').val();
    if (bookmark==null) bookmark="";
    ClearShortAnsModal();

    let url = "http://localhost:8080/form/"+formid+"/add-widget";
    fetch(url,{
        method:'POST',
        headers:{'content-type' : 'application/json'},
        body : JSON.stringify({
            'widgetName': widgetName,
            'widgetType':"SHORT_ANSWER",
            'widgetVersion': 1,
            'templateId': "shortAns",
            'metaData':{
                'userCreated':"xyz"
            },
            'title': title,
            'hint': bookmark

        })
    }).then(res => res.json()).
    then(data => obj=data).
    then(()=>console.log(obj)).
    then(()=>window.location.href = "http://localhost:8080/form/"+formid+"/edit");
    return false;
}