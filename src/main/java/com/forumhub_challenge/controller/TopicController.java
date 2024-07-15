package com.forumhub_challenge.controller;

import com.forumhub_challenge.domain.topic.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicController {

    @Autowired
    private TopicRepository topicRepository;

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid TopicRegisterData dataT, UriComponentsBuilder uriBuilder){
        var topic = new Topic(dataT);
        topicRepository.save(topic);

        var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topic.getId()).toUri();
        var dto = new DetailedTopicData(topic);
        return ResponseEntity.created(uri).body(dto);

    }

    @GetMapping
    public ResponseEntity<Page<ListTopicData>> listTopic(
            @PageableDefault(size = 10, page = 0, sort = {"creationDate"}) Pageable page,
            @RequestParam(required = false) Long courseId
    ) {
        if (courseId != null) {
            return ResponseEntity.ok(topicRepository.findByCourseId(courseId, page).map(ListTopicData::new));
        } else {
            return ResponseEntity.ok(topicRepository.findAll(page).map(ListTopicData::new));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity detailed(@PathVariable Long id){
        var topic = topicRepository.getReferenceById(id);
        var dto = new DetailedTopicData(topic);
        return ResponseEntity.ok(dto);
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid TopicUpdateData dataT){
        Optional<Topic> topicOptional = topicRepository.findById(dataT.id());
        if (topicOptional.isPresent()) {
            Topic topic = topicOptional.get();
            topic.updateInfo(dataT);
            return ResponseEntity.ok(new DetailedTopicData(topic));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        Optional<Topic> topicOptional = topicRepository.findById(id);
        if (topicOptional.isPresent()) {
            topicRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
