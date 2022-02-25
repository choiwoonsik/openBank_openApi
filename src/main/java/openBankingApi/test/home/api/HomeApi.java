package openBankingApi.test.home.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class HomeApi {

	@RequestMapping("/")
	public String home() {
		log.info("home controller");
		return "home";
	}

	@GetMapping("/callback")
	public String callback() {
		return "callback";
	}
}
