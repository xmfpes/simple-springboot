package com.kyunam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.kyunam.domain.SampleVO;

@Controller
public class HomeController {
	@GetMapping("/")
	public String Home() {
		SampleVO vo = new SampleVO();
		vo.setVal1("hhi");
		
		System.out.println(vo.getVal1());
		return "index";
	}
}
