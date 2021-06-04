package com.garden.taskgarden

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TimePicker
import kotlinx.android.synthetic.main.add_task_form.*
import kotlinx.android.synthetic.main.task_card_view.*
import java.util.*

class TaskFormActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    var taskName: EditText? = null
    var taskDescription: EditText? = null
    private val debugTag = "TaskFormActivity"

    var day = 0
    var month = 0
    var year = 0
    var hour = 0
    var minute = 0

    var savedDay = 0
    var savedMonth = 0
    var savedYear = 0
    var savedHour = 0
    var savedMinute = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_task_form)

        pickDate()
    }

    private fun getDateTimeCalendar() {
        val cal : Calendar = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)
        hour = cal.get(Calendar.HOUR)
        minute = cal.get(Calendar.MINUTE)
    }

    private fun pickDate() {

        task_time.setOnClickListener {
            getDateTimeCalendar()
            DatePickerDialog(this, this, year, month, day).show()
        }
    }

    fun addTaskForm(view: View?){
        taskName = findViewById(R.id.etForm_Task)
        taskDescription = findViewById(R.id.etForm_Description)
        try {
            val task = Task()
            task.setTitle(taskName!!.text.toString())
            task.setDescription(taskDescription!!.text.toString())
            if(savedDay == 0 && savedMonth == 0 && savedYear == 0 && savedHour == 0 && savedMinute == 0){
                task.setCompletedBy("No date and time selected")
            } else {
                task.setCompletedBy("$savedDay/$savedMonth/$savedYear $savedHour:$savedMinute")
            }
            DBInterface.addTask(task, this)
            val intent = Intent(this@TaskFormActivity, MainActivity::class.java)
            startActivity(intent)
        } catch (e: Exception) {
            Log.d(debugTag, "Got $e while trying to add task in TaskFormActivity!")
        }
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        savedDay = dayOfMonth
        savedMonth = month
        savedYear = year
        getDateTimeCalendar()

        TimePickerDialog(this, this, hour, minute, true).show()
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        savedHour = hourOfDay
        savedMinute = minute

        //time_completed.text = "$savedDay-$savedMonth-$savedYear\n Hour: $savedHour Minute: "
    }
}