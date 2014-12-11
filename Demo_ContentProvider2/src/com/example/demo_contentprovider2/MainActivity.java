package com.example.demo_contentprovider2;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Uri uri = Uri.parse("content://com.kaikeba.providers.book/book");
       	ContentValues values=new ContentValues();
       	values.put("name", "C++讲义");
       	values.put("price", 50.0);
       	values.put("publisher", "机电出版社");
        Uri retUri=getContentResolver().insert(uri, values);
        
        long id= ContentUris.parseId(retUri);
        Log.i("info", "插入的ID为"+id);
        
        
        Cursor c= getContentResolver().query(uri, null, null, null, null);
        
        if(c!=null){
        	while(c.moveToNext()){
        		String[] cols=c.getColumnNames();
        		  
        			for(String col:cols){
        				Log.i("info", col+":"+c.getString(c.getColumnIndex(col)));
        			}
        			Log.i("info", "======================");
        	}
        }
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
