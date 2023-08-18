package com.josejordan.logogenerator.ui

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.josejordan.logogenerator.audio.AudioRecorder
import com.josejordan.logogenerator.conf.Conf
import java.io.File

class LogoGeneratorViewModel : ViewModel() {

    var info: String by mutableStateOf("")

    private var recorder: AudioRecorder? = null
    private var audioFile: File? = null
    var recording = false

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
    private fun loadInfo(file: File?){
        
    }
}