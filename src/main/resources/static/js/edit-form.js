function CreateFiveStarWidget() {
    console.log("name");
    let obj;
    let widgetName = $('#inputName').val(),title = $('#inputTitle').val();
    let smiley1 = $('#smiley1').val(),smiley2 = $('#smiley2').val(),smiley3 = $('#smiley3').val();
    let smiley4 = $('#smiley4').val(),smiley5 = $('#smiley5').val();
    let url = "http://localhost:8080/form/" + formid + "/add-widget";
    document.getElementById("inputName").value = "";
    document.getElementById("inputTitle").value = "";
    document.getElementById("smiley1").value = "";
    document.getElementById("smiley2").value = "";
    document.getElementById("smiley3").value = "";
    document.getElementById("smiley4").value = "";
    document.getElementById("smiley5").value = "";
    fetch(url,{
        method:'POST',
        headers:{'content-type' : 'application/json'},
        body : JSON.stringify({
                'widgetName': widgetName,
                'widgetType':"Five Star",
                'widgetVersion': 1,
                'templateId': "fivestar",
                'data': {
                    'title': title,
                    'smiley1': smiley1,
                    'smiley2': smiley2,
                    'smiley3': smiley3,
                    'smiley4': smiley4,
                    'smiley5': smiley5
                }
        })
    }).then(res => res.json()).
    then(data => obj=data).
    then(()=>console.log(obj)).then(()=>window.location.href = "http://localhost:8080/form/" + obj["formId"]+"/edit");
    return false;
}
function deleteWidget(widgetId) {
    //console.log(widgetId);
    let obj;
    let url = "http://localhost:8080/form/"+formid +"/widget/" + widgetId + "/delete";
    console.log(url);
    fetch(url,{method:'POST'}).
    then(results=>results.json()).
    then(data => obj=data).
    then(()=>console.log(obj)).then(()=>window.location.href = "http://localhost:8080/form/" +obj["formId"] +"/edit");
}
function viewForm() {
    let url = "http://localhost:8080/form/"+formid;
    window.location.href = url;
}
function CopyFormUrl() {
    var inp = document.createElement('input');
    document.body.appendChild(inp);
    inp.value = "http://localhost:8080/form/"+formid;
    inp.select();
    document.execCommand("copy");
    inp.remove();
}