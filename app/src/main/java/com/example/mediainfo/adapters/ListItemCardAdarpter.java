package com.example.mediainfo.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mediainfo.R;
import com.example.mediainfo.models.CardDetails;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListItemCardAdarpter extends RecyclerView.Adapter<ListItemCardAdarpter.ListItemCardViewHolder> {

    private List<CardDetails> list;
    private ItemCardListner mClickListner;

    public interface ItemCardListner {
        void click(CardDetails cardDetails);
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

        public ListItemCardViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            layout.setOnClickListener(this);
        }

        public void bind(CardDetails cardDetails) {
            System.out.println(cardDetails);
            Picasso.get().load(R.drawable.ic_launcher_background).placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background).into(mThumbnail);
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
