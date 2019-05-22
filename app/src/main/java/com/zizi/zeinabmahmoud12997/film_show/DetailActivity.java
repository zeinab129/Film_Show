package com.zizi.zeinabmahmoud12997.film_show;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra("img");
        String name = intent.getStringExtra("filmName");
        String cat = intent.getStringExtra("cat");
        String det = intent.getStringExtra("des");
        final String film = intent.getStringExtra("film");

        ImageView imageView = findViewById(R.id.image_view_detail);
        TextView txtName = findViewById(R.id.text_view_name_detail);
        TextView txtCat = findViewById(R.id.text_view_cat_detail);
        TextView detail = findViewById(R.id.text_view_detail);
        Button video = findViewById(R.id.vv);


        Picasso.with(this).load(imageUrl).fit().centerInside().into(imageView);
        txtName.setText(name);
        txtCat.setText(cat);
        detail.setText(det);


        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent filmInt = new Intent(DetailActivity.this,ShowFilm.class);
                filmInt.putExtra("film", film);
                startActivity(filmInt);
            }
        });

    }
}
