# 🧊 DynamicAppIcon

A modern Android app that dynamically updates its launcher icon via **Firebase Remote Config** and also allows **local user customization** of icon themes. Built with **Kotlin**, **Jetpack Compose**, and the latest Android tools.

---

## 🚀 Features

- 🔁 **Dynamic Icon Switching**
  - Control app icon remotely using Firebase Remote Config
  - Easily change icons for themes like holidays, colors, events

- 🎨 **User Icon Customization**
  - Let users locally choose their preferred icon style
  - Fully managed using `<activity-alias>` definitions

- 🔧 **Manifest Placeholder Injection**
  - Icons and aliases are dynamically injected via placeholders from a `config` file

- ☁️ **Remote Config Integration**
  - Firebase controls which alias is active remotely
  - Supports rollout and fallback mechanisms

---

## 🏗️ Tech Stack

- 🛠️ Kotlin `2.0.21`
- ⚙️ Android Gradle Plugin `8.1.0+`
- 🧱 Jetpack Compose
- 🌐 Firebase Remote Config
- 🧾 Manifest Placeholders and `activity-alias`
- 🧪 Tested with **Android Studio Merkeët**

---

## 🛠️ Setup Guide

1. **Clone the Repository**

```bash
git clone https://github.com/AhmedMokhtarAli/DynamicAppIcon.git
cd DynamicAppIcon
