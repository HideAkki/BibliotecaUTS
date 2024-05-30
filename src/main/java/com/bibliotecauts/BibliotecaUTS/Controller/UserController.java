package com.bibliotecauts.BibliotecaUTS.Controller;

import com.bibliotecauts.BibliotecaUTS.Entities.User;
import com.bibliotecauts.BibliotecaUTS.Service.BookService;
import com.bibliotecauts.BibliotecaUTS.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @GetMapping
    public String userLoginPage() {
        return "login";  // Esto debe coincidir con el nombre del archivo login.html
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, Model model) {
        User user = userService.getUserByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            model.addAttribute("books", bookService.getBooksByUserId(user.getId()));
            return "user";  // Esto debe coincidir con el nombre del archivo user.html
        }
        model.addAttribute("error", "Correo o contraseña incorrectos");
        return "login";
    }
}
