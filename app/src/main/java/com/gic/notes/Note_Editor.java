package com.gic.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashSet;

public class Note_Editor extends AppCompatActivity {
    int NoteNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note__editor);

        Toast.makeText(Note_Editor.this, "Let's write a log in your journal.", Toast.LENGTH_SHORT).show();
        EditText NoteEditor = (EditText) findViewById(R.id.NoteEditorEditText);

Intent intent = getIntent();

  NoteNo= intent.getIntExtra("NoteNo.",-1);

if(NoteNo != -1){
NoteEditor.setText(MainActivity.noteTitles.get(NoteNo));
}
else{
MainActivity.noteTitles.add("");
NoteNo = MainActivity.noteTitles.size() -1;
}
        NoteEditor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
MainActivity.noteTitles.set(NoteNo, String.valueOf(charSequence));
MainActivity.ListViewAdapter.notifyDataSetChanged();

                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.gic.notes", Context.MODE_PRIVATE);

                HashSet<String> set = new HashSet<>(MainActivity.noteTitles);

sharedPreferences.edit().putStringSet("notes", set).apply();
            }

            @Override
            public void afterTextChanged(Editable editable) {
              //  Toast.makeText(Note_Editor.this, "Logs Saved!", Toast.LENGTH_SHORT).show();
            }
        });


















    }
}