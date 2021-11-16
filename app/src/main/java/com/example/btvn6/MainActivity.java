package com.example.btvn6;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rcv_list;
    private FolderAdapter adapter;
    private List<Folder> folderList;
    private ImageView imgback;
    private static final int ADD_REQUEST_CODE = 1;
    Database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();

        database = new Database(this);
//        adddata();
//        database.deleteAll();
        adapter = new FolderAdapter(this);
        folderList = database.getAll();
        adapter.setFolders(folderList);
        rcv_list.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false);
        rcv_list.setLayoutManager(linearLayoutManager);

        rcv_list.setHasFixedSize(true);
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, androidx.appcompat.R.anim.abc_fade_in));
                Intent intent = new Intent(MainActivity.this, add.class);
                intent.putExtra("KEY", "100");
                startActivity(intent);

            }
        });
        adapter.setClickListener(new FolderAdapter.ClickListener() {
            @Override
            public void onClick(Folder folder, int position) {
//                displayToast(String.valueOf(position));
//                displayToast(contact.toString());

                Intent intent1 = new Intent(MainActivity.this, add.class);
                String t=database.getAll().get(position).getTitle().toString();
                String c=database.getAll().get(position).getContent().toString();
                String id=database.getAll().get(position).getId()+"";
                intent1.putExtra("id",id);
                intent1.putExtra("t", t);
                intent1.putExtra("c", c);
                intent1.putExtra("KEY", "101");
                startActivity(intent1);
            }
        });

    }


    private void initUI() {
        rcv_list = findViewById(R.id.recycle);
        imgback = findViewById(R.id.imgback);
    }



    private void adddata(){
        database.addfolder(new Folder("thoi su","tinh hinh covit"));
        database.addfolder(new Folder("the thao","vong loai woldcup"));
    }

}