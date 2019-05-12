package icesi.johann.REST_Service;

import android.util.Log;

import java.io.IOException;

public class Service_REST_Manager {


    public static final String URL_SEARCH_PLAYLIST = "https://api.deezer.com/search/playlist?q=";
    public static final String URL_PLAYLIST = "https://api.deezer.com/playlist/";
    public static final String URL_TRACK= "https://api.deezer.com/track/";

    public static class SimpleGET {
        OnResponseListener listener;

        public SimpleGET(OnResponseListener listener) {
            this.listener = listener;
            HTTPSWebUtil util = new HTTPSWebUtil();
            try {
                String response = util.GETrequest(URL_PLAYLIST);
                listener.onResponse(response);
            } catch (IOException e) {
                Log.e(">>>>>>>>>>>", "Exception SimpleGet");
            }
        }

        public interface OnResponseListener {
            void onResponse(String response);
        }
    }

    public static class GET_Search_For_Playlist {
        OnResponseListener listener;

        public GET_Search_For_Playlist(String search, OnResponseListener listener) {
            this.listener = listener;
            HTTPSWebUtil util = new HTTPSWebUtil();
            try {
                String response = util.GETrequest(URL_SEARCH_PLAYLIST + search);
                listener.onResponse(response);
            } catch (IOException e) {
                Log.e(">>>>>>>>>>>", "Exception GET_Search_For_PlayList");
            }
        }

        public interface OnResponseListener {
            void onResponse(String response);
        }
    }

    public static class GET_Playlist {
        OnResponseListener listener;

        public GET_Playlist(String id, OnResponseListener listener) {
            this.listener = listener;
            HTTPSWebUtil util = new HTTPSWebUtil();
            try {
                String response = util.GETrequest(URL_PLAYLIST + id);
                listener.onResponse(response);
            } catch (IOException e) {
                Log.e(">>>>>>>>>>>", "Exception GET_PlayList");
            }
        }

        public interface OnResponseListener {
            void onResponse(String response);
        }
    }

    public static class GET_Track {
        OnResponseListener listener;

        public GET_Track(String id, OnResponseListener listener) {
            this.listener = listener;
            HTTPSWebUtil util = new HTTPSWebUtil();
            try {
                String response = util.GETrequest(URL_TRACK + id);
                listener.onResponse(response);
            } catch (IOException e) {
                Log.e(">>>>>>>>>>>", "Exception GET_Track");
            }
        }

        public interface OnResponseListener {
            void onResponse(String response);
        }
    }

}
