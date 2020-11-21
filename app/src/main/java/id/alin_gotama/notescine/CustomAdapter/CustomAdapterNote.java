package id.alin_gotama.notescine.CustomAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import id.alin_gotama.notescine.DetailNoteActivity;
import id.alin_gotama.notescine.R;
import id.alin_gotama.notescine.Room.Entity.Note;

public class CustomAdapterNote extends RecyclerView.Adapter<CustomAdapterNote.ViewHolder> {
    private Context context;
    public static ArrayList<Note> notes;

    public CustomAdapterNote(Context context, ArrayList<Note> notes) {
        this.context = context;
        CustomAdapterNote.notes = notes;
    }

    public ArrayList<Note> getNotes() {
        return notes;
    }

    public void setNotes(ArrayList<Note> notes) {
        CustomAdapterNote.notes = notes;
    }

    public void removeNote(int id){
        CustomAdapterNote.notes.remove(id);
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_row,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.tvTitle.setText(notes.get(position).getTitle());
        holder.tvDate.setText(notes.get(position).getDate());
        holder.tvNote.setText(notes.get(position).getNote());
        holder.cvNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailNoteActivity.class);
                intent.putExtra(DetailNoteActivity.EVENT_ID,notes.get(position).getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return CustomAdapterNote.notes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle,tvNote,tvDate;
        private CardView cvNote;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvTitle = itemView.findViewById(R.id.tvNoteRowTitle);
            this.tvNote = itemView.findViewById(R.id.tvNoteRowNote);
            this.tvDate = itemView.findViewById(R.id.tvNoteRowDate);
            this.cvNote = itemView.findViewById(R.id.cvNote);
        }
    }
}
