package kr.ac.hallym.prac10_database

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kr.ac.hallym.prac10_database.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_add)

        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when(item.itemId) {
        R.id.menu_add_save -> {
            val inputData = binding.addEditView.text.toString()

            if(inputData.isNotEmpty()) {
                val db = DBHelper(this).writableDatabase
                db.execSQL(
                    "insert into TODO_TB (todo) values (?)",
                    arrayOf<String>(inputData)
                )
                db.close()

                val intent = intent.putExtra("result",binding.addEditView.text.toString())
                setResult(Activity.RESULT_OK,intent)
                finish()

                //제목 추가후 바로 포트폴리오 입력 화면으로 이동
                val testintent = Intent(this,Test::class.java)
                startActivity(testintent)
                finish()


            }
            else{
                Toast.makeText(this, "빈칸이 있습니다.", Toast.LENGTH_SHORT).show()
            }
            true
        }
        else ->true
    }
}