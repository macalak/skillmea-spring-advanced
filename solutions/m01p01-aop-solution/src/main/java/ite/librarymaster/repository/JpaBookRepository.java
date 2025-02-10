package ite.librarymaster.repository;

import ite.librarymaster.model.Book;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository("jpaBookRepository")
public class JpaBookRepository implements BookRepository {
	
	@PersistenceContext
	EntityManager entityManager; 

	@Override
	public List<Book> findAll() {
		List<Book> result=entityManager.createNamedQuery("book.findAll",Book.class).
		        getResultList();
		return result;
	}

	@Override
	public Book findByIsbn(String isbn) {
		return entityManager.createNamedQuery("book.findByIsbn",Book.class).
		        setParameter("isbn", isbn).
		        getSingleResult();
	}

    @Override
    public Book findById(Long id) {
        return entityManager.find(Book.class, id);
    }

	@Override
	public void saveBook(Book book) {
		entityManager.persist(book);
	}

}
