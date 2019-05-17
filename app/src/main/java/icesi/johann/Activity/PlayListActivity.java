package icesi.johann.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import icesi.johann.Adapter.Adapter_Tracks;
import icesi.johann.Entity.Playlist;
import icesi.johann.Entity.Track;
import icesi.johann.R;
import icesi.johann.REST_Service.Service_REST_Manager;

public class PlayListActivity extends AppCompatActivity implements Serializable {

    private Button btn_back;

    private Playlist selected_playlist;

    private String playlist_id;

    private ImageView playlist_image;

    private TextView textView_playlist_name;

    private TextView textView_playlist_description;

    private TextView textView_playlist_number_songs;

    private RecyclerView recyclerView;

    private ArrayList<Track> tracks;

    private Adapter_Tracks adapter_tracks;

    private String tracks_json;

    private String playlist_json;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_list);
        tracks =new ArrayList<>();
        btn_back = findViewById(R.id.playlist_btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayListActivity.this.onBackPressed();

//                Intent in = new Intent(PlayListActivity.this, MainActivity.class);
//                startActivity(in);
//                finishAffinity();

            }
        });

        playlist_image=findViewById(R.id.playlist_image);

        textView_playlist_name=findViewById(R.id.playlist_editText_name);
        textView_playlist_description=findViewById(R.id.playlist_editText_description);
        textView_playlist_number_songs=findViewById(R.id.playlist_editText_numberSongs);
        adapter_tracks= new Adapter_Tracks(tracks, getApplicationContext());
        recyclerView= findViewById(R.id.playlist_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter_tracks);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(),
                DividerItemDecoration.VERTICAL));

        if(getIntent().hasExtra("Playlist_id")){
            playlist_id = getIntent().getStringExtra("Playlist_id");
            new Thread(() -> {
                new Service_REST_Manager.GET_Playlist(playlist_id, response -> runOnUiThread(() -> {
                    JSONObject json = null;
                    try{
                        json= new JSONObject(response);

                        JSONObject playlist_object = json.getJSONObject("tracks");
                        JSONArray playlist_json_array= playlist_object.getJSONArray("data");

                        tracks_json = playlist_json_array.toString();

                        playlist_json = json.toString();

                        selected_playlist = new Gson().fromJson(playlist_json, new TypeToken<Playlist>(){ }.getType());
                        tracks=new Gson().fromJson(tracks_json, new TypeToken<List<Track>>(){}.getType());

                        adapter_tracks.setTracks(tracks);

                        Picasso.get().load(selected_playlist.getPicture_big()).into(playlist_image);
                        textView_playlist_name.setText(selected_playlist.getTitle());
                        String des = selected_playlist.getDescription();
                        if(des.equals("")){
                            textView_playlist_description.setText("No description provided");
                        }
                        else{
                            textView_playlist_description.setText(des);
                        }
                        String number = selected_playlist.getNb_tracks()+" tracks";
                        textView_playlist_number_songs.setText(number);

                    }
                    catch(JSONException e){
                        Log.e(">>>>>>>>", e.getMessage());
                    }

                }));

            }).start();
        }



    }



}
