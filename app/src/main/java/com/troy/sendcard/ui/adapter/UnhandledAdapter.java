package com.troy.sendcard.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.troy.sendcard.R;
import com.troy.sendcard.bean.response.UnhandledResult;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by chenlongfei on 16/4/12.
 */
public class UnhandledAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<UnhandledResult.UnhandledDataEntity> mUnhandledDataEntityList;
    private View.OnClickListener onClickListener;

    public UnhandledAdapter(Context context, List<UnhandledResult.UnhandledDataEntity> unhandledDataEntityList) {
        mContext = context;
        mUnhandledDataEntityList = unhandledDataEntityList;
    }

    @Override
    public UnhandledViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_unhandled, parent, false);
        UnhandledViewHolder viewHolder = new UnhandledViewHolder(view);
        return viewHolder;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        UnhandledViewHolder viewHolder = (UnhandledViewHolder) holder;
        final UnhandledResult.UnhandledDataEntity unhandledDataEntity = mUnhandledDataEntityList.get(position);
        if (!TextUtils.isEmpty(unhandledDataEntity.getPhone())) {
            viewHolder.phoneNum.setText(unhandledDataEntity.getPhone());
        } else {
            viewHolder.phoneNum.setText("");
        }

        if (!TextUtils.isEmpty(unhandledDataEntity.getReceive_name())) {
            viewHolder.name.setText(unhandledDataEntity.getReceive_name());
        } else {
            viewHolder.name.setText("");
        }

        if (!TextUtils.isEmpty(unhandledDataEntity.getCard_no_total())) {
            viewHolder.cardAmount.setText(unhandledDataEntity.getCard_no_total() + "");
        } else {
            viewHolder.cardAmount.setText("");
        }

        if (!TextUtils.isEmpty(unhandledDataEntity.getProvince())) {
            viewHolder.province.setText(unhandledDataEntity.getProvince());
        } else {
            viewHolder.province.setText("");
        }

        if (!TextUtils.isEmpty(unhandledDataEntity.getCity())) {
            viewHolder.city.setText(unhandledDataEntity.getCity());
        } else {
            viewHolder.city.setText("");
        }

        if (unhandledDataEntity.isIs_handling()) {
            viewHolder.rootView.setOnClickListener(onClickListener);
            viewHolder.rootView.setTag(unhandledDataEntity.getApply_id());
            viewHolder.btnHandled.setVisibility(View.GONE);
            viewHolder.txtHandled.setVisibility(View.VISIBLE);
            viewHolder.txtHandled.setTextColor(mContext.getResources().getColor(R.color.color_jyb));
        } else {
            viewHolder.btnHandled.setVisibility(View.VISIBLE);
            viewHolder.btnHandled.setTag(unhandledDataEntity.getApply_id());
            viewHolder.btnHandled.setOnClickListener(onClickListener);
            viewHolder.txtHandled.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mUnhandledDataEntityList.size();
    }

    public class UnhandledViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.root_view)
        View rootView;
        @Bind(R.id.tv_phone_num)
        TextView phoneNum;
        @Bind(R.id.tv_name)
        TextView name;
        @Bind(R.id.tv_card_amount)
        TextView cardAmount;
        @Bind(R.id.tv_province)
        TextView province;
        @Bind(R.id.tv_city)
        TextView city;
        @Bind(R.id.btn_go_handled)
        Button btnHandled;
        @Bind(R.id.tv_tv_handled)
        TextView txtHandled;

        public UnhandledViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
