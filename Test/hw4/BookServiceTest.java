package hw4;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindBookById_Found() {
        String bookId = "1";
        Book mockBook = new Book(bookId, "Test Title", "Test Author");
        when(bookRepository.findById(bookId)).thenReturn(mockBook);

        Book result = bookService.findBookById(bookId);

        assertNotNull(result, "Ожидается, что книга не будет null");
        assertEquals(bookId, result.getId(), "ID книги должно совпадать");
        assertEquals("Test Title", result.getTitle(), "Название книги должно совпадать");
        assertEquals("Test Author", result.getAuthor(), "Автор книги должен совпадать");

        verify(bookRepository, times(1)).findById(bookId);
    }

    @Test
    void testFindBookById_NotFound() {
        String bookId = "nonexistent";
        when(bookRepository.findById(bookId)).thenReturn(null);

        Book result = bookService.findBookById(bookId);

        assertNull(result, "Ожидается, что результат будет null для несуществующей книги");

        verify(bookRepository, times(1)).findById(bookId);
    }

    @Test
    void testFindAllBooks() {
        List<Book> mockBooks = Arrays.asList(
                new Book("1", "Book One", "Author A"),
                new Book("2", "Book Two", "Author B")
        );
        when(bookRepository.findAll()).thenReturn(mockBooks);

        List<Book> result = bookService.findAllBooks();

        assertNotNull(result, "Список книг не должен быть null");
        assertEquals(2, result.size(), "Ожидается 2 книги в списке");
        assertEquals("Book One", result.get(0).getTitle(), "Название первой книги должно совпадать");
        assertEquals("Book Two", result.get(1).getTitle(), "Название второй книги должно совпадать");

        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void testFindAllBooks_EmptyList() {
        when(bookRepository.findAll()).thenReturn(Arrays.asList());

        List<Book> result = bookService.findAllBooks();

        assertNotNull(result, "Список книг не должен быть null");
        assertTrue(result.isEmpty(), "Список книг должен быть пустым");

        verify(bookRepository, times(1)).findAll();
    }
    @Test
    void testFindBookById_NullId() {
        Book result = bookService.findBookById(null);

        assertNull(result, "Ожидается, что метод вернет null при передаче null в качестве ID");
        verify(bookRepository, never()).findById(anyString());
    }

    @Test
    void testFindBookById_EmptyId() {
        Book result = bookService.findBookById("");

        assertNull(result, "Ожидается, что метод вернет null при передаче пустой строки в качестве ID");

        verify(bookRepository, never()).findById(anyString());
    }
    

    @Test
    void testFindAllBooks_NullRepositoryResponse() {
        when(bookRepository.findAll()).thenReturn(null);

        List<Book> result = bookService.findAllBooks();

        assertNotNull(result, "Ожидается, что метод вернет пустой список вместо null");
        assertTrue(result.isEmpty(), "Ожидается, что список будет пустым при null-ответе репозитория");

        verify(bookRepository, times(1)).findAll();
    }
}
