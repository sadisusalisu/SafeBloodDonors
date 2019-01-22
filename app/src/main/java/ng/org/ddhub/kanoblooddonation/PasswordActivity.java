package ng.org.ddhub.kanoblooddonation;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class PasswordActivity extends AppCompatActivity {

    private EditText PasswordEmail;
    private Button  ResetPassword;
    private  FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        PasswordEmail = (EditText)findViewById(R.id.etPasswordEmail);
        ResetPassword = (Button)findViewById(R.id.btnPasswordReset);
        firebaseAuth = FirebaseAuth.getInstance();

        ResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String useremail= PasswordEmail.getText().toString().trim();

                if (useremail.equals("")){
                    Toast.makeText(PasswordActivity.this, "Please Enter your registered email ID", Toast.LENGTH_SHORT).show();

                }else {
                    firebaseAuth.sendPasswordResetEmail(useremail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(PasswordActivity.this,"Password reset email has been sent!",Toast.LENGTH_LONG).show();
                                finish();
                                startActivity(new Intent(PasswordActivity.this,Login.class));
                            }else {
                                Toast.makeText(PasswordActivity.this,"Error in sending password reset email!",Toast.LENGTH_LONG).show();

                            }
                        }
                    });
                }


            }
        });

    }
}
