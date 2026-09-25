package com.jarvis.voiceassistant

import android.os.Bundle
import android.speech.RecognitionListener

class RecognitionListener(private val mainActivity: MainActivity) : RecognitionListener {

    override fun onReadyForSpeech(params: Bundle?) {}

    override fun onBeginningOfSpeech() {}

    override fun onRmsChanged(rmsdB: Float) {}

    override fun onBufferReceived(buffer: ByteArray?) {}

    override fun onEndOfSpeech() {}

    override fun onError(error: Int) {
        val message = when (error) {
            RecognitionListener.ERROR_AUDIO -> "Audio xatosi"
            RecognitionListener.ERROR_CLIENT -> "Client xatosi"
            RecognitionListener.ERROR_INSUFFICIENT_PERMISSIONS -> "Ruxsat kerak"
            RecognitionListener.ERROR_NETWORK -> "Internet xatosi"
            RecognitionListener.ERROR_NETWORK_TIMEOUT -> "Vaqt tugadi"
            RecognitionListener.ERROR_NO_MATCH -> "Tushunilmadi"
            RecognitionListener.ERROR_RECOGNIZER_BUSY -> "Ishchi band"
            RecognitionListener.ERROR_SERVER -> "Server xatosi"
            RecognitionListener.ERROR_SPEECH_TIMEOUT -> "Ovoz kutildi"
            else -> "Noma'lum xato"
        }
        mainActivity.onSpeechError(message)
    }

    override fun onResults(results: Bundle?) {
        val matches = results?.getStringArrayList(RecognitionListener.RESULTS_RECOGNITION)
        if (matches != null && matches.isNotEmpty()) {
            val result = matches[0]
            mainActivity.onSpeechResult(result)
        } else {
            mainActivity.onSpeechError("Natija topilmadi")
        }
    }

    override fun onPartialResults(partialResults: Bundle?) {}

    override fun onEvent(eventType: Int, params: Bundle?) {}
}