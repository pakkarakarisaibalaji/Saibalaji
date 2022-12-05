package com.digitalbooks.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.digitalbooks.model.BookVo;

@Repository
public interface BookRepository extends JpaRepository<BookVo, Long> {

	@Query(value="select * from tbl_book b where block=0 and b.category=?1 and b.book_title =?2 and b.author_id=?3 and b.price=?4 and b.publisher=?5" , nativeQuery = true)
	List<BookVo> searchBook(String category, String title, String author, String price, String publisher);

//	@Query(value="select b from tbl_book b where b.block=0 and b.book_id in(select s.book_id from tbl_subscribe s where s.email_id=?1)" , nativeQuery = true)
//	List<BookVo> getAllSubscribeBooksByReader(String emailId);

//	@Query("select book_id,book_title,book_code,author_id,price,category,publisher,book_logo,audio_url,book_content,is_active,upd_dt,crt_usr,crt_dt,block,subscription_Count from tbl_book where book_id in(select book_id from tbl_subscribe where email_id=:emailId and subscribe_id=:subscriptionId)")
//	BookVo getSubscribeBookByReader(String emailId, String subscriptionId);

}
