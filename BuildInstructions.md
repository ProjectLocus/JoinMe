# Build Instructions
Hello, and thank you for the taking the time to check out and or work on my app.

Below are specific instructions to clone and build this project. Enjoy.

This app is developed for Android, and Android builds on Java 8 so make sure you have it downloaded. Here's a
link to download [JDK 8](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html).
This project won't work on previous versions of the JDK, nor the latest versions ie (JDK 9, 10, nor 7).

Make sure you've configured a [project](https://developers.google.com/identity/sign-in/android/start-integrating) 
with Google Sign API to get their credentials file. It will be needed.

Here's a link to clone the repository from Github:
```
git@github.com:ProjectLocus/JoinMe.git
```
You could clone the repo from the command line, or you could clone it directly in IntelliJ.
Instructions below.
## Building with IntelliJ
This Project was built on IntelliJ. Here's some quick instructions to get started.
1. Open IntelliJ.
2. Make sure you have an active internet connection.
3. Click on 'Check Out from Version Control'.
4. Input the above git link to clone the project.
5. Make sure that the 3rd party libraries and External Services from the [README](https://rawcdn.githack.com/ProjectLocus/JoinMe/7a13faa65ad125e74e50f5d4af6b0d6657facc4a/README.md)
are implemented.
 
With these instructions, you should be able to successfully build on InelliJ.
 
Keep in mind that this app runs on a minimum API level of 23, but has been tested and works on API
levels 24 - 28.
  
##Building with Android Studio

## Didn't Work?
1. Make sure JDK 8 is installed.
2. Make sure java is available.
3. Make sure you have an active internet connection.
4. Make sure you've created and downloaded a Google Sign In credentials file from the site above.
5. Make sure the above implementations and java compile options are included.
6. Make sure you successfully sync your libraries from the above implementations.
7. Try invalidating the cache and restarting.
8. Try rebuilding the project.