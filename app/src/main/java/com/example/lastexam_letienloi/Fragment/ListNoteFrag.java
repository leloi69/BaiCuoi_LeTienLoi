package com.example.lastexam_letienloi.Fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.lastexam_letienloi.ListNoteAdapter;
import com.example.lastexam_letienloi.MainActivity;
import com.example.lastexam_letienloi.R;
import com.example.lastexam_letienloi.SQLiteHelpterProject;
import com.example.lastexam_letienloi.model.ListNote;

import java.util.List;
public class ListNoteFrag extends Fragment {
    RecyclerView recyclerView;
    ListNoteAdapter listNoteAdapter;
    SQLiteHelpterProject sqLiteHelpterProject;
    SearchView searchView;
    Context context;
    String userName;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        context = view.getContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences("profile", MainActivity.MODE_PRIVATE);
        userName = sharedPreferences.getString("name", null);
        super.onViewCreated(view, savedInstanceState);
        recyclerView=view.findViewById(R.id.recylerview);
        listNoteAdapter=new ListNoteAdapter();
        searchView=view.findViewById(R.id.searchview);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        sqLiteHelpterProject=new SQLiteHelpterProject(view.getContext());
        loadData();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<ListNote> list=sqLiteHelpterProject.getByTitle(newText);
                listNoteAdapter.getAll(list);
                recyclerView.setAdapter(listNoteAdapter);
                return true;
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_note, container, false);
    }
    public void loadData(){
        List<ListNote> list=sqLiteHelpterProject.getAllListnoteByUserName(userName);
        listNoteAdapter.getAll(list);
        recyclerView.setAdapter(listNoteAdapter);
    }
}