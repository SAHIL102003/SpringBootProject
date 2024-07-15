package mypackage.repositories.Codes;

import org.springframework.data.jpa.repository.JpaRepository;

import mypackage.model.Codes.Code_post_like_dislike;

public interface Icode_post_like_dislike extends JpaRepository<Code_post_like_dislike, Integer> {

}
