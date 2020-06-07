function deleteWidget(widgetId){
    let obj;
    let url = "http://localhost:8080/widget/"+ widgetId + "/delete";
    console.log(url);
    fetch(url,{method:'POST'}).
    then(res=>res.toString()).
    then(()=>console.log(obj)).then(()=>window.location.href = "http://localhost:8080/");

}
function CreateFiveStarWidget() {
    console.log("name");
    let obj;
    let widgetName = $('#inputName').val(),title = $('#inputTitle').val();
    let smiley1 = $('#smiley1').val(),smiley2 = $('#smiley2').val(),smiley3 = $('#smiley3').val();
    let smiley4 = $('#smiley4').val(),smiley5 = $('#smiley5').val()
    let url = "http://localhost:8080/widget";
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
            'userCreated':"",
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
    then(()=>console.log(obj)).then(()=>window.location.href = "http://localhost:8080/widget/" + obj["widgetId"]);
    return false;
}

function CreateForm() {
    let obj;
    let name = $('#message-text').val();
    document.getElementById("message-text").value = "";
    let url = "http://localhost:8080/form?name=" + name;
    console.log(url);
    fetch(url,{method:'POST'}).
    then(results=>results.json()).
    then(data => obj=data).
    then(()=>console.log(obj)).then(()=>window.location.href = "http://localhost:8080/form/" + obj["formId"] + "/edit");//update-form?id="+obj["formId"]);

    return false;
}
function copyWidgetUrl(widgetid) {
    console.log(widgetid);
    var inp = document.createElement('input');
    document.body.appendChild(inp);
    inp.value = "http://localhost:8080/widget/"+widgetid;
    inp.select();
    document.execCommand("copy");
    inp.remove();
}
function copyFormUrl(FormId) {
    console.log(FormId);
    var inp = document.createElement('input');
    document.body.appendChild(inp);
    inp.value = "http://localhost:8080/form/"+FormId;
    inp.select();
    document.execCommand("copy");
    inp.remove();
}