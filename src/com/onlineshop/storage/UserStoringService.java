package com.onlineshop.storage;

import java.util.List;

import com.onlineshop.enteties.User;

public interface UserStoringService {

	void saveUser(User user);
	
	List<User> loadUsers();
	
}
