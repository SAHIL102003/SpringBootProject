package mypackage.repositories.Userposts;

import org.springframework.data.jpa.repository.JpaRepository;

import mypackage.model.Userposts.Post_comment;

public interface PostcommentRepositories extends JpaRepository<Post_comment, Integer> {

}
