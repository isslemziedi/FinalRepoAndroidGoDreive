/*
package com.example.godrivetn.view.auth;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.godrivetn.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private EditText signupEmail;
    private EditText signupPassword;
    private EditText confirmPassword;

    private Button signup_btn;
    private TextView logInText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.sign_up), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        auth = FirebaseAuth.getInstance();
        signupEmail = findViewById(R.id.emailEditText);
        signupPassword = findViewById(R.id.passwordEditText);
        confirmPassword = findViewById(R.id.confirm_passwordEditText);

        signup_btn = findViewById(R.id.signupButton);
        logInText = findViewById(R.id.logInTextView);


        // Set up password visibility toggle for password field
        setupPasswordVisibilityToggle(signupPassword);
        // Set up password visibility toggle for confirm password field
        setupPasswordVisibilityToggle(confirmPassword);


        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Reset previous errors
                signupEmail.setError(null);
                signupPassword.setError(null);
                confirmPassword.setError(null);

                String user = signupEmail.getText().toString().trim();
                String pwd = signupPassword.getText().toString().trim();
                String confirmPwd = confirmPassword.getText().toString().trim();



                signup_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Reset previous errors
                        signupEmail.setError(null);
                        signupPassword.setError(null);
                        confirmPassword.setError(null);

                        String user = signupEmail.getText().toString().trim();
                        String pwd = signupPassword.getText().toString().trim();
                        String confirmPwd = confirmPassword.getText().toString().trim();

                        // Validate inputs
                        boolean isValid = true;

                        if(user.isEmpty()){
                            signupEmail.setError("Email cannot be empty");
                            isValid = false;
                        }

                        if (pwd.isEmpty()){
                            signupPassword.setError("Password cannot be empty");
                            isValid = false;
                        }

                        if (confirmPwd.isEmpty()){
                            confirmPassword.setError("Confirm Password cannot be empty");
                            isValid = false;
                        }

                        // Check if passwords match
                        if (!pwd.equals(confirmPwd)){
                            confirmPassword.setError("Passwords do not match");
                            isValid = false;
                        }

                        // Only proceed with signup if all validations pass
                        if (isValid){
                            auth.createUserWithEmailAndPassword(user,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(SignUpActivity.this,"SignUp Successful",Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                                        finish();
                                    }else{
                                        Toast.makeText(SignUpActivity.this,"SignUp Failed: "+ task.getException().getMessage(),Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }
                    }
                });


            }
        });


        logInText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void setupPasswordVisibilityToggle(final EditText passwordEditText) {
        passwordEditText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2;

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (passwordEditText.getRight() - passwordEditText.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // Toggle password visibility
                        int inputType = passwordEditText.getInputType();
                        if (inputType == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                            // Currently visible, make it hidden
                            passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                            passwordEditText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.eye_icon, 0);
                        } else {
                            // Currently hidden, make it visible
                            passwordEditText.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                            passwordEditText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.eye_open_icon, 0);
                        }

                        // Move cursor to the end of the text
                        passwordEditText.setSelection(passwordEditText.getText().length());

                        return true;
                    }
                }
                return false;
            }
        });
    }
}
*/

package com.example.godrivetn.view.auth;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.godrivetn.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private EditText signupEmail;
    private EditText signupPassword;
    private EditText confirmPassword;

    private Button signup_btn;
    private TextView logInText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.sign_up), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        auth = FirebaseAuth.getInstance();
        signupEmail = findViewById(R.id.emailEditText);
        signupPassword = findViewById(R.id.passwordEditText);
        confirmPassword = findViewById(R.id.confirm_passwordEditText);

        signup_btn = findViewById(R.id.signupButton);
        logInText = findViewById(R.id.logInTextView);


        // Set up password visibility toggle for password field
        setupPasswordVisibilityToggle(signupPassword);
        // Set up password visibility toggle for confirm password field
        setupPasswordVisibilityToggle(confirmPassword);


        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Reset previous errors
                signupEmail.setError(null);
                signupPassword.setError(null);
                confirmPassword.setError(null);

                String user = signupEmail.getText().toString().trim();
                String pwd = signupPassword.getText().toString().trim();
                String confirmPwd = confirmPassword.getText().toString().trim();



                signup_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Reset previous errors
                        signupEmail.setError(null);
                        signupPassword.setError(null);
                        confirmPassword.setError(null);

                        String user = signupEmail.getText().toString().trim();
                        String pwd = signupPassword.getText().toString().trim();
                        String confirmPwd = confirmPassword.getText().toString().trim();

                        // Validate inputs
                        boolean isValid = true;

                        if(user.isEmpty()){
                            signupEmail.setError("Email cannot be empty");
                            isValid = false;
                        }

                        if (pwd.isEmpty()){
                            signupPassword.setError("Password cannot be empty");
                            isValid = false;
                        }

                        if (confirmPwd.isEmpty()){
                            confirmPassword.setError("Confirm Password cannot be empty");
                            isValid = false;
                        }

                        // Check if passwords match
                        if (!pwd.equals(confirmPwd)){
                            confirmPassword.setError("Passwords do not match");
                            isValid = false;
                        }

                        // Only proceed with signup if all validations pass
                        if (isValid){
                            auth.createUserWithEmailAndPassword(user,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(SignUpActivity.this,"SignUp Successful",Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                                        finish();
                                    }else{
                                        Toast.makeText(SignUpActivity.this,"SignUp Failed: "+ task.getException().getMessage(),Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }
                    }
                });


            }
        });


        logInText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void setupPasswordVisibilityToggle(final EditText passwordEditText) {
        passwordEditText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2;

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (passwordEditText.getRight() - passwordEditText.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // Toggle password visibility
                        int inputType = passwordEditText.getInputType();
                        if (inputType == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                            // Currently visible, make it hidden
                            passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                            passwordEditText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.eye_icon, 0);
                        } else {
                            // Currently hidden, make it visible
                            passwordEditText.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                            passwordEditText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.eye_open_icon, 0);
                        }

                        // Move cursor to the end of the text
                        passwordEditText.setSelection(passwordEditText.getText().length());

                        return true;
                    }
                }
                return false;
            }
        });
    }
}
