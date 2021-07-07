package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", " 123123");
        Publisher awp = new Publisher("Addison-Wesley Professional", "Vladimira Nazora 2");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        awp.getBooks().add(ddd);
        ddd.setPublisher(awp);

        authorRepository.save(eric);
        publisherRepository.save(awp);
        bookRepository.save(ddd);

        Author rod = new Author("Rod", "Johnson");
        Book jdve = new Book("J2EE Development without EJB", "321321");
        Publisher wp = new Publisher("Wiley Publishing", "A.G. Matosa 3");
        rod.getBooks().add(jdve);
        jdve.getAuthors().add(rod);
        wp.getBooks().add(jdve);
        jdve.setPublisher(wp);

        authorRepository.save(rod);
        publisherRepository.save(wp);
        bookRepository.save(jdve);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of books: " + bookRepository.count());
        System.out.println("Number of publishers: " + publisherRepository.count());
    }
}
