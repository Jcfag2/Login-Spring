package fr.gtm.hellomvc;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class DemoController {
	@Autowired
	UserRepository repo;
	
	@GetMapping("/")
	public String hello(@RequestParam(name="name",defaultValue="Jon Snow",required=false) String name, Model model) {
		String message = "You know nothing "+name+"!";
		model.addAttribute("message",message);
		return "home";
		
	}
	
	@GetMapping("/login")
		public String login(Model model){
		User user = new User();
		model.addAttribute("user",user);
		return "login";
	}
	
	@PostMapping("/user/new")
	public void createUser(String nom,String role,String pw) throws NoSuchAlgorithmException {
		String sha = Digest.Sha256(pw);
		System.out.println(nom+" "+sha+" "+role+" "+pw);
		repo.createUser(nom, pw, role, sha);
	}
	
	@PostMapping("/connexion")
	public String connexion(@RequestParam("password") String password, User user, Model model) throws NoSuchAlgorithmException {
	String hash = Digest.Sha256(password);
	System.out.println("Hash du passe envoyé "+hash);
	System.out.println("Hash en base "+repo.getValues("gaston"));
	
		model.addAttribute("user",user);
		String nom = user.getNom();
		User bdd = repo.getByNom(nom);
		if(bdd != null) {
			if(repo.getValues(nom).equals(hash)) {
				return "ok";
			}else {
				return "non";
			}
			
		}else {
		return "non";
		}
	}
}
