package home.ahmad.springMVC03.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import home.ahmad.springMVC03.model.Comment;
import home.ahmad.springMVC03.model.Post;
import home.ahmad.springMVC03.repository.CommentRepository;

/**
 * 
 * @author Ahmad Alrefai
 */
@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> findAllCommentsForPost(Post post) {
        return commentRepository.findByPost(post);
    }

    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}
