package home.ahmad.springMVC03.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import home.ahmad.springMVC03.model.Post;
import home.ahmad.springMVC03.service.PostService;

/**
 * 
 * @author Ahmad Alrefai
 */
@Controller
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    public String listPosts(Model model) {
        model.addAttribute("posts", postService.findAllPosts());
        return "postList";
    }

    @GetMapping("/{id}")
    public String viewPost(@PathVariable Long id, Model model) {
        Optional<Post> post = postService.findPostById(id);
        if (post.isPresent()) {
            model.addAttribute("post", post.get());
            return "postDetail";
        } else {
            return "redirect:/posts";
        }
    }

    @GetMapping("/add")
    public String addPostForm(Model model) {
        model.addAttribute("post", new Post());
        return "addPost";
    }

    @PostMapping("/add")
    public String addPost(@ModelAttribute Post post) {
        postService.savePost(post);
        return "redirect:/posts";
    }

    @GetMapping("/edit/{id}")
    public String editPostForm(@PathVariable Long id, Model model) {
        Optional<Post> post = postService.findPostById(id);
        if (post.isPresent()) {
            model.addAttribute("post", post.get());
            return "editPost";
        } else {
            return "redirect:/posts";
        }
    }

    @PostMapping("/edit/{id}")
    public String editPost(@PathVariable Long id, @ModelAttribute Post post) {
        post.setId(id);
        postService.savePost(post);
        return "redirect:/posts";
    }

    @GetMapping("/delete/{id}")
    public String deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return "redirect:/posts";
    }
}
