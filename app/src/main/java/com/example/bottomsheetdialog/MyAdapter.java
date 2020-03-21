package com.example.bottomsheetdialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    ArrayList<User> user;
    Context context;

    public MyAdapter(ArrayList<User> user, Context context) {
        this.user = user;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_data,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText( user.get(position).getName());
        holder.city.setText( user.get(position).getCity());

    }

    private void setDataToView(TextView name, TextView city,int position) {
        name.setText(user.get(position).getName());
        city.setText(user.get(position).getCity());

    }

    @Override
    public int getItemCount() {
        return user.size();
    }

    private View.OnClickListener onClickListener(final int position) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.recycler_data);
                dialog.setTitle("Position " + position);
                dialog.setCancelable(true); // dismiss when touching outside Dialog

                // set the custom dialog components - texts and image
                TextView name = (TextView) dialog.findViewById(R.id.nameId);
                TextView city = (TextView) dialog.findViewById(R.id.cityId);


                setDataToView(name, city, position);

                dialog.show();
            }
        };
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView name,city;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.nameId);
            city=itemView.findViewById(R.id.cityId);

        }
    }
}
