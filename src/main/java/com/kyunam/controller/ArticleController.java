package com.kyunam.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kyunam.domain.Article;
import com.kyunam.domain.ArticleRepository;

@Controller
@RequestMapping("/article")
public class ArticleController {
	
	@Autowired
	ArticleRepository articleRepository;
	
	@GetMapping("/write")
	public String createForm() {
		return "/board/form";
	}
	
	@PostMapping("/write")
	public String create(Article article) {
		article.setRegDate(new Date());
		articleRepository.save(article);
		return "redirect:/article/list";
	}
	
	@GetMapping("/list")
	public String articleList(Model model) {
		model.addAttribute("articles", articleRepository.findAll());
		return "/board/list";
	}
	
	@GetMapping("/articleDetail/{id}")
	public String detail(@PathVariable Long id, Model model) {
		model.addAttribute(articleRepository.findOne(id));
		return "/board/articleDetail";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteArticle(@PathVariable Long id) {
		articleRepository.delete(id);
		return "/board/list";
	}
}
