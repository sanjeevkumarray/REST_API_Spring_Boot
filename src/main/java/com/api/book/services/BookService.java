package com.api.book.services;

import com.api.book.entities.Book; // Import your own Book class
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookService {
    private static List<Book> list = new ArrayList<>();

    static {
        list.add(new Book(1230, "Java", "Jp"));
        list.add(new Book(1231, "C++", "Jp2"));
        list.add(new Book(1232, "DSA", "Jp3"));
    }

    // Get all books
    public List<Book> getAllBooks() {
        return list;
    }

    // Get a single book by id
    public Book getBookById(int id) {
        Book book = null;
        try {
        book = list.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null); // Handle the case where the book is not found
        }
        catch(Exception e){
        	 e.printStackTrace();
        }
        return book;
    }

    //adding the book
   public Book addBook (Book b){
      list.add(b);
       return b;
   }
   
   //delete the book
   
   public void  deleteBook(int bid  ) {
	list=   list.stream().filter(book ->{
		   if(book.getId()!=bid){
		   return true;
		}
		else{
		  return false;
		}
		}).collect(Collectors.toList());
   }
		
//	list =list.stream().filter(book ->book.getId()!=bid).
//			collect(Collectors.toList());
//	   
   //}
   
   // Update  the book
   


     public  void updateBook(Book book, int bookId){
	list =list.stream().map(b->{
		if(b.getId()==bookId){
		  b.setTitle(book.getTitle());
		  b.setAuthor(book.getAuthor());
		}
		  return b;

		}).collect(Collectors.toList());
		}

}
