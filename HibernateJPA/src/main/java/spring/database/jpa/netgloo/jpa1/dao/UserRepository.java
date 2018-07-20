package spring.database.jpa.netgloo.jpa1.dao;

import javax.transaction.Transactional;

import spring.database.jpa.netgloo.jpa1.models.User;

@Transactional
public interface UserRepository extends UserBaseRepository<User> {

}
