package com.forumhub_challenge.domain.topic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;


public interface TopicRepository extends JpaRepository<Topic, Long> {

    Page<Topic> findByCourseId(@Param("courseId")Long courseId, Pageable page);
}
