function deleteWidget(widgetId) {
    let obj;
    let url = "http://localhost:8080/form/"+formid +"/widget/" + widgetId + "/delete";

    fetch(url,{method:'POST'}).
    then(results=>results.json()).
    then(data => obj=data).
    then(()=>console.log(obj)).then(()=>window.location.href = "http://localhost:8080/form/" +obj["formId"] +"/edit");
}

function displayModalForThisId(widgetId,templateId) {
    WidgetIdToBeEdited = widgetId;

    $('#'+templateId+'EditModal').modal('show');
}

function displayConstraintModal(widgetId) {
    WidgetIdToBeConstrained = widgetId;

    $('#constraintModal').modal('show');
}

function delConstraint(widgetId) {
    WidgetIdToBeConstrained = widgetId;

    let url = "http://localhost:8080/form/"+formid +"/widget/" + widgetId + "/delete-constraint";

    fetch(url,{method:'POST'}).
    then(res => res.toString()).then(()=>window.location.href = "http://localhost:8080/form/" + formid + "/edit");
}