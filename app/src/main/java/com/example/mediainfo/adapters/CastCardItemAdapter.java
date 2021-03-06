package com.example.mediainfo.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mediainfo.R;
import com.example.mediainfo.models.Cast;
import com.example.mediainfo.models.CastCardDetails;
import com.example.mediainfo.utils.URLUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class CastCardItemAdapter extends RecyclerView.Adapter<CastCardItemAdapter.CastListItemCardViewHolder> {

    private List<Cast> list;


    @NonNull
    @Override
    public CastListItemCardViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.cast_card_list_item, viewGroup, false);
        CastListItemCardViewHolder itemCardViewHolder = new CastListItemCardViewHolder(view);
        return itemCardViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CastListItemCardViewHolder castListItemCardViewHolder, int i) {
        castListItemCardViewHolder.bind(list.get(i));
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public void setData(List<Cast> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public interface CastItemCardListner {
        void click(CastCardDetails cardDetails);
    }


    public class CastListItemCardViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.cast_list_item_thumbnail)
        CircleImageView mThumbnail;

        @BindView(R.id.cast_list_item_name)
        TextView mTitle;

        public CastListItemCardViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(Cast cardDetails) {
            Log.i("Bind Details", "bind: " + cardDetails);
            try {
                Picasso.get().load(String.valueOf(URLUtils.getImageUrl(cardDetails.getProfile_path().replace("/", "")))).into(mThumbnail);
            } catch (Exception e) {
                Log.e("Bind Details", "bind: " + e);
            }
            mTitle.setText(cardDetails.getName());

        }


    }
}
