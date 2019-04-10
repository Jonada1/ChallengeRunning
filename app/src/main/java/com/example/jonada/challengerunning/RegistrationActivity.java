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
import com.example.jonada.challengerunning.LocalDB.UserModel;
import com.example.jonada.challengerunning.utils.InputValidation;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener{

    EditText mTextUsername;
    EditText mTextPassword;
    EditText mTextCnfPassword;
    EditText mTextEmail;
    Button mButtonRegister;
    TextView mTextViewLogin;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mTextUsername = (EditText)findViewById(R.id.edittext_username);
        mTextPassword = (EditText)findViewById(R.id.edittext_password);
        mTextCnfPassword = (EditText)findViewById(R.id.edittext_cnf_password);
        mButtonRegister = (Button)findViewById(R.id.button_register);
        mTextViewLogin = (TextView)findViewById(R.id.text_login);

        InitView();

        databaseHelper = new DatabaseHelper(this);

        mTextViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent LoginIntent = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(LoginIntent);
            }
        });

    }

    private void InitView() {
        mTextUsername = (EditText)findViewById(R.id.edittext_username);
        mTextPassword = (EditText)findViewById(R.id.edittext_password);
        mTextCnfPassword = (EditText)findViewById(R.id.edittext_cnf_password);
        mTextEmail =  (EditText)findViewById(R.id.email);

        mButtonRegister = (Button)findViewById(R.id.button_register);
        mButtonRegister.setOnClickListener(this);

        mTextViewLogin = (TextView)findViewById(R.id.text_login);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.button_register:
                RegData();
                break;
        }
    }

    private void RegData()
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
        else if(mTextCnfPassword.getText().toString().equals(""))
        {
            mTextCnfPassword.requestFocus();
            mTextCnfPassword.setError("Field Empty");
        }
        else if(!mTextPassword.getText().toString().equals(mTextCnfPassword.getText().toString()))
        {
            mTextCnfPassword.requestFocus();
            mTextCnfPassword.setError("Both password does not match");
        }
        else if(!mTextEmail.getText().toString().equals("") && InputValidation.isValidEmail(mTextEmail.getText().toString())==false)
        {
            mTextEmail.requestFocus();
            mTextEmail.setError("email@example.com");
        }
        else
        {


            if (!databaseHelper.checkUser(mTextEmail.getText().toString().trim())) {

                UserModel userModel = new UserModel();
                userModel.name = mTextUsername.getText().toString();
                userModel.password = mTextPassword.getText().toString();
                userModel.email = mTextEmail.getText().toString();

                databaseHelper.addUser(userModel);

                Toast.makeText(getApplicationContext(),"Regiestation Successfully Complete",Toast.LENGTH_SHORT).show();
                Reset();


            } else {
                // Snack Bar to show error message that record already exists
                Toast.makeText(getApplicationContext(),"User Already Exist",Toast.LENGTH_SHORT).show();
            }


        }
    }

    private void Reset()
    {
        mTextUsername.setText("");
        mTextPassword.setText("");
        mTextCnfPassword.setText("");
        mTextEmail.setText("");
    }
}
