package com.example.rmptest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {


    private Context context;
    private List<PostsEntity> posts;

    public CardAdapter(Context context) {
        this.context = context;
        this.posts = new ArrayList<>();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_card, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PostsEntity post = this.posts.get(position);
        holder.textViewDescription.setText(post.getText());
        holder.post_user_name.setText(AppDatabase.getDataBase(this.context).getUserDao().getUserById(post.getUserId()).getName());
    }

    @Override
    public int getItemCount() {
        return this.posts.size();
    }

    public void updateList(List<PostsEntity> newPosts) {
        this.posts = newPosts;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View root;
        TextView post_user_name;
        TextView textViewDescription;
        TextView date;
        ImageView post_profile_image;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.root = itemView;
            this.post_user_name = itemView.findViewById(R.id.post_user_name);
            this.textViewDescription = itemView.findViewById(R.id.post_descriptionEvent);
            this.date = itemView.findViewById(R.id.dateEvent);
           this.post_profile_image = itemView.findViewById(R.id.post_profile_image_event);
        }
    }


}
