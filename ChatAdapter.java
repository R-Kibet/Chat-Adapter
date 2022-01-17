package com.example.clone.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clone.Models.MessageModel;
import com.example.clone.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter{

    //Create 2 variables

    ArrayList<MessageModel> messageModels; //stores message
    Context context;


    //uniquely identify sender and receiver
    //create 2 more variables after adding overRide  method line 53
    int SENDER_VIEW_TYPE = 1;
    int RECEIVER_VIEW_TYPE = 2;

    public ChatAdapter(ArrayList<MessageModel> messageModels, Context context) {
        this.messageModels = messageModels;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == SENDER_VIEW_TYPE)
        {
        View view = LayoutInflater.from(context).inflate(R.layout.samp_sender,parent,false);
        return  new SenderViewHolder(view);

        }
        else {
            View view = LayoutInflater.from(context).inflate(R.layout.samp_receiver,parent,false);
            return  new ReceiverViewHolder(view);


        }
    }

    //add this override method
    @Override
    public int getItemViewType(int position) {
        if (messageModels.get(position).getuId().equals(FirebaseAuth.getInstance().getUid()))

        {
            return  SENDER_VIEW_TYPE;
        }
        else {
            return RECEIVER_VIEW_TYPE;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        MessageModel messageModel = messageModels.get(position);

        if(holder.getClass() == SenderViewHolder.class)
        {
            ((SenderViewHolder) holder).senderMess.setText(messageModel.getMessage());
        }
        else{

            ((ReceiverViewHolder) holder).receiverMess.setText(messageModel.getMessage());

        }

    }

    @Override
    public int getItemCount() {
        return messageModels.size();
    }

    //need to create 2 view holder class for the sender and receiver

    public class ReceiverViewHolder extends RecyclerView.ViewHolder{

        TextView receiverMess, receiverTIme;

        public ReceiverViewHolder(@NonNull View itemView) {
            super(itemView);


            receiverMess = itemView.findViewById(R.id.receivertxt);
            receiverTIme = itemView.findViewById(R.id.rectime);
        }
    }


    public class  SenderViewHolder extends RecyclerView.ViewHolder{

        TextView senderMess , senderTIme;


        public SenderViewHolder(@NonNull View itemView) {
            super(itemView);

            senderMess = itemView.findViewById(R.id.sendertxt);
            senderTIme = itemView.findViewById(R.id.sendtime);

        }
    }
}
