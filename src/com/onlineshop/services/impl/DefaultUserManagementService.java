package com.onlineshop.services.impl;

import java.util.ArrayList;
import java.util.List;

import com.onlineshop.enteties.User;
import com.onlineshop.services.UserManagementService;

public class DefaultUserManagementService implements UserManagementService {

	private static final String NOT_UNIQUE_EMAIL_ERROR_MESSAGE = "This email is already used by another user. Please, use another email";
	private static final String EMPTY_EMAIL_ERROR_MESSAGE = "You have to input email to register. Please, try one more time";
	private static final String NO_ERROR_MESSAGE = "";

	private List<User> users;

	private static DefaultUserManagementService instance;

	{
		users = new ArrayList<>();
	}

	private DefaultUserManagementService() {
	}

	@Override
	public String registerUser(User user) {
		if (user == null) {
			return NO_ERROR_MESSAGE;
		}
		String errorMessage = checkUniqueEmail(user.getEmail());
		if (errorMessage != null && !errorMessage.isEmpty()) {
			return errorMessage;
		}

		users.add(user);
		return NO_ERROR_MESSAGE;
	}

	private String checkUniqueEmail(String email) {
		if (email == null || email.isEmpty()) {
			return EMPTY_EMAIL_ERROR_MESSAGE;
		}
		for (User user : users) {
			if (user != null && 
							user.getEmail() != null && 
							user.getEmail().equalsIgnoreCase(email)) {
				return NOT_UNIQUE_EMAIL_ERROR_MESSAGE;
			}
		}
		return NO_ERROR_MESSAGE;
	}

	public static UserManagementService getInstance() {
		if (instance == null) {
			instance = new DefaultUserManagementService();
		}
		return instance;
	}

	@Override
	public List<User> getUsers() {
		return this.users;
	}

	@Override
	public User getUserByEmail(String userEmail) {
		for (User user : users) {
			if (user != null && user.getEmail().equalsIgnoreCase(userEmail)) {
				return user;
			}
		}
		return null;
	}

	void clearServiceState() {
		users.clear();
	}
}
