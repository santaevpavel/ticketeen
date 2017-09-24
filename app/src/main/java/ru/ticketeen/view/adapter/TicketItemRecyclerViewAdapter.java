package ru.ticketeen.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import ru.ticketeen.R;
import ru.ticketeen.databinding.TicketItemListItemBinding;
import ru.ticketeen.view.adapter.model.SearchItem;

public class TicketItemRecyclerViewAdapter extends RecyclerView.Adapter<TicketItemRecyclerViewAdapter.ViewHolder> {

    private List<SearchItem> data;

    public TicketItemRecyclerViewAdapter(List<SearchItem> data) {
        this.data = data;
    }

    public void setData(List<SearchItem> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        final TicketItemListItemBinding binding = DataBindingUtil.inflate(layoutInflater,
                R.layout.ticket_item_list_item, parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final SearchItem receiptItem = data.get(position);
        holder.binding.setItem(receiptItem);
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final TicketItemListItemBinding binding;

        public ViewHolder(TicketItemListItemBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }
}
