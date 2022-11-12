package com.example.bookingcom.dao;

import com.example.bookingcom.entities.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepository extends JpaRepository<Comments,Long> {

}
