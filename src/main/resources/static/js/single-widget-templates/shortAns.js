
$('#'+id).change(function(){
    let obj;
    var response = $('#'+id).val().trim();

    if (confirm("Do You Want to submit ?")) {

        var url = "http://localhost:8080/widgetResponse";

        fetch(url, {
            method: 'POST',
            headers: {'content-type': 'application/json'},
            body: JSON.stringify({
                'userId': 'xyz',
                'widgetId': id,
                'widgetType': "SHORT_ANSWER",
                'responseText':response
            })
        }).then(res => res.json())
            .then(data => obj=data)
            .then(()=>console.log(obj))
            .then(()=>alert(alertMsg));

        $("#"+id).attr('disabled', true);
    }
});