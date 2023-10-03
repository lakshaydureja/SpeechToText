package com.labs7.speechtotext

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.labs7.speechtotext.databinding.ActivityMainBinding
import java.util.Locale

class MainActivity : AppCompatActivity() {

    lateinit var activityResultLauncher:ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding:ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult(),
            ActivityResultCallback {result->
                val resultCode= result.resultCode
                val data= result.data

                if(resultCode == RESULT_OK && data != null){
                    val speakResult:ArrayList<String> = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS) as ArrayList<String>

                    binding.textView.text = speakResult[0]

                }
            })


        binding.imageButton.setOnClickListener {

convertSpeech()
        }


    }
    fun convertSpeech(){
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())

        //To track result of intent
        //so, we use activityResultLauncher Class
        activityResultLauncher.launch(intent)




    }
}