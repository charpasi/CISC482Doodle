package com.example.cisc482doodle;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private DoodleView doodleView;
    private int interpolateColor(int startColor, int middleColor, int endColor, float ratio) {
        if (ratio < 0.5f) {
            return blendColors(startColor, middleColor, ratio * 2);
        } else {
            return blendColors(middleColor, endColor, (ratio - 0.5f) * 2);
        }
    }

    private int blendColors(int color1, int color2, float ratio) {
        int alpha = (int) (Color.alpha(color1) * (1 - ratio) + Color.alpha(color2) * ratio);
        int red = (int) (Color.red(color1) * (1 - ratio) + Color.red(color2) * ratio);
        int green = (int) (Color.green(color1) * (1 - ratio) + Color.green(color2) * ratio);
        int blue = (int) (Color.blue(color1) * (1 - ratio) + Color.blue(color2) * ratio);

        return Color.argb(alpha, red, green, blue);
    }

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
        ImageButton clearButton = findViewById(R.id.clearButton);
        clearButton.setOnClickListener(view -> {
            doodleView.clearCanvas();
        });

        SeekBar brushSizer = findViewById(R.id.seekBarBrushSize);
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

        SeekBar brushOpacity = findViewById(R.id.seekBarOpacity);
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


        SeekBar colorSlider = findViewById(R.id.colorSlider);
        colorSlider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float ratio = progress / (float) seekBar.getMax();
                int color = interpolateColor(Color.RED, Color.GREEN, Color.BLUE, ratio);
                doodleView.changeColor(color);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

    }
}