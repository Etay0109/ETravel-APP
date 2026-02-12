# ✈️ ETravel – Travel & Flight Exploration App

**ETravel** is a cloud-connected Android application built with **Kotlin**, designed to help users explore travel destinations and seamlessly book flights after finding a suitable location.

The application integrates with **Firebase**, ensuring secure authentication, real-time data synchronization, and cloud-based storage of user information such as favorites, bookings, and personal files.

This project was developed as part of the **User Interface Development** course and demonstrates a complete end-to-end mobile experience.

---

# 🚀 Core Features

- 🔐 **Smart Authentication** – Email & Password login or Google Sign-In  
- 🌍 **Destination Discovery** – Browse popular and explore destinations  
- ❤️ **Personal Favorites** – Save preferred destinations  
- 🛫 **Flight Booking Simulation** – Complete reservation form with validation  
- 👤 **User Profile Management** – Upload profile picture and personal documents  
- ☁️ **Cloud Persistence** – All data stored securely in Firebase  

---

# 🔄 Application Flow

`Login / Register` ➝ `Home Screen` ➝ `Destination Details` ➝ `Booking Form` ➝ `Booking Success`
 

---

# 📱 Screens Breakdown

## 🔐 Login & Register Screens

**Purpose:**  
Authenticate users and protect personal data.

**Overview:**
- Separate screens for **Login** and **Registration**
- Email & Password authentication
- Google Sign-In integration
- Redirect to Home Screen after successful login

---

## 🏠 Home Screen

**Purpose:**  
Central hub for discovering destinations.

**Overview:**
- "Popular Destinations" and "Explore Destinations" sections
- ❤️ Save destinations to Favorites
- Navigation to Profile and Favorites screens
- Destinations and favorites stored per user in Firebase Realtime Database

---

## 🌍 Destination Details Screen

**Purpose:**  
Present detailed information about a selected destination.

**Overview:**
- Destination image and description
- Attractions list
- Official language
- Average daily cost
- "Book Now" button

---

## ⭐ Favorites Screen

**Purpose:**  
Display user-saved destinations.

**Overview:**
- List of destinations marked with ❤️
- Real-time synchronization with Firebase
- Personalized per authenticated user

---

## 🛫 Booking Form Screen

**Purpose:**  
Collect booking details.

**Overview:**
- Personal information
- Departure & return dates
- Airline selection
- Credit card details (simulation)
- Saves booking to database and navigates to confirmation

---

## ✅ Booking Success Screen

**Purpose:**  
Confirm successful reservation.

**Overview:**
- Booking summary
- Destination, dates, and airline details
- Confirmation message
- Booking stored in Firebase Realtime Database

---

## 👤 Profile Screen

**Purpose:**  
Manage user profile and personal files.

**Overview:**
- Upload profile picture
- Upload personal documents
- View account information
- Secure logout

---

# ☁️ Firebase Integration

## 🔐 Firebase Authentication
- Email & Password login
- Google Sign-In
- Secure session management

## 🗄 Firebase Realtime Database
- Store destinations
- Store user-specific favorites
- Store booking history

## ☁️ Firebase Storage
- Store profile images
- Store uploaded personal documents

---

# 🛠 Technologies Used

- **Language:** Kotlin  
- **UI Framework:** Android SDK (XML View System)  
- **Backend:** Firebase (Authentication, Realtime Database, Storage)  

---

# 🎥 Application Demo

Watch a full demonstration of the application flow:

[![Watch the Demo](https://img.shields.io/badge/YouTube-Watch%20Demo-red?style=for-the-badge&logo=youtube)](https://youtube.com/shorts/mrRY0QL5Zdc?feature=share)



---
