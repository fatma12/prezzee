package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context mContext ;
    private List<Card> mData ;
    RequestOptions option;


    public RecyclerViewAdapter(Context mContext, List<Card> mData) {
        this.mContext = mContext;
        this.mData = mData;


        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.list_items,parent,false) ;
        final MyViewHolder viewHolder = new MyViewHolder(view) ;
        viewHolder.view_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(mContext, DeletedCard.class);
                i.putExtra("id",mData.get(viewHolder.getAdapterPosition()).getId());
                i.putExtra("name",mData.get(viewHolder.getAdapterPosition()).getName());
                i.putExtra("value",mData.get(viewHolder.getAdapterPosition()).getValue());
                i.putExtra("currency",mData.get(viewHolder.getAdapterPosition()).getCurrency());
                i.putExtra("theme",mData.get(viewHolder.getAdapterPosition()).getTheme());

                mContext.startActivity(i);

            }
        });




        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv_id.setText(mData.get(position).getId());
        holder.tv_name.setText(mData.get(position).getName());
        holder.tv_currency.setText(mData.get(position).getCurrency());
        holder.tv_value.setText(mData.get(position).getValue());




        Glide.with(mContext).load(mData.get(position).getTheme()).apply(option).into(holder.theme);



    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_id;
        TextView tv_name ;
        TextView tv_currency ;
        TextView tv_value;
        ImageView theme;
        LinearLayout view_container;





        public MyViewHolder(View itemView) {
            super(itemView);

            view_container = itemView.findViewById(R.id.container);
            tv_name = itemView.findViewById(R.id.name);
            tv_value = itemView.findViewById(R.id.value);
            tv_currency = itemView.findViewById(R.id.currency);
            theme = itemView.findViewById(R.id.theme);

        }
    }

}

