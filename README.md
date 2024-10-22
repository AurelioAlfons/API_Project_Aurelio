Author: Aurelio Hevi Alfons

Android API Integration App

Objective
Develop an Android application that demonstrates proficiency in API integration, user interface design, and Android development best practices.

Project Overview
This Android application features three primary screens:
1. Login - Authenticates users via the 'vu-nit3213-api'.
2. Dashboard - Displays an overview of retrieved data.
3. Details - Shows detailed information fetched from the API.

The app interacts with the 'vu-nit3213-api' to authenticate users and retrieve data, showcasing modern Android development techniques.

Features
- API integration using Retrofit
- User authentication
- Simple, clean user interface following Material Design guidelines
- Proper navigation between screens
- Data fetching and display in Dashboard and Details screens
- Dependency injection using Hilt for managing objects efficiently
- Lambda functions for concise, flexible code logic

Dependencies
The project uses the following dependencies:
- Safe argument
- Retrofit for API calls
- Moshi for JSON parsing
- Material Design 3 for UI components
- OkHttp for HTTP logging
- Hilt for dependency injection
- JUnit for unit testing of lambda functions and other components

How to Build and Run the App

!!ENSURE!!: Using the Koala Android Studio Version

Step 1:
- Copy the GitHub repository link and paste it into your browser.
- Navigate to the Code tab of the repository.
- Click on the Code button
- Clone the repository or Download ZIP and extract the folder to your device.

Step 2:
- Open the project in Android Studio.
- Sync Gradle dependencies (Android Studio will usually prompt you to do this automatically).

Step 3:
- Run the app on the Android Studio Emulator or a connected Android device by clicking the Run button or pressing Shift + F10.

Step 4:
- App will display the Login Page
- Enter Username: Aurelio
- Enter Password: s4672291
- Ensuring the proper credentials
- Click the Sign In Button
- Wait until it navigates to dashboard page upon success
- Scroll throught the RecyclerView
- Click on Show data in any of the List Entity
- Navigates to Details
- Click Go back to return
