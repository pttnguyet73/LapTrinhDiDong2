package com.example.messageapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CONTACTS_ASK_PERMISSIONS = 1001;
    private static final int REQUEST_SMS_ASK_PERMISSIONS = 1002;
    private static final int REQUEST_CALL_LOG_ASK_PERMISSIONS = 1003; // Thêm mã yêu cầu quyền gọi
    Button btn1, btn2, btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        AnhXa();

        addEvents();
    }

    private void addEvents() {
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                XuLyMoManHinhDanhBa();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                XuLyMoManHinhTinNhan();
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                XuLyMoManHinhLichSuCuocGoi(); // Gọi hàm mới
            }
        });
    }

    private void XuLyMoManHinhTinNhan() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.READ_SMS"}, REQUEST_SMS_ASK_PERMISSIONS);
        } else {
            Intent intent = new Intent(MainActivity.this, DocTinNhan.class);
            startActivity(intent);
        }
    }

    private void XuLyMoManHinhDanhBa() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.READ_CONTACTS"}, REQUEST_CONTACTS_ASK_PERMISSIONS);
        } else {
            Intent intent = new Intent(MainActivity.this, DanhBa.class);
            startActivity(intent);
        }
    }

    private void XuLyMoManHinhLichSuCuocGoi() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.READ_CALL_LOG"}, REQUEST_CALL_LOG_ASK_PERMISSIONS);
        } else {
            Intent intent = new Intent(MainActivity.this, LichSuCuocGoi.class);
            startActivity(intent);
        }
    }

    private void AnhXa() {
        btn1 = findViewById(R.id.btn_1);
        btn2 = findViewById(R.id.btn_2);
        btn3 = findViewById(R.id.btn_3);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CONTACTS_ASK_PERMISSIONS:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    XuLyMoManHinhDanhBa();
                }
                break;
            case REQUEST_SMS_ASK_PERMISSIONS:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    XuLyMoManHinhTinNhan();
                }
                break;
            case REQUEST_CALL_LOG_ASK_PERMISSIONS:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    XuLyMoManHinhLichSuCuocGoi();
                }
                break;
            default:
                break;
        }
    }
}
