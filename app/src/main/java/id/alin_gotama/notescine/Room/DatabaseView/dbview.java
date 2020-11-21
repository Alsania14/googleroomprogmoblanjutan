package id.alin_gotama.notescine.Room.DatabaseView;

import androidx.room.DatabaseView;

@DatabaseView("SELECT * FROM Note")
public class dbview {
    public int id;
    public String title;
    public String note;
    public String date;
}
