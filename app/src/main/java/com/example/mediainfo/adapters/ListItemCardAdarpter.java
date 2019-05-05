package com.example.mediainfo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mediainfo.R;
import com.example.mediainfo.models.CardDetails;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ListItemCardAdarpter extends RecyclerView.Adapter<ListItemCardAdarpter.ListItemCardViewHolder> {

    private List<CardDetails> list;
    private ItemCardListner mClickListner;

    public interface ItemCardListner{
        void click(CardDetails cardDetails);
    }

    public ListItemCardAdarpter(List<CardDetails> list,ItemCardListner mClickListner){
        this.list = list;
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

    @Override
    public void onBindViewHolder(@NonNull ListItemCardViewHolder itemCardViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    public class ListItemCardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.card_list_item_thumbnail)
        ImageView mThumbnail;

        @BindView(R.id.card_list_item_title)
        TextView mTitle;

        @BindView(R.id.card_list_item_date)
        TextView mDate;

        public ListItemCardViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void bind(CardDetails cardDetails){
            Picasso.get().load(cardDetails.getImage()).error(0).into(mThumbnail);
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
