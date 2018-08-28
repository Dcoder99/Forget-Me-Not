package com.example.vishnusmurali.c2;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> contacts = new ArrayList<String>();
    ArrayList<String> numbers = new ArrayList<String>();
    Cursor cursor;
    int c = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID);
        startManagingCursor(cursor);

    }

        public void countContacts(View view){
        c=0;
        cursor.moveToFirst();
            while (cursor.moveToNext()) {
                ++c;
            }

            SharedPreferences prefs = getSharedPreferences("MyData", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("count", c);
            editor.apply();
            Toast.makeText(this,String.valueOf(c),Toast.LENGTH_LONG);

        }
        public void deleteContacts(View view){
        int i=0;
        Cursor cursor1;
            SharedPreferences prefs = getSharedPreferences("MyData", Context.MODE_PRIVATE);
            c = prefs.getInt("count", 0);
            cursor1 = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID);
            startManagingCursor(cursor);

            cursor1.moveToFirst();
        while (cursor1.moveToNext()){
                if(i>=c){
                    try{
                        String lookupKey = cursor1.getString(cursor1.getColumnIndex(ContactsContract.Contacts.LOOKUP_KEY));
                        Uri uri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_LOOKUP_URI, lookupKey);
                        System.out.println("The uri is " + uri.toString());
                        getContentResolver().delete(uri, null, null);
                    }
                    catch(Exception e)
                    {
                        System.out.println(e.getStackTrace());
                    }
                }
                ++i;
        }
        }
        }

