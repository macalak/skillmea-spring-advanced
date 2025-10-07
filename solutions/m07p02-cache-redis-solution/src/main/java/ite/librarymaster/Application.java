package ite.librarymaster;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;


import ite.librarymaster.model.Book;
import ite.librarymaster.model.BookGenre;
import ite.librarymaster.model.MediumAvailability;
import ite.librarymaster.service.LibraryService;



/**
 * Application entry point.
 * 
 * @author macalak@itexperts.sk
 *
 */

@SpringBootApplication
@EnableCaching
public class Application implements CommandLineRunner {
	private final static Logger LOG = LoggerFactory.getLogger(Application.class);

	@Autowired
	private CacheManager cacheManager;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

    // Simple ConcurrentMap Cache Manager
//	 @Bean
//     public CacheManager cacheManager() {
//         return new ConcurrentMapCacheManager("books");
//     }

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration()
				.setFieldMatchingEnabled(true)
				.setFieldAccessLevel(Configuration.AccessLevel.PUBLIC);
		return modelMapper;
	}

	@Override
	public void run(String... args) throws Exception {
		// Load Cache after startup
		Book book = new Book(100L,"CAT-0100", "Spring Cache", "Spring", "Spring","123456789", BookGenre.Fantasy, MediumAvailability.Available);
		// libraryService.saveBook(book);
		// libraryService.getBook(book.getId());
		Cache cache = cacheManager.getCache("books");
		cache.put(100L, book);
//		Book bookFromCache = cache.get(100L, Book.class);
//        System.out.println(bookFromCache.toString());
		//cache.put(book.getId(), book);		

	}

}
