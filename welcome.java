package tripathi.aditya.commonlogger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class welcome extends AppCompatActivity {

    TextView textView;
    Button signout;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        textView =(TextView)findViewById(R.id.textView);
        signout=(Button)findViewById(R.id.signout);
        textView.setText("You are logged in");
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                finish();
                startActivity(new Intent(welcome.this,login.class));
            }
        });
    }
}
