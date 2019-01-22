package ng.org.ddhub.kanoblooddonation;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class DonateBlood extends AppCompatActivity {

    TextView tvDate;
    Button btnDate;
    TextView tvBirth;
    Button btnBirth;
    Calendar c;
    Calendar ca;
    DatePickerDialog Dpdialog;
    DatePickerDialog dp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate_blood);


        tvDate = (TextView) findViewById(R.id.tvDate);
        btnDate = (Button) findViewById(R.id.btnDate);
        tvBirth = (TextView) findViewById(R.id.tvBirth);
        btnBirth = (Button) findViewById(R.id.btnBirth);


        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                c = Calendar.getInstance();
                int day = c.get(Calendar.DAY_OF_MONTH);
                int month = c.get(Calendar.MONTH);
                int year = c.get(Calendar.YEAR);

                // btnBirth.setOnClickListener(new View.OnClickListener() {
                //  @Override
                //  public void onClick(View view) {

                //  ca=Calendar.getInstance();
                //  int day = ca.get(Calendar.DAY_OF_MONTH);
                //  int month = ca.get(Calendar.MONTH);
                //  int year = ca.get(Calendar.YEAR);

                dp = new DatePickerDialog(DonateBlood.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int xYear, int xMonth, int xDay) {

                        tvDate.setText(xDay + "/" + (xMonth + 1) + "/" + xYear);

                    }
                }, year, month, day);
                dp.show();

                btnBirth.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        ca = Calendar.getInstance();
                        int day = ca.get(Calendar.DAY_OF_MONTH);
                        int month = ca.get(Calendar.MONTH);
                        int year = ca.get(Calendar.YEAR);


                        Dpdialog = new DatePickerDialog(DonateBlood.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int nYear, int nMonth, int nDay) {

                                tvBirth.setText(nDay + "/" + (nMonth + 1) + "/" + nYear);

                            }
                        }, year, month, day);
                        Dpdialog.show();


                    }

                    ;


                });
            }
        });
    }}