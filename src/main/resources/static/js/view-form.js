/*
    makeItMandatoryConstraint : dictionary with {widgetId : List<constraint> }
    displayConstraint : dictionary with {widgetId : List<constraint> }
    constraint : {widgetId, responseId}
    globalResponse : dictionary with {widgetId: {response details} } used to store
                      responses to widgets
    mandatorySet : set with list of widgetId of mandatory widget
 */

var globalResponse = new Object();
var mandatorySet = new Set();

//run this function once the whole page is loaded
document.addEventListener('DOMContentLoaded',function () {
    updateDOM();

});
function updateDOM() {
    displayWidgets();
    makeWidgetsMandatory();
}

function displayWidgets() {

    widgetList.forEach(function (item,index) {
        /* show the widget if all its display dependencies have been answered*/
        if (item in displayConstraint) {
            let flag = 1;
            /* flag = 1 if all display dependencies have been answered , else 0 */
            for (let i = 0; i < displayConstraint[item].length; i++) {
                if (displayConstraint[item][i] in globalResponse) {
                    continue;
                }
                else {
                    flag = 0;
                    break;
                }
            }
            if (flag) {
                /* show this widget because all its dependency have been answered*/
                document.getElementById(item + 'block').style.display = 'block';
            }
            else {
                /* If the response of some dependency of a widget is cleared, then this
                    widget must also be reset & removed from globalResponse Object ,
                    also since some of its dependency have not been answered so hide
                    the widget
                 */
                if (item in globalResponse) {
                    clearThisWidget(item);
                    delete globalResponse[item];

                }
                document.getElementById(item + 'block').style.display = 'none';
            }
        }
        /* if widget doesn't have any display dependency then just show the widget */
        else {
            document.getElementById(item + 'block').style.display = 'block';
        }
    });
}

function makeWidgetsMandatory() {
    widgetList.forEach(function (item,index) {
        /* add widget to mandatory set && show the mandatory asterik
          if all its submit dependencies have been answered */
        if (item in makeItMandatoryConstraint) {
            let flag = 1;
            /* flag = 1 if all makeItMandatory dependencies of this widget
                have been answered , else 0 */
            for(let i = 0; i < makeItMandatoryConstraint[item].length; i++) {
                if (makeItMandatoryConstraint[item][i] in  globalResponse) {
                    continue;
                }
                else {
                    flag = 0;
                    break;
                }
            }
            /* exceptional case when widget is mandatory in form & it's mandatoriness doesn't depend
                 on any other widget & it is not answered till now ,then the above code will set
                 flag = 0 , so we need to explicitly set flag = 1 in  this case.
             */
            if (makeItMandatoryConstraint[item].includes(item)) {
                flag = 1;
            }

            if (flag) {
                /* add widget to mandatory set & show the asterik as all it's makeItMandatory
                    dependency  have been answered */
                mandatorySet.add(item);
                document.getElementById(item + 'asterik').style.display = 'block';
            }
            else {
                /* remove from mandatory set if present & hide the asterik */
                if (mandatorySet.has(item)) mandatorySet.delete(item);
                document.getElementById(item + 'asterik').style.display = 'none';
            }
        }
        /* if item not in submitConstraint that means it is optional,
        * so hide the asterik mark */
        else {
            document.getElementById(item + 'asterik').style.display = 'none';
        }
    });
}

function clearThisWidget(widgetId) {
    /* check which type of widget & call respective function to reset it's value*/
    if (globalResponse[widgetId]["widgetType"] === "FIVE_STAR") {
        resetFiveStar(widgetId);
    } else if (globalResponse[widgetId]["widgetType"] === "SHORT_ANSWER") {
        resetShortAnswer(widgetId);
    }
}

function resetFiveStar(widgetId) {
    $('#'+widgetId).rating('reset');
}

function resetShortAnswer(widgetId) {
    document.getElementById(widgetId).value = "";

}
function updateFiveStarValue(widgetId,value) {
   globalResponse[widgetId] = {widgetId:widgetId,widgetType:"FIVE_STAR",ratingValue:value,userId:"xyz"};
   updateDOM();
}

function deleteFiveStarValue(widgetId) {
   delete globalResponse[widgetId];
   updateDOM();
}

function updateShortAnswerResponse(widgetId,response) {
    if (response==="") delete globalResponse[widgetId];
    else globalResponse[widgetId] = {widgetId:widgetId,widgetType: "SHORT_ANSWER",responseText:response,userId:"xyz"};

    updateDOM();
}

function validateForm() {
    let mandatoryList = Array.from(mandatorySet);

    for (let i = 0 ; i < mandatoryList.length; i++) {

        if (mandatoryList[i] in globalResponse) {
            continue;
        }
        else return false;
    }
    return true;
}

function submitForm() {
    let obj;
    var responseList = [];

    if (validateForm()){

        for (const [key, value] of Object.entries(globalResponse)) {
            responseList.push(value);
        }

         var url = "http://localhost:8080/formResponse";

         fetch(url, {
         method: 'POST',
             headers: {'content-type': 'application/json'},
             body: JSON.stringify({
                'userId': 'xyz',
                'formId': formId,
                'responseList': responseList
              })
         }).then(res => res.json())
             .then(data => obj = data)
             .then(() => console.log(obj))
             .then(() => alert(alertMsg)).then(() => window.location.href = "http://localhost:8080/");

    } else {
        window.alert("Please Fill the mandatory fields");
    }
}
