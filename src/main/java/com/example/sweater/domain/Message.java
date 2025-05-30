package com.example.sweater.domain;


import jakarta.persistence.*;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String text;
    private String tag;


    public String getAuthorName(){
        return author!=null ? author.getUsername(): "<none>";
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;


    public Message() {
    }

    public Message(String text, String tag, User user) {
        this.author = user;
        this.text = text;
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
