package id.ac.ubaya.informatika.todoapp.view

import android.view.View
import android.widget.CompoundButton
import id.ac.ubaya.informatika.todoapp.model.Todo

interface TodoCheckedChangeListener {
    fun onTodoCheckChanged(cb: CompoundButton, isChecked:Boolean, obj: Todo)
}

interface TodoEditClickListener {
    fun onTodoEditClick(v: View)
}

interface RadioClickListener {
    fun onRadioClick(v:View, obj:Todo)
}

interface TodoSaveChangesListener {
    fun onTodoSaveChanges(v: View, obj: Todo)
}

