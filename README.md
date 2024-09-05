# Music Recommendation Engine: A Mini-Spotify Experience on Android

## Overview
This project is a Kotlin-based Android application that simulates key features of Spotify, focusing on music recommendations and user favorites. It demonstrates modern Android development practices and integrates various technologies to create a seamless music browsing and playback experience.

## Key Features
- User-friendly interface for browsing music feed, albums, and managing favorites
- Music playback functionality using Google ExoPlayer
- Local caching of favorite tracks for offline access
- Mock RESTful API integration for simulating backend services

## Technologies Used
- **Frontend**: Kotlin, Android Jetpack (Compose, Navigation)
- **Backend**: Kotlin (IntelliJ IDEA), json-server for mock API
- **Architecture**: MVVM (Model-View-ViewModel)
- **Dependency Injection**: Hilt
- **Database**: Room for local caching
- **Networking**: Retrofit for API requests
- **UI Components**: Jetpack Compose
- **Music Playback**: Google ExoPlayer

## Project Structure
- `app/src/main/kotlin/`: Main application code
    - `ui/`: User interface components built with Jetpack Compose
    - `viewmodel/`: ViewModels for managing UI-related data
    - `repository/`: Data handling and business logic
    - `api/`: Retrofit interface for API calls
    - `database/`: Room database setup and DAOs
- `app/src/main/res/`: Resources (layouts, drawables, etc.)

## Setup and Installation
1. Clone the repository
2. Open the project in Android Studio
3. Sync Gradle files
4. Run the application on an emulator or physical device

## Future Enhancements
- Implement user authentication
- Integrate with a real music streaming API
- Add social features like sharing playlists
- Improve recommendation algorithm based on user listening habits

## Contributors
Yunao Li
