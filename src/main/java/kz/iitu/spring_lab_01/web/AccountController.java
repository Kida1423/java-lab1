package kz.iitu.spring_lab_01.web;

import kz.iitu.spring_lab_01.account.AccountService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/lab4/account")
public class AccountController {

	private final AccountService accountService;

	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}

	public record LoginRequest(String username, String password) {
	}

	public record RegisterRequest(String username, String email, String password, String cardNumber) {
	}

	@PostMapping("/login")
	public String login(@RequestBody LoginRequest request) {
		return accountService.login(request.username(), request.password());
	}

	@PostMapping("/register")
	public String register(@RequestBody RegisterRequest request) {
		return accountService.register(request.username(), request.email(),
				request.password(), request.cardNumber());
	}
}
