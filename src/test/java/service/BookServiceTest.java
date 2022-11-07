package service;

import com.example.bookstore.exception.BadRequestException;
import com.example.bookstore.model.Book;
import com.example.bookstore.service.impl.BookServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BookServiceTest {
    @InjectMocks
    private BookServiceImpl bookServiceImpl;

    @Test
    public void when_invalidPrice_ThenBadRequestException() {
        Book book = new Book();
        book.setPrice(-1);
        BadRequestException exception = assertThrows(BadRequestException.class, () -> bookServiceImpl.applyBookValidation(book));
        Assertions.assertEquals("Book price must be greater than 0!", exception.getReason());
    }

    @Test
    public void when_invalidQuantity_ThenBadRequestException() {
        Book book = new Book();
        book.setQuantity(-1);
        BadRequestException exception = assertThrows(BadRequestException.class, () -> bookServiceImpl.applyBookValidation(book));
        Assertions.assertEquals("Quantity of the book must be either 0 or greater than 0!", exception.getReason());
    }
}
