package id.alin_gotama.notescine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import id.alin_gotama.notescine.Room.Database.MyDatabase;
import id.alin_gotama.notescine.Room.Entity.Note;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyDatabase myDatabase = MyDatabase.createDatabase(this);

        Note note = new Note();
        note.setNote("hahaha");
        note.setTitle("heheh");
        note.setDate("2020-01-01");
        myDatabase.noteDao().insertNote(note);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        }, 1500);

    }
}