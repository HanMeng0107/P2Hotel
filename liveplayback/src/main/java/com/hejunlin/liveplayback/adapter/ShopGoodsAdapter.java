package com.hejunlin.liveplayback.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hejunlin.liveplayback.R;
import com.hejunlin.liveplayback.entity.Goods;

import java.util.List;


/**
 * Created by HM on 2017/3/28 16:07
 */

public class ShopGoodsAdapter extends RecyclerView.Adapter<ShopGoodsAdapter.ShopGoodsViewHolder> {

    private Context mcontext;
    private LayoutInflater minflater;
    private List<Goods> mgoodsInfo;

    public ShopGoodsAdapter(Context context, List<Goods> goodsInfo) {
        this.minflater = LayoutInflater.from(context);
        this.mgoodsInfo = goodsInfo;
    }

    @Override
    public ShopGoodsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = minflater.inflate(R.layout.item_shop_goods, parent, false);
        ShopGoodsViewHolder holder = new ShopGoodsViewHolder(view);

        holder.goodsName = (TextView) view.findViewById(R.id.shop_goods_name);
        holder.goodsPrice = (TextView) view.findViewById(R.id.shop_goods_price);
        holder.goodsPicture = (ImageView) view.findViewById(R.id.shop_goods_picture);
        holder.ivFocus = (ImageView) view.findViewById(R.id.shop_goods_focus);

        return holder;
    }

    @Override
    public void onBindViewHolder(final ShopGoodsViewHolder holder, int position) {

        holder.goodsName.setText(mgoodsInfo.get(position).getGoodsName());
        holder.goodsPicture.setImageResource((mgoodsInfo.get(position).getGoodsPicture()));
        holder.goodsPrice.setText(mgoodsInfo.get(position).getGoodsPrice());
        holder.itemView.setId(position);
        holder.itemView.setFocusable(true);
        holder.itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    holder.ivFocus.setVisibility(View.VISIBLE);
                    mSelectListener.onItemSelect(holder.itemView, holder.itemView.getId());
                } else {
                    holder.ivFocus.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mgoodsInfo.size();
    }

    public static class ShopGoodsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ShopGoodsViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        ImageView ivFocus, goodsPicture;
        TextView goodsName, goodsPrice;

        @Override
        public void onClick(View view) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(view);
            }
        }
    }

    private static OnRecyclerViewItemClickListener mOnItemClickListener = null;
    private static OnRecyclerViewItemSelectListener mSelectListener = null;

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view);
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public interface OnRecyclerViewItemSelectListener {
        void onItemSelect(View view, int position);
    }

    public void setOnItemSelectListener(OnRecyclerViewItemSelectListener listener) {
        this.mSelectListener = listener;
    }
}
