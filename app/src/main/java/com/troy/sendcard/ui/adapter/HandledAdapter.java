package com.troy.sendcard.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.troy.sendcard.R;
import com.troy.sendcard.bean.response.HandledResult;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by chenlongfei on 16/4/12.
 */
public class HandledAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<HandledResult.HandledDataEntity> mHandledDataEntityList;

    public HandledAdapter(Context context, List<HandledResult.HandledDataEntity> handledDataEntityList) {
        mContext = context;
        mHandledDataEntityList = handledDataEntityList;
    }

    public void addListData(List<HandledResult.HandledDataEntity> handledDataEntityList) {
        mHandledDataEntityList.addAll(handledDataEntityList);
    }

    @Override
    public HandledViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_handled, parent, false);
        HandledViewHolder viewHolder = new HandledViewHolder(view);
        return viewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        HandledViewHolder viewHolder = (HandledViewHolder) holder;
        HandledResult.HandledDataEntity handledDataEntity = mHandledDataEntityList.get(position);
        if (!TextUtils.isEmpty(handledDataEntity.getPhone())) {
            viewHolder.phoneNum.setText(handledDataEntity.getPhone());
        } else {
            viewHolder.phoneNum.setText("");
        }

        if (!TextUtils.isEmpty(handledDataEntity.getReceive_name())) {
            viewHolder.name.setText(handledDataEntity.getReceive_name());
        } else {
            viewHolder.name.setText("");
        }

        viewHolder.cardAmount.setText(handledDataEntity.getCard_no_total() + "");

        if (!TextUtils.isEmpty(handledDataEntity.getCity())) {
            viewHolder.city.setText(handledDataEntity.getCity());
        } else {
            viewHolder.city.setText(handledDataEntity.getCity());
        }

        if (!TextUtils.isEmpty(handledDataEntity.getSend_no())) {
            viewHolder.expressNum.setText(handledDataEntity.getSend_no());
        } else {
            viewHolder.expressNum.setText("");
        }

        if (null != handledDataEntity.getCard_no_list() && handledDataEntity.getCard_no_list().size() > 0) {
            StringBuilder sb = new StringBuilder();
            for (String cardNo : handledDataEntity.getCard_no_list()) {
                sb.append(cardNo);
                sb.append(",");
            }
            viewHolder.cardNo.setText(sb.substring(0, sb.length() - 1));
        }
    }

    @Override
    public int getItemCount() {
        return mHandledDataEntityList.size();
    }

    public class HandledViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_phone_num)
        TextView phoneNum;
        @Bind(R.id.tv_name)
        TextView name;
        @Bind(R.id.tv_card_amount)
        TextView cardAmount;
        @Bind(R.id.tv_city)
        TextView city;
        @Bind(R.id.tv_express_num)
        TextView expressNum;
        @Bind(R.id.tv_card_no)
        TextView cardNo;

        public HandledViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
