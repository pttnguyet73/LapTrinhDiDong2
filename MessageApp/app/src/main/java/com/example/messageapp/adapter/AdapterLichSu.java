package com.example.messageapp.adapter;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.messageapp.Model.HistoryCall; // Thay thế TinNhan bằng CallLog
import com.example.messageapp.R;

import java.util.List;
public class AdapterLichSu extends ArrayAdapter<HistoryCall> {



        Activity context;
        int resource;
        @NonNull List<HistoryCall> objects;

        public AdapterLichSu(@NonNull Activity context, int resource, @NonNull List<HistoryCall> objects) {
            super(context, resource, objects);
            this.context = context;
            this.resource = resource;
            this.objects = objects;
        }

    public AdapterLichSu(@NonNull Context context, int resource) {
        super(context, resource);
    }

    @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = this.context.getLayoutInflater();
            View row = layoutInflater.inflate(this.resource, null);

            TextView txtName = row.findViewById(R.id.tvTenNguoiGoi); // Thay thế bằng ID đúng
            TextView txtPhoneNumber = row.findViewById(R.id.tvSoDienThoai); // Thay thế bằng ID đúng
            TextView txtCallTime = row.findViewById(R.id.tvThoiGianCuocGoi); // Thay thế bằng ID đúng

            // Thiết lập dữ liệu cho các TextView
            txtName.setText(this.objects.get(position).getName());
            txtPhoneNumber.setText(this.objects.get(position).getPhoneNumber());
            txtCallTime.setText(this.objects.get(position).getCallTime());

            return row;
        }
    }


