package com.example.todolist.db

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.databinding.ItemTodoBinding

class ToDoRecyclerViewAdapter(private val todoList : ArrayList<ToDoEntity>, private val listner : OnItemLongClickListener) : RecyclerView.Adapter<ToDoRecyclerViewAdapter.MyViewHolder>() {

    inner class MyViewHolder(binding : ItemTodoBinding) : RecyclerView.ViewHolder(binding.root){
        val tv_importance = binding.tvImportance
        val tv_title = binding.tvTitle
        val root = binding.root

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding : ItemTodoBinding = ItemTodoBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val todoData = todoList[position]
        when(todoData.Importance){
            1 ->{
                holder.tv_importance.setBackgroundResource(R.color.red)
            }
            2 ->{
                holder.tv_importance.setBackgroundResource(R.color.yellow)
            }
            3 ->{
                holder.tv_importance.setBackgroundResource(R.color.green)
            }
        }
        holder.tv_importance.text = todoData.Importance.toString()
        holder.tv_title.text = todoData.title

        holder.root.setOnLongClickListener{
            listner.onLongClick(position)
            false
        }
    }

    override fun getItemCount(): Int {
        return todoList.size
    }


}