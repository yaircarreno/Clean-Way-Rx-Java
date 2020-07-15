package com.clean.way.rx.models;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.Subject;

public class Paginator {

    private Subject<Integer> page = PublishSubject.create();

    public Observable<String> getResults(String query) {
        return this.getPage()
                .flatMap(numberPage ->
                        Observable.just("page: " + numberPage + " and query: " + query))
                .share();
    }

    public void pushPage(Integer numberPage) {
        page.onNext(numberPage);
    }

    public void complete() {
        page.onComplete();
    }

    private Observable<Integer> getPage() {
        return page;
    }
}
