package icesi.johann.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;

import icesi.johann.Activity.PlayListActivity;
import icesi.johann.Entity.Playlist;
import icesi.johann.R;

public class Adapter_Playlist extends RecyclerView.Adapter<Adapter_Playlist.ViewHolderPlayList> implements Serializable {

    private ArrayList<Playlist> playlist;

    private Playlist selectedPlaylist;

    private int index;

    private Context context;

    public Adapter_Playlist(ArrayList<Playlist> playlist, Context context){
        this.playlist = playlist;
        this.context=context;
    }



    public ArrayList<Playlist> getPlaylist() {
        return playlist;
    }

    public void setPlaylist(ArrayList<Playlist> playlist) {
        this.playlist = playlist;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolderPlayList onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LinearLayout view = (LinearLayout) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_playlist,viewGroup,false);
        ViewHolderPlayList viewHolderPlayList = new ViewHolderPlayList(view);
        return viewHolderPlayList;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPlayList viewHolderPlayList, int i) {
        selectedPlaylist = playlist.get(index);
        Picasso.get().load(selectedPlaylist.getPicture_small()).into((ImageView) viewHolderPlayList.root.findViewById(R.id.item_playlist_image));
        ((TextView) viewHolderPlayList.root.findViewById(R.id.item_playlist_name)).setText(selectedPlaylist.getTitle());
        ((TextView) viewHolderPlayList.root.findViewById(R.id.item_playlist_owner)).setText(selectedPlaylist.getUser().getName());
        ((TextView) viewHolderPlayList.root.findViewById(R.id.item_playlist_numberSongs)).setText(selectedPlaylist.getNb_tracks()+"");
        index=i;
        viewHolderPlayList.root.setOnClickListener(v -> {
            Intent in = new Intent(viewHolderPlayList.root.getContext(), PlayListActivity.class);
            in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            in.putExtra("Playlist_id", selectedPlaylist.getId());
            context.startActivity(in);
        });

    }

    @Override
    public int getItemCount() {
        return playlist.size();
    }

    public static class ViewHolderPlayList extends RecyclerView.ViewHolder {

        public LinearLayout root;

        public ViewHolderPlayList(LinearLayout v) {
            super(v);
            root=v;
        }
    }
}
