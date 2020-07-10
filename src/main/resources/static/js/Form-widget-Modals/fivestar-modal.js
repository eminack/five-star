function hideFormFiveStarModal() {
    document.getElementById("inputName-11").value = "";
    document.getElementById("inputTitle-11").value = "";
    document.getElementById("smiley1-11").value = "";
    document.getElementById("smiley2-11").value = "";
    document.getElementById("smiley3-11").value = "";
    document.getElementById("smiley4-11").value = "";
    document.getElementById("smiley5-11").value = "";
    $('#fivestarModal').modal('hide');
}

/**
 * This method is triggered when user presses submit on "fivestar-modal". This method creates a POST  request to
 * backend to create a new FiveStar widget for form & redirect to edit-form page once the response is received
 */
function CreateFiveStarWidgetForForm() {
    let obj;
    let widgetName = $('#inputName-11').val(),title = $('#inputTitle-11').val();
    let smiley1 = $('#smiley1-11').val(),smiley2 = $('#smiley2-11').val(),smiley3 = $('#smiley3-11').val();
    let smiley4 = $('#smiley4-11').val(),smiley5 = $('#smiley5-11').val();

    let url = "http://localhost:8080/form/" + formid + "/add-widget";

    hideFormFiveStarModal();

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
