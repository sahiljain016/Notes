package com.gic.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity {

       static ListView notes;
       static ArrayList<String>  noteTitles   = new ArrayList<String>();
       static   ArrayAdapter  ListViewAdapter;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notes = (ListView) findViewById(R.id.notesListView);
        sharedPreferences = getApplicationContext().getSharedPreferences("com.gic.notes", Context.MODE_PRIVATE);

        HashSet<String> set = (HashSet<String>) sharedPreferences.getStringSet("notes",null);

        if(set == null){
            noteTitles.add("Logging To My Journal For The First Time");
        }
        else{
noteTitles= new ArrayList(set);

        }


        ListViewAdapter  = new ArrayAdapter(this, R.layout.customized_text,noteTitles);
        notes.setAdapter(ListViewAdapter);

        notes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(),Note_Editor.class);

                intent.putExtra("NoteNo.",i);

                startActivity(intent);
            }
        });

       notes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
           @Override
           public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
final int ItemToDelete = i;

new AlertDialog.Builder(MainActivity.this)
        .setIcon(android.R.drawable.ic_dialog_alert)
        .setTitle("Deleting Jorunal Entry!")
        .setMessage("Do you want this log to be erased?")
        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
noteTitles.remove(ItemToDelete);
ListViewAdapter.notifyDataSetChanged();

                HashSet<String> set = new HashSet<>(MainActivity.noteTitles);
                sharedPreferences.edit().putStringSet("notes", set).apply();
            }

        })
.setNegativeButton("no",null)
.show();
               return true;
           }
       });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_items, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);

if(item.getItemId() == R.id.Note_Add){

Intent intent= new Intent(getApplicationContext(), Note_Editor.class);

startActivity(intent);

return true;
}


    return false;

    }
}