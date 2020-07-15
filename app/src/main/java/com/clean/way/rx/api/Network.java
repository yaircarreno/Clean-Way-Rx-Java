package com.clean.way.rx.api;

import com.clean.way.rx.models.Comment;
import com.clean.way.rx.models.Post;
import com.clean.way.rx.models.Token;
import com.clean.way.rx.models.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public class Network {

    public Observable<Token> getToken(String apiKey) {
        return Observable.just(new Token());
    }

    public Observable<User> getUser(String username) {
        List<Comment> comments = new ArrayList<Comment>();
        comments.add(new Comment(new Date(), "This is a fake comment!"));
        comments.add(new Comment(new Date(), "This is another fake comment!"));

        List<Post> posts = new ArrayList<Post>();
        posts.add(new Post("Post", "", comments));

        return Observable.just(new User("Yair Carreno","yair@email.com", posts));
    }

    public Observable<User> getUser(String username, Token token) {
        return Observable.just(new User());
    }

    public Observable<List<Post>> getPosts(User user) {
        List<Post> posts = new ArrayList<>();
        posts.add(new Post());
        return Observable.just(posts);
    }

    public Observable<List<Comment>> getComments(Post post) {
        List<Comment> comments = new ArrayList<>();
        comments.add(new Comment());
        return Observable.just(comments);
    }
}
