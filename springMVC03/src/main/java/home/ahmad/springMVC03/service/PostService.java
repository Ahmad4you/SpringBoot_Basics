package home.ahmad.springMVC03.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import home.ahmad.springMVC03.model.Post;
import home.ahmad.springMVC03.repository.PostRepository;

/**
 * 
 * @author Ahmad Alrefai
 */
@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public List<Post> findAllPosts() {
        return postRepository.findAll();
    }

    public Optional<Post> findPostById(Long id) {
        return postRepository.findById(id);
    }

    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}
