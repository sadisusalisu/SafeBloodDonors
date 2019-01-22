package ng.org.ddhub.kanoblooddonation;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class GetBlood extends AppCompatActivity {

    TextView textView;
    Button btn;
    Calendar c;
    DatePickerDialog dp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_blood);

        textView = (TextView)findViewById(R.id.textView);
        btn = (Button)findViewById(R.id.btnPick);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                c=Calendar.getInstance();
                int day = c.get(Calendar.DAY_OF_MONTH);
                int month = c.get(Calendar.MONTH);
                int year = c.get(Calendar.YEAR);

                dp = new DatePickerDialog(GetBlood.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int xYear, int xMonth, int xDay) {

                        textView.setText(xDay + "/" + (xMonth +1) + "/" + xYear);

                    }},year,month, day);
                dp.show();
            };




        });
}}
