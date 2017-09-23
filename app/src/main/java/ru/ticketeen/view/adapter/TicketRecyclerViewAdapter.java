package ru.ticketeen.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import ru.ticketeen.R;
import ru.ticketeen.api.response.TicketsResponse;
import ru.ticketeen.databinding.TicketListItemBinding;

public class TicketRecyclerViewAdapter extends RecyclerView.Adapter<TicketRecyclerViewAdapter.ViewHolder> {

    private List<TicketsResponse.Document> data;

    public TicketRecyclerViewAdapter(List<TicketsResponse.Document> data) {
        this.data = data;
    }

    public void setData(List<TicketsResponse.Document> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        final TicketListItemBinding binding = DataBindingUtil.inflate(layoutInflater,
                R.layout.ticket_list_item, parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final TicketsResponse.Document document = data.get(position);
        holder.binding.setTicket(document);
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final TicketListItemBinding binding;

        public ViewHolder(TicketListItemBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }
}
