package kr.ac.hallym.prac10_database

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kr.ac.hallym.prac10_database.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: MyAdapter
    var contents: MutableList<String>?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val requestLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()){
            it.data!!.getStringExtra("result")?.let{
                contents?.add(it)
                adapter.notifyDataSetChanged()
            }
        }

        binding.mainFab.setOnClickListener {
            if ((contents!!.size) > 4){
                Toast.makeText(this@MainActivity,"포트폴리오는 4개까지 생성 가능합니다.",Toast.LENGTH_SHORT).show()
            }
            else{
                val intent = Intent(this,AddActivity::class.java)
                requestLauncher.launch(intent)
            }
        }

        contents = mutableListOf<String>()


        val db = DBHelper(this).readableDatabase
        val cursor = db.rawQuery("select * from TODO_TB",null)
        cursor.run {
            while (moveToNext()){
                contents?.add(cursor.getString(1))

            }
        }
        db.close()



        val layoutManager = LinearLayoutManager(this)
        binding.mainRecycleView.layoutManager = layoutManager
        adapter = MyAdapter(contents)
        binding.mainRecycleView.adapter = adapter
        binding.mainRecycleView.addItemDecoration(
            DividerItemDecoration(this,LinearLayoutManager.VERTICAL)
        )



    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_main_setting){
            val intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
}