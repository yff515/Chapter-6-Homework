package com.byted.camp.todolist;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.byted.camp.todolist.beans.Priority;
import com.byted.camp.todolist.beans.State;
import com.byted.camp.todolist.db.TodoContract;
import com.byted.camp.todolist.db.TodoDbHelper;

public class NoteActivity extends AppCompatActivity {

    private EditText editText;
    private Button addBtn;
    private SQLiteDatabase db;
    private TodoDbHelper todoDbHelper;

    private RadioGroup PriorityGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        setTitle(R.string.take_a_note);
        todoDbHelper = new TodoDbHelper(this);
        db = todoDbHelper.getWritableDatabase();

        editText = findViewById(R.id.edit_text);
        PriorityGroup = findViewById(R.id.Priority_group);
        editText.setFocusable(true);
        editText.requestFocus();
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputManager != null) {
            inputManager.showSoftInput(editText, 0);
        }

        addBtn = findViewById(R.id.btn_add);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence content = editText.getText();
                if (TextUtils.isEmpty(content)) {
                    Toast.makeText(NoteActivity.this,
                            "No content to add", Toast.LENGTH_SHORT).show();
                    return;
                }
                boolean succeed = saveNote2Database(content.toString().trim(),selectPriority());
                if (succeed) {
                    Toast.makeText(NoteActivity.this,
                            "Note added", Toast.LENGTH_SHORT).show();
                    setResult(Activity.RESULT_OK);
                } else {
                    Toast.makeText(NoteActivity.this,
                            "Error", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        todoDbHelper.close();
        todoDbHelper=null;
        db.close();
        db=null;
    }

    private boolean saveNote2Database(String content, Priority priority) {
        // TODO 插入一条新数据，返回是否插入成功
        if(db == null || TextUtils.isEmpty(content));
        ContentValues values = new ContentValues();
        values.put(TodoContract.Todolist.COLUMN_NAME_ONE,content);
        values.put(TodoContract.Todolist.COLUMN_NAME_TWO, State.TODO.intValue);
        values.put(TodoContract.Todolist.COLUMN_NAME_THREE,System.currentTimeMillis());
        values.put(TodoContract.Todolist.COLUMN_NAME_FOUR,priority.intValue);
        long newRowID = db.insert(TodoContract.Todolist.TABLE_TITLE,null,values);
        return newRowID!=-1;
    }

    private Priority selectPriority(){
        switch (PriorityGroup.getCheckedRadioButtonId()){
            case R.id.btn_low:
                return Priority.Low;
            case R.id.btn_medium:
                return Priority.Medium;
            case R.id.btn_high:
                return Priority.High;
                default:
                    return Priority.Low;
        }
    }
}
