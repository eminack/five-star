var smileylist = {1:$('#'+id).data("sm1"),2:$('#'+id).data("sm2"),
    3:$('#'+id).data("sm3"),4:$('#'+id).data("sm4"),5:$('#'+id).data("sm5")};


$('#'+id).rating({
    step: 1,
    starCaptions: smileylist,
    starCaptionClasses: {1: 'text-danger', 2: 'text-warning', 3: 'text-info', 4: 'text-primary', 5: 'text-success'}
});
$('#'+id).on('rating:change',function(event, value, caption) {
    let obj;
    console.log(value);
    console.log(caption);
    var url = "http://localhost:8080/widgetresponse";
    fetch(url, {
        method: 'POST',
        headers: {'content-type': 'application/json'},
        body: JSON.stringify({
            'userId': 'xyz',
            'widgetId': id,
            'widgetType': "FIVE_STAR",
            'ratingValue':value
        })
    }).then(res => res.json())
        .then(data => obj=data)
        .then(()=>console.log(obj))
        .then(()=>alert(alertMsg));
    $('#'+id).rating('refresh', {disabled: true, showClear: false, showCaption: true});
});