package com.example.task1.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task1.R;
import com.example.task1.item;
import com.squareup.picasso.Picasso;

import java.util.List;

public class places_adapter  extends RecyclerView.Adapter<places_adapter.ViewHolder>{

    private List<item> mData;
    private LayoutInflater mInflater;
    private places_adapter.ItemClickListener mClickListener;

    public places_adapter(Context context, List<item> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    @NonNull
    @Override
    public places_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_view, parent, false);
        return new ViewHolder(view);


    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String name = mData.get(position).getName();
        holder.myTextView.setText(name);
        String s = cut_String(mData.get(position).getDescription());
        holder.myDescView.setText(s);
        String photo = mData.get(position).getPhoto();
        if(photo.equals(""))
        {
        holder.work_image.setImageResource(R.drawable.no_image);
        }
        else
        Picasso.get().load(mData.get(position).getPhoto()).placeholder(R.drawable.ic_launcher_background).into(holder.work_image);

    }
String  cut_String (String statement)
{ String result = null;
    for (int i = 0; i < statement.length(); i++) {
        if (statement.charAt(i)!=',')
        result+=statement.charAt(i);
        else break;
    }
    return result;
}

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView,myDescView;
        ImageView work_image;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.item_name);
            myDescView = itemView.findViewById(R.id.item_description);
            work_image = itemView.findViewById(R.id.item_image);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    String getItem(int id) {
        return mData.get(id).getName();
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}



