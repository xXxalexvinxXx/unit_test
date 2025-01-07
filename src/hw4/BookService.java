package hw4;

import java.util.List;

public class BookService {
    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book findBookById(String id)
    {
        if (id == null || id.trim().isEmpty()){
            return null;
        }
        return bookRepository.findById(id);
    }

    public List<Book> findAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books != null ? books : List.of();
    }
}

