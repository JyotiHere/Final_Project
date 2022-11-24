package com.tmb.TrackMyBus;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

    @Autowired
    private UserRepository repo;

    @GetMapping("/index")
    public String home() {
        return "index";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    // @GetMapping("/contact")
    // public String contact() {
    //     return "contact";
    // }

    @GetMapping("/userProfile")
    public String userProfile() {
        return "userProfile";
    }

    @GetMapping("/adminProfile")
    public String adminProfile() {
        return "adminProfile";
    }

    @GetMapping("/welcomePage")
    public String welcomePage() {
        return "welcomePage";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/trackView")
    public String trackView() {
        return "trackView";
    }

    @GetMapping("/timetables")
    public String timetables() {
        return "timetables";
    }

    @GetMapping("/queries")
    public String queries() {
        return "queries";
    }

    @GetMapping("/register")
    public String register(Model model) {
        UserDetails user = new UserDetails(0, null, null, null, null);
        model.addAttribute("user", user);
        return "register";
    }

    public String secureMe(String pass) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(pass.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UserDetails user, HttpSession session) {
        user.setPassword(secureMe(user.getPassword()));
        repo.save(user);
        session.setAttribute("message", "Account registered succesfully!");

        return "redirect:/register";
    }

    @GetMapping("/login")
    public String login(Model model) {
        UserDetails user = new UserDetails();
        model.addAttribute("user", user);
        return "login";
    }

    @PostMapping("/login")
    public String LoginUser(@ModelAttribute("user") UserDetails user, HttpSession session) {
        UserDetails users = repo.findByEmailAndPassword(user.getEmail(), secureMe(user.getPassword()));

        if (!Objects.nonNull(users)) {
            session.setAttribute("message", "Incorrect Username or Password entered!");
            return "redirect:/login";
        } else if (user.getEmail().equals("admin@gmail.com") && user.getPassword().equals("Admin@1234")) {
            return "admin";
        } else {
            return "welcomePage";
        }

    }

    @RequestMapping(value = { "/logout" }, method = RequestMethod.POST)
    public String logoutDo(HttpServletRequest request, HttpServletResponse response) {
        return "redirect:/index";
    }

    // Mapping for User's queries through CONTACT form

    @Autowired
    private QueryRepository queryRepo;

    @GetMapping("/contact")
    public String contact(Model model) {
        QueryDetails query = new QueryDetails(0,null,null,null,null,false);
        model.addAttribute("query", query);
        return "contact";
    }

    @PostMapping("/contact")
    public String contact(@ModelAttribute QueryDetails query, HttpSession session) {
        // query.setConcern();
        queryRepo.save(query);
        session.setAttribute("message", "Thank you for reaching out to us, we will look into your query soon!");

        return "redirect:/contact";
    }


}
