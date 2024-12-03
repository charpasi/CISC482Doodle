package com.example.cisc482doodle;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private DoodleView doodleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        doodleView = findViewById(R.id.doodleView);
        ImageButton clearButton = findViewById(R.id.clearbutton);
        clearButton.setOnClickListener(view -> {
            doodleView.clearCanvas();
        });

        SeekBar brushSizer = findViewById(R.id.seekBar5);
        brushSizer.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                doodleView.brushSize(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        SeekBar brushOpacity = findViewById(R.id.seekBar6);
        brushOpacity.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                doodleView.brushOpacity(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        Button redButton = findViewById(R.id.colorRed);
        Button greenButton = findViewById(R.id.colorGreen);
        Button blueButton = findViewById(R.id.colorBlue);
        Button blackButton = findViewById(R.id.colorBlack);

        redButton.setOnClickListener(view -> doodleView.changeColor(Color.RED));
        greenButton.setOnClickListener(view -> doodleView.changeColor(Color.GREEN));
        blueButton.setOnClickListener(view -> doodleView.changeColor(Color.BLUE));
        blackButton.setOnClickListener(view -> doodleView.changeColor(Color.BLACK));

    }
}