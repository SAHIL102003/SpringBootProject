package mypackage.repositories.Userposts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mypackage.model.Userposts.Post_comment_reply;

@Repository
public interface PostcommentrRepliesRepositories extends JpaRepository<Post_comment_reply, Integer> {

}
