# catalogue
This is a training project

# In this project you will find:

<ul>
<li>This application is a training project.</li>
<li>The app is built using the API provided by https://www.yelp.com/developers/</li>
<li>The codebase is in 100% pure Kotlin.</li>
<li>The project is following the MVVM architecture pattern, making use of the following Android Architecture components: Navigation, Databinding and LiveData.</li>
<li>Dagger 2 is used as a dependency injection framework;</li>
<li>A combination of RxJava 2, Retrofit 2 and OkHttp4 for implementing the network infrastructure</li>
<li>Coil image library, which takes care of network image loading</li>
<li>Lottie for animation</li>
<li>Working to be lint free! Currently Android Linting has many offenders but new contributions should help progress towards zero.</li>
</ul>

# Opening the project in Android Studio
To open the project in Android Studio, begin by cloning the git respository, and then checking the master branch. After that open the root directory in Android Studio.
## Follow these steps:

Update Android studio to version 4.0

<b>Help -> Check for updates...</b>

Clone the repository:

```
$ git clone https://github.com/naimJus/catalogue
$ cd catalogue
```

This step checks out the master branch. If you want to change to a different sample:

```
git checkout master
```

Finally open the catalogue/ directory in Android Studio.


Open Android Studio, select "Open an existing Android Studio Project" from the main window, and select the repository folder from the file browser.


# Running the project

To run the project you will need to generate a API key from the Yelp developers website. 

https://www.yelp.com/developers/

After generating the key navigate to the root of the project and create a properties file. 

```
catalogue/apikey.properties</b>
```

Inside the file enter the API key in the following format:

```
API_KEY= "enter your key here"
```

Final step do a gradle build 

```
gradlew build
```
