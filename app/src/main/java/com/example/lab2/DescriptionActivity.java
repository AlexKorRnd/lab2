package com.example.lab2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DescriptionActivity extends AppCompatActivity {

    public static final String EXTRA_ITEM = "EXTRA_ITEM";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        TextView tvTitle = (TextView) findViewById(R.id.tvTitle);
        ImageView ivPhoto = (ImageView) findViewById(R.id.ivPhoto);

        Item item = (Item) getIntent().getSerializableExtra(EXTRA_ITEM);
        tvTitle.setText(item.getName());
        Glide.with(this)
                .load(item.getImage())
                .centerCrop()
                .crossFade()
                .into(ivPhoto);
    }
}
