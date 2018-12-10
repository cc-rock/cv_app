CV App
======

Implementation and architecture choices
---------------------------------------

- The app uses a Model-View-Presenter architecture, and in particular:
  - **model**: is responsible to call the remote api and provide an
  object model of the returned data. Retrofit, rxjava and moshi have been used
  to accomplish the purpose.
  - **presenter**: acts as a brigde between the model part and the view, receiving
    calls from the view (corresponding to user actions) and executing the actions
    by communicating with the model as appropriate. It also instructs the view to
    display the results of such actions.
    In this implementation, rxjava has been also used to handle the communication between
    view and presenter: each action exposed by the presenter (just one in this case) returns
    an Observable which emits instances of view models (objects that describe what the view
    should display). The view is then responsible to subscribe to the Observable and
    render the received view models.
    With this particular way of communicating, there is no "View" interface, as the presenter
    never needs to call back to the view.
  - **view**: the UI part, composed by the Activity (which communicates with the Presenter)
    and other UI components such as adapters and view holders

- Dependency injection: dagger 2 has been used, with dagger-android extensions

- Unit tests: JUnit, Mockito, and Mockito-Kotlin have been used

- UI tests: Espresso has been used, and in particular, to test the Activity I made the choice
  to override the dagger components and modules in order to inject a mock presenter, and then
  instruct it to return the responses I wanted to test, without making real calls to the
  remote api. To accomplish this, it has been necessary to override the AndroidJUnitRunner test runner
  in order to instantiate a custom version of the Application object, that will use the test
  versions of the dagger component and module.

Known issues / possible improvements
====================================

- Add a "pull to refresh" action to the recyclerview, that reloads the data from the
remoter api
- Turn the email, phone, and company websites into clickable links
- The "Years of experience" field in the skills section shown a decimal digit even when
the number is integer
- Add proguard rules for the various libraries used: I haven't done that for lack of time,
so there is chance that the app won't work properly with a release build and proguard enabled
