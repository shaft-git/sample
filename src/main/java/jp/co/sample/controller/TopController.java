package jp.co.sample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * トップ画面用コントローラー
 *
 */
@Controller
public class TopController {
	
	/**
	 * 初期画面
	 */
	@RequestMapping("/")
	public String home() {
		return "index";
	}
}
