package com.example.trackkeeper.ui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trackkeeper.R;
import com.example.trackkeeper.databinding.NoteItemBinding;
import com.example.trackkeeper.persistence.database.Note;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {
    private Context context;
    private List<Note> notes;

    public NoteAdapter(Context context, List<Note> notes) {
        this.context = context;
        this.notes = notes;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        NoteItemBinding binding = NoteItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new NoteViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int i) {
        Note note = notes.get(i);
        holder.dataBinding(note);
    }

    @Override
    public int getItemCount() {
        return notes != null ? notes.size() : 0;
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {

        private ImageView noteImage;
        private TextView noteTitle;
        private TextView noteDescription;
        private TextView noteDate;
        private ImageView noteLockIcon;
        private LinearLayout titleContainer;
        private SimpleDateFormat format;

        public NoteViewHolder(NoteItemBinding b) {
            super(b.getRoot());
            noteTitle = b.noteTitle;
            noteDescription = b.noteDescription;
            noteImage = b.noteImage;
            noteDate = b.noteDate;
            noteLockIcon = b.noteLockIcon;
            titleContainer = b.titleContainer;
        }

        private void dataBinding(Note item) {
            if (!TextUtils.isEmpty(item.getImageUrl())) {
                noteImage.setVisibility(View.VISIBLE);
                Picasso.get().load(item.getImageUrl()).into(noteImage);
                noteDescription.setEllipsize(TextUtils.TruncateAt.END);
                noteDescription.setMaxLines(3);
            }
            setBackground(item.getColor());
            noteTitle.setText(item.getTitle());
            noteDescription.setText(item.getDescription());
            format = new SimpleDateFormat("EEE, d MMM", Locale.US);
            noteDate.setText(format.format(item.getDate()));
        }

        private void setBackground(String color) {
            switch (color) {
                case "YELLOW":
                    noteTitle.setTextColor(context.getResources().getColor(R.color.black_color));
                    noteDescription.setTextColor(context.getResources().getColor(R.color.gray_color));
                    noteDate.setTextColor(context.getResources().getColor(R.color.gray_color));
                    noteLockIcon.setImageResource(R.drawable.unlock_icon_gray);
                    titleContainer.setBackgroundResource(R.color.yellow_color);
                    break;
                case "BLUE":
                    noteTitle.setTextColor(context.getResources().getColor(R.color.white));
                    noteDescription.setTextColor(context.getResources().getColor(R.color.gray_300));
                    noteDate.setTextColor(context.getResources().getColor(R.color.gray_300));
                    noteLockIcon.setImageResource(R.drawable.unlock_icon);
                    titleContainer.setBackgroundResource(R.color.blue_color);
                    break;
                case "RED":
                    noteTitle.setTextColor(context.getResources().getColor(R.color.white));
                    noteDescription.setTextColor(context.getResources().getColor(R.color.gray_300));
                    noteDate.setTextColor(context.getResources().getColor(R.color.gray_300));
                    noteLockIcon.setImageResource(R.drawable.unlock_icon);
                    titleContainer.setBackgroundResource(R.color.red_color);
                    break;
                case "GRAY":
                    noteTitle.setTextColor(context.getResources().getColor(R.color.white));
                    noteDescription.setTextColor(context.getResources().getColor(R.color.gray_300));
                    noteDate.setTextColor(context.getResources().getColor(R.color.gray_300));
                    noteLockIcon.setImageResource(R.drawable.unlock_icon);
                    titleContainer.setBackgroundResource(R.color.gray_color);
                    break;
                case "BLACK":
                    noteTitle.setTextColor(context.getResources().getColor(R.color.white));
                    noteDescription.setTextColor(context.getResources().getColor(R.color.gray_300));
                    noteDate.setTextColor(context.getResources().getColor(R.color.gray_300));
                    noteLockIcon.setImageResource(R.drawable.unlock_icon);
                    titleContainer.setBackgroundResource(R.color.black_color);
                    break;
            }
        }
    }
}
