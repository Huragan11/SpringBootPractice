package org.example.springbootpractice;

import lombok.RequiredArgsConstructor;
import org.example.springbootpractice.dtos.BookRequest;
import org.example.springbootpractice.dtos.BookResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
class BookService {

    private final BookRepository bookRepository;

    @Transactional(readOnly = true)
    public Optional<BookResponse> getBook(long id) {
        return bookRepository.findById(id)
                .map(book -> BookResponse.builder()
                        .author(book.getAuthor())
                        .title(book.getTitle())
                        .yearOfPublication(book.getYearOfPublication())
                        .build());
    }

    @Transactional
    public BookRequest createBook(BookRequest bookResponse) {
        Book book = new Book();
        book.setAuthor(bookResponse.author());
        book.setTitle(bookResponse.title());
        book.setYearOfPublication(bookResponse.yearOfPublication());
        bookRepository.save(book);
        return bookResponse;
    }

    @Transactional
    public void deleteBook(long id) {
        bookRepository.deleteById(id);
    }

    @Transactional
    public BookResponse updateBook(long id, BookRequest bookRequest) {
        Book book = bookRepository.findById(id).orElseThrow();
        book.setAuthor(bookRequest.author());
        book.setTitle(bookRequest.title());
        book.setYearOfPublication(bookRequest.yearOfPublication());

        return bookRepository.save(book).toResponse();
    }

    @Transactional(readOnly = true)
    public List<BookResponse> getBooks() {
        return bookRepository.findAll().stream().map(Book::toResponse).toList();
    }
}
