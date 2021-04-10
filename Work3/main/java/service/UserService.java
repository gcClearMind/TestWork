package service;

import org.springframework.transaction.annotation.Transactional;

import dao.UserDao;
import entity.User;

@Transactional
public class UserService {
	private UserDao userDao;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public boolean loginVerify(String username, String password) {
		boolean passLogin = false;
		passLogin = userDao.verifyUsername(username);
		if(passLogin) {
			passLogin = userDao.verifyPassword(username, password);
		}
		return passLogin;
	}

	public boolean userRegister(String username, String password) {
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		return userDao.addUser(user);
	}
}
