package com.example.laboras4;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

public class DeleteNoteActivity {

    public void onNoteClick(final int pos) {
        new AlertDialog.Builder(com.example.laboras4.AddNoteActivity.this)
                .setTitle("Select Options")
                .setItems(new String[]{"Delete"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i) {
                            case 0:
                                noteDatabase.getNoteDao().deleteNote(notes.get(pos));
                                notes.remove(pos);
                                listVisibility();
                                break;
                            case 1:
                                com.example.laboras4.AddNoteActivity.this.pos = pos;
                                startActivityForResult(
                                        new Intent(com.example.laboras4.AddNoteActivity.this,
                                                AddNoteActivity.class).putExtra("note", notes.get(pos)),
                                        100);
                                break;
                        }
                    }
                }).show();

    }}
