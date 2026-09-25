# 🤖 JARVIS - Android Voice Assistant

JARVIS - bu Android telefonida ishlaydi ovozli yordam beruvchi dastur. U ovoz orqali buyruqlarni qabul qiladi, tushunadi va javob beradi.

## ✨ Funksiyalar

- 🎤 **Ovoz orqali buyruqlar** - "Salom Jarvis", "Bugun nechta?", va hokazo
- 🔊 **Ovozli javoblar** - Jarvis sizga ovoz bilan javob beradi
- 🌐 **Internet buyruqlari** - YouTube, Google, xarita, va boshqalar
- ⏰ **Vaqt va sana** - "Soat nechchi?", "Bugun sana nima?"
- 📱 **Telefon buyruqlari** - Telefon, SMS, kamera, va hokazo
- 🏎️ **Tez va samarali** - Buyruqlarni darhol bajaradi

## 🚀 Qanday ishga tushurish

### Talablar
- Android Studio
- Kotlin
- Android 7.0+ (API 24)
- Internet connection

### O'rnatish

1. **GitHub'dan clone qiling:**
   ```bash
   git clone https://github.com/abduqodiruvchdostoneb-sys/jarvis.git
   cd jarvis
   ```

2. **Android Studio'da oching:**
   - Android Studio ishga tushiring
   - File → Open → jarvis papkasini tanlang

3. **Gradle synchronize qiling:**
   - Gradle sync tugmasini bosing
   - Yoki: File → Sync Now

4. **Telefonda o'rnatish:**
   - Telefon USB orqali kompyuterga ulang
   - Developer mode yoqing
   - Run button (▶️) bosing yoki Shift + F10

## 📋 Buyruqlar

### Salomlashish
- "Salom Jarvis"
- "Assalomu alaikum"

### Vaqt va Sana
- "Soat nechchi?"
- "Vaqt qanday?"
- "Bugun nechta?"
- "Sana nima?"

### Internet
- "YouTube ochish"
- "Google ochish"
- "Google'da Jarvis qidir" (qidiruv)
- "Xarita ochish"

### Telefon
- "Telefon ochish" (raqam dialogs)
- "Kamera ochish"
- "Sozlama ochish"

## 🛠️ Loyiha Tuzilishi

```
jarvis/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/jarvis/voiceassistant/
│   │   │   │   ├── MainActivity.kt
│   │   │   │   ├── RecognitionListener.kt
│   │   │   │   └── commands/
│   │   │   │       └── CommandProcessor.kt
│   │   │   ├── res/
│   │   │   │   ├── layout/
│   │   │   │   ├── drawable/
│   │   │   │   └── values/
│   │   │   └── AndroidManifest.xml
│   └── build.gradle
├── build.gradle
├── settings.gradle
└── README.md
```

## 📱 Ruxsatlar

Jarvis quyidagi ruxsatlarni talab qiladi:
- `RECORD_AUDIO` - Ovozni yozib olish
- `INTERNET` - Internet ullanish
- `CALL_PHONE` - Qo'ng'iroq qilish
- `SEND_SMS` - SMS yuborish
- `ACCESS_FINE_LOCATION` - Joyni aniqlash

## 🎨 Tuzatish va Kengaytirish

### Yangi buyruq qo'shish

`CommandProcessor.kt` faylidagi `processCommand()` metodiga qo'shing:

```kotlin
lowerCommand.contains("yangi buyruq") ->
    "Javob bu yerda"
```

### Qo'shimcha funksiyalar
- AI integratsiyasi (OpenAI API)
- Doimiy ovoz tinglash
- Smart home boshqaruvi
- Bildirishnomalar
- Sozlamalar

## 🐛 Muammolarni Hal Qilish

### Ovoz tangilanmaydi
- Mikrofon ruxsatini tekshiring
- Telefon sessizlik rejimida ekanligini tekshiring
- Dil sozlamalarini tekshiring (uz_UZ)

### Internet buyruqlari ishlamaydi
- Internet ulanganligini tekshiring
- Brauzer sozlamalarini tekshiring

### TextToSpeech javob bermaydi
- Telefonda TTS tilini o'rnatish kerak bo'lishi mumkin
- Play Store'dan Google Text-to-Speech o'rnating

## 📄 Litsenziya

MIT License - Erkin foydalaning!

## 👨‍💻 Muallif

Jarvis - Abduqodir Yusupov tomonidan yaratilgan

---

**Jarvis va siz birgalikda nima qila olasiz? Buyruq berishni boshlang! 🚀**