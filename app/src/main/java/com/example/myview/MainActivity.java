package com.example.myview;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.model.AutoViewDataModel;
import com.example.my_interface.IViewInsideClick;
import com.example.viewutil.AutoLinearLayout;
import com.example.viewutil.DensityUtil;
import com.example.viewutil.DrawCorner;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class MainActivity extends Activity {

    private List<Integer> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        for (int i = 0; i < 1000; i++) {
//            list.add(i);
//        }

//        RecyclerView recyclerView = findViewById(R.id.myTestView);
//        LinearLayoutManager manager = new LinearLayoutManager(this);
//        manager.setOrientation(LinearLayoutManager.VERTICAL);
//        recyclerView.setLayoutManager(manager);
//        MyTestAdapter myTestAdapter = new MyTestAdapter(list);
//        recyclerView.setAdapter(myTestAdapter);

        AutoLinearLayout autoLinearLayout = findViewById(R.id.auto_linearlayout);
        autoLinearLayout.setOutMargin(10, 10);
        autoLinearLayout.setItemMargin(10, 5, 10, 5);
        autoLinearLayout.setPadding(7, 3, 7, 3);
        StringBuilder showString = new StringBuilder();
        List<AutoViewDataModel> viewList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            if (i % 9 == 0) {
                showString = new StringBuilder();
            }
            showString.append("啦");
            viewList.add(new AutoViewDataModel(String.valueOf(i), showString.toString()));
        }
        autoLinearLayout.setData(viewList);
        autoLinearLayout.setViewInsideClick(new IViewInsideClick() {
            @Override
            public void onChildClick(View view) {
                String string = view.getTag().toString();
                System.out.println(string);
            }

            @Override
            public void getTag(String tag) {
                String string = tag;
                System.out.println(string);
            }
        });

    }

    class MyTestAdapter extends RecyclerView.Adapter<MyTestAdapter.MyHolder> {

        List<Integer> adapterList;

        MyTestAdapter(List<Integer> adapterList) {
            this.adapterList = adapterList;
        }

        @NonNull
        @Override
        public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_test_item, parent, false);
            return new MyHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyHolder holder, int position) {
            holder.myTestItem.setBackground(DrawCorner.drawCorner(new float[]{0, 0, 200, 200, 200, 200, 0, 0},
                    new int[]{getColor(R.color.greenyellow),
                            getColor(R.color.green),
                            getColor(R.color.blue),
                            getColor(R.color.mediumblue),
                            getColor(R.color.purple)}, 6));
//            StringBuilder string = new StringBuilder("~");
//            for (int i = 0; i < adapterList.get(position); i++) {
//                string.append("~");
//            }
//            holder.myTestItem.setText(string.toString());
            holder.myTestItem.setText("测试视图" + adapterList.get(position) + "：渐变颜色");
        }

        @Override
        public int getItemCount() {
            return adapterList.size();
        }

        class MyHolder extends RecyclerView.ViewHolder {

            TextView myTestItem;
            public MyHolder(@NonNull View itemView) {
                super(itemView);
                myTestItem = itemView.findViewById(R.id.myTestItem);
            }
        }

    }

}