package id.alin_gotama.notescine.Room.Dao;

import androidx.room.Dao;
import androidx.room.DatabaseView;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;

import id.alin_gotama.notescine.Room.Entity.Note;

@Dao
public interface MyDao {
    @Insert
    public void insertNote(Note note);

    @Query("SELECT * FROM Note")
    public Note[] loadAllNote();

    @Update
    public void updateNote(Note note);

    @Delete
    public void deleteNote(Note note);

    @Query("SELECT * FROM Note WHERE Note.id = :idnya")
    public Note detailNote(int idnya);

}
