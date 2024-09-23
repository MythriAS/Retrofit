package com.example.retrofitpratice;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    EditText ed1, ed2, ed3;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ed1 = findViewById(R.id.edt_first_name);
        ed2 = findViewById(R.id.edt_second_name);
        ed3 = findViewById(R.id.edt_email);
        btn = findViewById(R.id.btn_sign_up);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveUser(creatUserRequest());
            }
        });
    }

    public UserRequest creatUserRequest() {
        UserRequest userRequest = new UserRequest();
        userRequest.setFirst_name(ed1.getText().toString());
        userRequest.setUserName(ed2.getText().toString());
        userRequest.setEmail(ed3.getText().toString());
        return userRequest;
    }

    public void saveUser(UserRequest userRequest) {
        Call<UserResponse> responseCall = ApiClient.getUserService().saveUser(userRequest);
        responseCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(MainActivity.this,"Success",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(MainActivity.this,"request failed",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this,"request failed",Toast.LENGTH_LONG).show();
            }
        });
    }
}