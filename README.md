# JoinMe
A social media invitation app with a focus on connecting people on a human level by allowing users 
to send invitations to other people to actual events such as enjoy a cup of coffee or attend an office party.
Here's our [Apache License 2.0](https://rawcdn.githack.com/ProjectLocus/JoinMe/dc49ee484daa1a7591474ff979f394719ab53cbf/LICENSE)

## Team Members:
* **Alex Rael**:
Did most of the Room Persistence manipulation throughout the app. That includes grabbing the necessary
information to create a new user, displaying that info in the user profile section, grabbing info from
the create invitation fragment and displaying it in the new invitation fragment. Also setup most of the
Retrofit calls to communicate with the backend.

* **Dina Rabanal**:

* **Brian Bleck**:

## User Stories:
* As a user I want to be able to send an invitation to other people who I can see.
  * As a user I want to be able to invite one other person out to coffee.
  * As a leader of an organization I want to be able to invite everyone in a meeting to lunch.
  * As a user I want to be able to connect with new people in close proximity.
* As a senior I want to be able to alert people around me that I am in need of medical attention.
  * As a user I want to be able to alert people in the immediate vicinity that I am in danger.
  * As a user I want to be able to alert people of a fire.
* As a user I want to able to receive invitations from other users.
* As a store owner I want to be able to inform users of current sales.
  * As a bar owner I want to be able to inform users of bar events.
* As a community organizer I want to be able to alert people that there is a rally coming together.
* As a student I want to be able to invite my friends to a party.
* As a user I want to be able to drop down an event in an area for people to see within that are.
  * As a user I want to be able to see events dropped down by other users within the same area.
* As a user I want to be able to tell everyone that I've lost my pet.
  * As a user I want to be able to tell everyone that I have a gig going on.

## Technical Information:
The app was developed in mostly English with a restricted Portrait orientation. Operating Systems
developed in were Windows and OSx. Software used includes both IntelliJ and Android Studio. Project
was developed in Java 8 and the app had a minimum SDK level of 23 but has been tested to work on
SDKs 24-28. Most test were ran on both emulators and physical android devices that run at least
Marshmellow.

## External Services, 3rd Part Libraries with Licenses:
* Google Services (including Google Sign In and Google Location):
  * Licenses:
      * [Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0)
      * [Creative Commons Attribution 2.5](https://creativecommons.org/licenses/by/2.5/)
  * Dependencies:
```
implementation 'com.google.gms:google-services:4.1.0'
implementation 'com.google.android.gms:play-services-auth:16.0.1'
implementation 'com.google.android.gms:play-services-location:16.0.0'                                                         
```

* Room Persistence ORM:
  * Licenses:
    * [Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0)
    * [Creative Commons Attribution 2.5](https://creativecommons.org/licenses/by/2.5/)
  * Dependencies:
``` 
implementation 'android.arch.persistence.room:runtime:1.1.1'
annotationProcessor "android.arch.persistence.room:compiler:1.1.1"
testImplementation "android.arch.persistence.room:testing:1.1.1"
```

* Retrofit and Gson:
  * Licenses: 
    * Retrofit [Apache License 2.0](https://rawcdn.githack.com/square/retrofit/ee72ada9bb9d227f133786a866606c019c349064/LICENSE.txt),
    * Gson [Apache License 2.0](https://rawcdn.githack.com/google/gson/da5cae371e8b739fe63a6c6d16debf7b297dea0e/LICENSE)
  * Dependencies:
```
implementation 'com.google.code.gson:gson:2.8.5'
implementation 'com.squareup.retrofit2:retrofit:2.4.0'
implementation 'com.squareup.retrofit2:converter-gson:2.4.0'
```

* Glide:
  * Licenses (and other associated Licenses): [Licenses](https://rawcdn.githack.com/bumptech/glide/f7d860412f061e059aa84a42f2563a01ac8c303b/LICENSE)
  * Dependencies:
```
implementation 'com.github.bumptech.glide:glide:4.8.0'
annotationProcessor 'com.github.bumptech.glide:compiler:4.8.0'
```

* Ramotion (Circle Menu):
  * License: [MIT License](https://rawcdn.githack.com/Ramotion/circle-menu-android/c958736ee12b73c41f469d6ceff7956c706233dc/LICENSE)
  * Dependencies:
```
implementation 'com.ramotion.circlemenu:circle-menu:0.3.1'
```

* Hdodenof (Circle Image View):
  * License: [Apache License 2.0](https://rawcdn.githack.com/hdodenhof/CircleImageView/e9ce455fdd2cceed5d5dab2a1fc7216deb7d2b37/LICENSE.txt)
  * Dependencies:
```
implementation 'de.hdodenhof:circleimageview:2.2.0'
```
