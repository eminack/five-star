/* Either of Checkbox is clicked , hide-show
corresponding labels
 */
function checkBoxClicked() {
    var checkbox_1 = document.getElementById("checkbox-1");
    var checkbox_2 = document.getElementById("checkbox-2");

    if (checkbox_1.checked) {
        document.getElementById('option-2-input-id').style.display = 'none';
        document.getElementById("displayConstraint").value = null;
        document.getElementById("mandatoryConstraint").value = null;
    }
    else if (checkbox_2.checked) {
        document.getElementById('option-2-input-id').style.display = 'block';
    }
}

function showAlertMsg() {
    window.alert("Enter Correct details");
}

function createConstraint() {
    var checkbox_1 = document.getElementById("checkbox-1");
    var checkbox_2 = document.getElementById("checkbox-2");
    var displayString = document.getElementById("displayConstraint").value;
    var mandatoryString = document.getElementById("mandatoryConstraint").value;

    let makeItMandatoryConstraintList = [];
    let displayConstraintList = [];

    if (!checkbox_1.checked && !checkbox_2.checked) return false;
    if (checkbox_1.checked) {
        makeItMandatoryConstraintList.push({widgetId:WidgetIdToBeConstrained,responseId:""});
        console.log("checkbox-1 checked");
    }
    /* else means checkbox 2 is checked*/
    else {
        if (displayString === "" && mandatoryString === "") {
            showAlertMsg();
        }
        else {
            makeItMandatoryConstraintList = createConstraintListFromUserString(mandatoryString);
            displayConstraintList = createConstraintListFromUserString(displayString);
        }
    }
    /* condition so that at-least 1 constraint has been added,
        then only make request to backend;
     */
    if (displayConstraintList.length || makeItMandatoryConstraintList.length) {
        makePostRequestToBackend(makeItMandatoryConstraintList,displayConstraintList);
    }

    return false;
}

function makePostRequestToBackend(mandatoryList, displayList) {
    let url = "http://localhost:8080/form/" + formid + "/widget/"+WidgetIdToBeConstrained+"/add-constraint";
    fetch(url,{
        method:'POST',
        headers:{'content-type' : 'application/json'},
        body:JSON.stringify({
            'displayConstraints':displayList,
            'makeItMandatoryConstraints':mandatoryList
        })
    }).then(res => res.toString())
        .then(()=>window.location.href = "http://localhost:8080/form/"+formid+"/edit");
}

function createConstraintListFromUserString(userString) {
    var constraintList = [];
    if (userString === "") return constraintList;
    var userList = userString.split(",");

    userList.forEach(function (value) {
       let userResponse = parseInt(value);
       userResponse--;      /* because indexing starts from 0, but user entered
                                thinking it starts from 1*/

       if (validateUserResponse(userResponse)) {
           constraintList.push({widgetId:WidgetIdList[userResponse],responseId:""});
       }
    });

    return constraintList;
}

function validateUserResponse(userResponse) {
    if (userResponse < 0 || userResponse >= WidgetIdList.length) return false;

    return true;
}