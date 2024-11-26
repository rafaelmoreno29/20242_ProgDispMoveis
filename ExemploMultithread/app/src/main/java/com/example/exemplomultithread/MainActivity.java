package com.example.exemplomultithread;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button btn;
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btn = (Button)findViewById(R.id.buttonExecutar);
        txt = (TextView)findViewById(R.id.textView);

       btn.setOnClickListener(v -> {
           btn.setEnabled(false);
           new Thread(() -> {
               for(int i =0; i <= 10; i++){
                   final int x = i;
                   runOnUiThread(() -> {
                       txt.setText("" + x);
                   });
                   try {
                       Thread.sleep(500);
                   } catch (InterruptedException e) {
                       throw new RuntimeException(e);
                   }
               }
               runOnUiThread(() -> {
                   btn.setEnabled(true);
               });
           }).start();
       });




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}