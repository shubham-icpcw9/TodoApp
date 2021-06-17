package com.example.todoapp

data class TodoItem (
    val title : String,
    var isChecked : Boolean = false
)