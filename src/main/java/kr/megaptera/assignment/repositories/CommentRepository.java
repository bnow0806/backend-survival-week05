package kr.megaptera.assignment.repositories;

import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.CommentId;
import kr.megaptera.assignment.models.PostId;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CommentRepository {
    Map<CommentId, Comment> comments;

    public CommentRepository() {
        this.comments = new HashMap<>();
    }

    public List<Comment> findAll(PostId postId) {
        List<Comment> commentsList = comments.values().stream()
                        .filter(comment -> comment.postId().equals(postId))
                        .toList();

        return commentsList;
    }

    public Comment find(CommentId commentId, PostId postId) {
        Comment comment = comments.get(commentId);

//        if (comment == null | comment.postId() != postId){
//            throw new CommentNotFound();
//        }

        return comment;
    }

    public void save(Comment comment) {
        comments.put(comment.id(), comment);
    }

    public void delete(CommentId commentId) {
        comments.remove(commentId);
    }
}
