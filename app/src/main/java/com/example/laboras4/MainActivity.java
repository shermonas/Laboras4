package com.example.laboras4;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;



import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText et_title, et_content;
    private NoteDatabase noteDatabase;
    private ContactsContract.CommonDataKinds.Note note;
    private boolean update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        et_title = findViewById(R.id.et_title);
        et_content = findViewById(R.id.et_content);
        noteDatabase = NoteDatabase.getInstance(AddNoteActivity.this);
        Button button = findViewById(R.id.but_save);
        button.setOnClickListener(new View.OnClickListener() {


            private static class InsertTask extends AsyncTask<Void, Void, Boolean> {

                private WeakReference<AddNoteActivity> activityReference;
                private ContactsContract.CommonDataKinds.Note note;

                InsertTask(AddNoteActivity context, ContactsContract.CommonDataKinds.Note note) {
                    activityReference = new WeakReference<>(context);
                    this.note = note;
                }

                @Override
                protected Boolean doInBackground(Void... objs) {
                    long j = activityReference.get().noteDatabase.getNoteDao().insertNote(note);
                    note.setNote_id(j);
                    Log.e("ID ", "doInBackground: " + j);
                    return true;
                }

                @Override
                protected void onPostExecute(Boolean bool) {
                    if (bool) {
                        activityReference.get().setResult(note, 1);
                        activityReference.get().finish();
                    }
                }
            }
        }
    }
}
