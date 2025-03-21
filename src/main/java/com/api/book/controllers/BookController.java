package com.api.book.controllers;

import java.util.List;
import java.util.Optional;

import com.api.book.entities.Book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.book.services.BookService;

@SuppressWarnings("unused")
@RestController
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	// get all books handlers
    
    @GetMapping("/books")
    
    //Using Normal
//    public List<Book> getBooks() {
    	
//    	Book book = new Book();
//    	book.setId(1230);
//    	book.setTitle("Java");
//    	book.setAuthor("JP");
//    
    
//        return  this.bookService.getAllBooks();
//    }
    
    //Using Status 
    public  ResponseEntity<List<Book>>getBooks(){
      List<Book> list = bookService.getAllBooks();
      if(list.size()<=0){
       return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    return ResponseEntity.of(Optional.of(list));
    }

    
    //  get single book handlers
    @GetMapping("/books/{id}")
    
    //Using Normal method
//    public  Book  getBook(@PathVariable("id")int id ) {
//    return  bookService.getBookById(id);
//    }
//    
    public ResponseEntity<Book>getBook(@PathVariable("id") int id){
    	  Book book= bookService.getBookById(id);
    	  if(book==null){
    	   return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    	}
    	return ResponseEntity.of(Optional.of(book));
    	}
    
    
    
    // new book handlers

    @PostMapping("/books")
    //Using  normal
//    public  Book addBook(@RequestBody Book book){
//       Book b= this.bookService.addBook(book);
//        System.out.println(book);
//         return b;
//    }

    
    public ResponseEntity<Book> addBook(@RequestBody Book book){
    	Book b=null;
    	try{
    	b=this.bookService.addBook(book);
    	System.out.println(book);
    	return ResponseEntity.of(Optional.of(b));
    	}
    	catch(Exception e){
    	//TODO:handle exception
    	e.printStackTrace();
    	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    	}
    }

    
     // delete book handlers
    
    @DeleteMapping("/books/{bookId}")
    //Using normal
    
//    public void  deleteBook(@PathVariable("bookId") int  bookId) {
//    	this.bookService.deleteBook(bookId);
//    	
//    }
    public ResponseEntity<Void> deleteBook(@PathVariable("bookId") int bookId){
    	  try{
    	   this.bookService.deleteBook(bookId);
    	   return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    	}
    	catch(Exception e){
    	   e.printStackTrace();
    	  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    	}
    	}
    

    // update book handlers
    
    @PutMapping("/books/{bookId}")
    
    //Using  normal
//    public  Book updateBook(@RequestBody Book book,@PathVariable("bookId") int bookId){
//    this.bookService.updateBook(book, bookId);
//    return book;
//    
//    }
//    
 
    public ResponseEntity<Book> updateBook(@RequestBody Book  book, @PathVariable("bookId") int bookId){
    	  try{
    	this.bookService.updateBook(book,bookId);
    	return ResponseEntity.ok().body(book);
    	}
    	catch(Exception e){
    	 e.printStackTrace();
    	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    	}
    	} 

}
