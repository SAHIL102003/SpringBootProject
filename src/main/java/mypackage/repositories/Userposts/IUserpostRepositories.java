package mypackage.repositories.Userposts;

import org.springframework.data.jpa.repository.JpaRepository;

import mypackage.model.Userposts.User_posts;

public interface IUserpostRepositories extends JpaRepository<User_posts, Integer> {

}
