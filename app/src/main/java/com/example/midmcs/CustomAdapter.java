package com.example.midmcs;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList item_id, item_name, item_quantity, item_desc;

    CustomAdapter(Activity activity, Context context,ArrayList item_id, ArrayList item_name, ArrayList item_quantity, ArrayList item_desc)
    {
        this.activity = activity;
        this.context = context;
        this.item_id = item_id;
        this.item_name = item_name;
        this.item_quantity = item_quantity;
        this.item_desc = item_desc;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.item_name_tv.setText(String.valueOf(item_name.get(position)));
        holder.item_quantity_tv.setText(String.valueOf(item_quantity.get(position)));
        String line = String.valueOf(item_desc.get(position));

        if(line.length() > 100) line = line.substring(0,100) + "...";

        holder.item_desc_tv.setText(line);
        holder.listLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Update.class);
                intent.putExtra("id",String.valueOf(item_id.get(position)));
                intent.putExtra("name",String.valueOf(item_name.get(position)));
                intent.putExtra("quantity",String.valueOf(item_quantity.get(position)));
                intent.putExtra("desc",String.valueOf(item_desc.get(position)));
                activity.startActivityForResult(intent,1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return item_name.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView item_name_tv, item_quantity_tv, item_desc_tv;
        RelativeLayout listLayout;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_name_tv = itemView.findViewById(R.id.item_name);
            item_quantity_tv = itemView.findViewById(R.id.item_quantity);
            item_desc_tv = itemView.findViewById(R.id.item_desc);
            listLayout = itemView.findViewById(R.id.list_layout);
        }
    }
}
