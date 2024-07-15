package com.forumhub_challenge.domain.topic;

import com.forumhub_challenge.domain.course.Course;
import com.forumhub_challenge.domain.user.User;

import java.time.LocalDateTime;

public record ListTopicData(
        String title,
        String message,
        LocalDateTime creationData,
        Status status,
        User author,
        Course course
) {
    public ListTopicData(Topic topic){
        this(topic.getTitle(), topic.getMessage(), topic.getCreationData(),topic.getStatus(), topic.getAuthor(), topic.getCourse());
    }
}
