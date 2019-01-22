package ng.org.ddhub.kanoblooddonation;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private int counter = 5;
    private TextView UserRegistration;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private TextView forgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Name = (EditText)findViewById(R.id.etName);
        Password = (EditText) findViewById(R.id.etPassword);
        Info = (TextView) findViewById(R.id.etInfo);
        Login = (Button) findViewById(R.id.btnLogin);
        UserRegistration = (TextView)findViewById(R.id.tvRegister);
        forgotPassword = (TextView)findViewById(R.id.etForgetPassword);

        Info.setText("No of attempts remaining: 5");


        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

        FirebaseUser user = firebaseAuth.getCurrentUser();

        if(user !=null) {
            finish();
            startActivity(new Intent(ng.org.ddhub.kanoblooddonation.Login.this,Main2Activity.class));
        }


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate (Name.getText().toString(),Password.getText().toString());

            }
        });

        UserRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ng.org.ddhub.kanoblooddonation.Login.this,Registration.class));
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ng.org.ddhub.kanoblooddonation.Login.this,PasswordActivity.class));
            }
        });
    }



    private void validate(String userEmail, String userPassword ) {

        progressDialog.setMessage("Please wait a few seconds, we are verifying your details!");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(userEmail,userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    progressDialog.dismiss();

                    checkEmailVerification();
                    //Toast.makeText(ng.org.ddhub.kanoblooddonation.Login.this,"Login Successful", Toast.LENGTH_SHORT).show();

                    //startActivity(new Intent(ng.org.ddhub.kanoblooddonation.Login.this,Main2Activity.class));

                }else{
                    Toast.makeText(ng.org.ddhub.kanoblooddonation.Login.this,"Login Failed", Toast.LENGTH_SHORT).show();
                    counter--;
                    Info.setText("No of attempts remaining: " + counter);
                    progressDialog.dismiss();
                     if (counter == 0){
                         Login.setEnabled(false);
                     }
                }


            }
        });


    }
     private void checkEmailVerification(){
        FirebaseUser firebaseUser = firebaseAuth.getInstance().getCurrentUser();
        Boolean emailflag = firebaseUser.isEmailVerified();


         startActivity(new Intent(ng.org.ddhub.kanoblooddonation.Login.this,Main2Activity.class));
//       if (emailflag){
//           finish();
//           startActivity(new Intent(ng.org.ddhub.kanoblooddonation.Login.this,Main2Activity.class));
//       }else {
//          Toast.makeText(this, "Please Verify your Email", Toast.LENGTH_SHORT).show();
//            firebaseAuth.signOut();
//        }
     }


}
