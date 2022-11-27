package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.todolist.databinding.ActivityAddToDoBinding
import com.example.todolist.db.AppDatabase
import com.example.todolist.db.ToDoDao
import com.example.todolist.db.ToDoEntity

class AddToDoActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAddToDoBinding
    lateinit var db : AppDatabase
    lateinit var todoDao : ToDoDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddToDoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.getInstance(this)!!
        todoDao = db.getToDoDao()

        binding.btnComplete.setOnClickListener{
            insertTodo()
        }
    }

    private fun insertTodo(){
        val todoTitle = binding.edtTitle.text.toString()
        var todoImportance = binding.radioGroup.checkedRadioButtonId

        var impData = 0
        when(todoImportance){
            R.id.btn_high ->{
                impData = 1
            }
            R.id.btn_middle ->{
                impData = 2
            }
            R.id.btn_low ->{
                impData = 3
            }
        }

        if(impData == 0 || todoTitle.isBlank()){
            Toast.makeText(this,"모든 항목을 채워주세요.",Toast.LENGTH_SHORT).show()
        } else{
            Thread{
                todoDao.insertToDo(ToDoEntity(null,todoTitle,impData))
                runOnUiThread{
                    Toast.makeText(this,"할 일이 추가되었습니다.",Toast.LENGTH_SHORT).show()
                    finish()
                }
            }.start()
        }
    }
}