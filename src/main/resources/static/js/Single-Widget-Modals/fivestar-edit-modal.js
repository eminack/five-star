function clearFiveStarModal() {
    document.getElementById("Edit-inputName").value = "";
    document.getElementById("Edit-inputTitle").value = "";
    document.getElementById("Edit-smiley1").value = "";
    document.getElementById("Edit-smiley2").value = "";
    document.getElementById("Edit-smiley3").value = "";
    document.getElementById("Edit-smiley4").value = "";
    document.getElementById("Edit-smiley5").value = "";

}
function EditFiveStarWidget() {
    let obj;
    let widgetName = $('#Edit-inputName').val(),title = $('#Edit-inputTitle').val();
    let smiley1 = $('#Edit-smiley1').val(),smiley2 = $('#Edit-smiley2').val(),smiley3 = $('#Edit-smiley3').val();
    let smiley4 = $('#Edit-smiley4').val(),smiley5 = $('#Edit-smiley5').val();
    let url = "http://localhost:8080/widget/"+SingleWidgetIdToBeEdited+'/edit';
    clearFiveStarModal();
    fetch(url,{
        method:'POST',
        headers:{'content-type' : 'application/json'},
        body : JSON.stringify({
            'widgetId':SingleWidgetIdToBeEdited,
            'widgetName': widgetName,
            'widgetType':"FIVE_STAR",
            'title': title,
            'hint1': smiley1,
            'hint2': smiley2,
            'hint3': smiley3,
            'hint4': smiley4,
            'hint5': smiley5
        })
    }).then(res => res.json()).
    then(data => obj=data).
    then(()=>console.log(obj)).then(()=>window.location.href = "http://localhost:8080/widget/" + obj["widgetId"]);
    $('#fivestarEditModal').modal('hide');

    return false;
}