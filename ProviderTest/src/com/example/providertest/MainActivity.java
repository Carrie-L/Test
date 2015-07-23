package com.example.providertest;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputFilter.LengthFilter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {
	private List<String> infos=new ArrayList<String>();
	private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=(TextView) findViewById(R.id.tv);
    }

    public void insert(View v){
    	Uri uri=Uri.parse("content://com.example.databasetest.provider/book");
    	ContentValues values=new ContentValues();
    	values.put("name", "权利的游戏");
    	values.put("author", "美国人");
    	values.put("pages", 358);
    	values.put("price", 36.59);
    	Toast.makeText(this, "添加成功", 0).show();
    }
   
    public void query(View v){
    	Uri uri=Uri.parse("content://com.example.databasetest.provider/book");
    	Cursor cursor=getContentResolver().query(uri, null, null, null, null);
    	while(cursor.moveToNext()){
    		String name=cursor.getString(cursor.getColumnIndex("name"));
    		String author=cursor.getString(cursor.getColumnIndex("author"));
    		int pages=cursor.getInt(cursor.getColumnIndex("pages"));
    		double price=cursor.getDouble(cursor.getColumnIndex("price"));
    		infos.add(name+" ,"+author+" ,"+pages+" ,"+price+"\n");
    	}
    	
    	tv.setText(infos.toString()+"\n");
    	cursor.close();
    }
}
