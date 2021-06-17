package com.example.todoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var todoAdapter: TodoAdapter
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        todoAdapter = TodoAdapter(mutableListOf())

        todoItems.adapter = todoAdapter
        todoItems.layoutManager = LinearLayoutManager(this)

        addTodo.setOnClickListener {
            val todoTitle = writeTodo.text.toString()
            if(todoTitle.isNotEmpty()) {
                val todo = TodoItem(todoTitle)
                todoAdapter.addTodo(todo)
                writeTodo.text.clear()
            }
        }
        deleteTodo.setOnClickListener {
            todoAdapter.deleteDoneTodos()
        }
        auth = FirebaseAuth.getInstance()
        signOut.setOnClickListener{
            auth.signOut()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}