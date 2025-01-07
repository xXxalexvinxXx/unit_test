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
        // Arrange
        String bookId = "1";
        Book mockBook = new Book(bookId, "Test Title", "Test Author");
        when(bookRepository.findById(bookId)).thenReturn(mockBook);

        // Act
        Book result = bookService.findBookById(bookId);

        // Assert
        assertNotNull(result, "Ожидается, что книга не будет null");
        assertEquals(bookId, result.getId(), "ID книги должно совпадать");
        assertEquals("Test Title", result.getTitle(), "Название книги должно совпадать");
        assertEquals("Test Author", result.getAuthor(), "Автор книги должен совпадать");

        // Verify
        verify(bookRepository, times(1)).findById(bookId);
    }

    @Test
    void testFindBookById_NotFound() {
        // Arrange
        String bookId = "nonexistent";
        when(bookRepository.findById(bookId)).thenReturn(null);

        // Act
        Book result = bookService.findBookById(bookId);

        // Assert
        assertNull(result, "Ожидается, что результат будет null для несуществующей книги");

        // Verify
        verify(bookRepository, times(1)).findById(bookId);
    }

    @Test
    void testFindAllBooks() {
        // Arrange
        List<Book> mockBooks = Arrays.asList(
                new Book("1", "Book One", "Author A"),
                new Book("2", "Book Two", "Author B")
        );
        when(bookRepository.findAll()).thenReturn(mockBooks);

        // Act
        List<Book> result = bookService.findAllBooks();

        // Assert
        assertNotNull(result, "Список книг не должен быть null");
        assertEquals(2, result.size(), "Ожидается 2 книги в списке");
        assertEquals("Book One", result.get(0).getTitle(), "Название первой книги должно совпадать");
        assertEquals("Book Two", result.get(1).getTitle(), "Название второй книги должно совпадать");

        // Verify
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void testFindAllBooks_EmptyList() {
        // Arrange
        when(bookRepository.findAll()).thenReturn(Arrays.asList());

        // Act
        List<Book> result = bookService.findAllBooks();

        // Assert
        assertNotNull(result, "Список книг не должен быть null");
        assertTrue(result.isEmpty(), "Список книг должен быть пустым");

        // Verify
        verify(bookRepository, times(1)).findAll();
    }
    @Test
    void testFindBookById_NullId() {
        // Act
        Book result = bookService.findBookById(null);

        // Assert
        assertNull(result, "Ожидается, что метод вернет null при передаче null в качестве ID");

        // Verify
        verify(bookRepository, never()).findById(anyString());
    }

    @Test
    void testFindBookById_EmptyId() {
        // Act
        Book result = bookService.findBookById("");

        // Assert
        assertNull(result, "Ожидается, что метод вернет null при передаче пустой строки в качестве ID");

        // Verify
        verify(bookRepository, never()).findById(anyString());
    }

    @Test
    void testFindAllBooks_NullRepositoryResponse() {
        // Arrange
        when(bookRepository.findAll()).thenReturn(null);

        // Act
        List<Book> result = bookService.findAllBooks();

        // Assert
        assertNotNull(result, "Ожидается, что метод вернет пустой список вместо null");
        assertTrue(result.isEmpty(), "Ожидается, что список будет пустым при null-ответе репозитория");

        // Verify
        verify(bookRepository, times(1)).findAll();
    }
}
