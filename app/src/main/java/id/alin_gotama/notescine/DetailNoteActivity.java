package id.alin_gotama.notescine;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import id.alin_gotama.notescine.CustomAdapter.CustomAdapterNote;
import id.alin_gotama.notescine.Room.Database.MyDatabase;
import id.alin_gotama.notescine.Room.Entity.Note;

public class DetailNoteActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String EVENT_ID = "EVENT_ID";
    private TextView etTitle, etNote, tvDate;
    private Button btnSave,tbnDelete;
    private Animation myAnim;
    private MyDatabase myDatabase;
    private Note note;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_note);
        int idnya = getIntent().getIntExtra(EVENT_ID,-1);

        myDatabase = MyDatabase.createDatabase(this);
        note = myDatabase.noteDao().detailNote(idnya);

        this.etNote = findViewById(R.id.etDetailNoteNote);
        this.etTitle = findViewById(R.id.etDetailNoteTitle);
        this.tvDate = findViewById(R.id.tvDetailNoteDate);

        this.tvDate.setText(note.getDate());
        this.etNote .setText(note.getNote());
        this.etTitle.setText(note.getTitle());

        this.btnSave = findViewById(R.id.btnDetailNoteSave);
        myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
        this.btnSave.setOnClickListener(this);

        this.tbnDelete = findViewById(R.id.btnDetailNoteDelete);
        this.tbnDelete.setOnClickListener(this);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnDetailNoteSave){
            btnSave.startAnimation(myAnim);
            note.setTitle(this.etTitle.getText().toString().trim());
            note.setNote(this.etNote.getText().toString().trim());
            myDatabase.noteDao().updateNote(this.note);
            Note[] notes = myDatabase.noteDao().loadAllNote();
            ArrayList<Note> arNote = new ArrayList<>();

            for(int i = 0 ; i <= (notes.length-1);i++){
                arNote.add(notes[i]);
            }



            HomeActivity.customAdapterNote.setNotes(arNote);
            HomeActivity.customAdapterNote.notifyDataSetChanged();

            finish();
        }

        if(v.getId() == R.id.btnDetailNoteDelete){
            final AlertDialog.Builder alert = new AlertDialog.Builder(this,R.style.MyDialogTheme);
            TextView tvAlert = new TextView(this);



            alert.setTitle("YAKIN MENGHAPUS CATATAN INI ?");

            alert.setPositiveButton("YES", new AlertDialog.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ProgressDialog loading = ProgressDialog.show(DetailNoteActivity.this, "",
                            "", true);

                    myDatabase.noteDao().deleteNote(note);

                    Note[] notes = myDatabase.noteDao().loadAllNote();
                    ArrayList<Note> arNotes = new ArrayList<>();
                    for(int i = 0 ; i <= notes.length-1;i++){
                        arNotes.add(notes[i]);
                    }

                    HomeActivity.customAdapterNote.setNotes(arNotes);
                    HomeActivity.customAdapterNote.notifyDataSetChanged();

                    loading.dismiss();
                    finish();



                }
            });
            alert.setNegativeButton("No", new AlertDialog.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            alert.setCancelable(true);
            alert.create();
            alert.show();
        }
    }
}