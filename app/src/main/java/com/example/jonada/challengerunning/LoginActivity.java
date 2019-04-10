package com.example.jonada.challengerunning;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jonada.challengerunning.LocalDB.DatabaseHelper;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{




    EditText mTextUsername;
    EditText mTextPassword;
    Button mButtonLogin;
    TextView mTextViewRegister;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        databaseHelper = new DatabaseHelper(this);
        InitView();

        mTextViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(getApplicationContext(), RegistrationActivity.class);
                startActivity(registerIntent);
            }
        });
    }

    private void InitView()
    {
        mTextUsername = (EditText)findViewById(R.id.edittext_username);
        mTextPassword = (EditText)findViewById(R.id.edittext_password);
        mButtonLogin = (Button)findViewById(R.id.button_login);
        mButtonLogin.setOnClickListener(this);

        mTextViewRegister = (TextView)findViewById(R.id.text_register);

    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.button_login:

            LoginCheck();
            break;
        }

    }

    private void Reset()
    {
        mTextUsername.setText("");
        mTextPassword.setText("");
    }

    private void LoginCheck()
    {
        if(mTextUsername.getText().toString().equals(""))
        {
            mTextUsername.requestFocus();
            mTextUsername.setError("Field Empty");
        }
        else if(mTextPassword.getText().toString().equals(""))
        {
            mTextPassword.requestFocus();
            mTextPassword.setError("Field Empty");
        }
        else
        {
            if (databaseHelper.checkUser(mTextUsername.getText().toString().trim()
                    ,mTextPassword.getText().toString().trim())) {

                Intent accountsIntent = new Intent(getApplicationContext(), HomeActivity.class);
                Reset();
                startActivity(accountsIntent);


            } else {
                // Snack Bar to show success message that record is wrong
                Toast.makeText(getApplicationContext(),"Invalid username or password",Toast.LENGTH_SHORT).show();
            }
        }

    }
}
