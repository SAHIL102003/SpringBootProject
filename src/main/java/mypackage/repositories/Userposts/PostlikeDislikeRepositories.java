package mypackage.repositories.Userposts;

import org.springframework.data.jpa.repository.JpaRepository;

import mypackage.model.Userposts.Post_like_Dislike;

public interface PostlikeDislikeRepositories extends JpaRepository<Post_like_Dislike, Integer> {



}
