package com.example.foodorder;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodorder.Food_Adapter.OrderAdapter;
import com.example.foodorder.Food_model.OrderModel;
import com.example.foodorder.databinding.ActivityFoodOrderBinding;

import java.util.ArrayList;

public class FoodOrderActivity extends AppCompatActivity {

    ActivityFoodOrderBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityFoodOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

       getSupportActionBar().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this,R.color.yellow)));

       DatabaseHelper helper=new DatabaseHelper(this);
        ArrayList<OrderModel> list=helper.getOrders();

        OrderAdapter adapter=new OrderAdapter(list,this);
        binding.recyclerOrder.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        binding.recyclerOrder.setLayoutManager(linearLayoutManager);

    }
}