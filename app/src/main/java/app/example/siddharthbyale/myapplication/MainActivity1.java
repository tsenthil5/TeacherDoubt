package app.example.siddharthbyale.myapplication;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity1 extends AppCompatActivity {
    EditText sub1,sub2,sub3;
    private Button mFirebaseBtn;
    DatabaseReference reference;
    String day;
    String cls;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        day = getIntent().getStringExtra("day");
        Toast.makeText(this, getIntent().getStringExtra("day")+"",Toast.LENGTH_SHORT).show();
        sub1 = (EditText) findViewById(R.id.plain_text_input_1);
        sub2 = (EditText) findViewById(R.id.plain_text_input_2);
        sub3 = (EditText) findViewById(R.id.plain_text_input_3);
        mFirebaseBtn=(Button) findViewById(R.id.submit_button);

        reference = FirebaseDatabase.getInstance().getReference().child("Users").child("Students");
        mFirebaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase.getInstance().getReference().child("TimeTable").child(day).child(cls).child("10-11").child("subject").setValue(sub1.getText().toString());
                FirebaseDatabase.getInstance().getReference().child("TimeTable").child(day).child(cls).child("11-12").child("subject").setValue(sub2.getText().toString());
                FirebaseDatabase.getInstance().getReference().child("TimeTable").child(day).child(cls).child("12-1").child("subject").setValue(sub3.getText().toString());

            }
        });
        reference.child("dqzl13Gsv9VgKwVbHIX8N4YtOgU2").child("class").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                cls = dataSnapshot.getValue(String.class);

                FirebaseDatabase.getInstance().getReference().child("TimeTable")
                        .child(day).child(cls).addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        if (dataSnapshot.getKey().equals("10-11")) {
                            sub1.setText(dataSnapshot.child("subject").getValue(String.class));
                        } else if (dataSnapshot.getKey().equals("11-12")) {
                            sub2.setText(dataSnapshot.child("subject").getValue(String.class));
                        } else if (dataSnapshot.getKey().equals("12-1")) {
                            sub3.setText(dataSnapshot.child("subject").getValue(String.class));
                        }
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//                        if(s.equals("10-11")) {
//                            sub1.setText();
//                        }
                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
