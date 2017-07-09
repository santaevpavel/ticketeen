package ru.ticketeen.util;


import android.support.annotation.Nullable;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.schedulers.Schedulers;

public class RxUtil {

    public static <R> Subscription work(Func0<R> function, Action1<Throwable> handler,
                                        @Nullable Action1<R> callback) {
        return getObservable(function)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(t -> {
                            if (callback != null) {
                                callback.call(t);
                            }
                        }, handler,
                        () -> {
                        });
    }

    public static <R> Subscription makeRequest(Func0<R> function, Action1<Throwable> handler,
                                               @Nullable Action1<R> callback) {
        return getObservable(function)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(t -> {
                            if (callback != null) {
                                callback.call(t);
                            }
                        }, handler,
                        () -> {
                        });
    }

    public static <R> Observable<R> getObservable(Func0<R> function) {
        return Observable.create(
                (subscriber -> {
                    R resp = function.call();
                    subscriber.onNext(resp);
                    subscriber.onCompleted();
                })
        );
    }
}
