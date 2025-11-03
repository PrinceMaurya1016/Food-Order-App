package com.example.foodorder.Food_Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodorder.DatabaseHelper;
import com.example.foodorder.DetailActivity;
import com.example.foodorder.Food_model.OrderModel;
import com.example.foodorder.R;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.viewHolder> {
    ArrayList<OrderModel> list;
    Context context;

    public  OrderAdapter(ArrayList<OrderModel> list, Context context){
        this.list=list;
        this.context=context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.order_recycler_layout,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        OrderModel model=list.get(position);
        holder.imageView.setImageResource(model.getPic());
        holder.name.setText(model.getName());
        holder.orderNumber.setText(model.getOrdernumber());
        holder.price.setText(model.getPrice());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("id",Integer.parseInt(model.getOrdernumber())); // by the help of order no, we pass all thing.
                intent.putExtra("type",2);
                context.startActivity(intent);
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View view) {
                new AlertDialog.Builder(context)
                        .setIcon(R.drawable.baseline_back_hand_24)
                        .setTitle("Delete Order")
                        .setMessage("Are you sure you want to delete?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                DatabaseHelper helper=new DatabaseHelper(context);
                                if(helper.deleteOrder(model.getOrdernumber()) > 0 ){
                                    Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(context,"Try again",Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNeutralButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        }).show();
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class viewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView name,orderNumber,price;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.orderImage);
            name=itemView.findViewById(R.id.orderFN);
            orderNumber=itemView.findViewById(R.id.orderN);
            price=itemView.findViewById(R.id.orderP);
        }
    }


}
