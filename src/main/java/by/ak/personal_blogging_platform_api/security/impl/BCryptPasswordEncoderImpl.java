package by.ak.personal_blogging_platform_api.security.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class BCryptPasswordEncoderImpl implements PasswordEncoder {

	private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

	@Override
	public String encode(CharSequence rawPassword) {
		return bCryptPasswordEncoder.encode(rawPassword);
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return bCryptPasswordEncoder.matches(rawPassword, encodedPassword);
	}

}
