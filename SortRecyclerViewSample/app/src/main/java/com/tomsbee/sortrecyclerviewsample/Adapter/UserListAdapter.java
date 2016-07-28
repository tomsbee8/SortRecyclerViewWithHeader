package com.tomsbee.sortrecyclerviewsample.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter;
import com.tomsbee.sortrecyclerviewsample.Bean.UserInfo;
import com.tomsbee.sortrecyclerviewsample.R;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by 2015 on 2016/7/28.
 */
public class UserListAdapter extends RecyclerView.Adapter implements StickyRecyclerHeadersAdapter<UserListAdapter.HeaderViewHolder>
{
    //item 的ViewHolder
    class InnerViewHolder extends RecyclerView.ViewHolder
    {
        public TextView nameText;
        public ImageView headImageView;


        public int position;

        public InnerViewHolder(View itemView)
        {
            super(itemView);

            nameText = (TextView) itemView.findViewById(R.id.item_user_name);
            headImageView = (ImageView) itemView.findViewById(R.id.item_user_avatar);
            itemView.setOnClickListener(
                    new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v)
                        {
                            if (onItemViewClickListener != null)
                            {
                                onItemViewClickListener.onInvestItemClick(position);
                            }
                        }
                    }
            );
        }
    }
    //header 的ViewHolder
    class HeaderViewHolder extends RecyclerView.ViewHolder {

        TextView title;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.item_header_view_text);
        }
    }


    public interface OnItemViewClickListener
    {
        void onInvestItemClick(int position);
    }

    private OnItemViewClickListener onItemViewClickListener;
    private List<UserInfo> mSourceDateList;
    private Context mContext;

    public OnItemViewClickListener getOnItemClickListener()
    {
        return onItemViewClickListener;
    }

    public void setOnItemViewClickListener(OnItemViewClickListener onItemViewClickListener)
    {
        this.onItemViewClickListener = onItemViewClickListener;
    }

    public UserListAdapter(Context context, List<UserInfo> sourceDateList)
    {
        mSourceDateList = sourceDateList;
        mContext = context;
    }

    public List<UserInfo> getmSourceDateList() {
        return mSourceDateList;
    }

    public void setmSourceDateList(List<UserInfo> mSourceDateList) {
        this.mSourceDateList = mSourceDateList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user_list, parent, false);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        v.setLayoutParams(lp);
        return new InnerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {

        UserInfo entity = mSourceDateList.get(position);
        InnerViewHolder viewHolder = (InnerViewHolder) holder;
        viewHolder.position = position;
        viewHolder.nameText.setText(entity.getName());
        String imgText =entity.getName().substring(0,1);
        TextDrawable drawable = TextDrawable.builder()
                .buildRound(imgText, Color.RED);
        viewHolder.headImageView.setImageDrawable(drawable);
    }

    @Override
    public long getHeaderId(int position)
    {
        return mSourceDateList.get(position).getSortLetters().charAt(0);
    }

    @Override
    public UserListAdapter.HeaderViewHolder onCreateHeaderViewHolder(ViewGroup parent)
    {
        final View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_header_user_list, parent, false);

        return new HeaderViewHolder(itemView);
    }

    @Override
    public void onBindHeaderViewHolder(UserListAdapter.HeaderViewHolder holder, int position)
    {
        UserInfo item = mSourceDateList.get(position);
        if(item != null) {
            holder.title.setText(item.getSortLetters());
        }

    }

    @Override
    public int getItemCount()
    {
        return mSourceDateList.size();
    }

    public HashMap<String, Integer> getMapIndex()
    {
        HashMap<String, Integer> mapIndex = new LinkedHashMap<String, Integer>();
        for (int i = 0; i < mSourceDateList.size(); i++)
        {
            mapIndex.put(mSourceDateList.get(i).getSortLetters(), i);
        }
        return mapIndex;
    }

}
