package com.bibliotecauts.BibliotecaUTS.Controller;


import com.bibliotecauts.BibliotecaUTS.Entities.Book;
import com.bibliotecauts.BibliotecaUTS.Entities.User;
import com.bibliotecauts.BibliotecaUTS.Service.BookService;
import com.bibliotecauts.BibliotecaUTS.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @GetMapping
    public String adminHome() {
        return "admin";
    }

    @GetMapping("/users")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin_users";
    }

    @PostMapping("/user/create")
    public String createUser(@ModelAttribute User user) {
        userService.saveUser(user);
        return "redirect:/admin/users";
    }

    @PostMapping("/book/create")
    public String createBook(@ModelAttribute Book book) {
        bookService.saveBook(book);
        return "redirect:/admin/books";
    }

    @GetMapping("/user/edit/{id}")
    public String editUser(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "edit_user";
    }

    @PostMapping("/user/edit/{id}")
    public String updateUser(@PathVariable Long id, @ModelAttribute User user) {
        user.setId(id);
        userService.saveUser(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/book/edit/{id}")
    public String editBook(@PathVariable Long id, Model model) {
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        return "edit_book";
    }

    @PostMapping("/book/edit/{id}")
    public String updateBook(@PathVariable Long id, @ModelAttribute Book book) {
        book.setId(id);
        bookService.saveBook(book);
        return "redirect:/admin/books";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }

    @GetMapping("/book/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "redirect:/admin/books";
    }
    @GetMapping("/books")
    public String adminBooks(Model model) {
        List<Book> books = bookService.getAllBooks();
        List<User> users = userService.getAllUsers();
        model.addAttribute("books", books);
        model.addAttribute("users", users);
        return "admin_books";
    }

    @PostMapping("/book/assign/{id}")
    public String assignBookToUser(@PathVariable Long id, @RequestParam Long userId) {
        Book book = bookService.getBookById(id);
        User user = userService.getUserById(userId);
        if (book != null && user != null) {
            book.setUser(user);
            bookService.saveBook(book);
        }
        return "redirect:/admin/books";
    }
}

