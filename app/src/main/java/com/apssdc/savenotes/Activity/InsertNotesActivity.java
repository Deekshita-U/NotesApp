package com.apssdc.savenotes.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.Toast;

import com.apssdc.savenotes.Model.Notes;
import com.apssdc.savenotes.R;
import com.apssdc.savenotes.ViewModel.NotesViewModel;
import com.apssdc.savenotes.databinding.ActivityInsertNotesBinding;
import com.apssdc.savenotes.databinding.ActivityUpdateNotesBinding;

import java.util.Date;

public class InsertNotesActivity extends AppCompatActivity {
    ActivityInsertNotesBinding binding;
    String title,subtitle,notes;
    NotesViewModel notesViewModel;
    String priority = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityInsertNotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        notesViewModel= ViewModelProviders.of(this).get(NotesViewModel.class);
        binding.greenPriority.setOnClickListener(v -> {
            binding.greenPriority.setImageResource(R.drawable.ic_baseline_done_24);
            binding.redPriority.setImageResource(0);
            binding.yellowPriority.setImageResource(0);
            binding.pinkPriority.setImageResource(0);
            priority="1";
        });
        binding.redPriority.setOnClickListener(v -> {
            binding.greenPriority.setImageResource(0);
            binding.redPriority.setImageResource(R.drawable.ic_baseline_done_24);
            binding.yellowPriority.setImageResource(0);
            binding.pinkPriority.setImageResource(0);
            priority="2";
        });
        binding.yellowPriority.setOnClickListener(v -> {
            binding.greenPriority.setImageResource(0);
            binding.redPriority.setImageResource(0);
            binding.yellowPriority.setImageResource(R.drawable.ic_baseline_done_24);
            binding.pinkPriority.setImageResource(0);
            priority="3";
        });
        binding.pinkPriority.setOnClickListener(v -> {
            binding.greenPriority.setImageResource(0);
            binding.redPriority.setImageResource(0);
            binding.yellowPriority.setImageResource(0);
            binding.pinkPriority.setImageResource(R.drawable.ic_baseline_done_24);
            priority="4";
        });


        binding.doneNotesBtn.setOnClickListener(v -> {

            title=binding.notesTitle.getText().toString();
            subtitle=binding.notesSubtitle.getText().toString();
            notes=binding.notesDate.getText().toString();

            CreateNotes(title,subtitle,notes);

        });

    }

    private void CreateNotes(String title, String subtitle, String notes) {
        Date date = new Date();
        CharSequence sequence = DateFormat.format("MMMM d,yyyy",date.getTime());

        Notes notes1 = new Notes();
        notes1.notesTitle=title;
        notes1.notesSubtitle=subtitle;
        notes1.notes=notes;
        notes1.notesPriority=priority;
        notes1.notesDate=sequence.toString();
        notesViewModel.insertNote(notes1);

        Toast.makeText(this, "Notes Created Successfully", Toast.LENGTH_SHORT).show();

        finish();


    }
}