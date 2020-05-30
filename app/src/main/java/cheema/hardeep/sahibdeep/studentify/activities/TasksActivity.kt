package cheema.hardeep.sahibdeep.studentify.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import cheema.hardeep.sahibdeep.studentify.R
import cheema.hardeep.sahibdeep.studentify.adapters.TaskAdapter
import cheema.hardeep.sahibdeep.studentify.database.StudentifyDatabaseProvider
import cheema.hardeep.sahibdeep.studentify.interfaces.ViewRefreshInterface
import cheema.hardeep.sahibdeep.studentify.models.tables.TaskType

private const val KEY_CLASS_ID = "key-class-id"

fun createTaskActivityIntent(context: Context, classId: Int) =
        Intent(context, TasksActivity::class.java).apply {putExtra(KEY_CLASS_ID, classId)}

class TasksActivity : AppCompatActivity(), ViewRefreshInterface {

    @BindView(R.id.addHomeworkButton)
    lateinit var addHomeworkButton: ImageView

    @BindView(R.id.addTestButton)
    lateinit var addTestButton: ImageView

    @BindView(R.id.homeworkRecyclerView)
    lateinit var homeworkRecyclerView: RecyclerView

    @BindView(R.id.noTest)
    lateinit var noTest: TextView

    @BindView(R.id.noHomework)
    lateinit var noHomework: TextView

    @BindView(R.id.testRecyclerView)
    lateinit var testRecyclerView: RecyclerView

    @BindView(R.id.doneButton)
    lateinit var doneButton: Button

    lateinit var homeworkAdapter: TaskAdapter
    lateinit var testAdapter: TaskAdapter
    var classId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tasks)
        supportActionBar?.hide()
        ButterKnife.bind(this)
        classId = intent.getIntExtra(KEY_CLASS_ID, -1)

        setupHomeworkRV()
        setupTestRV()

        addHomeworkButton.setOnClickListener { v: View -> startActivity(TasksDetailsActivity.createIntent(v.context, true, classId, TasksDetailsActivity.NEGATIVE_TASK_ID)) }
        addTestButton.setOnClickListener { v: View -> startActivity(TasksDetailsActivity.createIntent(v.context, false, classId, TasksDetailsActivity.NEGATIVE_TASK_ID)) }
        doneButton.setOnClickListener { v: View? -> finish() }
    }

    override fun onResume() {
        super.onResume()
        refreshTest(false)
        refreshHomework(false)
    }

    override fun refreshTest(isDelete: Boolean) {
        if (isDelete) StudentifyDatabaseProvider.getStudentClassDao(this).updateStudentClassTotalTest(classId, -1)
        val taskWithType = StudentifyDatabaseProvider
                .getTaskDao(this)
                .getTaskWithType(classId, TaskType.TEST.name)
        noTest.visibility = if (taskWithType.isEmpty()) View.VISIBLE else View.GONE
        testAdapter.updateList(taskWithType)
    }

    override fun refreshHomework(isDelete: Boolean) {
        if (isDelete) StudentifyDatabaseProvider.getStudentClassDao(this).updateStudentClassTotalHomework(classId, -1)
        val taskWithType = StudentifyDatabaseProvider
                .getTaskDao(this)
                .getTaskWithType(classId, TaskType.HOMEWORK.name)
        noHomework.visibility = if (taskWithType.isEmpty()) View.VISIBLE else View.GONE
        homeworkAdapter.updateList(taskWithType)
    }

    private fun setupTestRV() {
        testRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@TasksActivity, RecyclerView.VERTICAL, false)
            adapter = TaskAdapter(this@TasksActivity, false).also { testAdapter = it }
        }
    }

    private fun setupHomeworkRV() {
        homeworkRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@TasksActivity, RecyclerView.VERTICAL, false)
            adapter = TaskAdapter(this@TasksActivity, true).also { homeworkAdapter = it }
        }
    }



}
