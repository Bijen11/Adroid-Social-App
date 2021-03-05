package com.example.courseapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class UserlistAdapter  extends RecyclerView.Adapter<UserlistAdapter.MyViewHolder> {
    Context context;
    ArrayList<Userslist> userlist;

    public UserlistAdapter(Context c, ArrayList<Userslist> ulist){
        context = c;
        userlist = ulist;

    }

    @NonNull
    @Override
    public UserlistAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.list_user,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserlistAdapter.MyViewHolder holder, int position) {
        final Userslist user = userlist.get(position);
        holder.username.setText(user.getUsername());
        if(user.getImageURL().equals("default")){
            holder.profile_image.setImageResource(R.mipmap.ic_launcher);
        }
        else{
            Glide.with(context).load(user.getImageURL()).into(holder.profile_image);

        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DashboardActivity.class);
                intent.putExtra("userid", user.getId());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return userlist.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView username;
        ImageView profile_image;

        public MyViewHolder(View itemView){
          super(itemView);

          username = (TextView) itemView.findViewById((R.id.list_username_id));
          profile_image = itemView.findViewById((R.id.profile_image));
        }
    }

}
