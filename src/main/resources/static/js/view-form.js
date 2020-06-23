var globalResponse = new Object();

function updateFiveStarValue(widgetId,value) {
   globalResponse[widgetId] = {widgetId:widgetId,widgetType:"FIVE_STAR",ratingValue:value,userId:"xyz"};
   console.log("inside update five star");
}
function deleteFiveStarValue(widgetId) {
   delete globalResponse[widgetId];
}
function updateShortAnswerResponse(widgetId,response) {
    globalResponse[widgetId] = {widgetId:widgetId,widgetType: "SHORT_ANSWER",responseText:response,userId:"xyz"};
    console.log("inside update short answer")
}
function submitForm() {
    let obj;
    var responseList = [];
    for (const [key1,value] of Object.entries(globalResponse)){
        responseList.push(value);
    }
    console.log(responseList);

    var url="http://localhost:8080/formresponse";
    fetch(url, {
        method: 'POST',
        headers: {'content-type': 'application/json'},
        body: JSON.stringify({
            'userId': 'xyz',
            'formId': formId,
            'responseList':responseList
        })
    }).then(res=>res.json())
        .then(data => obj=data)
        .then(()=>console.log(obj))
        .then(()=>alert(alertMsg)).
        then(()=>window.location.href="http://localhost:8080/");
}
