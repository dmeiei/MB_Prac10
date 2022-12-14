package kr.ac.hallym.prac10

import android.content.ContentUris
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import kr.ac.hallym.prac10.databinding.ActivityMain3Binding
import java.io.BufferedReader
import java.io.File
import java.io.OutputStreamWriter

class MainActivity3 : AppCompatActivity() {
    lateinit var binding: ActivityMain3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main3)

        binding = ActivityMain3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button1.setOnClickListener {

            //파일쓰기
            val file: File = File(getExternalFilesDir(null),"test.txt")
            val writeStream: OutputStreamWriter = file.writer()
            writeStream.write("hello world")
            writeStream.flush()

            //파일 읽기
            val readStream: BufferedReader = file.reader().buffered()
            readStream.forEachLine {
                Log.d("kkang","$it")
            }
        }

        binding.button2.setOnClickListener {
            //공용 저장소
            val projection = arrayOf(
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.DISPLAY_NAME
            )
            val cursor = contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            projection,null,null,null)
            cursor?.let {
                while (cursor.moveToNext()){
                    Log.d("kkang","id: ${cursor.getLong(0)}, name: ${cursor.getString(1)}")

                    val contentUri: Uri = ContentUris.withAppendedId(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,cursor.getLong(0))
                    val resolver = applicationContext.contentResolver
                    resolver.openInputStream(contentUri).use{
                        //input stream 객체에서 작업 수행
                        val option = BitmapFactory.Options()
                        option.inSampleSize = 10
                        val bitmap = BitmapFactory.decodeStream(it,null,option)
                        binding.resultImage.setImageBitmap(bitmap)
                    }
                }
            }
        }


    }
}