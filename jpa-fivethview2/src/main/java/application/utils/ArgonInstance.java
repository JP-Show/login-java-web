package application.utils;

import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

import application.web.exception.WebExceptions;

public class ArgonInstance {
	private static final Argon2PasswordEncoder ARGONENCODER = new Argon2PasswordEncoder(16, 64, 2, 128000, 10);
	public static boolean matchPassword(CharSequence rawPassword, String enconderPassword) {
		if(rawPassword == "") throw new WebExceptions("You need to put your password");
		boolean match = ARGONENCODER.matches(rawPassword, enconderPassword);
		return match;
	}
}
