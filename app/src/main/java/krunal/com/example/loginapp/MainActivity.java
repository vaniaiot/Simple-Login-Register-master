package krunal.com.example.loginapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private Button mSign,mRegister;
    private EditText mEmail,mPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSign = findViewById(R.id.sign_id);
        mRegister = findViewById(R.id.register_id);

        mEmail = findViewById(R.id.email_id);
        mPassword = findViewById(R.id.password_id);

        mSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validation()){
                    if (!TextUtils.isEmpty(mEmail.getText().toString().trim()) && !TextUtils.isEmpty(mPassword.getText().toString().trim())){
                        Toast.makeText(getApplication(),"It is Valid",Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(getApplication(),"Enter Valid Email and Password!",Toast.LENGTH_LONG).show();
                    }

                }else {
                    Toast.makeText(getApplication(),"Enter Valid Email",Toast.LENGTH_LONG).show();
                }
            }
        });

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent= new Intent(getApplication(),SignUpActivity.class);
                startActivity(intent);

            }
        });


    }


    private boolean validation(){
        String validemail = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +

                "\\@" +

                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +

                "(" +

                "\\." +

                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +

                ")+";

        String email = mEmail.getText().toString();

        Matcher matcher = Pattern.compile(validemail).matcher(email);

        if (matcher.matches()){
            return true;
        }else {
            return false;
        }


    }
}
