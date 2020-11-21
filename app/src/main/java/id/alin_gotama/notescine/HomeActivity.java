package id.alin_gotama.notescine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import id.alin_gotama.notescine.CustomAdapter.CustomAdapterNote;
import id.alin_gotama.notescine.CustomAdapter.CustomAnimation;
import id.alin_gotama.notescine.Room.Database.MyDatabase;
import id.alin_gotama.notescine.Room.Entity.Note;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Note[] notes;
    private Button btnTambah;
    public static CustomAdapterNote customAdapterNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        this.btnTambah = findViewById(R.id.btnTambah);
        this.btnTambah.setOnClickListener(this);
        MyDatabase myDatabase = MyDatabase.createDatabase(this);


        notes = myDatabase.noteDao().loadAllNote();
        ArrayList<Note> arNotes = new ArrayList<>();

        for(int i=0;i<=(notes.length-1);i++){
            arNotes.add(notes[i]);
            Log.d("list",notes[i].getTitle());
        }

        HomeActivity.customAdapterNote = new CustomAdapterNote(this,arNotes);
        CustomAnimation customAnimation = new CustomAnimation(this.btnTambah);

        this.recyclerView = findViewById(R.id.rvNote);
        this.layoutManager = new LinearLayoutManager(this);
        this.recyclerView.addOnScrollListener(customAnimation);
        this.recyclerView.setAdapter(customAdapterNote);
        this.recyclerView.setLayoutManager(layoutManager);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnTambah){
            Intent intent = new Intent(this,TambahNoteActivity.class);
            startActivity(intent);
        }
    }
}