package tripathi.aditya.commonlogger;

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
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {

    EditText username,password;
    TextView register;
    Button login;
    private FirebaseAuth gg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);
        login=(Button)findViewById(R.id.login);
        register=(TextView)findViewById(R.id.register);

        gg=gg.getInstance();

        FirebaseUser user = gg.getCurrentUser();

        if(user!=null){
            finish();
            startActivity(new Intent(login.this,welcome.class));
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailID= username.getText().toString();
                String pwd=password.getText().toString();

                if(emailID.isEmpty()||pwd.isEmpty()){
                    Toast.makeText(login.this, "Enter in all fields", Toast.LENGTH_SHORT).show();

                }else {
                    gg.signInWithEmailAndPassword(emailID,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                FirebaseUser user = gg.getCurrentUser();
                                startActivity(new Intent(login.this, welcome.class));
                            }else{
                                FirebaseAuthException e = (FirebaseAuthException )task.getException();
                                Toast.makeText(login.this, "Login failed"+e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

       register.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               finish();
               startActivity(new Intent(login.this,MainActivity.class));
           }
       });
    }
}
