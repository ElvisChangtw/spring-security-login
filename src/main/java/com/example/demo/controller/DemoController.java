package com.example.demo.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.entity.AppUser;
import com.example.demo.service.impl.FacadeServiceImpl;

@Controller
public class DemoController {
	@Autowired
	FacadeServiceImpl facadeService;
	@GetMapping("/")
	public String slash() {
		return "home";
	}
	@RequestMapping("/home")
	public String home() {
		return "home";
	}
	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	@RequestMapping("/public")
	public String publicPage() {
		return "public";
	}

	@RequestMapping("/accessDenied")
	public String accessDenied() {
		return "accessDenied";
	}

//	@RolesAllowed({"ADMIN"})
	@PreAuthorize("hasAuthority('admin:view')")
	@RequestMapping("/admin")
	public String admin() {
		return "onlyforadmin";
	}

//	@RolesAllowed({ "ADMIN", "USER", "STAFF" })
	@RequestMapping("/hello")
	@PreAuthorize("hasAuthority('hello:view')")
	public String hello() {
		return "hello";
	}
	
//	@RolesAllowed({ "ADMIN", "STAFF" })
	@PreAuthorize("hasAuthority('staff:view')")
	@RequestMapping("/staff")
	public String staff() {
		return "staff";
	}
	
	@PreAuthorize("hasAuthority('list:view')")
	@RequestMapping("/list")
//	@PostFilter("hasRole('ADMIN')")
	public String listUsers(Model model) {
		List<AppUser> users = facadeService.findAllUsers();
		model.addAttribute("list", users);
		
		return "listUsers";
	}
	
	@ModelAttribute("currentUser")
	public User getCurrentUser() {
		return facadeService.getCurrentUser();
	}
	@ModelAttribute("urls")
	public Set<String> getUrls(){
		// hibernate
		Set<String> urls = facadeService.getAccessibleUrls();	
//		// native SQL
//		Set<String> urls = facadeService.getAccessibleUrls2();  
//		// 業務流
//		Set<String> urls = facadeService.getAccessibleUrls3(); 
		return urls;
	}
	@ModelAttribute
	public void getName() {
		facadeService.updateAuthority();
	}

}
