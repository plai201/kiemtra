package com.example.myapplication;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import java.util.List;

public class SanPhamAdapter extends ArrayAdapter<SanPham> {
    private Context context;
    private List<SanPham> sanPhamList;
    public SanPhamAdapter(@NonNull Context context, List<SanPham> sanPhamList) {
        super(context,0, sanPhamList);
        this.context = context;
        this.sanPhamList = sanPhamList;
    }
}
