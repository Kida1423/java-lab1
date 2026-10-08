package kz.iitu.spring_lab_01.account;

import kz.iitu.spring_lab_01.masking.Sensitive;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

	public String login(String username, @Sensitive String password) {
		return "User " + username + " logged in (password length " + password.length() + ")";
	}

	public String register(String username, String email, @Sensitive String password,
			@Sensitive String cardNumber) {
		return "Registered " + username + " <" + email + ">";
	}
}
