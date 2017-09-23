package ru.ticketeen.view.activity;

import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ru.ticketeen.R;
import ru.ticketeen.api.response.TicketsResponse;
import ru.ticketeen.databinding.ActivitySearchBinding;
import ru.ticketeen.view.adapter.TicketItemRecyclerViewAdapter;
import ru.ticketeen.viewmodel.TicketListViewModel;

public class SearchActivity extends LifecycleActivity {

    private TicketListViewModel viewModel;
    private ActivitySearchBinding binding;
    private TicketItemRecyclerViewAdapter adapter;
    private List<TicketsResponse.Document> documents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = ViewModelProviders.of(this).get(TicketListViewModel.class);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_search);

        adapter = new TicketItemRecyclerViewAdapter(Collections.EMPTY_LIST);
        binding.list.setLayoutManager(new LinearLayoutManager(this));
        binding.list.setAdapter(adapter);

        viewModel.getTickets().observe(this, documents -> {
            this.documents = documents;
            adapter.setData(findItemsByQuery(""));
            adapter.notifyDataSetChanged();
        });

        binding.searchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                final String query = binding.searchInput.getText().toString();
                final List<TicketsResponse.Item> foundItems = findItemsByQuery(query);
                adapter.setData(foundItems);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private List<TicketsResponse.Item> findItemsByQuery(String query) {
        List<TicketsResponse.Item> foundItems = new ArrayList<>();
        if (documents != null) {
            for (TicketsResponse.Document document : documents) {
                final List<TicketsResponse.Item> items = document.receipt.items;
                for (TicketsResponse.Item item : items) {
                    if (item.name.toLowerCase().contains(query.toLowerCase())) {
                        foundItems.add(item);
                    }
                }
            }
            return foundItems;
        }
        return foundItems;
    }
}
