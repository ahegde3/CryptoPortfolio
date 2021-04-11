package com.example.cryptoportfolio;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder> {

    private MyListData[] listData;
    public MyListAdapter(MyListData[] listData)
    {
        this.listData=listData;
    }

   @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.activity_linear, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

@Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final MyListData myListData = listData[position];
        holder.currency.setText(listData[position].getToken());
        holder.price.setText(String.valueOf(listData[position].getPrice()));
       /* holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(view.getContext(),"click on item: "+myListData.getDescription(),Toast.LENGTH_LONG).show();
            }
        });*/
    }



    public int getItemCount() {
        return listData.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView currency,price;
        public RelativeLayout relativeLayout;
        public ViewHolder(View itemView) {
            super(itemView);

            this.currency = (TextView) itemView.findViewById(R.id.textView);
            this.price=(TextView) itemView.findViewById(R.id.textView2);
            //relativeLayout = (RelativeLayout)itemView.findViewById(R.layout.activity_linear);
        }
    }
}
