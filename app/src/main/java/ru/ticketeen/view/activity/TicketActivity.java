package ru.ticketeen.view.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import ru.ticketeen.R;
import ru.ticketeen.api.response.TicketsResponse;
import ru.ticketeen.databinding.ActivityTicketBinding;
import ru.ticketeen.view.adapter.TicketItemRecyclerViewAdapter;

public class TicketActivity extends AppCompatActivity {

    private static final String KEY_TICKET = "KEY_TICKET";

    private ActivityTicketBinding binding;
    private TicketsResponse.Document ticket;

    public static Intent getIntent(Context context, TicketsResponse.Document ticket) {
        Intent intent = new Intent(context, TicketActivity.class);
        intent.putExtra(KEY_TICKET, ticket);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_ticket);

        binding.list.setLayoutManager(new LinearLayoutManager(this));

        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.containsKey(KEY_TICKET)) {
            ticket = (TicketsResponse.Document) bundle.getSerializable(KEY_TICKET);


            binding.list.setAdapter(new TicketItemRecyclerViewAdapter(null));
        }

    }
}
