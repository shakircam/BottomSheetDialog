package com.example.bottomsheetdialog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    MyAdapter myAdapter;
    FloatingActionButton fab;
    private ArrayList<User> userArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userArrayList = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_id);
        fab = (FloatingActionButton) findViewById(R.id.floating_buttonId);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        MyAdapter myAdapter= new MyAdapter(userArrayList,this);
        recyclerView.setAdapter(myAdapter);
        fab.setOnClickListener(onAddingListener());


    }

    private View.OnClickListener onAddingListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.dialog_add); //layout for dialog
                dialog.setTitle("Add Your Information");
                dialog.setCancelable(false); //none-dismiss when touching outside Dialog

                // set the custom dialog components - texts and image
                EditText name = (EditText) dialog.findViewById(R.id.nameEd);
                EditText city = (EditText) dialog.findViewById(R.id.cityEd);

                View btnAdd = dialog.findViewById(R.id.saveId);

                //set handling event for 2 buttons and spinner

                btnAdd.setOnClickListener(onConfirmListener(name, city, dialog));

                dialog.show();
            }
        };
    }

    private View.OnClickListener onConfirmListener(final EditText name, final EditText city, final Dialog dialog) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User(name.getText().toString().trim(),city.getText().toString().trim());

                //adding new object to arraylist
                userArrayList.add(user);

                //notify data set changed in RecyclerView adapter

                //myAdapter.notifyDataSetChanged();

                //close dialog after all
                dialog.dismiss();
            }
        };
    }
}
