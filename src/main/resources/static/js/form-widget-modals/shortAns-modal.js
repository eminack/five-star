function ClearShortAnsModal(){
   document.getElementById("inputName-1").value = "";
   document.getElementById("inputTitle-1").value = "";
   document.getElementById("bookmark").value = "";
}

/**
 * This method is triggered when user presses submit on "shortAns-modal". This method creates a POST  request to
 * backend to create a new short Answer widget for form & redirect to edit-form page once the response is received
 */
function CreateShortAnsWidgetForForm() {
    let obj;
    let widgetName = $('#inputName-1').val(),title = $('#inputTitle-1').val();
    let bookmark = $('#bookmark').val();
    if (bookmark==null) bookmark="";
    let url = "http://localhost:8080/form/"+formid+"/add-widget";

    ClearShortAnsModal();

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