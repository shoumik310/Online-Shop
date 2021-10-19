package com.onlineshop.storage.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.onlineshop.enteties.User;
import com.onlineshop.enteties.impl.DefaultUser;
import com.onlineshop.storage.UserStoringService;

public class DefaultUserStoringService implements UserStoringService {

	private final String ASSETS_FOLDER = "assets";
	private final String USERS_INFO_STORAGE = "users.csv";
	private final int USER_ID_INDEX = 0;
	private final int USER_FIRSTNAME_INDEX = 1;
	private final int USER_LASTNAME_INDEX = 2;
	private final int USER_PASSWORD_INDEX = 3;
	private final int USER_EMAIL_INDEX = 4;

	private static DefaultUserStoringService instance;

	@Override
	public void saveUser(User user) {
		try{
			Files.writeString(Paths.get(ASSETS_FOLDER, USERS_INFO_STORAGE), System.lineSeparator()+convertToWritableString(user), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
		}catch(IOException e){
			e.printStackTrace();
		}

	}
	
	private String convertToWritableString(User user) {
		return user.getId()+","+user.getFirstName()+","+user.getLastName()+","+user.getPassword()+","+user.getEmail(); 
	}

	@Override
	public List<User> loadUsers() {
		try (var stream = Files.lines(Paths.get(ASSETS_FOLDER, USERS_INFO_STORAGE))) {
			return stream.filter(Objects::nonNull).filter((line) -> !line.isEmpty()).map((line) -> {
				String[] userElements = line.split(",");
				return new DefaultUser(Integer.valueOf(userElements[USER_ID_INDEX]), userElements[USER_FIRSTNAME_INDEX],
						userElements[USER_LASTNAME_INDEX], userElements[USER_PASSWORD_INDEX],
						userElements[USER_EMAIL_INDEX]);
			}).collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	public static DefaultUserStoringService getInstance() {
		if (instance == null) {
			instance = new DefaultUserStoringService();
		}
		return instance;
	}

}
