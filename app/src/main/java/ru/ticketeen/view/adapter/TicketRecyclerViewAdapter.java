package ru.ticketeen.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ru.ticketeen.api.response.TicketsResponse;

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
        //final ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, android.R.layout.simple_list_item_1, parent, false);
        final View inflate = layoutInflater.inflate(android.R.layout.simple_list_item_1, parent, false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final TicketsResponse.Document document = data.get(position);
        ((TextView) holder.itemView).setText("Ticket: " + document.receipt.dateTime);
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final View itemView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
        }
    }
}
