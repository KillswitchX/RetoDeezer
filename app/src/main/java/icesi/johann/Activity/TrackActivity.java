package icesi.johann.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.Serializable;

import icesi.johann.R;

public class TrackActivity extends AppCompatActivity implements Serializable {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track);
    }
}
