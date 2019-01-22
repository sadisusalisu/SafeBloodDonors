package ng.org.ddhub.kanoblooddonation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

    private ImageView ProfilePic;
    private TextView ProfileName,ProfilePhone,ProfileEmail,ProfileBloodGroup,ProfileAge,ProfileState;
    private Button ProfileUpdate;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ProfilePic = findViewById(R.id.ivProfilePic);
        ProfileName = findViewById(R.id.tvProfileName);
        ProfilePhone = findViewById(R.id.tvProfilePhone);
        ProfileEmail = findViewById(R.id.tvProfileEmail);
        ProfileBloodGroup = findViewById(R.id.tvProfileBG);
        ProfileAge = findViewById( R.id.tvProfileAge);
        ProfileState = findViewById(R.id.tvProfileState);
        ProfileUpdate = findViewById(R.id.btnProfileUpdate);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getCurrentUser().getUid());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserProfile userProfile = dataSnapshot.getValue(UserProfile.class);
                ProfileName.setText("Name:" + userProfile.getUserName());
                ProfilePhone.setText("Phone:" + userProfile.getUserPhone());
                ProfileEmail.setText("Email:" +userProfile.getUserEmail());
                ProfileBloodGroup.setText("Blood Group:" +userProfile.getUserBLoodGroup());
                ProfileAge.setText("Age:"+ userProfile.getUserAge());
                ProfileState.setText("State:" + userProfile.getUserState());


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(ProfileActivity.this,databaseError.getCode(),Toast.LENGTH_SHORT).show();

            }
        });

    }
}
