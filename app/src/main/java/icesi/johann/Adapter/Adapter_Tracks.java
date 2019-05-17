package icesi.johann.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.io.Serializable;
import java.util.ArrayList;
import icesi.johann.Activity.TrackActivity;
import icesi.johann.Entity.Track;
import icesi.johann.R;

public class Adapter_Tracks extends RecyclerView.Adapter<Adapter_Tracks.ViewHolderSongs> implements Serializable {

    private ArrayList<Track> tracks;

    private Context context;

    public ArrayList<Track> getTracks() {
        return tracks;
    }

    public void setTracks(ArrayList<Track> tracks) {
        this.tracks = tracks;
        notifyDataSetChanged();
    }

    public Adapter_Tracks(ArrayList<Track> tracks, Context context) {
        this.tracks = tracks;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolderSongs onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LinearLayout view = (LinearLayout) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_tracks,viewGroup,false);
        ViewHolderSongs viewHolderSongs = new ViewHolderSongs(view);
        return viewHolderSongs;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderSongs viewHolderSongs, int i) {
        Picasso.get().load(getTracks().get(i).getAlbum().getCover_medium()).into((ImageView)viewHolderSongs.root.findViewById(R.id.item_songs_image));
        ((TextView)viewHolderSongs.root.findViewById(R.id.item_songs_name)).setText(getTracks().get(i).getTitle());
        ((TextView)viewHolderSongs.root.findViewById(R.id.item_songs_artist)).setText(getTracks().get(i).getArtist().getName());
        ((TextView)viewHolderSongs.root.findViewById(R.id.item_songs_releaseYear)).setText(getTracks().get(i).getRelease_date() + " \uD83C\uDF1F");


        viewHolderSongs.root.setOnClickListener(v -> {
            Intent in = new Intent(viewHolderSongs.root.getContext(), TrackActivity.class);
            in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            in.putExtra("track_id", getTracks().get(i).getId());
            context.startActivity(in);
        });


    }

    @Override
    public int getItemCount() {
        return tracks.size();
    }

    public static class ViewHolderSongs extends RecyclerView.ViewHolder {

        public LinearLayout root;

        public ViewHolderSongs(LinearLayout v) {
            super(v);
            root=v;
        }
    }
}
