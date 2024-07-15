package com.forumhub_challenge.domain.topic;


import com.forumhub_challenge.domain.answer.Answer;
import com.forumhub_challenge.domain.course.Course;
import com.forumhub_challenge.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

@Table(name="topics", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"title", "message"})
})
@Entity(name="Topic")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String message;
    @CreatedDate
    private LocalDateTime creationData;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User author;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="course_id")
    private Course course;
    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL)
    private List<Answer> answerList;

    public Topic(TopicRegisterData dataT) {
        this.title = dataT.title();
        this.message = dataT.message();
        this.status = Status.ATIVO;
        this.author = dataT.author();
        this.course = dataT.course();
        this.answerList = null;
    }

    public void updateInfo(TopicUpdateData dataT) {
            this.title = dataT.title() != null ? dataT.title() : this.title;
            this.message = dataT.message() != null ? dataT.message() : this.message;
            this.status = dataT.status() != null ? dataT.status() : this.status;
            this.course = dataT.course() != null ? dataT.course() : this.course;
    }
}
