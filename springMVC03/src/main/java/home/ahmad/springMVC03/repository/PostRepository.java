package home.ahmad.springMVC03.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import home.ahmad.springMVC03.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
