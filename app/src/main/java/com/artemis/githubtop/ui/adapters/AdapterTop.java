package com.artemis.githubtop.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.artemis.githubtop.R;
import com.artemis.githubtop.mvp.presenter.ListPresenter;
import com.artemis.githubtop.mvp.view.ItemSearchView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by artoym on 24.03.2018.
 */
public class AdapterTop extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ListPresenter presenter;

    public AdapterTop(ListPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TopListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_top, parent, false));
    }

    public void addItems(int from, int itemAmount) {
        notifyItemRangeInserted(from, itemAmount);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        presenter.bindItemView((TopListViewHolder)holder, position);
    }

    @Override
    public int getItemCount() {
        return presenter.getItemCount();
    }

    class TopListViewHolder extends RecyclerView.ViewHolder implements ItemSearchView {

        @BindView(R.id.textview_rep_name)
        TextView vTxtName;

        @BindView(R.id.textview_rep_owner)
        TextView vTxtOwner;

        @BindView(R.id.textview_rep_rate)
        TextView vTxtRate;


        TopListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void setRepName(String name) {
            vTxtName.setText(name);
        }

        @Override
        public void setOwner(String owner) {
            vTxtOwner.setText(owner);
        }

        @Override
        public void setRate(int rate) {
            vTxtRate.setText(itemView.getContext().getString(R.string.label_rep_name, rate));
        }

        @OnClick(R.id.container_item_top)
        void onClickItemTop() {
            presenter.onItemClick(getAdapterPosition());
        }
    }
}
