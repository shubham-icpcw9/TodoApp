package com.example.todoapp

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.todo_items.view.*

class TodoAdapter( private val todoItem : MutableList<TodoItem>) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {
    class TodoViewHolder(itemView : View):RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.todo_items, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val curr = todoItem[position]
        holder.itemView.apply {
            todo_text.text = curr.title
            todo_done.isChecked = curr.isChecked
            toggleStrikeThrough(todo_text, curr.isChecked)
            todo_done.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(todo_text, isChecked)
                curr.isChecked = !curr.isChecked
            }
        }
    }

    fun addTodo(todo: TodoItem) {
        todoItem.add(todo)
        notifyItemInserted(todoItem.size - 1)
    }

    fun deleteDoneTodos() {
        todoItem.removeAll { todo ->
            todo.isChecked
        }
        notifyDataSetChanged()
    }

    private fun toggleStrikeThrough(todoText: TextView?, checked: Boolean) {
        if(checked) {
            todoText?.paintFlags  = todoText?.paintFlags?.or(STRIKE_THRU_TEXT_FLAG)!!
        } else {
            todoText?.paintFlags = todoText?.paintFlags?.and(STRIKE_THRU_TEXT_FLAG.inv())!!
        }
    }

    override fun getItemCount(): Int {
        return todoItem.size
    }
}