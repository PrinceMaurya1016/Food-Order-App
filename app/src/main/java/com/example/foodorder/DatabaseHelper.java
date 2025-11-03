package com.example.foodorder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import com.example.foodorder.Food_model.OrderModel;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    final static String DNAME="mydatabase.db";
    final static int DBVERSION=1;
    public DatabaseHelper(@Nullable Context context) {
        super(context, DNAME, null, DBVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "create table orders" +
                        "(id integer primary key autoincrement," +
                        "name text,"+
                        "phone text,"+
                        "price int,"+
                        "quantity int,"+
                        "image int,"+
                        "description text ,"+
                        "foodname text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("Drop table if exists orders");
        onCreate(sqLiteDatabase);
    }

    public boolean insertOrder(String name,String phone,int price,int quantity, int image,String desc, String foodName){
        /*
        Index no
        id=0
        name= 1
        phone= 2
        price= 3
        quantity= 4
        image= 5
        description= 6
        foodname=7
         */
        SQLiteDatabase database=getReadableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("name",name);
        contentValues.put("phone",phone);
        contentValues.put("price",price);
        contentValues.put("quantity",quantity);
        contentValues.put("image",image);
        contentValues.put("description",desc);
        contentValues.put("foodname",foodName);

        long id= database.insert("orders",null,contentValues);
        if(id<=0){
            return false;
        }else{
            return true;
        }
    }

    public ArrayList<OrderModel> getOrders(){
        ArrayList<OrderModel> orders=new ArrayList<>();
        SQLiteDatabase database=this.getWritableDatabase();
        Cursor cursor=database.rawQuery("Select id,foodname,image,price from orders",null);
        if(cursor.moveToFirst()){
            while(cursor.moveToNext()){
                OrderModel model=new OrderModel();
                model.setOrdernumber(cursor.getInt(0)+"");
                model.setName(cursor.getString(1));
                model.setPic(cursor.getInt(2));
                model.setPrice(cursor.getInt(3)+"");
                orders.add(model);
            }
        }
        cursor.close();
        database.close();
        return orders;
    }

    public Cursor getOrderById(int id){
        SQLiteDatabase database=this.getWritableDatabase();

        Cursor cursor=database.rawQuery("Select * from orders where id="+id,null);

        if(cursor!=null)
            cursor.moveToFirst();

//        cursor.close();
//        database.close();
        return cursor;
    }

    public boolean updateOrder(String name,String phone,int price,int quantity, int image,String desc, String foodName, int id){
        /*
        Index no
        id=0
        name= 1
        phone= 2
        price= 3
        quantity= 4
        image= 5
        description= 6
        foodname=7
         */
        SQLiteDatabase database=getReadableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("name",name);
        contentValues.put("phone",phone);
        contentValues.put("price",price);
        contentValues.put("quantity",quantity);
        contentValues.put("image",image);
        contentValues.put("description",desc);
        contentValues.put("foodname",foodName);

        long row= database.update("orders",contentValues,"id="+id,null);
        if(row<=0){
            return false;
        }else{
            return true;
        }
    }

    public int deleteOrder(String id){
        SQLiteDatabase database=this.getWritableDatabase();
        return database.delete("orders","id="+id,null);
    }

}
