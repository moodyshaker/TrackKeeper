package com.example.trackkeeper.ui.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.webkit.URLUtil;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.trackkeeper.R;
import com.example.trackkeeper.databinding.ActivityNewNoteBinding;
import com.example.trackkeeper.databinding.UrlDialogLayoutBinding;
import com.example.trackkeeper.persistence.database.Note;
import com.example.trackkeeper.utils.AppConstants;
import com.example.trackkeeper.utils.Utils;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class NewNote extends AppCompatActivity {

    private ActivityNewNoteBinding binding;
    private Context context = this;
    private Activity activity = this;
    private ImageView saveButton,
            backButton,
            noteImage,
            redColor,
            blueColor,
            yellowColor,
            blackColor,
            whiteColor,
            redColorMarker,
            blueColorMarker,
            yellowColorMarker,
            blackColorMarker,
            whiteColorMarker, actionsButton;
    private EditText noteTitleET,
            noteSubtitleET,
            noteDescriptionET,
            webUrlET;
    private TextInputLayout webUrlInput;
    private TextView noteDateTimeTV,
            noteUrlTV,
            urlAddButton, urlCancelButton;
    private View subTitleColor;
    private LinearLayout deleteNote,
            addImage,
            addRecord,
            addUrl,
            moreActions;
    private BottomSheetBehavior<LinearLayout> bottomSheetBehavior;
    private String noteTitle,
            noteSubtitle,
            noteDescription,
            noteUrl,
            colorName;
    private Uri noteImageUrl;
    private Date date;
    private SimpleDateFormat format;
    private Intent i;
    private String extraString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.checkDarkTheme(context);
        binding = ActivityNewNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initUi();
        i = getIntent();
        if (i != null) {
            extraString = i.getStringExtra("extra_msg");
            switch (extraString) {
                case "NEW IMAGE":
                    break;
                case "NEW URL":
                    break;
            }
        }
        date = new Date();
        format = new SimpleDateFormat("EEEE, d MMM yyyy hh:mm aa", Locale.US);
        noteDateTimeTV.setText(format.format(date));
        backButton.setOnClickListener(back -> {
            if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            } else {
                onBackPressed();
            }
        });
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int i) {
                switch (i) {
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        setRotation(actionsButton, 180.0f, 0.0f);
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        setRotation(actionsButton, 0.0f, 180.0f);
                        break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        break;
                    case BottomSheetBehavior.STATE_HALF_EXPANDED:
                        break;
                    case BottomSheetBehavior.STATE_HIDDEN:
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View view, float v) {

            }
        });
        actionsButton.setOnClickListener(click -> {
            if (bottomSheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            } else {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });
        redColor.setOnClickListener(red -> {
            redColorMarker.setVisibility(View.VISIBLE);
            blueColorMarker.setVisibility(View.GONE);
            yellowColorMarker.setVisibility(View.GONE);
            whiteColorMarker.setVisibility(View.GONE);
            blackColorMarker.setVisibility(View.GONE);
            colorName = "RED";
            subTitleColor.setBackgroundColor(getResources().getColor(R.color.red_color));
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        });
        blueColor.setOnClickListener(red -> {
            blueColorMarker.setVisibility(View.VISIBLE);
            redColorMarker.setVisibility(View.GONE);
            yellowColorMarker.setVisibility(View.GONE);
            whiteColorMarker.setVisibility(View.GONE);
            blackColorMarker.setVisibility(View.GONE);
            colorName = "BLUE";
            subTitleColor.setBackgroundColor(getResources().getColor(R.color.blue_color));
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        });
        yellowColor.setOnClickListener(red -> {
            redColorMarker.setVisibility(View.GONE);
            blueColorMarker.setVisibility(View.GONE);
            yellowColorMarker.setVisibility(View.VISIBLE);
            whiteColorMarker.setVisibility(View.GONE);
            blackColorMarker.setVisibility(View.GONE);
            colorName = "YELLOW";
            subTitleColor.setBackgroundColor(getResources().getColor(R.color.yellow_color));
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        });
        whiteColor.setOnClickListener(red -> {
            redColorMarker.setVisibility(View.GONE);
            blueColorMarker.setVisibility(View.GONE);
            yellowColorMarker.setVisibility(View.GONE);
            whiteColorMarker.setVisibility(View.VISIBLE);
            blackColorMarker.setVisibility(View.GONE);
            colorName = "GRAY";
            subTitleColor.setBackgroundColor(getResources().getColor(R.color.gray_color));
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        });
        blackColor.setOnClickListener(red -> {
            redColorMarker.setVisibility(View.GONE);
            blueColorMarker.setVisibility(View.GONE);
            yellowColorMarker.setVisibility(View.GONE);
            whiteColorMarker.setVisibility(View.GONE);
            blackColorMarker.setVisibility(View.VISIBLE);
            colorName = "BLACK";
            subTitleColor.setBackgroundColor(getResources().getColor(R.color.black_color));
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        });
        addImage.setOnClickListener(click -> {
            if (Utils.checkImagePermission(context)) {
                Utils.openGallery(activity);
            } else {
                Utils.requestImagePermission(activity);
            }
        });
        addUrl.setOnClickListener(click -> {
            webUrlDialog();
            if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });
        colorName = colorTracker();
        saveButton.setOnClickListener(click -> {
            noteTitle = noteTitleET.getText().toString();
            noteSubtitle = noteSubtitleET.getText().toString();
            noteDescription = noteDescriptionET.getText().toString();
            if (TextUtils.isEmpty(noteTitle)) {
                Toast.makeText(context, "Please Enter Note Title", Toast.LENGTH_SHORT).show();
            } else if (TextUtils.isEmpty(noteSubtitle)) {
                Toast.makeText(context, "Please Enter Note SubTitle", Toast.LENGTH_SHORT).show();
            } else if (TextUtils.isEmpty(noteDescription)) {
                Toast.makeText(context, "Please Enter Note Description", Toast.LENGTH_SHORT).show();
            } else {
                Note note;
                if (noteImageUrl != null && noteUrl != null) {
                    note = new Note(noteTitle,
                            noteSubtitle,
                            noteDescription,
                            colorName,
                            String.valueOf(noteImageUrl),
                            noteUrl,
                            date);
                } else if (noteImageUrl != null) {
                    note = new Note();
                    note.setTitle(noteTitle);
                    note.setSubTitle(noteSubtitle);
                    note.setDescription(noteDescription);
                    note.setColor(colorName);
                    note.setDate(date);
                    note.setImageUrl(noteUrl);
                } else if (noteUrl != null) {
                    note = new Note();
                    note.setTitle(noteTitle);
                    note.setSubTitle(noteSubtitle);
                    note.setDescription(noteDescription);
                    note.setColor(colorName);
                    note.setDate(date);
                    note.setNoteUrl(noteUrl);
                } else {
                    note = new Note(noteTitle,
                            noteSubtitle,
                            noteDescription,
                            colorName,
                            date);
                }
                addItemToList(note);
                Log.d(AppConstants.TAG, "onCreate: " +
                        "Title: " + noteTitle +
                        " SubTitle: " + noteSubtitle +
                        " Description: " + noteDescription +
                        " Color: " + colorName +
                        " Date: " + date +
                        " ImageUrl: " + noteImageUrl +
                        " ImageUrl: " + noteUrl);
            }
        });
    }

    private void initUi() {
        noteTitleET = binding.noteTitleEt;
        noteSubtitleET = binding.noteSubtitleEt;
        noteImage = binding.noteImage;
        noteDescriptionET = binding.noteEt;
        noteDateTimeTV = binding.timeDateTv;
        noteUrlTV = binding.noteUrl;
        saveButton = binding.saveButton;
        backButton = binding.backArrow;
        subTitleColor = binding.subtitleColor;
        moreActions = binding.moreActionLayout.moreActionContainer;
        actionsButton = binding.moreActions;
        whiteColor = binding.moreActionLayout.whiteColor;
        whiteColorMarker = binding.moreActionLayout.whiteColorMarker;
        blackColor = binding.moreActionLayout.blackColor;
        blackColorMarker = binding.moreActionLayout.blackColorMarker;
        blueColor = binding.moreActionLayout.blueColor;
        blueColorMarker = binding.moreActionLayout.blueColorMarker;
        redColor = binding.moreActionLayout.redColor;
        redColorMarker = binding.moreActionLayout.redColorMarker;
        yellowColor = binding.moreActionLayout.yellowColor;
        yellowColorMarker = binding.moreActionLayout.yellowColorMarker;
        bottomSheetBehavior = BottomSheetBehavior.from(moreActions);
        addImage = binding.moreActionLayout.addImage;
        addUrl = binding.moreActionLayout.addUrl;
//        addRecord = binding.moreActionLayout.addRecord;
//        deleteNote = binding.moreActionLayout.deleteNote;

    }

    private String colorTracker() {
        String colorName = "";
        if (redColorMarker.getVisibility() == View.VISIBLE) {
            colorName = "RED";
        } else if (blueColorMarker.getVisibility() == View.VISIBLE) {
            colorName = "BLUE";
        } else if (blackColorMarker.getVisibility() == View.VISIBLE) {
            colorName = "BLACK";
        } else if (whiteColorMarker.getVisibility() == View.VISIBLE) {
            colorName = "GRAY";
        } else {
            colorName = "YELLOW";
        }
        return colorName;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                return true;
            } else {
                onBackPressed();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AppConstants.IMAGE_GALLERY_REQUEST && resultCode == RESULT_OK) {
//            String path = Utils.getFilePath(context, data.getData());
//            File file = new File(path);
//            noteImageUrl = Uri.fromFile(file);
            noteImageUrl = data.getData();
            noteImage.setVisibility(View.VISIBLE);
            noteImage.setImageURI(noteImageUrl);
            if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
//            try {
//                InputStream inputStream = getContentResolver().openInputStream(noteImageUrl);
//                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
//                noteImage.setImageBitmap(bitmap);
//            } catch (Exception e) {
//                Log.d(AppConstants.TAG, "onActivityResult: " + e.getMessage());
//            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == AppConstants.IMAGE_REQUEST_PERMISSION && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Utils.openGallery(activity);
        } else {
            Toast.makeText(context, "Permission denied", Toast.LENGTH_SHORT).show();
        }
    }

    private void addItemToList(Note note) {
        Home.noteList.add(note);
        Home.adapter.notifyDataSetChanged();
        finish();
    }

    private void setRotation(View v, float from, float to) {
        RotateAnimation rotate = new RotateAnimation(from, to,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(250);
        rotate.setFillAfter(true);
        v.startAnimation(rotate);
    }

    private void webUrlDialog() {
        Dialog d = new Dialog(context);
        UrlDialogLayoutBinding db = UrlDialogLayoutBinding.inflate(getLayoutInflater());
        d.setContentView(db.getRoot());
        d.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        d.setCancelable(false);
        d.show();
        webUrlET = db.urlEt;
        webUrlInput = db.urlInputLayout;
        urlAddButton = db.addUrlButton;
        urlCancelButton = db.cancelUrlButton;
        urlCancelButton.setOnClickListener(click -> {
            d.dismiss();
        });
        urlAddButton.setOnClickListener(click -> {
            noteUrl = webUrlET.getText().toString();
            if (!URLUtil.isValidUrl(noteUrl)) {
                webUrlInput.setError("https://www.example.com");
            } else {
                noteUrlTV.setText(noteUrl);
                noteUrlTV.setVisibility(View.VISIBLE);
                d.dismiss();
            }
        });

    }
}