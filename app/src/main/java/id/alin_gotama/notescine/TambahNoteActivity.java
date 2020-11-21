package id.alin_gotama.notescine;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.icu.util.Calendar;
import android.icu.util.TimeZone;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import id.alin_gotama.notescine.Room.Database.MyDatabase;
import id.alin_gotama.notescine.Room.Entity.Note;

public class TambahNoteActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText tvTitle,tvNote;
    private TextView tvDate;
    private Button btnSave;
    private Calendar cal;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_note);

        this.tvNote = findViewById(R.id.etTambahNoteNote);
        this.tvDate = findViewById(R.id.tvTambahNoteTitleDate);
        this.tvTitle = findViewById(R.id.etTambahNoteTitle);

        this.btnSave = findViewById(R.id.btnTambahSave);
        this.btnSave.setOnClickListener(this);

        cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Makassar"));
        Date currentLocalTime = cal.getTime();
        DateFormat date = new SimpleDateFormat("dd/MM/YYYY");

        String localTime = date.format(currentLocalTime);
        this.tvDate.setText(localTime);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnTambahSave){
            MyDatabase myDatabase = MyDatabase.createDatabase(this);
            Note note = new Note();
            note.setTitle(this.tvTitle.getText().toString().trim());
            note.setNote(this.tvNote.getText().toString().trim());
            note.setDate(this.tvDate.getText().toString().trim());

            myDatabase.noteDao().insertNote(note);

            Note[] notes = myDatabase.noteDao().loadAllNote();
            ArrayList<Note> arNote = new ArrayList<>();

            for(int i = 0 ; i <= (notes.length-1);i++){
                arNote.add(notes[i]);
            }
            HomeActivity.customAdapterNote.setNotes(arNote);
            HomeActivity.customAdapterNote.notifyDataSetChanged();

            finish();
        }
    }
}