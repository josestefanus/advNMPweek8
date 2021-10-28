package id.ac.ubaya.informatika.todoapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import id.ac.ubaya.informatika.todoapp.R
import id.ac.ubaya.informatika.todoapp.databinding.TodoItemLayoutBinding
import id.ac.ubaya.informatika.todoapp.model.Todo
import kotlinx.android.synthetic.main.todo_item_layout.view.*

class TodoListAdapter(val todoList:ArrayList<Todo>, val adapterOnClick:(Any) -> Unit):RecyclerView.Adapter<TodoListAdapter.TodoListViewHolder>()
    , TodoCheckedChangeListener, TodoEditClickListener {
    class TodoListViewHolder(var view:TodoItemLayoutBinding):RecyclerView.ViewHolder(view.root)

    fun updateTodoList(newTodoList: List<Todo>) {
        todoList.clear()
        todoList.addAll(newTodoList)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        //val view = inflater.inflate(R.layout.todo_item_layout, parent, false)
        val view = DataBindingUtil.inflate<TodoItemLayoutBinding>(inflater, R.layout.todo_item_layout, parent, false)

        return TodoListViewHolder(view)

    }

    override fun onBindViewHolder(holder: TodoListViewHolder, position: Int) {

        holder.view.todo = todoList[position]
        holder.view.listener = this
        holder.view.editlistener = this
//        holder.view.checkTask.text = todoList[position].title + " " + todoList[position].priority
//
//        holder.view.imgEdit.setOnClickListener {
//            val action = TodoListFragmentDirections.actionEditTodoFragment(todoList[position].uuid)
//            Navigation.findNavController(it).navigate(action)
//        }

//        holder.view.checkTask.setOnCheckedChangeListener { compoundButton, isChecked ->
//            if(isChecked) {
//                adapterOnClick(todoList[position])
//            }
//        }
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    override fun onTodoCheckChanged(cb: CompoundButton, isChecked: Boolean, obj: Todo) {
        if(isChecked) {
                adapterOnClick(obj)
        }
    }

    override fun onTodoEditClick(v: View) {
        val action = TodoListFragmentDirections.actionEditTodoFragment(v.tag.toString().toInt())
        Navigation.findNavController(v).navigate(action)
    }
}