package jp.co.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import jp.co.sample.form.UserSearchForm;
import jp.co.sample.service.UserService;

@Controller
@RequestMapping("user")
@SessionAttributes("userSearchForm")
public class UserController {

	@Autowired
	private UserService userService;
	
	@ModelAttribute
	public UserSearchForm userSearchForm() {
		UserSearchForm userSearchForm = new UserSearchForm();
		userService.setInitData(userSearchForm);
		return userSearchForm;
	}
	
	/**
	 * 検索画面初期表示
	 * @return 検索画面JSP
	 */
	@RequestMapping("/init")
	public String init() {
		return "user/search";
	}
	
	/**
	 * 検索画面での検索ボタン押下
	 * @param userSearchForm 検索画面用Form
	 * @return 検索画面JSP
	 */
	@RequestMapping(path="/search", method=RequestMethod.POST)
	public String search(UserSearchForm userSearchForm) {
		userService.execSearch(userSearchForm);
		return "user/search";
	}
}
