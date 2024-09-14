package com.example.buoi3_thhuy;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class register extends AppCompatActivity {
    private EditText hoten;
    private EditText username;
    private EditText password;
    private EditText password2;
    private Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        hoten = findViewById(R.id.hoten);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        password2 = findViewById(R.id.password2);
        login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String ten = hoten.getText().toString();
                String un = username.getText().toString();
                String pass = password.getText().toString();
                String pass2 = password2.getText().toString();

                AlertDialog.Builder builder = new AlertDialog.Builder(register.this);
                builder.setTitle("Chúc mừng bạn đăng ký thành công")
                        .setMessage("Chúc mừng bạn: \nTên: " + ten + "\nMã sinh viên: " + un +" đã đăng ký thành công")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Đóng hộp thoại khi nhấn OK
                                dialog.dismiss();
                            }
                        });
                // Hiển thị hộp thoại
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
}