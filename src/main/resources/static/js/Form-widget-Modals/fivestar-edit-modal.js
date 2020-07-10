/**
 * clear the values from fields of fivestar-edit-modal.html
 */
function clearFiveStarModal() {
    document.getElementById("Edit-inputName-1").value = "";
    document.getElementById("Edit-inputTitle-1").value = "";
    document.getElementById("Edit-smiley1-1").value = "";
    document.getElementById("Edit-smiley2-1").value = "";
    document.getElementById("Edit-smiley3-1").value = "";
    document.getElementById("Edit-smiley4-1").value = "";
    document.getElementById("Edit-smiley5-1").value = "";
}

/**
 * This method is invoked when user presses submit on fivestar-edit-modal , it reads all the values entered by user
 * and creates a POST request to backend , for updating a particular  fivestar widget in form
 */
function EditFiveStarWidget() {
    let obj;
    let widgetName = $('#Edit-inputName-1').val(),title = $('#Edit-inputTitle-1').val();
    let smiley1 = $('#Edit-smiley1-1').val(),smiley2 = $('#Edit-smiley2-1').val(),smiley3 = $('#Edit-smiley3-1').val();
    let smiley4 = $('#Edit-smiley4-1').val(),smiley5 = $('#Edit-smiley5-1').val();

    let url = "http://localhost:8080/form/" + formid + "/widget/"+WidgetIdToBeEdited+"/edit";

    clearFiveStarModal();

    fetch(url,{
        method:'POST',
        headers:{'content-type' : 'application/json'},
        body : JSON.stringify({
            'widgetId':WidgetIdToBeEdited,
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
    then(()=>console.log(obj)).then(()=>window.location.href = "http://localhost:8080/form/" + obj["formId"]+"/edit");

    $('#fivestarEditModal').modal('hide');

    return false;
}