package com.ebeauty;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

//Step 6: create Spring MVC Controller
//to handle requests from spring boot web application
//viewHomePage(), processRegister(), listUsers()

@Controller
public class EbeautyController {
	
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private UserService service;
	
	@RequestMapping("/e-beauty.ca")
	public String viewHomePage() {
		return "index"; //home page of the application
	}
	
	@GetMapping("/expert")
	public String showExpertRegistration() {
		return "expert_register";
	}
	
	
	@GetMapping("/signup") 
	public String showSignUpForm(Model model) {
	  model.addAttribute("user",new User()); 
	  return "signup_form"; 
	  
	}
	
	
	@PostMapping("/process_register")
	public String processRegistration(User user) {
		service.saveUserWithDefault(user);
		
		return "register_success";
	}
	
	@GetMapping("/main")
	public String showMainPage() {
		return "user_main";
	}
	
	@GetMapping("/expertmain")
	public String showExpertMainPage() {
		return "expert_main";
	}
	
	@GetMapping("/login")
	public String showLoginPage() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth == null || auth instanceof AnonymousAuthenticationToken) {
		return "login";
		}
		return "redirect:/";
	}
	
	
	@GetMapping("/ebeauty-admin.ca")
	public String viewUsersList(Model model) {
		List<User> listUsers = repo.findAll();
		model.addAttribute("listUsers",listUsers);
		
		return "adminmain";
	}
	
	
	@GetMapping("/ebeauty-admin.ca/edit/{id}")
	public String editUser(@PathVariable("id") Long id, Model model) {
		User user = service.get(id);
		List<Role> listRoles = service.getRoles();
		model.addAttribute("user", user);
		model.addAttribute("listRoles", listRoles);
		
		return "user_form";
	}
	
	@PostMapping("/ebeauty-admin.ca/save")
	public String saveUser(User user) {
		service.save(user);
		
		return "redirect:/ebeauty-admin.ca";
	}
	 
	
	@GetMapping("/pending")
	public String viewPendingApprovals() {
		return "pending_approvals";
	}
	
	@GetMapping("/tutorials")
	public String viewAdminTuTorials() {
		return "admin_tutorials";
	}
	
	@GetMapping("/tutorialsNew")
	public String showAddNewTuTorials() {
		return "admin_tutorials_new";
	}
	
	@GetMapping("/consultings")
	public String viewAdminConsultings() {
		return "admin_consultings";
	}
	
	//for Client users 
	@GetMapping("/user_consultings")
	public String showUserConsultingPage() {
		return "user_consulting";
	}
	
	@GetMapping("/user_tutorials")
	public String showUserTutorials() {
		return "user_tutorial";
	}
	
	@GetMapping("/user_profiles")
	public String showUserProfile() {
		return "user_profile";
	}
	
	@GetMapping("/reviews")
	public String showUserConsultingReview() {
		return "consulting_review";
	}
	
	@GetMapping("/user_ar")
	public String showARMakeup() {
		return "user_ar";
	}
	
	//for Expert users 
	@GetMapping("/expert_consultings")
	public String showExpertConsultingPage() {
		return "expert_consulting";
	}
	
	@GetMapping("/expert_pastOffer")
	public String showExpertTutorials() {
		return "expert_history";
	}
	
	@GetMapping("/expertprofiles")
	public String showExpertProfile() {
		return "expert_profile";
	}
	
	@GetMapping("/expert_reviews")
	public String showExpertConsultingReview() {
		return "expert_review";
	}
	
	@GetMapping("/expert_earnings")
	public String showExpertEarnings() {
		return "expert_earning";
	}
	
	@GetMapping("/detail1")
	public String showTutorial1() {
		return "tutorial_detail1";
	}
	
	@GetMapping("/detail2")
	public String showTutorial2() {
		return "tutorial_detail2";
	}
	
	@GetMapping("/detail3")
	public String showTutorial3() {
		return "tutorial_detail3";
	}
}
