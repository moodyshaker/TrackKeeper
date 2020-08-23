package com.example.trackkeeper.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.trackkeeper.R;
import com.example.trackkeeper.databinding.ActivityHomeBinding;
import com.example.trackkeeper.persistence.database.Note;
import com.example.trackkeeper.persistence.sharedpreference.KeeperPreference;
import com.example.trackkeeper.ui.adapter.NoteAdapter;
import com.example.trackkeeper.utils.AppConstants;
import com.example.trackkeeper.utils.Utils;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class Home extends AppCompatActivity {

    private ActivityHomeBinding binding;
    private Context context = this;
    private Activity activity = this;
    private RecyclerView notesRecyclerview;
    private BottomAppBar bottomAppBar;
    private FloatingActionButton addFab;
    private CardView searchBar;
    private TextView userName, userFirstLetter;
    private CircleImageView userIcon;
    private Intent i;
    public static NoteAdapter adapter;
    public static ArrayList<Note> noteList;
    private SwitchCompat darkModeSwitch;
    private KeeperPreference.UserPreferences userPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.checkDarkTheme(context);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initUi();
        userPreferences = KeeperPreference.UserPreferences.getUserPreferences(context);
        if (userPreferences.getDarkMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            darkModeSwitch.setChecked(true);
        }
        noteList = new ArrayList<>();
        notesRecyclerview.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        adapter = new NoteAdapter(context, noteList);
        notesRecyclerview.setAdapter(adapter);
        searchBar.setOnClickListener(search -> {

        });
        bottomAppBar.replaceMenu(R.menu.bottom_bar_items);
        bottomAppBar.setOnMenuItemClickListener(item -> {
            int id = item.getItemId();
            switch (id) {
                case R.id.add_image:
                    i = new Intent(context, NewNote.class);
                    i.putExtra("extra_msg", "NEW IMAGE");
                    startActivity(i);
                    return true;
                case R.id.add_record:
                    Toast.makeText(context, "Under Maintenance", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.add_url:
                    i = new Intent(context, NewNote.class);
                    i.putExtra("extra_msg", "NEW URL");
                    startActivity(i);
                    return true;
                default:
                    return false;
            }
        });
        darkModeSwitch.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            if (isChecked) {
                Utils.setDarkTheme(activity, AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                Utils.setDarkTheme(activity, AppCompatDelegate.MODE_NIGHT_NO);
            }
        });
        addFab.setOnClickListener(click -> {
            i = new Intent(context, NewNote.class);
            startActivityForResult(i, AppConstants.REQUEST_CODE_ACTIVITY);
        });
    }

    private void initUi() {
        bottomAppBar = binding.bottomAppBar;
        addFab = binding.floatingActionButton;
        notesRecyclerview = binding.notesRecyclerview;
        searchBar = binding.searchBar;
        userName = binding.userName;
        userIcon = binding.userIcon;
        userFirstLetter = binding.userFirstNameLetter;
        darkModeSwitch = binding.darkModeSwitch;
    }
}