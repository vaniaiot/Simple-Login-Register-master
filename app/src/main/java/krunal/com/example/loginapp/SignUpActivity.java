package krunal.com.example.loginapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Set;

public class SignUpActivity extends AppCompatActivity {


    public static final int GENDER_UNKNOWN = 0;
    public static final int GENDER_MALE = 1;
    public static final int GENDER_FEMALE = 2;
    private int GENDER = GENDER_UNKNOWN;

    private Spinner mGender;
    private EditText mUsername_Edit,mEmail_Edit,mPassword_Edit,mRe_Password;
    private Button mSignUp;

    private String mpasswordInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mUsername_Edit = findViewById(R.id.UserName);
        mEmail_Edit = findViewById(R.id.Email);
        mPassword_Edit = findViewById(R.id.Password);
        mSignUp = findViewById(R.id.btn);
        mRe_Password = findViewById(R.id.Re_Password);
        mGender = findViewById(R.id.spinner_gender);

        SetupSpinner();

        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!validateEmail() | !validatePassword() | !validateUsername() | !validateRePassword()){

                    Toast.makeText(getApplication(),"Enter Worry!",Toast.LENGTH_LONG).show();
                }else {
                    String input =  "Email: " + mEmail_Edit.getText().toString();
                    input += "\n";
                    input += "Username: " + mUsername_Edit.getText().toString();
                    input += "\n";
                    input += "Password: " + mPassword_Edit.getText().toString();
                    input += "\n";
                    input += "RePassword: " + mRe_Password.getText().toString();
                    input += "\n";
                    input += "GENDER: " + GENDER;

                    Toast.makeText(getApplication(),input,Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private void SetupSpinner() {
        ArrayAdapter genderSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_gender_options, android.R.layout.simple_spinner_item);

        genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        mGender.setAdapter(genderSpinnerAdapter);
        mGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)){
                    if (selection.equals(getString(R.string.gender_male))){
                        GENDER = GENDER_MALE;
                    }else if (selection.equals(getString(R.string.gender_female))){
                        GENDER = GENDER_FEMALE;
                    }else {
                        GENDER = GENDER_UNKNOWN;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                GENDER = GENDER_UNKNOWN;
            }
        });

    }

    private boolean validateEmail() {
        String emailInput = mEmail_Edit.getText().toString().trim();

        if (emailInput.isEmpty()) {
            mEmail_Edit.setError("Field can't be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            mEmail_Edit.setError("Please enter a valid email address");
            return false;
        } else {
            mEmail_Edit.setError(null);
            return true;
        }
    }

    private boolean validateUsername() {
        String usernameInput = mUsername_Edit.getText().toString().trim();

        if (usernameInput.isEmpty()) {
            mUsername_Edit.setError("Field can't be empty");
            return false;
        } else if (usernameInput.length() > 15) {
            mUsername_Edit.setError("Username too long");
            return false;
        } else {
            mUsername_Edit.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        mpasswordInput = mPassword_Edit.getText().toString().trim();

        if (mpasswordInput.isEmpty()) {
            mPassword_Edit.setError("Field can't be empty");
            return false;
        }else if (mpasswordInput.length() < 4 || mpasswordInput.length() > 10){
            mPassword_Edit.setError("between 4 and 10 alphanumeric characters");
            return false;
        }else {
            mPassword_Edit.setError(null);
            return true;
        }
    }

    private boolean validateRePassword() {
        String re_passwordInput = mRe_Password.getText().toString().trim();

        if (re_passwordInput.isEmpty()) {
            mRe_Password.setError("Field can't be empty!");
            return false;
        }else if (re_passwordInput.length() < 4 || re_passwordInput.length() > 10){
            mRe_Password.setError("between 4 and 10 alphanumeric characters");
            return false;
        }else if (!re_passwordInput.equals(mpasswordInput)){
            mRe_Password.setError("Passowrd Not Match!");
            return false;
        } else {
            mRe_Password.setError(null);
            return true;
        }
    }

}
