package com.security.mvc.test.controller;

import java.util.Collection;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.security.mvc.test.entity.UFOBean;

@Controller
@RequestMapping("/test")
public class Con1 {

	@RequestMapping("/list")
	public @ResponseBody void list(@RequestParam Collection<String> values,
			Model model) {
		model.addAttribute("ufo1", "ufo1v");
		model.addAttribute("ufo2", "ufo2v");
	}

	@RequestMapping(value = "/list2", method = RequestMethod.GET)
	public @ResponseBody String conTest(Model model) {
		model.addAttribute("ufo1", "ufo1v");
		model.addAttribute("ufo2", "ufo2v");
		return "test";
	}

	/*
	 * //必须没有参数又没有返回值的才能自动跑到test
	 * 
	 * @RequestMapping(method = RequestMethod.GET) public void setupForm() { }
	 */

	@RequestMapping(method = RequestMethod.GET)
	public void setupForm(Model model) {
		model.addAttribute("a", "av");
	}

	@RequestMapping(value = "/add1", method = RequestMethod.POST)
	public  String add1(@RequestParam String b) {
		System.out.println(b);
		return "test";
	}

	@RequestMapping(value = "/add2", method = RequestMethod.POST)
	public @ResponseBody String add2(@RequestParam String b) {
		System.out.println(b);
		return "test";
	}
}
