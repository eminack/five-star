# Widgets as a Service

Widgets as Service is an application to create various widgets which can be embedded into other webpages and collect information from user interactions. The application should provide an interface to create new widgets like forms and questionnaire set, Text widget etc.

Widgets as a service will have 3 applications

1. Forms creation tool
2. Form serving
3. Data analysis and presentation.

## Form Creation Tool

From creation tool should provide an interface to create forms and widgets using smaller web constructs. User should be able to select various inbuilt widgets and customize for their need. On successful creation of a form/widget the tool will provide an URL which he can embed into other pages. Product managers can preview and test newly created form/widget/

## Form/Widget serving

Form/widget should be able to embedded into other pages, using the URL provided by the form creation tool. The forms should collect the data on user interactions and store in the database. The forms should have a feedback mechanism to tell the customer whether form submission is success or failure or also alert on any validation failures.

## Data analysis Tool

Data analysis tool should provide an interface to access the data collected.

## Sample widgets

Plain Text Widget

* Plain text 
* Plain text widget with link to feedback form


Star widget

* Star widget
* Star widget should be able to surface specific form link based on response (1 star, 5 star etc)


Image upload

* Image upload

Voice Recording

* Voice recording

Video recording widget

* Video widget

HMD widget

* HMD Widget should be able to capture one click feedback response (thumps up/down)

Scale

* Rate experience from 1 to 10

Dynamic Questions

* Dynamic questionnaire set. Question should start appearing based on answer to previous question.

Form

* Generic form for taking feedback from customer

Screenshot

* Current page screenshot upload
* Customer should be able to annotate screenshot before uploading  to feedback response


General

* Once a feedback is submitted on inline forms, an acknowledgement message has to be surfaced
* Customer should be able to dismiss a feedback form if they don’t wish to submit feedback
* If we are not able to complete feedback data processing, error/sorry message has to be surfaced
* Ability to configure number of times we can solicit feedback from a given customer set
* Should be able to embed in Webpages
* should be able to embed in emails

## Mock images

![Your Opinion Matter](/img/your-opinion-matters.png)
![Scale](/img/scale.png)
![Rate](/img/rate.png)
![Rate with Title](/img/rate-with-title.png)
![Rate With title and Hint](/img/rate-with-title-hint.png)
![Screenshot select](/img/screenshot-select.png)
![Screenshot select area](/img/screenshot-select-area.png)

## References

* Google Forms
* Iframe
* Embedded Widgets

