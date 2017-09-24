package ru.ticketeen.view.activity;

import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import ru.ticketeen.App;
import ru.ticketeen.R;
import ru.ticketeen.api.response.TicketsResponse;
import ru.ticketeen.databinding.ActivitySearchBinding;
import ru.ticketeen.preference.LoginPasswordPreference;
import ru.ticketeen.view.adapter.TicketItemRecyclerViewAdapter;
import ru.ticketeen.view.adapter.model.SearchItem;
import ru.ticketeen.viewmodel.TicketListViewModel;

import static ru.ticketeen.preference.LoginPasswordPreference.EMPTY;

public class SearchActivity extends LifecycleActivity {

    private TicketListViewModel viewModel;
    private ActivitySearchBinding binding;
    private TicketItemRecyclerViewAdapter adapter;
    private List<TicketsResponse.Document> documents;

    @Inject
    LoginPasswordPreference loginPasswordPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.component().inject(this);

        viewModel = ViewModelProviders.of(this).get(TicketListViewModel.class);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_search);

        setActionBar(binding.toolbar);

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
                final List<SearchItem> foundItems = findItemsByQuery(query);
                adapter.setData(foundItems);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                logout();
                return true;
        }
        return false;
    }

    private void logout() {
        loginPasswordPreference.setLogin(EMPTY);
        loginPasswordPreference.setPassword(EMPTY);
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    private List<SearchItem> findItemsByQuery(String query) {
        List<SearchItem> foundItems = new ArrayList<>();
        if (documents != null) {
            for (TicketsResponse.Document document : documents) {
                final List<TicketsResponse.Item> items = document.receipt.items;
                for (TicketsResponse.Item item : items) {
                    if (item.name.toLowerCase().contains(query.toLowerCase())) {
                        foundItems.add(toSearchItem(item, document.receipt));
                    }
                }
            }
            return foundItems;
        }
        return foundItems;
    }

    private SearchItem toSearchItem(TicketsResponse.Item item, TicketsResponse.Receipt receipt) {
        final SearchItem searchItem = new SearchItem();
        searchItem.setName(item.name.trim().replaceAll("\\s+", " "));
        searchItem.setQuantity(item.quantity);
        searchItem.setPrice(item.price / 100.0);
        searchItem.setSum(item.sum / 100.0);
        searchItem.setWeightedGood((item.quantity % 1) != 0);
        searchItem.setMarket(receipt.user.trim().replaceAll("\\s+", " "));
        searchItem.setDateTime(receipt.dateTime);
        return searchItem;
    }
}
