package com.digitalbooks.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitalbooks.dto.ReaderVo;
import com.digitalbooks.model.BookVo;
import com.digitalbooks.model.SubscribeBookVo;
import com.digitalbooks.repository.BookRepository;
import com.digitalbooks.repository.SubscribeBookRepository;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private SubscribeBookRepository subscribeBookRepository;

	@Override
	public BookVo createBook(BookVo book, String authorId) {
		book.setCreatedDt(new Date());
		if (book.getBlock() == null || book.getBlock() > 1)
			book.setBlock(0L);
		if (book.getPrice() == null || book.getPrice() < 0)
			book.setPrice(0.0);
		return bookRepository.save(book);
	}

	@Override
	public BookVo updateBook(BookVo book, String authorId, String bookId) throws Exception {
		if (bookId != null && !bookId.equalsIgnoreCase("")) {
			Optional<BookVo> oBook = bookRepository.findById(Long.parseLong(bookId));
			if (oBook.get().getAuthorId().equalsIgnoreCase(authorId)) {
				if (book.getLogo() != null)
					oBook.get().setLogo(book.getLogo());
				if (book.getBookTitle() != null && !book.getBookTitle().equalsIgnoreCase(""))
					oBook.get().setBookTitle(book.getBookTitle());
				if (book.getCategory() != null && !book.getCategory().equalsIgnoreCase(""))
					oBook.get().setCategory(book.getCategory());
				if (book.getPrice() != null && book.getPrice() >= 0)
					oBook.get().setPrice(book.getPrice());
				if (book.getAuthorId() != null && !book.getAuthorId().equalsIgnoreCase(""))
					oBook.get().setAuthorId(book.getAuthorId());
				if (book.getPublisher() != null && !book.getPublisher().equalsIgnoreCase(""))
					oBook.get().setPublisher(book.getPublisher());
				if (book.getPublisher() != null && !book.getPublisher().equalsIgnoreCase(""))
					oBook.get().setPublisher(book.getPublisher());
				if (book.getBookContent() != null && !book.getBookContent().equalsIgnoreCase(""))
					oBook.get().setBookContent(book.getBookContent());
				if (book.getIsActive() != null && book.getIsActive() > 2 && book.getIsActive() <= 0)
					oBook.get().setIsActive(book.getIsActive());
				oBook.get().setUpdatedDt(new Date());

				return bookRepository.save(oBook.get());
			} else {
				throw new Exception("Author Id is not matching with this book!!!");
			}

		} else {
			throw new Exception("Can not find book with id: " + bookId);
		}
	}

	@Override
	public List<BookVo> searchBook(String category, String title, String author, String price, String publisher) {
		return bookRepository.searchBook(category, title, author, price, publisher);

	}

	@Override
	public boolean subscribeBook(String bookId, ReaderVo reader) throws Exception {
		if (bookId != null && !bookId.equalsIgnoreCase("")) {
			Optional<BookVo> book = bookRepository.findById(Long.parseLong(bookId));
			if (!book.isEmpty()) {
				List<SubscribeBookVo> books = subscribeBookRepository.findByReaderId(reader.getReaderId());
				boolean notsubscribe = true;
				for (SubscribeBookVo subbook : books) {
					if (subbook.getBookId() == Long.parseLong(bookId)) {
						notsubscribe = false;
					}
				}
				if (notsubscribe) {
					SubscribeBookVo sBook = new SubscribeBookVo();

					sBook.setBookId(Long.parseLong(bookId));
					sBook.setReaderId(reader.getReaderId());
					sBook.setEmailId(reader.getReaderEmail());
					sBook.setSubscriptionDt(new Date());
					sBook.setIsActive(1L);
					subscribeBookRepository.save(sBook);

					bookRepository.save(book.get());

					return true;
				} else {
					throw new Exception("Book is already Subscribed" + bookId);
				}
			} else {
				throw new Exception("Can not find book with id: " + bookId);
			}

		} else {
			throw new Exception("Can not find book with id: " + bookId);
		}

	}

	@Override
	public boolean blockOrUnBlockBookByAuthor(String authorId, String bookId, String block) throws Exception {
		if (bookId != null && !bookId.equalsIgnoreCase("")) {
			Optional<BookVo> book = bookRepository.findById(Long.parseLong(bookId));
			if (!book.isEmpty() && book.get().getAuthorId().equalsIgnoreCase(authorId)) {
				if (block != null && block.equalsIgnoreCase("Yes"))
					book.get().setBlock(1L);
				else
					book.get().setBlock(0L);
				bookRepository.save(book.get());
				return true;
			} else {
				throw new Exception("Can not find book with id: " + bookId);
			}

		} else {
			throw new Exception("Can not find book with id: " + bookId);
		}
	}

	@Override
	public boolean cancleSubscriptionWithIn24Hours(String readerId, String subscriptionId) throws Exception {

		if (subscriptionId != null && !subscriptionId.equalsIgnoreCase("")) {
			Optional<SubscribeBookVo> subscribeBook = subscribeBookRepository.findById(Long.parseLong(subscriptionId));

			if (!subscribeBook.isEmpty() && subscribeBook.get().getReaderId().equalsIgnoreCase(readerId)) {
				Date presentDate = new Date();
				;
				Date subscribDt = subscribeBook.get().getSubscriptionDt();
				long diffInHours = getDateDiff(subscribDt, presentDate, TimeUnit.HOURS);
				if (diffInHours < 24) {
					subscribeBook.get().setIsActive(0L);
					subscribeBookRepository.save(subscribeBook.get());

					return true;
				} else {
					throw new Exception("Can not Cancel Subscription After 24 Hours..." + subscriptionId);
				}
			} else {
				throw new Exception("Access Denied!.." + subscriptionId);
			}

		} else {
			throw new Exception("Can not find the Subscription for the  book with id: " + subscriptionId);
		}

	}

	@Override
	public List<BookVo> getAllSubscribeBooksByReader(String emailId) {

		return null; // bookRepository.getAllSubscribeBooksByReader(emailId);
	}

	@Override
	public BookVo getSubscribeBookByReader(String emailId, String subscriptionId) throws Exception {

		if (subscriptionId != null && !subscriptionId.equalsIgnoreCase("")) {
			Optional<SubscribeBookVo> subcribeBook = subscribeBookRepository.findById(Long.parseLong(subscriptionId));
			if (!subcribeBook.isEmpty() && subcribeBook.get() != null
					&& subcribeBook.get().getEmailId().equalsIgnoreCase(emailId)) {
				Optional<BookVo> optionalBook = bookRepository.findById(subcribeBook.get().getBookId());
				if (!optionalBook.isEmpty()) {
					return optionalBook.get();

				} else {
					throw new Exception("Can not find the book " + subscriptionId);
				}
			} else {
				throw new Exception("Can not find the Subscription id: " + subscriptionId);
			}

		} else {
			throw new Exception("Data missing..." + subscriptionId);
		}

	}

	public static long getDateDiff(final Date date1, final Date date2, final TimeUnit timeUnit) {
		long diffInMillies = date2.getTime() - date1.getTime();

		return timeUnit.convert(diffInMillies, timeUnit.MILLISECONDS);
	}

}
