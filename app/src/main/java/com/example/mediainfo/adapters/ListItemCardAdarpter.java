package com.example.mediainfo.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mediainfo.R;
import com.example.mediainfo.models.CardDetails;
import com.example.mediainfo.utils.URLUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListItemCardAdarpter extends RecyclerView.Adapter<ListItemCardAdarpter.ListItemCardViewHolder> {

    private List<CardDetails> list;
    private ItemCardListner mClickListner;

    public List<CardDetails> getData() {
        return this.list;
    }


    public interface ItemCardListner {
        void click(CardDetails cardDetails);
        void clickFav(CardDetails cardDetails);
    }

    public ListItemCardAdarpter(ItemCardListner mClickListner) {
        this.mClickListner = mClickListner;
    }

    @NonNull
    @Override
    public ListItemCardViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_list_item, viewGroup, false);
        ListItemCardViewHolder itemCardViewHolder = new ListItemCardViewHolder(view);
        return itemCardViewHolder;
    }

    public void setData(List<CardDetails> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void appendData(List<CardDetails> results) {
        this.list.addAll(results);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ListItemCardViewHolder itemCardViewHolder, int i) {
        itemCardViewHolder.bind(list.get(i));
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class ListItemCardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.card_list_item_thumbnail)
        ImageView mThumbnail;

        @BindView(R.id.card_list_item_title)
        TextView mTitle;

        @BindView(R.id.card_list_item_date)
        TextView mDate;

        @BindView(R.id.list_card_layout)
        CardView layout;

        @BindView(R.id.favorite)
        ImageView mFavIcon;

        public ListItemCardViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            layout.setOnClickListener(this);
            mFavIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mClickListner.clickFav(list.get(getAdapterPosition()));
                }
            });
        }

        public void bind(CardDetails cardDetails) {
            Log.i("Bind Details", "bind: "+cardDetails);
            Log.d("ListItemCard Image url", String.valueOf(URLUtils.getImageUrl(cardDetails.getImage())));
            Picasso.get().load(String.valueOf(URLUtils.getImageUrl(cardDetails.getImage().replace("/","")))).placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background).into(mThumbnail);
            mTitle.setText(cardDetails.getName());
            mDate.setText(cardDetails.getDate());
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            mClickListner.click(list.get(position));
        }
    }
}
