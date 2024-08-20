package home.ahmad.springMVC03.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import home.ahmad.springMVC03.model.Comment;
import home.ahmad.springMVC03.model.Post;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

	List<Comment> findByPost(Post post);
}
