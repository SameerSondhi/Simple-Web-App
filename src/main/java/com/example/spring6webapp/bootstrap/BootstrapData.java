package com.example.spring6webapp.bootstrap;

import com.example.spring6webapp.domain.Author;
import com.example.spring6webapp.domain.Book;
import com.example.spring6webapp.domain.Publisher;
import com.example.spring6webapp.repositories.AuthorRepository;
import com.example.spring6webapp.repositories.BookRepository;
import com.example.spring6webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author eric = new Author();
        eric.setFirstName("Eric");
        eric.setLastName("Evans");

        Book ddd = new Book();
        ddd.setTitle("Domain Driven Design");
        ddd.setIsbn("123456");

        Author ericSaved = authorRepository.save(eric);
        Book dddSaved = bookRepository.save(ddd);

        Author king = new Author();
        king.setFirstName("Stephen");
        king.setLastName("King");

        Book it = new Book();
        it.setTitle("It");
        it.setIsbn("234567");

        Author kingSaved = authorRepository.save(king);
        Book itSaved = bookRepository.save(it);

        ericSaved.getBooks().add(dddSaved);
        kingSaved.getBooks().add(itSaved);
        dddSaved.getAuthors().add(ericSaved);
        itSaved.getAuthors().add(kingSaved);


        Publisher harpercollins = new Publisher();
        harpercollins.setName("HarperCollins");
        Publisher savedPublisher = publisherRepository.save(harpercollins);

        dddSaved.setPublisher(savedPublisher);
        itSaved.setPublisher(savedPublisher);

        authorRepository.save(ericSaved);
        authorRepository.save(kingSaved);
        bookRepository.save(dddSaved);
        bookRepository.save(itSaved);

        System.out.println("In Bootstrap");
        System.out.println("Author count: " + authorRepository.count());
        System.out.println("Book count: " + bookRepository.count());
        System.out.println("Publisher count: " + publisherRepository.count());
    }
}
