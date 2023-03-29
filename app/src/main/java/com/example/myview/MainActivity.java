package com.example.myview;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dynamicviewclass.MyEditText;
import com.example.dynamicviewclass.MyMemoEditText;
import com.example.dynamicviewclass.MyTextView;
import com.example.dynamicviewclass.MyView;
import com.example.myview.databinding.ActivityMainBinding;
import com.mine.mylibrary.viewutil.DrawCorner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        List<MyView> list = new ArrayList<>();
        list.add(new MyTextView(this, "测试1"));
        list.add(new MyTextView(this, "测试2"));
        list.add(new MyTextView(this, "测试3"));
        list.add(new MyEditText(this, "测试3", "请输入测试3"));
        list.add(new MyMemoEditText(this, "测试3", "请输入测试3"));
        for (MyView myView : list) {
            if (myView.getView() != null) {
                binding.linearlayout.addView(myView.getView());
            }
        }

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