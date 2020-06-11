$('#'+id).change(function(){
    var response = $('#'+id).val().trim();
    console.log(response);
    if (confirm("Do You Want to submit ?")) {
        var url = "http://localhost:8080/SingleWidgetResponse";
        fetch(url, {
            method: 'POST',
            headers: {'content-type': 'application/json'},
            body: JSON.stringify({
                'userId': 'xyz',
                'widgetId': id,
                'widgetType': "Short Answer",
                'data': {
                    'response': response
                }
            })
        }).then(res => res.toString()).then(()=>alert(alertMsg));
        $("#"+id).attr('disabled', true);
    }
});