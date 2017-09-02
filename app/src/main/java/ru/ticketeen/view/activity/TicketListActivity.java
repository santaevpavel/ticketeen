package ru.ticketeen.view.activity;

import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import ru.ticketeen.R;
import ru.ticketeen.databinding.ActivityTicketListBinding;
import ru.ticketeen.view.adapter.TicketRecyclerViewAdapter;
import ru.ticketeen.viewmodel.TicketListViewModel;

public class TicketListActivity extends LifecycleActivity {

    private TicketListViewModel viewModel;
    private ActivityTicketListBinding binding;
    private TicketRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = ViewModelProviders.of(this).get(TicketListViewModel.class);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_ticket_list);

        adapter = new TicketRecyclerViewAdapter(null);
        binding.list.setLayoutManager(new LinearLayoutManager(this));
        binding.list.setAdapter(adapter);

        viewModel.getTickets().observe(this, documents -> {
            adapter.setData(documents);
            adapter.notifyDataSetChanged();
        });
    }

}
