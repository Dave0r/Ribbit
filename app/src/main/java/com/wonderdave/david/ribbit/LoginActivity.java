package com.wonderdave.david.ribbit;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


public class LoginActivity extends Activity {

    protected TextView mSignUpTextView;
    protected TextView mForgottenPassword;
    protected EditText mUsername;
    protected EditText mPassword;
    protected Button mloginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_login);

        mSignUpTextView = (TextView)findViewById(R.id.signUpText);
        mSignUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
        mForgottenPassword = (TextView)findViewById(R.id.ForgotText);
        mForgottenPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, ForgottenActivity.class);
                startActivity(intent);
            }
        });
        mUsername = (EditText)findViewById(R.id.userNameField);
        mPassword = (EditText)findViewById(R.id.passwordField);
        mloginButton = (Button)findViewById(R.id.loginButton);
        mloginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = mUsername.getText().toString();
                String password = mPassword.getText().toString();

                username = username.trim();
                password = password.trim();


                if (username.isEmpty() || password.isEmpty()){
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                    builder.setMessage(R.string.login_error_message)
                            .setTitle(R.string.login_error_title)
                            .setPositiveButton(android.R.string.ok, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else {
                    setProgressBarIndeterminateVisibility(true);
                  ParseUser.logInInBackground(username, password, new LogInCallback() {
                      @Override
                      public void done(ParseUser user, ParseException e) {
                          setProgressBarIndeterminateVisibility(false);
                          if (e == null) {
                            //login
                              Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                              intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                              intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                              startActivity(intent);
                          } else {
                              AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                              builder.setMessage(e.getMessage())
                                      .setTitle(R.string.login_error_title)
                                      .setPositiveButton(android.R.string.ok, null);
                              AlertDialog dialog = builder.create();
                              dialog.show();
                          }
                          }

                  });
                }

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
