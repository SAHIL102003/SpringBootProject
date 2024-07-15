package mypackage.repositories.Userposts;

import org.springframework.data.jpa.repository.JpaRepository;

import mypackage.model.Userposts.Post_Shares;

public interface PostShareRepositories extends JpaRepository<Post_Shares, Integer>{

}
