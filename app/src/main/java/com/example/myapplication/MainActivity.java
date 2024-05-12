package com.example.myapplication;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listViewSanPham;
    private ArrayAdapter<SanPham> sanPhamArrayAdapter;
    private List<SanPham> sanPhamList;
    private MyDatabase myDatabase;
    private Button btnThem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        myDatabase = new MyDatabase(this);
//        myDatabase.themSanPham(new SanPham(1, "Sản phẩm 1", 10.5));
//        myDatabase.themSanPham(new SanPham(2, "Sản phẩm 2", 20.5));
//        myDatabase.themSanPham(new SanPham(3, "Sản phẩm 3", 30.5));
//        myDatabase.themSanPham(new SanPham(4, "Sản phẩm 4", 40.5));
//        myDatabase.themSanPham(new SanPham(5, "Sản phẩm 5", 50.5));
//        myDatabase.themSanPham(new SanPham(6, "Sản phẩm 6", 60.5));
        sanPhamList = myDatabase.latTatCaSanPham();

        listViewSanPham = findViewById(R.id.listviewmain);
        sanPhamArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,sanPhamList);
        listViewSanPham.setAdapter(sanPhamArrayAdapter);
        listViewSanPham.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showChiTietSanPhamDialog(sanPhamList.get(position));
            }
        });
        btnThem = findViewById(R.id.btnThem);
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themSanPham(new SanPham());
            }
        });

    }
    public void themSanPham(SanPham sanPham){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_them_san_pham);

        EditText textViewTen = dialog.findViewById(R.id.textViewTen);
        EditText textViewGia = dialog.findViewById(R.id.textViewGia);
        EditText textViewId = dialog.findViewById(R.id.textViewId);
        Button buttonThem = dialog.findViewById(R.id.buttonThem);
        Button buttonDong = dialog.findViewById(R.id.buttonDong);

        buttonThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sanPham.setTen(textViewTen.getText().toString());
                double gia = Double.parseDouble(textViewGia.getText().toString());
                sanPham.setGia(gia);
                int id = Integer.parseInt(textViewId.getText().toString());
                sanPham.setId(id);
                myDatabase.themSanPham(sanPham);
                sanPhamList.add(sanPham);
                sanPhamArrayAdapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
        buttonDong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();


    }
    private void showChiTietSanPhamDialog(SanPham sanPham) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_chi_tiet_sanpham);

        // Hiển thị thông tin chi tiết của sản phẩm trong dialog
        EditText textViewTen = dialog.findViewById(R.id.textViewTen);
        EditText textViewGia = dialog.findViewById(R.id.textViewGia);

        textViewTen.setText(sanPham.getTen());
        textViewGia.setText(String.valueOf(sanPham.getGia()));

        Button buttonXoa = dialog.findViewById(R.id.buttonXoa);
        Button buttonCapNhat = dialog.findViewById(R.id.buttonCapNhat);
        Button buttonDong = dialog.findViewById(R.id.buttonDong);

        buttonXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xóa sản phẩm khỏi cơ sở dữ liệu và cập nhật lại ListView
                myDatabase.xoaSanPham(sanPham.getId());
                sanPhamList.remove(sanPham);
                sanPhamArrayAdapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });

        buttonCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mở Activity để cập nhật thông tin sản phẩm và cập nhật lại ListView sau khi cập nhật
                // Ví dụ: Intent intent = new Intent(MainActivity.this, CapNhatSanPhamActivity.class);
                // intent.putExtra("SAN_PHAM", sanPham);
                // startActivityForResult(intent, CAP_NHAT_SAN_PHAM_REQUEST_CODE);
                // dialog.dismiss();
                sanPham.setTen(textViewTen.getText().toString());
                double gia = Double.parseDouble(textViewGia.getText().toString());
                sanPham.setGia(gia);
                myDatabase.capNhapSanPham(sanPham);
                sanPhamArrayAdapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });

        buttonDong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}