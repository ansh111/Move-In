package inhouse.movein.Adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import butterknife.Bind;
import butterknife.ButterKnife;
import inhouse.movein.Model.Post;
import inhouse.movein.Model.SearchModel;
import inhouse.movein.R;

/**
 * Created by MMT5575 on 24-09-2016.
 */

public class FlatSearchListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private SearchModel searchResponse;
    private Fragment fragment;

    public FlatSearchListAdapter(Fragment fragment, SearchModel searchModel) {
        this.fragment = fragment;
        this.searchResponse = searchModel;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.flt_srch_list_items, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        if (null != searchResponse && null != searchResponse.getPosts() && null != searchResponse.getPosts().get(position)) {
            Post post = searchResponse.getPosts().get(position);
            viewHolder.title.setText(post.getTitle());
            viewHolder.phone.setText(post.getPhone());
            viewHolder.rent.setText(post.getRent() + "");
            String images = post.getImageUrls().getUrls().get(0);
            Glide.with(fragment)
                    .load(images)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .skipMemoryCache(true)
                    .into(viewHolder.iv);
        }
    }

    @Override
    public int getItemCount() {
        return searchResponse.getPosts().size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        @Nullable
        @Bind(R.id.flt_items_pic)
        ImageView iv;
        @Nullable
        @Bind(R.id.flt_items_title)
        TextView title;
        @Nullable
        @Bind(R.id.flt_items_rent)
        TextView rent;
        @Nullable
        @Bind(R.id.flt_items_phone)
        TextView phone;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    @Override
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
    }
}
