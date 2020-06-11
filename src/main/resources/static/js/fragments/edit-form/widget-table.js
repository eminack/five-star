function deleteWidget(widgetId) {
    let obj;
    let url = "http://localhost:8080/form/"+formid +"/widget/" + widgetId + "/delete";
    console.log(url);
    fetch(url,{method:'POST'}).
    then(results=>results.json()).
    then(data => obj=data).
    then(()=>console.log(obj)).then(()=>window.location.href = "http://localhost:8080/form/" +obj["formId"] +"/edit");
}
function displayModalForThisId(widgetId,templateId) {
    console.log(widgetId,templateId);
    WidgetIdToBeEdited = widgetId;
    $('#'+templateId+'EditModal').modal('show');
}
