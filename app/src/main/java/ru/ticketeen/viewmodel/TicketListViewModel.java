package ru.ticketeen.viewmodel;


import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ru.ticketeen.App;
import ru.ticketeen.api.ApiRequester;
import ru.ticketeen.api.response.ExtractResponse;
import ru.ticketeen.api.response.TicketsResponse;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class TicketListViewModel extends ViewModel {

    @Inject
    ApiRequester apiRequester;

    private MutableLiveData<List<TicketsResponse.Document>> tickets;

    public TicketListViewModel() {
        App.component().inject(this);

        tickets = new MutableLiveData<>();
        loadTickets();
    }

    public MutableLiveData<List<TicketsResponse.Document>> getTickets() {
        return tickets;
    }

    private void loadTickets() {
        final Observable<ExtractResponse> getLinkObservable =
                Observable.fromCallable(() -> apiRequester.getLinkToTickets());

        getLinkObservable
                .flatMap(extractResponse -> getTicketsObservable(extractResponse.url))
                .map(this::getDocumentsFromTicketResponses)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(documents -> tickets.setValue(documents), throwable -> {
                    Toast.makeText(App.getContext(), "Error", Toast.LENGTH_SHORT).show();
                });

    }

    @NonNull
    private List<TicketsResponse.Document> getDocumentsFromTicketResponses(List<TicketsResponse> ticketsResponses) {
        List<TicketsResponse.Document> documents = new ArrayList<>();
        for (TicketsResponse ticketsResponse : ticketsResponses) {
            documents.add(ticketsResponse.document);
        }
        return documents;
    }

    private Observable<List<TicketsResponse>> getTicketsObservable(String url) {
        return Observable.fromCallable(() -> apiRequester.getTickets(url));
    }
}
