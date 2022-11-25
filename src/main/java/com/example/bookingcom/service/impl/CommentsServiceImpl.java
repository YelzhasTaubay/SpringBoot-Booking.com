package com.example.bookingcom.service.impl;

import com.example.bookingcom.dao.CommentsRepository;
import com.example.bookingcom.entities.Comments;
import com.example.bookingcom.service.CommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentsServiceImpl implements CommentsService {

    private final CommentsRepository commentsRepository;

    @Override
    public List<Comments> getAllComments() {
        List<Comments> allComments=commentsRepository.findAll();
        return allComments;
    }
}
