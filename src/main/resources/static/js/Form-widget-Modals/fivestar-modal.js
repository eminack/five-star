function hideFiveStarModal() {
    document.getElementById("inputName").value = "";
    document.getElementById("inputTitle").value = "";
    document.getElementById("smiley1").value = "";
    document.getElementById("smiley2").value = "";
    document.getElementById("smiley3").value = "";
    document.getElementById("smiley4").value = "";
    document.getElementById("smiley5").value = "";
    $('#fivestarModal').modal('hide');
}
function CreateFiveStarWidgetForForm() {
    let obj;
    let widgetName = $('#inputName').val(),title = $('#inputTitle').val();
    let smiley1 = $('#smiley1').val(),smiley2 = $('#smiley2').val(),smiley3 = $('#smiley3').val();
    let smiley4 = $('#smiley4').val(),smiley5 = $('#smiley5').val();
    let url = "http://localhost:8080/form/" + formid + "/add-widget";
    hideFiveStarModal();
    fetch(url,{
        method:'POST',
        headers:{'content-type' : 'application/json'},
        body : JSON.stringify({
            'widgetName': widgetName,
            'widgetType':"FIVE_STAR",
            'widgetVersion': 1,
            'templateId': "fivestar",
            'metaData':{
                'userCreated':'xyz'
            },
            'title': title,
            'hint1': smiley1,
            'hint2': smiley2,
            'hint3': smiley3,
            'hint4': smiley4,
            'hint5': smiley5
        })
    }).then(res => res.json()).
    then(data => obj=data).
    then(()=>console.log(obj)).then(()=>window.location.href = "http://localhost:8080/form/" + obj["formId"]+"/edit");
    return false;
}
