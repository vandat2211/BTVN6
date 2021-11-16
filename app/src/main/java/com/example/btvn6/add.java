package com.example.btvn6;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class add extends AppCompatActivity {
    private TextView tvAdd,tvsua;
    private TextView tvCancel,tvname;
    private EditText edtTitleAdd,edid;
    private EditText edtContentAdd;

   private final Database database=new Database(this);
    private void initUI() {
//        edid=findViewById(R.id.edid);
        tvname=findViewById(R.id.tvname);
        tvAdd = findViewById(R.id.tvluu);
        tvsua=findViewById(R.id.tvsua);
        tvCancel = findViewById(R.id.tvhuy);
        edtTitleAdd = findViewById(R.id.edt_title_add);
        edtContentAdd = findViewById(R.id.edt_content_add);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initUI();
        Intent intent1=getIntent();
//        String idd=intent1.getStringExtra("id");
        String title=intent1.getStringExtra("t");
        String content=intent1.getStringExtra("c");
        String key = intent1.getStringExtra("KEY");
        if (key.equals("100")){
            tvname.setText("Them thu muc");
            tvAdd.setVisibility(View.VISIBLE);
            tvsua.setVisibility(View.GONE);
//            edid.setVisibility(View.GONE);
        } else if (key.equals("101")) {
            tvname.setText("chinh sua");
//            edid.setVisibility(View.VISIBLE);
            tvAdd.setVisibility(View.GONE);
            tvsua.setVisibility(View.VISIBLE);
        }
//        edid.setText(idd);
        edtTitleAdd.setText(title);
        edtContentAdd.setText(content);



        tvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(add.this, androidx.appcompat.R.anim.abc_fade_in));
                String titleAdd = edtTitleAdd.getText().toString().trim();
                String contentAdd = edtContentAdd.getText().toString().trim();
                if ((titleAdd.isEmpty()) || (contentAdd.isEmpty())) {
                    displayToast("Điền đủ thông tin đê bạn ơi!");
                    return;
                }
                else{
                    database.addfolder(new Folder(edtTitleAdd.getText().toString(),
                            edtContentAdd.getText().toString()));
                    displayToast("OK bạn ơi!");
                    cancelActivity();
                }

            }
        });

        tvsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(AnimationUtils.loadAnimation(add.this, androidx.appcompat.R.anim.abc_fade_in));
                database.updatedb(new Folder(edtTitleAdd.getText().toString(), edtContentAdd.getText().toString()));
                cancelActivity();
            }
        });

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(add.this, androidx.appcompat.R.anim.abc_fade_in));
                cancelActivity();
            }
        });
    }

    private void cancelActivity() {
        Intent intent = new Intent(add.this, MainActivity.class);
        startActivity(intent);
    }
    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}