var globalResponse = new Object();

function updateFiveStarValue(widgetId,value) {
   globalResponse[widgetId] = {widgetId:widgetId,widgetType:"Five Star","rating-value":value};
}
function deleteFiveStarValue(widgetId) {
   delete globalResponse[widgetId];
}
function submitForm() {
    var reponseList = [];
    for (const [key1,value] of Object.entries(globalResponse)){
        reponseList.push(value);
    }

    var url="http://localhost:8080/FormResponse";
    fetch(url, {
        method: 'POST',
        headers: {'content-type': 'application/json'},
        body: JSON.stringify({
            'userId': 'xyz',
            'formId': formId,
            'data': reponseList
        })
    }).then(res=>res.toString()).then(()=>alert(alertMsg)).
        then(()=>window.location.href="http://localhost:8080/");
}
