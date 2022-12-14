package kr.ac.hallym.prac10

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kr.ac.hallym.prac10.databinding.ActivityMain2Binding
import java.io.BufferedReader
import java.io.File
import java.io.OutputStreamWriter

class MainActivity2 : AppCompatActivity() {
    lateinit var binding: ActivityMain2Binding
    lateinit var file: File

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main2)

        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button1.setOnClickListener {
            file = File(filesDir, "test.txt")
            val writeStream: OutputStreamWriter = file.writer()
            writeStream.write("hello world")
            writeStream.flush()//파일에 씀

//            openFileOutput("test.txt",Context.MODE_PRIVATE).use {
//                it.write("hello world!!!".toByteArray())
//            }

            Toast.makeText(this,"파일에 저장되었습니다.",Toast.LENGTH_SHORT).show()
        }

        binding.button2.setOnClickListener {
            val readStream: BufferedReader = file.reader().buffered()
            readStream.forEachLine {
                Log.d("kkang","$it")
            }

//            openFileInput("test.txt").bufferedReader().forEachLine {
//                Log.d("kkang","$it")
//            }
        }
    }
}