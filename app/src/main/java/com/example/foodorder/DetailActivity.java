package com.example.foodorder;

import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.opengl.GLDebugHelper;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.foodorder.databinding.ActivityDetailBinding;

public class DetailActivity extends AppCompatActivity {

    ActivityDetailBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.detailActivity), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
       getSupportActionBar().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this,R.color.yellow)));

        final DatabaseHelper databaseHelper = new DatabaseHelper(DetailActivity.this);

       if(getIntent().getIntExtra("type",0)==1) {

           final int image = getIntent().getIntExtra("image", 0);
           final int price = Integer.parseInt(getIntent().getStringExtra("price"));
           final String name = getIntent().getStringExtra("name");
           final String discription = getIntent().getStringExtra("discription");


           binding.detailImg.setImageResource(image);
           binding.detailFN.setText(name);
           binding.detailPrice.setText(String.format("%d", price));
           binding.detailDescription.setText(discription);

           binding.insertButton.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   boolean isInserted = databaseHelper.insertOrder(
                           binding.detailname.getText().toString(),
                           binding.detailPhone.getText().toString(),
                           price,
                           Integer.parseInt(binding.detailQunatity.getText().toString()),
                           image,
                           discription,
                           name

                   );

                   if (isInserted == true) {
                       Toast.makeText(DetailActivity.this, "Ordered", Toast.LENGTH_SHORT).show();
                   } else {
                       Toast.makeText(DetailActivity.this, "Opps! Something went wrong.", Toast.LENGTH_SHORT).show();
                   }
               }
           });
       }
       else{
           int id=getIntent().getIntExtra("id",0);
           Cursor cursor=databaseHelper.getOrderById(id);

           final int image=cursor.getInt(5);
           binding.detailImg.setImageResource(image);
           binding.detailFN.setText(cursor.getString(7));
           binding.detailPrice.setText(String.format("%d",cursor.getInt(3)));
           binding.detailDescription.setText(cursor.getString(6));

           binding.detailname.setText(cursor.getString(1));
           binding.detailPhone.setText(cursor.getString(2));

           binding.insertButton.setText("Udate Now");

           binding.insertButton.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   boolean isUpdated=databaseHelper.updateOrder(
                           binding.detailname.getText().toString(),
                           binding.detailPhone.getText().toString(),
                           Integer.parseInt(binding.detailPrice.getText().toString()),
                           1,
                           image,
                           binding.detailDescription.getText().toString(),
                           binding.detailFN.getText().toString(),
                           id
                           );
                   if(isUpdated) {
                       Toast.makeText(DetailActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                   }else{
                       Toast.makeText(DetailActivity.this,"Failed",Toast.LENGTH_SHORT).show();
                   }
               }
           });

       }
    }
}
