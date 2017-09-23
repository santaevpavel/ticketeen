package ru.ticketeen.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import ru.ticketeen.R;
import ru.ticketeen.api.response.TicketsResponse;
import ru.ticketeen.databinding.TicketItemListItemBinding;

public class TicketItemRecyclerViewAdapter extends RecyclerView.Adapter<TicketItemRecyclerViewAdapter.ViewHolder> {

    private List<TicketsResponse.Item> data;
    private OnClickTicketListener listener;

    public TicketItemRecyclerViewAdapter(List<TicketsResponse.Item> data) {
        this.data = data;
    }

    public void setData(List<TicketsResponse.Item> data) {
        this.data = data;
    }

    public void setListener(OnClickTicketListener listener) {
        this.listener = listener;
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
        final TicketsResponse.Item receiptItem = data.get(position);
        holder.binding.setItem(receiptItem);

        holder.binding.getRoot().setOnClickListener(v -> {
            if (listener != null) {
                listener.onClick(receiptItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    public interface OnClickTicketListener {

        void onClick(TicketsResponse.Item item);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final TicketItemListItemBinding binding;

        public ViewHolder(TicketItemListItemBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }
}
