## Movies App

**General Description**

* This application retrieves information using TheMovieDataBase TMDB api to show different data sets
  including a profile retrieval and multiple movie lists to be shown in an intuitive and user friendly structure.
* Additionally, it includes a feature to upload an image picked from the phone's gallery to the
  Firebase Storage remote cloud service.

**Libraries integrated**

1. HILT - Used to add dependency injections to the project.
2. Retrofit - Used to perform asynchronous calls to a remote service API.
3. Glide - Used to load images into the UI safely.
4. Firebase Storage - Used to upload and store files, in this case images, to the remote cloud storage.
5. Room - Used for local persistence of data.
6. Material - Used for a rich, intuitive and user friendly UI elements.
7. Kotlin Flow - Used to integrate coroutine-based asynchronous requests and operations.
8. Kotlin Coroutines - Used to integrate asynchronous code and tasks for a seamless interaction.

**Architecture**

This app was programmed under the rules and practices of MVVM, Clean code architecture,
SOLID principles, Repository pattern, Dependency Injection.

**Data Flow**

* Profile feature - A call to the PopularPerson TMDB api is made using retrofit to retrieve an
  actor's profile information to be displayed to the user.
* Movies List feature - Similarly, three calls are made to the TMDB api to retrieve popular,
  top rated and recommended movies to be displayed in recyclerview lists in an intuitive fashion.
* Image upload - The user is able to choose an image from the phone's gallery and upload it to
  firebase storage.

**Evidence**

| Feature              | Evidence                                    |
|----------------------|---------------------------------------------|
| Profile Feature      | ![img.png](./assets/Evidence/img.png)       |
| Movies List Feature  | ![img_1.png](./assets/Evidence/img_1.png)   |
| Movies List Feature  | ![img_2.png](./assets/Evidence/img_2.png)   |
| Movies List Feature  | ![img_3.png](./assets/Evidence/img_3.png)   |
| Image upload Feature | ![img_4.png](./assets/Evidence/img_4.png)   |
| Image upload Feature | ![img_5.png](./assets/Evidence/img_5.png)   |
| Image upload Feature | ![img_7.png](./assets/Evidence/img_7.png)   |
| Firebase Console     | ![img_8.png](./assets/Evidence/img_8.png)   |
| Firebase Console     | ![img_9.png](./assets/Evidence/img_9.png)   |
| Firebase Console     | ![img_10.png](./assets/Evidence/img_10.png) |


**Future Improvements and additions**

* Add persistence to feature 1 and 2. Using Room to store the data retrieved with the api services
  into the database. A logic code would be needed to determine whether to call the service or show
  the database information in case there was a problem accessing the remote service.
* Proper loading UI elements and their logic to show the user when is the app working on the
  retrieval of data. (Use of FragmentDialogs and LiveData/Flow)
* Integration of App location services to store the app's phone location every 5 minutes and store
  it in Firebase. (Use of Firebase Database, Google Maps location services).
* Add multiple image selection to Feature 4 where now only a single image can be selected.
* Improved styles, colors and an App specific Theme.
* Improved UI success and failure of all retrieval events screens.
* Tweaking the code to use SharedFlows in contrast to StateFlows to determine events in view models.
* A Login addition would com in handy to have users. (Use of Firebase Authentication)

=======================================

### App Version Reference

* version 1         1.0 - MAR 02 2023
