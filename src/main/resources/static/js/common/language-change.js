$("#locales").change(function () {
    var selectedOption = $('#locales').val();
    console.log(selectedOption);
    var link = window.location.href;
    link = link.split('?')[0];
    link = link+'?lang='+selectedOption;
    if (selectedOption !== ''){
        window.location.replace(link);
    }
});