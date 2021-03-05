package com.example.courseapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MyViewHolder>{
    public static final int MSH_TYPE_LEFT = 0;
    public static final int MSH_TYPE_Right = 1;
    FirebaseUser fuser;
    private Context context;
    private List<ChatMessage> mChat;
    private String imageURL;

    public MessageAdapter(Context context, List<ChatMessage> mChat, String imageURL){
        this.context = context;
        this.mChat = mChat;
        this.imageURL = imageURL;

    }

    @NonNull
    @Override
    public MessageAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

//            View view = LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);
//            return new MessageAdapter.MyViewHolder(view);
            if(viewType == MSH_TYPE_Right) {
                return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.itemright, parent, false));
            }
            else{
                return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, parent, false));

            }

    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.MyViewHolder holder, int position) {
        ChatMessage chat = mChat.get(position);

        holder.message_text.setText(chat.getMessage());
        if(imageURL.equals("default")){
            holder.profile_image.setImageResource(R.mipmap.ic_launcher);
        }
        else{
            Glide.with(context).load(imageURL).into(holder.profile_image);

        }
    }

    @Override
    public int getItemCount() {
        return mChat.size();
    }

   public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView message_text;
        ImageView profile_image;
        public MyViewHolder(View itemView){
            super(itemView);

            message_text = (TextView) itemView.findViewById((R.id.message_text));
            profile_image = itemView.findViewById((R.id.profile_image));
        }
    }

    public int getItemViewType(int postion){

        fuser = FirebaseAuth.getInstance().getCurrentUser();
        if(mChat.get(postion).getSender().equals(fuser.getUid())){
            return MSH_TYPE_Right;
        }
        else {
            return MSH_TYPE_LEFT;
        }
    }


}
