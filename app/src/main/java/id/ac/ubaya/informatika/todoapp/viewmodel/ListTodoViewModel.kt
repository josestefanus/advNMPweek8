package id.ac.ubaya.informatika.todoapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import id.ac.ubaya.informatika.todoapp.model.Todo
import id.ac.ubaya.informatika.todoapp.model.TodoDatabase
import id.ac.ubaya.informatika.todoapp.util.buildDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ListTodoViewModel(application: Application):AndroidViewModel(application), CoroutineScope {
    val todoLD = MutableLiveData<List<Todo>>()
    private var job = Job()

    fun refresh() {
        launch {
            val db = buildDB(getApplication())
            todoLD.value = db.todoDao().selectAllTodo()
        }
    }

    //fun clearTask(todo: Todo) {
    //    launch {
    //        val db = buildDB(getApplication())
    //        db.todoDao().deleteTodo(todo)
    //        todoLD.value = db.todoDao().selectAllTodo()
    //    }
    //}
    fun clearTask(todo: Todo) {
        launch {
            val db = buildDB(getApplication())
            db.todoDao().updateIsDone(todo.uuid)
            todoLD.value = db.todoDao().selectAllTodo()
        }
    }


    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

}