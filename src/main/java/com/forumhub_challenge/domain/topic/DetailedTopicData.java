package com.forumhub_challenge.domain.topic;

import com.forumhub_challenge.domain.answer.Answer;
import com.forumhub_challenge.domain.course.Course;
import com.forumhub_challenge.domain.user.User;

import java.util.List;

public record DetailedTopicData(
        Long id,
        String title,
        String message,
        Status status,
        User author,
        Course course,
        List<Answer> answerList

) {
    public DetailedTopicData(Topic topic){
        this(topic.getId(), topic.getTitle(), topic.getMessage(), topic.getStatus(),topic.getAuthor(),topic.getCourse(),topic.getAnswerList());
    }
}
