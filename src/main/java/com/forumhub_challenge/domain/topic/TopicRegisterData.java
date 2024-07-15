package com.forumhub_challenge.domain.topic;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.forumhub_challenge.domain.course.Course;
import com.forumhub_challenge.domain.user.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicRegisterData(

        @JsonAlias({"titulo","nome"})
        @NotBlank
        String title,
        @JsonAlias({"mensagem","msg"})
        @NotBlank
        String message,
        @JsonAlias({"autor", "user", "usuario","criador"})
        @NotNull
        User author,
        @JsonAlias({"curso"})
        @NotNull
        Course course


) {

}
