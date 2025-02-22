# Vecros RTSP Video Streaming App
This Android application allows users to stream RTSP (Real-Time Streaming Protocol) video streams directly on their devices. Built using VLC's LibVLC library, it supports essential controls like play, pause, and stop, ensuring a smooth streaming experience.

## 📱 Features
- **🎥 RTSP Video Streaming –** Stream RTSP URLs directly on your Android device.
- **⏯️ Playback Controls –** Play, Pause, and Stop the stream anytime.
- **💡 User-Friendly Interface –** Simple UI with an input field and essential controls.
- **🔒 Error Handling –** Informs users when the RTSP URL is invalid or if there’s no stream to pause/stop.

## ⚙️ Tech Stack
- **Language:** Java
- **UI:** XML, SurfaceView
- **Library:** LibVLC (VLC for Android)

## 🚀 Getting Started
- 1️⃣ Prerequisites
- Android Studio
- Android SDK (API 21 or higher)
- LibVLC dependency added to your project

## 3️⃣ Clone the Repository
```bash
  git clone (https://github.com/Dewanshi4012/VecrosFinal.git
  ```

## 4️⃣ Run the App
- Open the project in Android Studio.
- Connect an Android device or use an emulator.
- Click Run ▶️.

## 📖 How to Use the App
- **Enter RTSP URL –** Paste the RTSP link in the input field.
- **Click "Play" –** The stream starts playing on the SurfaceView.
- **Pause or Stop –** Use the respective buttons to control the stream.
💡 Tip: Make sure the RTSP URL is valid and accessible from your device.

## Challenges Faced
- **🔗 Handling Invalid RTSP URLs:** Managing user inputs like incorrect or unsupported RTSP links required proper error handling to avoid app crashes and provide meaningful feedback.
- **⚡ RTSP Stream Stability:** Ensuring smooth and consistent streaming without buffering or crashes was a key challenge, especially with varying RTSP server responses.
