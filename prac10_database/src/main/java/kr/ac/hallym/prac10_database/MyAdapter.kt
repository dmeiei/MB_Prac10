package kr.ac.hallym.prac10_database

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import kr.ac.hallym.prac10_database.databinding.ItemRecycleviewBinding

class MyViewHolder(val binding:ItemRecycleviewBinding):RecyclerView.ViewHolder(binding.root)

class MyAdapter(val contents:MutableList<String>?):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        MyViewHolder(ItemRecycleviewBinding.inflate(LayoutInflater.from(parent.context),
        parent,false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as MyViewHolder).binding
        binding.itemData.text = contents!![position]

        //제목 선택시
        binding.itemRoot.setOnClickListener {
            Log.d("Lee","$position")
            //첫번째 포트폴리오
            if(position == 0){
                val intent = Intent(binding.itemRoot?.context,Test::class.java)
                ContextCompat.startActivity(binding.itemRoot.context,intent,null)
            }
            //두번째 포트폴리오
            if(position == 1){
                val intent2 = Intent(binding.itemRoot?.context,Test2::class.java)
                ContextCompat.startActivity(binding.itemRoot.context,intent2,null)
            }
            //세번째 포트폴리오
            if(position == 2){
                val intent3 = Intent(binding.itemRoot?.context,Test3::class.java)
                ContextCompat.startActivity(binding.itemRoot.context,intent3,null)
            }

        }
    }

    override fun getItemCount(): Int {
        return contents?.size ?:0
    }
}