package icesi.johann.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import icesi.johann.Adapter.Adapter_Playlist;
import icesi.johann.Entity.Playlist;
import icesi.johann.R;
import icesi.johann.REST_Service.Service_REST_Manager;

public class MainActivity extends AppCompatActivity {

    private EditText editText_playlist;

    private Button btn_search;

    private RecyclerView recyclerView;

    private ArrayList<Playlist> playlists;

    private Adapter_Playlist adapter_playlist;

    private String search;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView= findViewById(R.id.main_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);
        playlists= new ArrayList<>();
        editText_playlist=findViewById(R.id.main_edittext_playlist);
        btn_search=findViewById(R.id.main_btn_search);
        adapter_playlist= new Adapter_Playlist(playlists, getApplicationContext());
        recyclerView.setAdapter(adapter_playlist);


        btn_search.setOnClickListener(v -> {
            search = editText_playlist.getText().toString();
            if(search!=null){

                new Thread(()-> {
                    new Service_REST_Manager.GET_Search_For_Playlist(search, response -> runOnUiThread(() -> {
                        JSONObject json;
                        try {
                            json = new JSONObject(response);
                            JSONArray data = json.getJSONArray("data");

                            String playlist_json = data.toString();

                             playlists = new Gson().fromJson(playlist_json, new TypeToken<List<Playlist>>() {
                            }.getType());

                            adapter_playlist.setPlaylist(playlists);
                        } catch (JSONException e) {
                            Log.e(">>>>>>>>>>>", e.getMessage());
                        }
                    }));


                }).start();


            }
            else{
                Toast.makeText(MainActivity.this, "Type the name of the playlist", Toast.LENGTH_SHORT).show();
            }

        });


    }



}
