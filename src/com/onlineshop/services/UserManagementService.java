package com.onlineshop.services;

import java.util.List;
import com.onlineshop.enteties.User;

public interface UserManagementService {

	String registerUser(User user);

	List<User> getUsers();

	User getUserByEmail(String userEmail);

}