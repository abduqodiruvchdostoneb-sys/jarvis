package com.jarvis.voiceassistant

import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jarvis.voiceassistant.commands.CommandProcessor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.Locale

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private lateinit var speechRecognizer: SpeechRecognizer
    private lateinit var textToSpeech: TextToSpeech
    private lateinit var statusText: TextView
    private lateinit var responseText: TextView
    private lateinit var listenButton: Button
    private lateinit var commandProcessor: CommandProcessor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize UI
        statusText = findViewById(R.id.statusText)
        responseText = findViewById(R.id.responseText)
        listenButton = findViewById(R.id.listenButton)

        // Initialize Speech Recognizer
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this)
        val recognitionListener = RecognitionListener(this)
        speechRecognizer.setRecognitionListener(recognitionListener)

        // Initialize Text-to-Speech
        textToSpeech = TextToSpeech(this, this)

        // Initialize Command Processor
        commandProcessor = CommandProcessor(this)

        // Set up listen button
        listenButton.setOnClickListener {
            startListening()
        }
    }

    private fun startListening() {
        statusText.text = "🎤 Tinglayapman..."
        responseText.text = ""
        listenButton.isEnabled = false

        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, "uz_UZ")
            putExtra(RecognizerIntent.EXTRA_PROMPT, "Buyruqni ayting...")
        }

        try {
            speechRecognizer.startListening(intent)
        } catch (e: Exception) {
            statusText.text = "❌ Xato: ${e.message}"
            listenButton.isEnabled = true
        }
    }

    fun onSpeechResult(result: String) {
        statusText.text = "📝 Tushunildi: $result"
        responseText.text = "Buyruq qayta ishlanmoqda..."
        listenButton.isEnabled = true

        // Process command in background
        GlobalScope.launch(Dispatchers.Main) {
            val response = commandProcessor.processCommand(result)
            responseText.text = response
            speak(response)
        }
    }

    fun onSpeechError(error: String) {
        statusText.text = "❌ Xato: $error"
        listenButton.isEnabled = true
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    private fun speak(text: String) {
        if (textToSpeech.isSpeaking) {
            textToSpeech.stop()
        }
        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null)
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = textToSpeech.setLanguage(Locale("uz", "UZ"))
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                // Fallback to English
                textToSpeech.setLanguage(Locale.ENGLISH)
            }
            statusText.text = "✅ Jarvis tayyor!"
        } else {
            statusText.text = "❌ TextToSpeech initsialize bo'lmadi"
        }
    }

    override fun onDestroy() {
        speechRecognizer.destroy()
        textToSpeech.shutdown()
        super.onDestroy()
    }
}