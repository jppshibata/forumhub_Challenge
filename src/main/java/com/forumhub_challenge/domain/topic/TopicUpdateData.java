package com.forumhub_challenge.domain.topic;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.forumhub_challenge.domain.course.Course;
import com.forumhub_challenge.domain.user.User;
import jakarta.validation.constraints.NotNull;


public record TopicUpdateData(
        @NotNull
        Long id,
        @JsonAlias({"titulo","nome"})
        String title,
        @JsonAlias({"mensagem","msg"})
        String message,
        Status status,
        @JsonAlias({"autor", "user", "usuario","criador"})
        User author,
        @JsonAlias("curso")
        Course course

) {
}
