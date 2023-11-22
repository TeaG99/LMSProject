package hr.tvz.java.aplikacija.controllers;
import hr.tvz.java.aplikacija.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/search")
    public String searchBooks(@RequestParam String query) {
        return bookService.searchBooks(query);
    }
}
