package com.example.todolist

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.databinding.ActivityMainBinding
import com.example.todolist.db.*

class MainActivity : AppCompatActivity(), OnItemLongClickListener{
    private lateinit var binding : ActivityMainBinding

    private lateinit var db : AppDatabase
    private lateinit var todoDao : ToDoDao
    private lateinit var todoList : ArrayList<ToDoEntity>
    private lateinit var adapter : ToDoRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.getInstance(this)!!
        todoDao = db.getToDoDao()

        getAllTodoList()

        binding.btnAddtodo.setOnClickListener{
            val intent = Intent(this,AddToDoActivity::class.java)
            startActivity(intent)
        }
    }

    private fun getAllTodoList(){
        Thread{
            todoList = ArrayList(todoDao.getAllToDo())
            setRecyclerView()
        }.start()
    }

    private fun setRecyclerView(){
        runOnUiThread{
            adapter = ToDoRecyclerViewAdapter(todoList,this)
            binding.recyclerview.adapter = adapter
            binding.recyclerview.layoutManager = LinearLayoutManager(this)

        }
    }

    override fun onRestart() {
        super.onRestart()
        getAllTodoList()
    }

    override fun onLongClick(position: Int) {
        val builder : AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.alert_title))
        builder.setMessage(getString(R.string.alert_message))
        builder.setPositiveButton(getString(R.string.alert_yes),
        object : DialogInterface.OnClickListener{
            override fun onClick(p0: DialogInterface?, p1: Int) {
                deleteTodo(position)
            }
        })
        builder.setNegativeButton(getString(R.string.alert_no),null)
        builder.show()
    }
    private fun deleteTodo(position : Int){
        Thread{
            todoDao.deleteToDo(todoList[position])
            todoList.removeAt(position)
            runOnUiThread{
                adapter.notifyDataSetChanged()
                Toast.makeText(this,"삭제 되었습니다.",Toast.LENGTH_SHORT).show()
            }
        }.start()
    }
}