package ng.org.ddhub.kanoblooddonation;

import android.content.Intent;
//import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Registration extends AppCompatActivity {
    private EditText UserName, UserPhone, UserEmail, UserPassword, UserBloodGroup, UserAge, UserState;
    private Button regButton;
    private TextView UserLogin;
    private FirebaseAuth firebaseAuth;
  // private ImageView UserProfilePic;
    String name,Phone,Email,Password,BloodGroup,Age,State;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setupUIViews();

        firebaseAuth = FirebaseAuth.getInstance();

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    // upload data to the database

                    String user_email = UserEmail.getText().toString().trim();
                    String user_password = UserPassword.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()){
                               // senEmailVerification();
                                sendUserdata();
                                firebaseAuth.signOut();
                                Toast.makeText(Registration.this,"Successefully Registered,Upload Complete!",Toast.LENGTH_SHORT ).show();
                                finish();
                                startActivity(new Intent(Registration.this,Login.class));


                            }else {
                               Toast.makeText(Registration.this,"Registration Failed",Toast.LENGTH_SHORT ).show();



                            }



                        }
                    });
                }
            }
        });

        UserLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Registration.this, Login.class));
            }
        });
    }

    private void setupUIViews() {
        UserName = (EditText) findViewById(R.id.etUserName);
        UserPhone = (EditText) findViewById(R.id.etUserPhone);
        UserEmail = (EditText) findViewById(R.id.etUserEmail);
        UserPassword = (EditText) findViewById(R.id.etUserPassword);
        UserBloodGroup = (EditText) findViewById(R.id.etUserBG);
        UserAge = (EditText) findViewById(R.id.etUserAge);
        UserState = (EditText) findViewById(R.id.etUserState);
        regButton = (Button) findViewById(R.id.btnReg);
        UserLogin = (TextView) findViewById(R.id.tvUserLogin);
       // UserProfilePic = (ImageView)findViewById(R.id.ivProfilePic);

    }

    private Boolean validate() {
        Boolean result = false;

        name = UserName.getText().toString();
         Phone = UserPhone.getText().toString();
         Email = UserEmail.getText().toString();
        Password = UserPassword.getText().toString();
        BloodGroup = UserBloodGroup.getText().toString();
         Age = UserAge.getText().toString();
         State = UserState.getText().toString();



        if (name.isEmpty() || Phone.isEmpty() || Email.isEmpty() || Password.isEmpty() || BloodGroup.isEmpty() || Age.isEmpty() || State.isEmpty()) {
            Toast.makeText(this, "Please enter all the details", Toast.LENGTH_SHORT).show();

        } else {
            result = true;
        }

        return result;
    }

    private void senEmailVerification() {
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser != null) {
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        sendUserdata();

                       // senEmailVerification();



                       Toast.makeText(Registration.this, "Succcessfully Registered, Verification mail have been sent!", Toast.LENGTH_SHORT).show();
                        firebaseAuth.signOut();
                       finish();
                       startActivity(new Intent(Registration.this, Login.class));
                    } else {
                        Toast.makeText(Registration.this, "Verification mail has'nt been sent!", Toast.LENGTH_SHORT).show();
                    }


                }
            });
        }
    }

        private void sendUserdata(){
           FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
           DatabaseReference myRef = firebaseDatabase.getReference(firebaseAuth.getCurrentUser().getUid());

          UserProfile userProfile = new UserProfile(Age,BloodGroup,Email,name,Phone,State);
           myRef.setValue(userProfile);




    }
   }








