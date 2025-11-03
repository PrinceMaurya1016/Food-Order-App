package com.example.foodorder;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.foodorder.Food_Adapter.FoodAdapter;
import com.example.foodorder.Food_model.Foodmodel;
import com.example.foodorder.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    ArrayList<Foodmodel> list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        EdgeToEdge.enable(this);
       // setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this,R.color.yellow)));

        list.add(new Foodmodel(R.drawable.fast_food,getString(R.string.burger),"10",getString(R.string.get_10_off_using_creadit_card)));
        list.add(new Foodmodel(R.drawable.fast_food2,getString(R.string.burger),"5",getString(R.string.fast_delivery_within_1h_under_5km)));
        list.add(new Foodmodel(R.drawable.fast_food3,getString(R.string.veg_burger),"10",getString(R.string.get_extra_coupon_and_cashback)));
        list.add(new Foodmodel(R.drawable.fast_food5,getString(R.string.chaumin),"2",getString(R.string.get_veg_chaumin_very_fast_devlivery_under_5km)));
        list.add(new Foodmodel(R.drawable.fast_food5,getString(R.string.chaumin),"0",getString(R.string.use_gold_coupon_to_get_free)));
        list.add(new Foodmodel(R.drawable.fast_food,getString(R.string.burger),"10",getString(R.string.get_10_off_using_silver_coupon_or_creadit_card)));
        list.add(new Foodmodel(R.drawable.fast_food2,getString(R.string.burger),"20",getString(R.string.fast_delivery_within_20min_under_2km)));

        FoodAdapter foodAdapter=new FoodAdapter(list,this);
        binding.recyclerview.setAdapter(foodAdapter);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        binding.recyclerview.setLayoutManager(linearLayoutManager);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.myOrder){
            startActivity(new Intent(MainActivity.this,FoodOrderActivity.class));
        }
        return true;
    }
}