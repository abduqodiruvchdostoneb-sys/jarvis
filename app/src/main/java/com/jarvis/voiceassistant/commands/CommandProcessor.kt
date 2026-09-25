package com.jarvis.voiceassistant.commands

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import kotlinx.coroutines.delay
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class CommandProcessor(private val context: Context) {

    suspend fun processCommand(command: String): String {
        val lowerCommand = command.lowercase().trim()

        return when {
            // Greeting
            lowerCommand.contains("salom") || lowerCommand.contains("assalomu") ->
                "Salom! Men Jarvis. Sizga yordam bera olaman."

            // Time
            lowerCommand.contains("soat") || lowerCommand.contains("vaqt") ->
                getTime()

            // Date
            lowerCommand.contains("bugun") || lowerCommand.contains("sana") ->
                getDate()

            // Open YouTube
            lowerCommand.contains("youtube") ->
                openYouTube()

            // Open Google
            lowerCommand.contains("google") ->
                openGoogle()

            // Search on Google
            lowerCommand.contains("qidir") || lowerCommand.contains("search") -> {
                val query = lowerCommand.replace("qidir", "").replace("search", "").trim()
                googleSearch(query)
            }

            // Open Maps
            lowerCommand.contains("xarita") || lowerCommand.contains("map") ->
                openMaps()

            // Open Phone
            lowerCommand.contains("telefon") ->
                openPhone()

            // Open Camera
            lowerCommand.contains("kamera") ->
                openCamera()

            // Open Settings
            lowerCommand.contains("sozlama") ->
                openSettings()

            // Default response
            else -> "Sizning '$command' buyruqini tushunmadi. Iltimos, boshqa buyruq aytib ko'ring."
        }
    }

    private fun getTime(): String {
        val currentTime = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("HH:mm")
        val time = currentTime.format(formatter)
        return "Hozir soat $time"
    }

    private fun getDate(): String {
        val currentDate = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("dd-MMMM")
        val date = currentDate.format(formatter)
        return "Bugun $date"
    }

    private fun openYouTube(): String {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com"))
            context.startActivity(intent)
            return "YouTube ochilmoqda..."
        } catch (e: Exception) {
            return "YouTube ochilmadi. ${e.message}"
        }
    }

    private fun openGoogle(): String {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"))
            context.startActivity(intent)
            return "Google ochilmoqda..."
        } catch (e: Exception) {
            return "Google ochilmadi. ${e.message}"
        }
    }

    private fun googleSearch(query: String): String {
        return try {
            val searchQuery = if (query.isEmpty()) "Jarvis" else query
            val intent = Intent(Intent.ACTION_WEB_SEARCH).apply {
                putExtra(SearchManager.QUERY, searchQuery)
            }
            context.startActivity(intent)
            "Google'da '$searchQuery' qidirilmoqda..."
        } catch (e: Exception) {
            "Qidiruv bajarilmadi. ${e.message}"
        }
    }

    private fun openMaps(): String {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://maps.google.com"))
            context.startActivity(intent)
            return "Xarita ochilmoqda..."
        } catch (e: Exception) {
            return "Xarita ochilmadi. ${e.message}"
        }
    }

    private fun openPhone(): String {
        try {
            val intent = Intent(Intent.ACTION_DIAL)
            context.startActivity(intent)
            return "Telefon nomeri dialogs ochildi."
        } catch (e: Exception) {
            return "Telefon ochilmadi. ${e.message}"
        }
    }

    private fun openCamera(): String {
        try {
            val intent = Intent("android.media.action.IMAGE_CAPTURE")
            context.startActivity(intent)
            return "Kamera ochilmoqda..."
        } catch (e: Exception) {
            return "Kamera ochilmadi. ${e.message}"
        }
    }

    private fun openSettings(): String {
        try {
            val intent = Intent(android.provider.Settings.ACTION_SETTINGS)
            context.startActivity(intent)
            return "Sozlamalar ochilmoqda..."
        } catch (e: Exception) {
            return "Sozlamalar ochilmadi. ${e.message}"
        }
    }
}