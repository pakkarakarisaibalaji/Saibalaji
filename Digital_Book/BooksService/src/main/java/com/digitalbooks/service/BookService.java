package com.digitalbooks.service;

import java.util.List;

import com.digitalbooks.dto.ReaderVo;
import com.digitalbooks.model.BookVo;

public interface BookService {

	BookVo createBook(BookVo book, String authorId);

	BookVo updateBook(BookVo book, String authorId,String bookId) throws Exception;

	List<BookVo> searchBook(String category, String title, String author, String price, String publisher);

	boolean subscribeBook(String bookId, ReaderVo reader) throws Exception;

	boolean blockOrUnBlockBookByAuthor(String authorId, String bookId, String block) throws Exception;

	boolean cancleSubscriptionWithIn24Hours(String readerId, String subscriptionId) throws Exception;

	List<BookVo> getAllSubscribeBooksByReader(String emailId);

	BookVo getSubscribeBookByReader(String emailId, String subscriptionId) throws Exception;

}
