function ClearWidgetModal(){
   document.getElementById("inputName-1").value = "";
   document.getElementById("inputTitle-1").value = "";
   document.getElementById("bookmark").value = "";
}
function CreateShortAnsWidget() {
    let obj;
    let widgetName = $('#inputName-1').val(),title = $('#inputTitle-1').val();
    let bookmark = $('#bookmark').val();
    if (bookmark==null) bookmark="";

    let url = "http://localhost:8080/widget";

    ClearWidgetModal();

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
    then(()=>window.location.href = "http://localhost:8080/widget/" + obj["widgetId"]);

    return false;
}