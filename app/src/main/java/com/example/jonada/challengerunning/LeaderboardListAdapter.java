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

public class LeaderboardListAdapter extends RecyclerView.Adapter<LeaderboardListAdapter.ViewHolder> {
    private LeaderboardData[] lDlistdata;

    // RecyclerView recyclerView;
    public LeaderboardListAdapter(LeaderboardData[] lDlistdata) {
        this.lDlistdata = lDlistdata;
    }
    @Override
    public LeaderboardListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.leaderboard_item, parent, false);
        LeaderboardListAdapter.ViewHolder viewHolder = new LeaderboardListAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(LeaderboardListAdapter.ViewHolder holder, int position) {
        final LeaderboardData myListData = lDlistdata[position];

        holder.profile_photo.setImageResource(lDlistdata[position].getImgId());
        holder.name.setText(lDlistdata[position].getName());
        holder.total_distance.setText(lDlistdata[position].getDistance());
        holder.place.setImageResource(lDlistdata[position].getImg());
    }


    @Override
    public int getItemCount() {
        return lDlistdata.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView profile_photo;
        public TextView name;
        public  TextView total_distance;
        public ImageView place;
        public RelativeLayout relativeLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            this.profile_photo = (ImageView) itemView.findViewById(R.id.profile_picture);
            this.name = (TextView) itemView.findViewById(R.id.textView_name);
            this.name = (TextView) itemView.findViewById(R.id.total_distance);
            this.profile_photo = (ImageView) itemView.findViewById(R.id.place);

            relativeLayout = (RelativeLayout)itemView.findViewById(R.id.relative_layout);
        }
    }
}
