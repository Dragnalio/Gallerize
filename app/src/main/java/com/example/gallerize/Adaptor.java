package com.example.gallerize;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Adaptor extends RecyclerView.Adapter<Adaptor.ImageHolder>{

    Context mContext;
    ArrayList<Pictures> arrPictures;

    public Adaptor(Context context, ArrayList<Pictures> pictureArray){
        mContext = context;
        arrPictures = pictureArray;
    }

    //menyimpan gambar
    public class ImageHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        public ImageHolder(View view){
            super(view);
            imageView = view.findViewById(R.id.recylerImage);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ImageHolder holder, int position) {
        final Uri uri = arrPictures.get(position).getUri();
        Glide.with(mContext).load(uri).placeholder(R.drawable.imgholder).centerCrop().into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(FullActivity.newIntent(mContext, uri));
            }
        });

    }

    @Override
    public Adaptor.ImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_layout, parent, false);

        ImageHolder imageHolder = new ImageHolder(view);

        return imageHolder;
    }

    @Override
    public int getItemCount() {
        return arrPictures.size();
    }
}
