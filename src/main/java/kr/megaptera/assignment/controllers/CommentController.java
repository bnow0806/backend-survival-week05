package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.application.*;
import kr.megaptera.assignment.dtos.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@CrossOrigin
public class CommentController {
    private final GetCommentsService getCommentsService;
    private final CreateCommentService createCommentService;
    private final UpdateCommentService updateCommentService;
    private final DeleteCommentService deleteCommentService;

    public CommentController(GetCommentsService getCommentsService,
                             CreateCommentService createCommentService,
                             UpdateCommentService updateCommentService,
                             DeleteCommentService deleteCommentService) {
        this.getCommentsService = getCommentsService;
        this.createCommentService = createCommentService;
        this.updateCommentService = updateCommentService;
        this.deleteCommentService = deleteCommentService;
    }

    @GetMapping
    public List<CommentDto> list(@RequestParam String postId) {
        List<CommentDto> commentDtos = getCommentsService
                .getCommentDto(postId);

        return commentDtos;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDto create(@RequestParam String postId,
                             @RequestBody CommentCreateDto commentCreateDto) {
        CommentDto created = createCommentService
                .createComment(postId, commentCreateDto);

        return created;
    }

    @PatchMapping("/{id}")
    public CommentDto update(@PathVariable String id,
                          @RequestParam String postId,
                          @RequestBody CommentUpdateDto commentUpdateDto) {
        CommentDto updated = updateCommentService.updateComment(id, postId, commentUpdateDto);

        return updated;
    }

    @DeleteMapping("/{id}")
    public CommentDto delete(@PathVariable String id,
                          @RequestParam String postId) {
        CommentDto deleted = deleteCommentService.deleteComment(id, postId);

        return deleted;
    }
}
