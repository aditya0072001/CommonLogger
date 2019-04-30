package tripathi.aditya.commonlogger;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class MainActivity extends AppCompatActivity {

    EditText email,password;
    Button register;
    private FirebaseAuth firebaseAuth;
    TextView lgn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email=(EditText) findViewById(R.id.email);
        password=(EditText) findViewById(R.id.password);
        register=(Button)findViewById(R.id.register);
        lgn=(TextView)findViewById(R.id.lgn);

        firebaseAuth=firebaseAuth.getInstance();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailID=email.getText().toString();
                String pwd=password.getText().toString();
                if(emailID.isEmpty()||pwd.isEmpty()){
                    Toast.makeText(MainActivity.this,"Please fill all spaces",Toast.LENGTH_SHORT).show();
                }else{
                    firebaseAuth.createUserWithEmailAndPassword(emailID,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(MainActivity.this, welcome.class));
                            }else{
                                FirebaseAuthException e = (FirebaseAuthException )task.getException();
                                Toast.makeText(MainActivity.this,"Registration Failed"+e.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }


            }


        });

        lgn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,login.class));
            }
        });
    }
}
