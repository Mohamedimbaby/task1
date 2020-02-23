package com.example.task1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.drm.DrmStore;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.task1.adapters.places_adapter;
import com.michaldrabik.tapbarmenulib.TapBarMenu;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
Presenter presenter;
    ApiCall instance;
    places_adapter adapter;
    RecyclerView events_recycler,hotspots_recycler,attractions_recycler;
ArrayList<item> dummy_items= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Collections.addAll(dummy_items,new item("","",""),new item("","",""),new item("","",""),new item("","",""),new item("","",""));
        events_recycler= findViewById(R.id.Events_list);
        hotspots_recycler= findViewById(R.id.Hotspots_list);
        attractions_recycler=findViewById(R.id.Attraction_list);
        presenter= Presenter.getInstance();
        presenter.presenter_init(this);
        instance=ApiCall.getInstance(this);
        instance.getAttractions();
        instance.getAttractions_LiveData().observe(this, new Observer<List<item>>() {
            @Override
            public void onChanged(List<item> items) {
                LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
                attractions_recycler.setLayoutManager(layoutManager);
                if (items.size()>0)
                    adapter = new places_adapter(MainActivity.this,items);
                else
                    adapter = new places_adapter(MainActivity.this,dummy_items);

                attractions_recycler.setAdapter(adapter);

            }
        });
        instance.getEvents();
        instance.getEvents_LiveData().observe(this, new Observer<List<item>>() {
            @Override
            public void onChanged(List<item> items) {
                LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
                events_recycler.setLayoutManager(layoutManager);
                if (items.size()>0)
                adapter = new places_adapter(MainActivity.this,items);
                else
                   adapter = new places_adapter(MainActivity.this,dummy_items);
                events_recycler.setAdapter(adapter);
            }
        });
        instance.getHotspots();
        instance.getHotspots_LiveData().observe(this, new Observer<List<item>>() {
            @Override
            public void onChanged(List<item> items) {

                LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
                hotspots_recycler.setLayoutManager(layoutManager);
                if (items.size()>0)
                    adapter = new places_adapter(MainActivity.this,items);
                else
                    adapter = new places_adapter(MainActivity.this,dummy_items);
                hotspots_recycler.setAdapter(adapter);
            }
        });
    }}


/*
    ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setDisplayShowCustomEnabled(true);
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View action_bar_view = inflater.inflate(R.layout.cutom_action_bar,null);
                actionBar.setCustomView(action_bar_view);
*/

