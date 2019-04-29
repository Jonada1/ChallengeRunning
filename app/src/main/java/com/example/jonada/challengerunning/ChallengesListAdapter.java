package com.example.jonada.challengerunning;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ChallengesListAdapter extends RecyclerView.Adapter<ChallengesListAdapter.ViewHolder>{
    public List<ChallengeData> getChallenges() {
        return challenges;
    }

    public void setChallenges(List<ChallengeData> challenges) {
        this.challenges = challenges;
    }

    public ChallengesListAdapter(List<ChallengeData> challenges) {
        this.challenges = challenges;
    }

    private List<ChallengeData> challenges;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.challenge_completed_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ChallengeData challenge = challenges.get(position);
        if(!challenge.getInitiator().isFinished() || !challenge.getReceiver().isFinished()) {
            holder.imageView_incomplete.setVisibility(holder.imageView_incomplete.VISIBLE);
        } else {
            boolean isInitiatorWinner = challenge.getInitiator().getDistance() > challenge.getReceiver().getDistance();
            if(isInitiatorWinner) {
                holder.imageView_initiator.setVisibility(holder.imageView_initiator.VISIBLE);
            } else {
                holder.imageView_receiver.setVisibility(holder.imageView_receiver.VISIBLE);
            }
        }
        holder.textView_initiator.setText(challenge.getInitiator().getName());
        holder.textView_distance.setText(challenge.getInitiator().getDistance().toString());
        holder.textView_receiver.setText(challenge.getReceiver().getName());
        holder.textView_distanceR.setText(challenge.getReceiver().getDistance().toString());
    }

    @Override
    public int getItemCount() {
        return this.challenges.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView_initiator;
        public TextView textView_distance;
        public ImageView imageView_initiator;
        public ImageView imageView_incomplete;
        public ImageView imageView_receiver;
        public TextView textView_receiver;
        public TextView textView_distanceR;
        public ViewHolder(View itemView) {
            super(itemView);
            this.textView_initiator = (TextView) itemView.findViewById(R.id.textView_initiator);
            this.textView_distance = (TextView) itemView.findViewById(R.id.textView_distance);
            this.imageView_initiator = (ImageView) itemView.findViewById(R.id.imageView_initiator );
            this.imageView_incomplete = (ImageView) itemView.findViewById(R.id.imageView_incomplete );
            this.imageView_receiver = (ImageView) itemView.findViewById(R.id.imageView_receiver );
            this.textView_receiver = (TextView) itemView.findViewById(R.id.textView_receiver);
            this.textView_distanceR = (TextView) itemView.findViewById(R.id.textView_distanceR);
        }
    }
}
