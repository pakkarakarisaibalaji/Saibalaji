package com.digitalbooks.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digitalbooks.dto.ReaderVo;
import com.digitalbooks.model.BookVo;
import com.digitalbooks.service.BookService;

@RestController
@RequestMapping("/book")
@CrossOrigin
public class BookController {
	@Autowired
	private BookService bookService;

	@PostMapping(value="/author/{authorId}/books")
	public ResponseEntity<BookVo> createBook(@RequestBody BookVo book,@PathVariable String authorId) throws Exception{
		
		try {
			book= bookService.createBook(book,authorId);
			return ResponseEntity.status(200).body(book);
			
		}catch(Exception e) {
			throw new Exception("insertUserData()--->",e);
		}
	}

	@PostMapping(value="/author/{authorId}/books/{bookId}")
	public ResponseEntity<BookVo> updateBook(@RequestBody BookVo book,@PathVariable String authorId,@PathVariable String bookId) throws Exception{
		
		try {
			book= bookService.updateBook(book,authorId,bookId);
			return ResponseEntity.status(200).body(book);
			
		}catch(Exception e) {
			throw new Exception("insertUserData()--->",e);
		}
	}
	
	@GetMapping("/search/{category}/{title}/{author}/{price}/{publisher}")
	public ResponseEntity<List<BookVo>> searchBooks(@PathVariable Map<String, String> pathVarsMap) throws Exception{
		List<BookVo> books=null; 
		 String category= pathVarsMap.get("category");
		 String title= pathVarsMap.get("title");
		 String author= pathVarsMap.get("author");
		 String price= pathVarsMap.get("price");
		 String publisher= pathVarsMap.get("publisher");
		try {
			books= bookService.searchBook(category,title,author,price,publisher);
			return ResponseEntity.status(200).body(books);
			
		}catch(Exception e) {
			throw new Exception("Search ERROR()--->",e);
		}
		
		
	}
	
	@PostMapping("/{bookId}/subscribe") 
	public  ResponseEntity<String> subscribeBook(@RequestBody ReaderVo reader,  @PathVariable String bookId) throws Exception{
		try {
			boolean sub= bookService.subscribeBook(bookId,reader);
			if(sub)
			return ResponseEntity.status(200).body("Successfully Subscribed...");
			else
				throw new Exception("Not Susbscribed!!!");
			
		}catch(Exception e) {
			throw new Exception("Not Susbscribed!!!",e);
		}
	}
	
	
	@GetMapping("/readers/{emailId}/books")
	public ResponseEntity<List<BookVo>> getAllSubscribeBooksByReader(@PathVariable String emailId) throws Exception{
		if(emailId!=null&&!emailId.equalsIgnoreCase("")) {
			try {
			List<BookVo> subscribBooksByReader=bookService.getAllSubscribeBooksByReader(emailId);
			return ResponseEntity.status(200).body(subscribBooksByReader);
			}catch(Exception e) {
				throw new Exception("Something went wrong plese try after some time!!!");
			}
		}
		else {
			throw new Exception("Data Missing!..");
		}
	}
	@GetMapping("/readers/{emailId}/books/{subscriptionId}")
	public ResponseEntity<BookVo> getSubscribeBookByReaderEmailId(@PathVariable String emailId,@PathVariable String subscriptionId) throws Exception{
		if(emailId!=null&&!emailId.equalsIgnoreCase("")) {
			try {
			BookVo subscribBookByReader=bookService.getSubscribeBookByReader(emailId,subscriptionId);
			return ResponseEntity.status(200).body(subscribBookByReader);
			}catch(Exception e) {
				throw new Exception("Something went wrong plese try after some time!!!");
			}
		}
		else {
			throw new Exception("Data Missing!..");
		}
	}
	
	
	@PostMapping("/readers/{readerId}/books/{subscriptionId}/cancel-subscription")
	public ResponseEntity<String>cancleSubscriptionWithIn24Hours(@PathVariable String readerId,@PathVariable String subscriptionId ) throws Exception{
		if(readerId!=null &&!readerId.equalsIgnoreCase("") && !subscriptionId.equalsIgnoreCase("") &&subscriptionId!=null) {
			boolean cancle=bookService.cancleSubscriptionWithIn24Hours(readerId,subscriptionId);
			if(cancle) {
				return ResponseEntity.status(200).body("Subscription Cancle Sucessfully...");
			}
			else {
				throw new Exception("Something went wrong plese try after some time!!!");
			}
		}
		else {
			return ResponseEntity.status(400).body("Data Missing!..");
		}
	}
	
	@PostMapping("author/{authorId}/books/{bookId}/block={block}")
	public ResponseEntity<String> blockOrUnBlockBookByAuthor(@PathVariable(value="authorId") String authorId,@PathVariable(value="bookId") String bookId,@PathVariable(value="block") String block) throws Exception{
		if(block!=null&&!block.equalsIgnoreCase("")&&authorId!=null&&bookId!=null) {
			try {
			boolean update=bookService.blockOrUnBlockBookByAuthor(authorId,bookId,block);
			if(update) {
				if(block.equalsIgnoreCase("Yes"))
				return ResponseEntity.status(200).body("Successfully Blocked...");
				else
					return ResponseEntity.status(200).body("Successfully Unblocked...");
			}
			else {
				throw new Exception("Something went wrong plese try after some time!!!");
			}
			}catch(Exception e) {
				throw new Exception("Something went wrong plese try after some time!!!",e);
			}
		}
		else {
			return ResponseEntity.status(400).body("Data Missing!.. authorId:"+authorId+"bookId:"+bookId+"block:"+block);
		}
	}

	
	
}
