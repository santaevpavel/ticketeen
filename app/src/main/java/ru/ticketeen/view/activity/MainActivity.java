package ru.ticketeen.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ru.ticketeen.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        startActivity(new Intent(getApplicationContext(), TicketListActivity.class));
    }
}
