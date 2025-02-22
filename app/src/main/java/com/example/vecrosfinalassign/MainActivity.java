package com.example.vecrosfinalassign;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.SurfaceView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vecrosfinalassign.databinding.ActivityMainBinding;

import org.videolan.libvlc.LibVLC;
import org.videolan.libvlc.Media;
import org.videolan.libvlc.MediaPlayer;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private LibVLC libVLC;
    private MediaPlayer mediaPlayer;
    private EditText rtspUrlInput;
    private SurfaceView videoView;
    private Button playButton, pauseButton, stopButton;
    private boolean isPlaying = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        // Initialize VLC
        ArrayList<String> options = new ArrayList<>();
        options.add("--aout=opensles");
        options.add("--audio-time-stretch");
        options.add("-vvv");
        libVLC = new LibVLC(this, options);

        // Bind UI elements
        rtspUrlInput = findViewById(R.id.rtspUrlInput);
        videoView = findViewById(R.id.videoView);
        playButton = findViewById(R.id.playButton);
        pauseButton = findViewById(R.id.pauseButton);
        stopButton = findViewById(R.id.stopButton);

        // Initialize MediaPlayer
        mediaPlayer = new MediaPlayer(libVLC);

        playButton.setOnClickListener(v -> playStream());
        pauseButton.setOnClickListener(v -> pauseStream());
        stopButton.setOnClickListener(v -> stopStream());
    }

    private void playStream() {
        String url = rtspUrlInput.getText().toString().trim();
        if (url.isEmpty()) {
            Toast.makeText(this, "Please enter a valid RTSP URL", Toast.LENGTH_SHORT).show();
            return;
        }

        if (isPlaying) {
            Toast.makeText(this, "Stream is already playing", Toast.LENGTH_SHORT).show();
            return;
        }

        Media media = new Media(libVLC, Uri.parse(url));
        media.addOption("--aout=opensles");
        media.addOption("--audio-time-stretch");
        media.addOption("-vvv");

        mediaPlayer.setMedia(media);
        mediaPlayer.getVLCVout().setVideoSurface(videoView.getHolder().getSurface(), videoView.getHolder());
        mediaPlayer.getVLCVout().setWindowSize(videoView.getWidth(), videoView.getHeight());
        mediaPlayer.getVLCVout().attachViews();
        mediaPlayer.play();

        isPlaying = true;
        Toast.makeText(this, "Streaming started", Toast.LENGTH_SHORT).show();
    }

    private void pauseStream() {
        if (isPlaying) {
            mediaPlayer.pause();
            Toast.makeText(this, "Stream paused", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No stream to pause", Toast.LENGTH_SHORT).show();
        }
    }

    private void stopStream() {
        if (isPlaying) {
            mediaPlayer.stop();
            mediaPlayer.getVLCVout().detachViews();
            isPlaying = false;
            Toast.makeText(this, "Stream stopped", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No stream to stop", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
        if (libVLC != null) {
            libVLC.release();
        }
    }
}