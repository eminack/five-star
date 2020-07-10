function deleteWidget(widgetId){
    let url = "http://localhost:8080/widget/"+ widgetId + "/delete";

    fetch(url,{method:'POST'}).
    then(res=>res.toString()).
    then(()=>window.location.href = "http://localhost:8080/");
}

function copyWidgetUrl(widgetid) {
    var inp = document.createElement('input');
    document.body.appendChild(inp);

    inp.value = "http://localhost:8080/widget/"+widgetid;

    inp.select();
    document.execCommand("copy");
    inp.remove();
}

function displayModalForId(widgetId,templateId) {
    SingleWidgetIdToBeEdited = widgetId;

    $('#'+templateId+'EditModal').modal('show');
}