package model;

import java.util.Date;

public class recipeVO {
	
	private String id;                  //멤버아이디
	   private int no;                  //레서피번호
	   private String subject;            //제목
	   private String r_category;         //레서피 카테고리
	   private String count;               //조회수
	   private Date regdate;            //등록일자
	   private String content;         //레서피 요약 내용
	   private String thumbnail;
	   private String img;
	   private String text1;
	   private String text2;
	   private String text3;
	   private String text4;
	   private String text5;
	   private String text6;
	   private String text7;
	   private String text8;
	   private String text9;
	   private Date b_regdate;
	   private String r_ingr;
	   private int totalgrade;
	   
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getText1() {
		return text1;
	}
	public void setText1(String text1) {
		this.text1 = text1;
	}
	public String getText2() {
		return text2;
	}
	public void setText2(String text2) {
		this.text2 = text2;
	}
	public String getText3() {
		return text3;
	}
	public void setText3(String text3) {
		this.text3 = text3;
	}
	public String getText4() {
		return text4;
	}
	public void setText4(String text4) {
		this.text4 = text4;
	}
	public String getText5() {
		return text5;
	}
	public void setText5(String text5) {
		this.text5 = text5;
	}
	public String getText6() {
		return text6;
	}
	public void setText6(String text6) {
		this.text6 = text6;
	}
	public String getText7() {
		return text7;
	}
	public void setText7(String text7) {
		this.text7 = text7;
	}
	public String getText8() {
		return text8;
	}
	public void setText8(String text8) {
		this.text8 = text8;
	}
	public String getText9() {
		return text9;
	}
	public void setText9(String text9) {
		this.text9 = text9;
	}

	public Date getB_regdate() {
		return b_regdate;
	}
	public void setB_regdate(Date b_regdate) {
		this.b_regdate = b_regdate;
	}
	
	public int getTotalgrade() {
		return totalgrade;
	}
	public void setTotalgrade(int totalgrade) {
		this.totalgrade = totalgrade;
	}
	public String getR_category() {
		return r_category;
	}
	public void setR_category(String r_category) {
		this.r_category = r_category;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getR_ingr() {
		return r_ingr;
	}
	public void setR_ingr(String r_ingr) {
		this.r_ingr = r_ingr;
	}
	
	   
	   
	   
}
