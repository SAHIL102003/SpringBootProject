package mypackage.repositories.Codes;

import org.springframework.data.jpa.repository.JpaRepository;

import mypackage.model.Codes.Code_post_comment;

public interface Icode_post_comment extends JpaRepository<Code_post_comment, Integer> {

}
