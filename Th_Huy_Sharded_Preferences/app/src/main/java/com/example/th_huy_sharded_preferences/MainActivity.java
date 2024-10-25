package com.example.th_huy_sharded_preferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText username, msv;
    CheckBox rememberPass;
    Button login;
    TextView tv;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Anhxa();
        sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);

        username.setText(sharedPreferences.getString("tensv",""));
        msv.setText(sharedPreferences.getString("msv",""));
        rememberPass.setChecked(sharedPreferences.getBoolean("Checked", false));

        login.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                String name = username.getText().toString().trim();
                String pass = msv.getText().toString().trim();

                if (name.equals("Pham Thi Thu Nguyet") && pass.equals("22115053122125")) {
                    Toast.makeText(MainActivity.this, "Lưu thành công", Toast.LENGTH_SHORT).show();
                    //nếu có check
                    if (rememberPass.isChecked()) {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("tensv", name);
                        editor.putString("msv", pass);
                        editor.putBoolean("Checked", true);
                        editor.commit();
                    }
                    else {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove("tensv");
                        editor.remove("msv");
                        editor.remove("Checked");
                        editor.commit();
                    }
                }
                else {
                    Toast.makeText(MainActivity.this, "Lỗi đăng nhập!!", Toast.LENGTH_SHORT).show();
                }
            tv.setText("Tên sinh viên là "+name+ "và mã sinh viên là " + pass);
            }
        });
    }
    private void Anhxa() {
        username = findViewById(R.id.et_username);
        msv = findViewById(R.id.et_msv);
        rememberPass = findViewById(R.id.cb_remeberPass);
        login = findViewById(R.id.btn_login);
        tv = findViewById(R.id.textview1);
    }
}