package com.digitalbooks.dto;

import java.util.Date;


public class BookVo {
	private Long bookId;
	private String bookTitle;
	private String bookCode;
	private String authorId;
	private Double price;
	private String category;
	private String publisher;
	private String logo;
	private String audiourl;
	private String bookContent;
	private Long isActive;
	private Date updatedDt;
	private Long crtUsr;// userIDpk
	private Date createdDt;

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getBookCode() {
		return bookCode;
	}

	public void setBookCode(String bookCode) {
		this.bookCode = bookCode;
	}

	public String getAuthorId() {
		return authorId;
	}

	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getAudiourl() {
		return audiourl;
	}

	public void setAudiourl(String audiourl) {
		this.audiourl = audiourl;
	}

	public String getBookContent() {
		return bookContent;
	}

	public void setBookContent(String bookContent) {
		this.bookContent = bookContent;
	}

	public Long getIsActive() {
		return isActive;
	}

	public void setIsActive(Long isActive) {
		this.isActive = isActive;
	}

	public Date getUpdatedDt() {
		return updatedDt;
	}

	public void setUpdatedDt(Date updatedDt) {
		this.updatedDt = updatedDt;
	}

	public Long getCrtUsr() {
		return crtUsr;
	}

	public void setCrtUsr(Long crtUsr) {
		this.crtUsr = crtUsr;
	}

	public Date getCreatedDt() {
		return createdDt;
	}

	public void setCreatedDt(Date createdDt) {
		this.createdDt = createdDt;
	}

	@Override
	public String toString() {
		return "BookVo [bookId=" + bookId + ", bookTitle=" + bookTitle + ", bookCode=" + bookCode + ", authorId="
				+ authorId + ", price=" + price + ", category=" + category + ", publisher=" + publisher + ", logo="
				+ logo + ", audiourl=" + audiourl + ", bookContent=" + bookContent + ", isActive=" + isActive
				+ ", updatedDt=" + updatedDt + ", crtUsr=" + crtUsr + ", createdDt=" + createdDt + "]";
	}

	public BookVo(Long bookId, String bookTitle, String book_code, String author_id, Double price, String category,
			String publisher, String logo, String audiourl, String bookContent, Long is_active, Date updatedDt,
			Long created_by, Date createdDt) {
		this(bookTitle, book_code, author_id, price, category, publisher, logo, bookContent, audiourl, is_active,
				updatedDt, created_by, createdDt);
		this.bookId = bookId;

	}

	public BookVo() {
		super();
	}

	public BookVo(String bookTitle, String bookCode, String authorId, Double price, String category, String publisher,
			String logo, String audiourl, String bookContent, Long isActive, Date updatedDt, Long crtUsr,
			Date createdDt) {
		this.bookTitle = bookTitle;
		this.bookCode = bookCode;
		this.authorId = authorId;
		this.price = price;
		this.category = category;
		this.publisher = publisher;
		this.logo = logo;
		this.audiourl = audiourl;
		this.bookContent = bookContent;
		this.isActive = isActive;
		this.updatedDt = updatedDt;
		this.crtUsr = crtUsr;
		this.createdDt = createdDt;
	}

}
