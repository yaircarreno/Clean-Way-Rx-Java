package com.clean.way.rx.items;

import android.util.Log;

import com.clean.way.rx.api.Cache;
import com.clean.way.rx.api.Network;
import com.clean.way.rx.models.Comment;
import com.clean.way.rx.models.Post;
import com.clean.way.rx.models.User;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class Item16 {

    public static String TAG = "Item16";
    private CompositeDisposable compositeDisposable;
    private Network network;
    private Cache cache;

    public Item16(Network network, Cache cache) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        this.network = network;
        this.cache = cache;
    }

    public void itemExample(String username) {
        this.case3(username);
    }

    private void case1(String username) {
        compositeDisposable.add(
                this.network.getToken("api-key")
                        .concatMap(token -> this.cache.storeToken(token))
                        .concatMap(saved -> this.network.getUser(username))
                        .subscribe(user -> {
                            if (!user.getName().isEmpty() && !user.getPosts().isEmpty()) {
                                for (Post post : user.getPosts()) {
                                    for (Comment comment : post.getComments()) {
                                        Log.d(TAG, "Date: " + comment.getDate() + " Comment: " + comment.getText());
                                    }
                                }
                            }
                        }));
    }

    private void case2(String username) {

        compositeDisposable.add(
                this.network.getToken("api-key")
                        .concatMap(token -> this.cache.storeToken(token))
                        .concatMap(saved -> this.network.getUser(username))
                        .filter(user -> !user.getName().isEmpty() && !user.getPosts().isEmpty())
                        .map(User::getPosts)
                        .flatMap(Observable::fromIterable)
                        .subscribe(post -> {
                            for (Comment comment : post.getComments()) {
                                Log.d(TAG, "Date: " + comment.getDate() + " Comment: " + comment.getText());
                            }
                        }));
    }

    private void case3(String username) {
        compositeDisposable.add(
                this.network.getToken("api-key")
                        .concatMap(token -> this.cache.storeToken(token))
                        .concatMap(saved -> this.network.getUser(username))
                        .filter(user -> !user.getName().isEmpty() && !user.getPosts().isEmpty())
                        .map(User::getPosts)
                        .flatMap(posts -> Observable.fromIterable(posts).map(Post::getComments))
                        .flatMap(Observable::fromIterable)
                        .subscribe(comment -> {
                            Log.d(TAG, "Date: " + comment.getDate() + " Comment: " + comment.getText());
                        }));
    }
}
