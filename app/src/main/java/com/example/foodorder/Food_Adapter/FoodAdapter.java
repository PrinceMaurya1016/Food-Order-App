package com.example.foodorder.Food_Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodorder.DetailActivity;
import com.example.foodorder.Food_model.Foodmodel;
import com.example.foodorder.R;

import java.util.ArrayList;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.viewHolder>{

    ArrayList<Foodmodel> list;
    Context context;

    public FoodAdapter(ArrayList<Foodmodel> list,Context context){
        this.list=list;
        this.context=context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.main_food_layout,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Foodmodel foodmodel=list.get(position);
        holder.imageView.setImageResource(foodmodel.getPic());
        holder.name.setText(foodmodel.getName());
        holder.price.setText(foodmodel.getPrice());
        holder.description.setText(foodmodel.getDiscription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, DetailActivity.class);
                intent.putExtra("image",foodmodel.getPic());
                intent.putExtra("name",foodmodel.getName());
                intent.putExtra("price",foodmodel.getPrice());
                intent.putExtra("discription",foodmodel.getDiscription());
                intent.putExtra("type",1);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView name,price,description;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.foodImage);
            name=itemView.findViewById(R.id.foodName);
            price=itemView.findViewById(R.id.foodPrice);
            description=itemView.findViewById(R.id.foodDiscription);

        }
    }


}
