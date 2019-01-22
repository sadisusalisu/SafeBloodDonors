package ng.org.ddhub.kanoblooddonation;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import static android.transition.Fade.OUT;

public class Welcome extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        boolean postDelayed = new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntend = new Intent(Welcome.this,Login.class);
                startActivity(homeIntend);
                finish();


            }

        },SPLASH_TIME_OUT);
    }
}


