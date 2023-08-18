package com.josejordan.logogenerator.ui

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aallam.openai.api.BetaOpenAI
import com.aallam.openai.api.audio.TranscriptionRequest
import com.aallam.openai.api.file.FileSource
import com.aallam.openai.api.logging.LogLevel
import com.aallam.openai.api.model.ModelId
import com.aallam.openai.client.LoggingConfig
import com.aallam.openai.client.OpenAI
import com.josejordan.logogenerator.audio.AudioRecorder
import com.josejordan.logogenerator.conf.Conf
import com.josejordan.logogenerator.conf.Env
import kotlinx.coroutines.launch
import okio.source
import java.io.File

class LogoGeneratorViewModel : ViewModel() {

    private var openAI = OpenAI(token = Env.OPENAI_API_KEY, logging = LoggingConfig(LogLevel.All))

    var info: String by mutableStateOf("")

    var loading: Boolean by mutableStateOf(false)
    var recording : Boolean by mutableStateOf(false)

    private var recorder: AudioRecorder? = null
    private var audioFile: File? = null


    //Audio
    fun recordAudio(context: Context) {

        if (recording) {
            recording = false
            recorder?.stop()
            loadInfo(audioFile)
        } else {
            if (recorder == null) {
                recorder = AudioRecorder(context)
            }
            File(context.cacheDir, Conf.AUDIO_FILE).also {
                recorder?.record(it)
                audioFile = it
                recording = true
            }
        }
    }

    //Transcripción
    @OptIn(BetaOpenAI::class)
    private fun loadInfo(file: File?) = viewModelScope.launch {

        val source = file?.source()?.let {

            startLoading()

            val transcriptionRequest = TranscriptionRequest(
                audio = FileSource(name = Conf.AUDIO_FILE, source = it),
                model = ModelId(Conf.WHISPER_MODEL),
            )
            val transcription = openAI.transcription(transcriptionRequest)
            info = transcription.text

            endLoading()
        }
    }

    //Loading
    private fun startLoading(){
        loading = true
    }

    private fun endLoading(){
        loading = false
    }
}