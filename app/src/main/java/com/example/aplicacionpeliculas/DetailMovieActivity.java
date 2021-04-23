package com.example.aplicacionpeliculas;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

public class DetailMovieActivity extends AppCompatActivity {

    private TextView tvtoolbar_titulo;
    private TextView tvSummary;
    private ImageView imgMovieBackground;

    private String summary = "";
    private String title = "";
    private String backdrop = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        tvtoolbar_titulo = (TextView) findViewById(R.id.tvtoolbar_titulo);
        tvSummary = (TextView) findViewById(R.id.tvSummary);
        imgMovieBackground = (ImageView) findViewById(R.id.imgMovieBackground);

        Bundle parameters = getIntent().getExtras();
        summary = parameters.getString("summary","");
        title = parameters.getString("title","");
        backdrop = parameters.getString("backdrop_path","");

        tvtoolbar_titulo.setText(title);
        tvSummary.setText(summary);

        Transformation transformation = new Transformation() {
            @Override
            public Bitmap transform(Bitmap source) {
                int targetWidth = imgMovieBackground.getWidth();
                double aspectRatio = (double) source.getHeight() / source.getWidth();
                int targetHeight = (int) (targetWidth*aspectRatio);
                Bitmap result = Bitmap.createScaledBitmap(source,source.getWidth(),source.getHeight(),false);
                if(imgMovieBackground.getWidth()>0 && imgMovieBackground.getHeight()>0){
                    result = Bitmap.createScaledBitmap(source,targetWidth,targetHeight,false);
                }
                if(result != source){
                    source.recycle();
                }
                return result;
            }

            @Override
            public String key() {
                return "transformation" + " desiredWidth";
            }
        };

        try {
            imgMovieBackground.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Picasso.with(DetailMovieActivity.this)
                    .load("https://image.tmdb.org/t/p/original"+backdrop)
                    .placeholder(R.mipmap.ic_no_imagen)
                    .transform(transformation)
                    .into(imgMovieBackground);
        }catch (Exception e){

        }

    }
}