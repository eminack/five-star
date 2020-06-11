var smileylist = {1:$('#'+id).data("sm1"),2:$('#'+id).data("sm2"),
    3:$('#'+id).data("sm3"),4:$('#'+id).data("sm4"),5:$('#'+id).data("sm5")};


$('#'+id).rating({
    step: 1,
    starCaptions: smileylist,
    starCaptionClasses: {1: 'text-danger', 2: 'text-warning', 3: 'text-info', 4: 'text-primary', 5: 'text-success'}
});
$('#'+id).on('rating:change',function(event, value, caption) {
    console.log(value);
    console.log(caption);
    var url = "http://localhost:8080/SingleWidgetResponse";
    fetch(url, {
        method: 'POST',
        headers: {'content-type': 'application/json'},
        body: JSON.stringify({
            'userId': 'xyz',
            'widgetId': id,
            'widgetType': "Five Star",
            'data': {
                'rating-value': value
            }
        })
    }).then(res => res.toString()).then(()=>alert(alertMsg));
    $('#'+id).rating('refresh', {disabled: true, showClear: false, showCaption: true});
});