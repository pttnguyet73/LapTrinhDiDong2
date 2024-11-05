package com.example.messageapp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.messageapp.Model.HistoryCall;
import com.example.messageapp.adapter.AdapterLichSu;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class LichSuCuocGoi extends AppCompatActivity {
    private static final int PERMISSION_REQUEST_READ_CALL_LOG = 1;
    private ListView lvLichSuCuocGoi;
    private ArrayList<HistoryCall> dsLichSuCuocGoi;
    private AdapterLichSu adapterLichSuCuocGoi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_show_call_log);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        addControls();

        // Kiểm tra và yêu cầu quyền READ_CALL_LOG
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CALL_LOG}, PERMISSION_REQUEST_READ_CALL_LOG);
        } else {
            showAllCallHistoryFromDevice();
        }
    }

    private void showAllCallHistoryFromDevice() {
        Uri uri = android.provider.CallLog.Calls.CONTENT_URI; // Sửa lại để gọi đúng đối tượng
        try (Cursor cursor = getContentResolver().query(uri, null, null, null, null)) {
            if (cursor != null && cursor.moveToFirst()) {
                dsLichSuCuocGoi.clear();
                do {
                    // Lấy dữ liệu từ các cột
                    @SuppressLint("Range") String phoneNumber = cursor.getString(cursor.getColumnIndex(android.provider.CallLog.Calls.NUMBER));
                    @SuppressLint("Range") long timestamp = cursor.getLong(cursor.getColumnIndex(android.provider.CallLog.Calls.DATE));

                    // Chuyển đổi thời gian từ long sang chuỗi
                    String callTime = convertTimestampToString(timestamp);

                    // Tạo đối tượng HistoryCall và thêm vào danh sách chỉ với ba thuộc tính
                    HistoryCall callHistory = new HistoryCall(phoneNumber, callTime);
                    dsLichSuCuocGoi.add(callHistory);
                } while (cursor.moveToNext());
                adapterLichSuCuocGoi.notifyDataSetChanged();
            } else {
                Log.e("LichSuCuocGoi", "Không có dữ liệu trong lịch sử cuộc gọi.");
                Toast.makeText(this, "Không có cuộc gọi nào trong lịch sử.", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.e("LichSuCuocGoi", "Lỗi khi đọc lịch sử cuộc gọi: " + e.getMessage());
        }
    }

    private String convertTimestampToString(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
        return sdf.format(new Date(timestamp));
    }

    private void addControls() {
        lvLichSuCuocGoi = findViewById(R.id.lvLichSuCuocGoi);
        dsLichSuCuocGoi = new ArrayList<>();
        adapterLichSuCuocGoi = new AdapterLichSu(this, R.layout.item_lich_su_cuoc_goi, dsLichSuCuocGoi);
        lvLichSuCuocGoi.setAdapter(adapterLichSuCuocGoi);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_READ_CALL_LOG) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Quyền được cấp, gọi hàm lấy lịch sử cuộc gọi
                showAllCallHistoryFromDevice();
            } else {
                Toast.makeText(this, "Quyền truy cập lịch sử cuộc gọi đã bị từ chối", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
