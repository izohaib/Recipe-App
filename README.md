# 🍽 Recipe App

A clean and simple Android app to browse and view food recipes. Built using **XML layouts**, **Retrofit**, **Room Database**, and **TheMealDB API**.

---

## 📽 Demo Video

[![Watch the demo](https://img.youtube.com/vi/Z_MBPa_QvVE/0.jpg)](https://youtu.be/Z_MBPa_QvVE?si=YCWJZk5yL6So2Des)

---

## 📸 Screenshots

| Home Screen | Details Screen |
|-------------|----------------|
| ![Home](https://i.postimg.cc/fRWTzghv/photo-2025-06-16-22-05-18.jpg) | ![Details](https://i.postimg.cc/m2XK9dHj/photo-2025-06-16-22-05-24.jpg) |

> 📝 *Screenshots stored in the `screenshots/` folder in your repo.*

---

## 🔧 Features

- ✅ Browse recipe **categories**
- ✅ View **meals** by category
- ✅ See detailed recipe info including **images** and **ingredients**
- ✅ Simple and modern UI using **CardView**, **RecyclerView**, and **SearchView**
- ✅ Uses **Room** for offline caching
- ✅ Follows clean architecture: API → Repository → ViewModel → UI

---

## 📡 APIs Used

Data is fetched from [TheMealDB](https://www.themealdb.com/api.php) — a free, open recipe API.

- **Get All Categories**  
  `https://www.themealdb.com/api/json/v1/1/categories.php`

- **Get Meals by Category**  
  `https://www.themealdb.com/api/json/v1/1/filter.php?c=Seafood`

- **Get Meal Details by ID**  
  `https://www.themealdb.com/api/json/v1/1/lookup.php?i=52772`

---

## 🛠 Tech Stack

- **Language:** Kotlin  
- **UI:** XML Layouts, RecyclerView, CardView, SearchView  
- **Networking:** Retrofit  
- **Database:** Room (for offline storage)  
- **Image Loading:** Glide  
- **Architecture:** MVVM (Model–View–ViewModel)

---

## 🚀 Getting Started

1. Clone this repository:
   ```bash
   git clone https://github.com/izohaib/Recipe-App.git
