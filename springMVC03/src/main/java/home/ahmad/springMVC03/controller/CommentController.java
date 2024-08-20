package home.ahmad.springMVC03.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import home.ahmad.springMVC03.model.Comment;
import home.ahmad.springMVC03.model.Post;
import home.ahmad.springMVC03.service.CommentService;
import home.ahmad.springMVC03.service.PostService;

/**
 * 
 * @author Ahmad Alrefai
 */
@Controller
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;
    
    @Autowired
    private PostService postService;

    @PostMapping("/add")
    public String addComment(@ModelAttribute Comment comment, @RequestParam Long postId) {
        Post post = postService.findPostById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        comment.setPost(post);
        commentService.saveComment(comment);
        return "redirect:/posts/" + postId;
    }

    @GetMapping("/delete/{id}")
    public String deleteComment(@PathVariable Long id, @RequestParam Long postId) {
        commentService.deleteComment(id);
        return "redirect:/posts/" + postId;
    }
}
