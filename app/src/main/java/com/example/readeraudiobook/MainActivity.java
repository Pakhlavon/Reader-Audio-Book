package com.example.readeraudiobook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclweView;
    public RecyclerView.LayoutManager layoutManager;
    private CopyDatabase copyDatabase;
    Adapter adapter;
    Model model;

    private List<Model> modelList =new ArrayList<Model>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclweView = findViewById(R.id.recyclweView);
        recyclweView.setHasFixedSize(false);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclweView.addItemDecoration(new DividerItemDecoration(getApplicationContext(),DividerItemDecoration.VERTICAL));
        recyclweView.setLayoutManager(layoutManager);
        recyclweView.setItemAnimator(new DefaultItemAnimator());
        copyDatabase = new CopyDatabase(this);
        modelList=  copyDatabase.barchasi();


        adapter = new Adapter(this,modelList);
        recyclweView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}