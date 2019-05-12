package icesi.johann.Activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

import icesi.johann.Entity.Track;
import icesi.johann.R;
import icesi.johann.REST_Service.Service_REST_Manager;

public class TrackActivity extends AppCompatActivity{

    private Button btn_back;

    private ImageView song_image;

    private TextView editText_name;

    private TextView editText_artist;

    private TextView editText_album;

    private TextView editText_duration;

    private Button btn_listen;

    private Track selectedTrack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track);
        btn_back = findViewById(R.id.track_btn_back);
        song_image = findViewById(R.id.track_image);
        editText_name=findViewById(R.id.track_name);
        editText_artist = findViewById(R.id.track_artist);
        editText_album = findViewById(R.id.track_album);
        editText_duration = findViewById(R.id.track_duration);
        btn_listen = findViewById(R.id.btn_listen);
        btn_back.setOnClickListener(v -> {
            Intent in = new Intent(TrackActivity.this, MainActivity.class);
            startActivity(in);
        });
        btn_listen.setOnClickListener(v -> {

        });

        if(getIntent().hasExtra("track_id")){
            String track_id= getIntent().getStringExtra("track_id");
                new Thread(() -> {

                    new Service_REST_Manager.GET_Track(track_id, new Service_REST_Manager.GET_Track.OnResponseListener() {
                        @Override
                        public void onResponse(String response) {

                            runOnUiThread(() -> {

                                JSONObject json = null;
                                try{

                                    json = new JSONObject(response);
                                    String track_json = json.toString();


                                    selectedTrack = new Gson().fromJson(track_json, new TypeToken<Track>(){}.getType());

                                    loadImage(selectedTrack.getAlbum().getCover_medium());
                                    editText_name.setText(selectedTrack.getTitle());
                                    editText_artist.setText(selectedTrack.getArtist().getName());
                                    editText_album.setText(selectedTrack.getAlbum().getTitle());
                                    editText_duration.setText("");


                                }
                                catch(JSONException e){
                                    Log.e(">>>>>>>>", e.getMessage());
                                }

                            });

                        }
                    });

                }).start();

        }


    }

    public void loadImage(String image){
        Picasso.get().load(image).into(song_image);

    }

}
