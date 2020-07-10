
/**
 * It is called when any checkbox in clicked on constraint-modal
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

/**
 * this method reads the value from fields on constraint-modal & create list of constraints which is then
 * sent to backend
 * @returns {boolean}
 */
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

/**
 * function to make POST request for creation of constraints & redirect to edit-form page after response id received
 * @param mandatoryList contains the WidgetConstraint objects
 * @param displayList contains the WidgetConstraint objects
 */
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

/**
 * This methods extracts the numbers from input string , get the corresponding widgetId for that serial number,
 * then create a constraint list out of it. Constraint list contains the WidgetConstraint object
 * @param userString
 * @returns {[]}
 */
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

/**
 * validate that the serial number entered by user is mapped to some widgetId
 * @param userResponse the serial number
 * @returns {boolean} true if it has a widgetId, false otherwise
 */
function validateUserResponse(userResponse) {
    if (userResponse < 0 || userResponse >= WidgetIdList.length) return false;

    return true;
}