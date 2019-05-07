package com.example.mediainfo.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mediainfo.R;
import com.example.mediainfo.models.CastCardDetails;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CastCardItemAdapter extends RecyclerView.Adapter<CastCardItemAdapter.CastListItemCardViewHolder> {

    private List<CastCardDetails> list;
    private CastItemCardListner mClickListner;

    public CastCardItemAdapter(CastItemCardListner mClickListner ){
        this.mClickListner = mClickListner;
    }

    @NonNull
    @Override
    public CastListItemCardViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_list_item, viewGroup, false);
        CastListItemCardViewHolder itemCardViewHolder = new CastListItemCardViewHolder(view);
        return itemCardViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CastListItemCardViewHolder castListItemCardViewHolder, int i) {
        castListItemCardViewHolder.bind(list.get(i));
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    public void setData(List<CastCardDetails> list){
        this.list = list;
        notifyDataSetChanged();
    }

    public interface CastItemCardListner {
        void click(CastCardDetails cardDetails);
    }



    public class CastListItemCardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.cast_list_item_thumbnail)
        ImageView mThumbnail;

        @BindView(R.id.cast_list_item_name)
        TextView mTitle;

        public CastListItemCardViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(CastCardDetails cardDetails) {
            Picasso.get().load(cardDetails.getImage()).error(0).into(mThumbnail);
            mTitle.setText(cardDetails.getName());

        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            mClickListner.click(list.get(position));
        }
    }
}
