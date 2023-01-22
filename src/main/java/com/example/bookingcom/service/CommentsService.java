package com.example.bookingcom.service;

import com.example.bookingcom.entities.Comments;

import java.util.List;

public interface CommentsService {

    List<Comments> getAllComments();

    void addComment(Comments comment);

}
