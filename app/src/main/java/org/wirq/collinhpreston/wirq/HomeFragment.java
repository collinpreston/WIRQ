package org.wirq.collinhpreston.wirq;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class HomeFragment extends Fragment{
    private boolean initializeMediaPlayer = true;
    private boolean isMute = true;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        FloatingActionButton fltActBtnCallStation = view.findViewById(R.id.fltActBtnCallStation);
        final Button playPauseBtn = view.findViewById(R.id.btnPlayPauseStream);
        playPauseBtn.setBackgroundResource(R.drawable.ic_play_arrow_black_24dp);
        fltActBtnCallStation.setImageResource(R.drawable.ic_call_black_18dp);
        if (RadioClient.mediaPlayer.isPlaying()) {
            // Use this logic to check if the stream is playing.  Update the view accordingly.
            // Needed for after backing out of the app while the stream is playing.
            playPauseBtn.setBackgroundResource(R.drawable.ic_pause_black_24dp);
            initializeMediaPlayer = false;
            isMute = false;
        }
        playPauseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!RadioClient.mediaPlayer.isPlaying() && initializeMediaPlayer) {
                    // This is where I start the service to stream the station
                    initializeRadio();
                    initializeMediaPlayer = false;
                    isMute = false;
                }
                else {
                    startStopRadio();
                }
                if (isMute) {
                    playPauseBtn.setBackgroundResource(R.drawable.ic_play_arrow_black_24dp);
                }
                else {
                    playPauseBtn.setBackgroundResource(R.drawable.ic_pause_black_24dp);
                }
            }
        });
        fltActBtnCallStation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:5853360740"));
                startActivity(callIntent);
            }
        });

        return view;
    }
    private void initializeRadio() {
        final Intent intent = new Intent(getActivity(), RadioClient.class);
        getActivity().startService(intent);
    }


    /**
     * @Functionality: This function mutes/unmutes and mediaPlayer stream
     * This allows the stream to quickly be played and paused without needing to reconnect
     */
    private void startStopRadio()
    {
        if (isMute) {
            RadioClient.mediaPlayer.setVolume(1, 1);
            isMute = false;
        }
        else {
            RadioClient.mediaPlayer.setVolume(0,0);
            isMute = true;
        }
    }
}

