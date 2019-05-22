package com.zizi.zeinabmahmoud12997.film_show;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.animation.Positioning;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class  ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {

    private Context mContext;
    private ArrayList<ExamplItem> mExampleList;
    private OnItemClickListner mListner;


    public interface OnItemClickListner {
        void OnItemClick(int position);

    }

    public void setOnItemClickListner(OnItemClickListner listner){
        mListner = listner;
    }

    public ExampleAdapter(Context context, ArrayList<ExamplItem> examplList){
        mContext = context;
        mExampleList = examplList;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.example_item, parent, false);
        return new ExampleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        ExamplItem currentItem = mExampleList.get(position);

        String imgURL = currentItem.getmImageURL();
        String filmName = currentItem.getFilmName();
        String cat = currentItem.getCat();

        holder.mTextViewName.setText(filmName);
        holder.mTextViewCat.setText(cat);
        Picasso.with(mContext).load(imgURL).fit().centerInside().into(holder.mImageView);

    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }


    public class ExampleViewHolder extends RecyclerView.ViewHolder {

        public ImageView mImageView;
        public TextView mTextViewName;
        public TextView mTextViewCat;


        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);

            mImageView = itemView.findViewById(R.id.image_view);
            mTextViewName = itemView.findViewById(R.id.text_view_name);
            mTextViewCat = itemView.findViewById(R.id.text_view_cat);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListner != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            mListner.OnItemClick(position);
                        }
                    }
                }
            });

        }
    }
}
