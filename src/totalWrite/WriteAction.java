package totalWrite;

import model.*;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import com.opensymphony.xwork2.ActionSupport;

public class WriteAction extends ActionSupport implements ServletRequestAware{

	public static Reader reader;
	public static SqlMapClient sqlMapper;
	
	private int currentPage;
	HttpServletRequest request;
	Calendar today = Calendar.getInstance();
	
	//tip, cmn, notice, qna, recipe
	private String userReq;
	private String modifyReq="";
	
	private int no;
	private String subject;
	private String content;
	private String id;
	private Date regdate;
	private String r_ingr;
	private int ref;
	private int re_step;
	private int re_level;
	
	boolean reply = false;
	
	private String session_ID;

	//tip, cmn만 해당
	private File upload;
	private String uploadContentType; 
	private String uploadFileName; 
	private String fileUploadPath;
	
	//recipe
	private String r_category;
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
	private int totalgrade;
	
	private List<File> uploads = new ArrayList<File>();
	private List<String> uploadsContentType = new ArrayList<String>();
	private List<String> uploadsFileName = new ArrayList<String>();

	private List<String> ingr = new ArrayList<String>();
	private List<String> category = new ArrayList<String>();
   
	private int index;  //이미지
	private int index2; //카테고리
	private int index3; //재료

	
	public WriteAction() throws IOException	{
		reader = Resources.getResourceAsReader("sqlMapConfig.xml");
		sqlMapper = SqlMapClientBuilder.buildSqlMapClient(reader);
		reader.close();
	}
	
	public String form() throws Exception{
		return SUCCESS;
	}
	
	@SuppressWarnings("deprecation")
	public String execute() throws Exception{
		regdate = today.getTime();
		fileUploadPath = request.getRealPath("/image/" + getUserReq() + "/");
				
		if(getUserReq().equals("tip")){
			tipWrite();
		}else if(getUserReq().equals("cmn")){
			cmnWrite();
		}else if(getUserReq().equals("notice")){
			noticeWrite();
		}else if(getUserReq().equals("qna")){
			qnaWrite();
		}else if(getUserReq().equals("recipe")){
			recipeWrite();
		}
		
		return SUCCESS;
	}
	
	public void tipWrite() throws SQLException, IOException {
		
		tipVO paramClass = new tipVO();
		tipVO resultClass = new tipVO();
		
		//첨부파일을 선택했다면 파일을 업로드한다.
		if(getUpload() != null){
			if(no == 0 ) {
				no = (int) sqlMapper.queryForObject("tipSeqNo");
			} else {
				no = getNo();
			}
			img = uploadImg();
		}
		paramClass.setImg(img);
		paramClass.setRegdate(regdate);
		paramClass.setSubject(getSubject());
		paramClass.setContent(getContent());
		
		
		if(ref == 0){
	         paramClass.setRe_step(0);
	         paramClass.setRe_level(0);
	      }
	      else{
	         paramClass.setRef(getRef());
	         paramClass.setRe_step(getRe_step());
	         sqlMapper.update("TIP-updateReplyStep", paramClass);
	         
	         paramClass.setRe_step(getRe_step() + 1);
	         paramClass.setRe_level(getRe_level() + 1);
	         paramClass.setRef(getRef());
	         
	      }
		//등록할 항목 설정
				paramClass.setSubject(getSubject());
				paramClass.setId(getId());
				paramClass.setContent(getContent());
				paramClass.setRegdate(today.getTime());
				
		//등록 쿼리 수행
				if(getModifyReq().equals("")||getModifyReq()==null){
					if(ref==0){
			sqlMapper.insert("TIP-insertBoard", paramClass);
		}else{
			sqlMapper.update("TIP-insertBoardReply", paramClass);
		}
		}else{
			sqlMapper.update("TIP-updateBoard", paramClass);
		}
				
	
	}
	public void cmnWrite() throws SQLException, IOException{
		
		cmnVO paramClass = new cmnVO();
		cmnVO resultClass = new cmnVO();
		
		if (getUpload() != null) {
			if(no == 0 ) {
				no = (int) sqlMapper.queryForObject("CMNSeqNo");
			} else {
				no = getNo();
			}
			img = uploadImg();
		}
		paramClass.setImg(img);
		paramClass.setRegdate(regdate);
		paramClass.setSubject(getSubject());
		paramClass.setContent(getContent());
		
		
		if(ref == 0){
	         paramClass.setRe_step(0);
	         paramClass.setRe_level(0);
	      }
	      else{
	         paramClass.setRef(getRef());
	         paramClass.setRe_step(getRe_step());
	         sqlMapper.update("CMN-updateReplyStep", paramClass);
	         
	         paramClass.setRe_step(getRe_step() + 1);
	         paramClass.setRe_level(getRe_level() + 1);
	         paramClass.setRef(getRef());
	         
	      }
		//등록할 항목 설정
				paramClass.setSubject(getSubject());
				paramClass.setId(getId());
				paramClass.setContent(getContent());
				paramClass.setRegdate(today.getTime());
				
		//등록 쿼리 수행
				if(getModifyReq().equals("")||getModifyReq()==null){
					if(ref==0){
			sqlMapper.insert("CMN-insertBoard", paramClass);
		}else{
			sqlMapper.update("CMN-insertBoardReply", paramClass);
		}
		}else{
			sqlMapper.update("CMN-updateBoard", paramClass);
		}
				
		//첨부파일을 선택했다면 파일을 업로드한다.
		
				
	}
	
	public String uploadImg() throws IOException {
		
		String file_name = userReq + no;
		String file_ext = getUploadFileName().substring(getUploadFileName().lastIndexOf('.') 
			+ 1, getUploadFileName().length());
		
		File destFile = new File(fileUploadPath + file_name + "." + file_ext);
		
		FileUtils.copyFile(getUpload(), destFile);


		String resultName = file_name + "." + file_ext;
		
		return resultName;
		
	}

	
	public void noticeWrite() throws SQLException, IOException{
		noticeVO paramClass = new noticeVO(); 
		noticeVO resultClass = new noticeVO(); 
		
		paramClass.setSubject(getSubject());
		paramClass.setId(getId());
		paramClass.setContent(getContent());
		paramClass.setRegdate(today.getTime());	
		
		sqlMapper.insert("NOTICE-insertBoard", paramClass);

	}
	
	public void qnaWrite() throws SQLException, IOException{
		
		faqVO paramClass=new faqVO();
		faqVO resultClass=new faqVO();
		
		if(ref == 0){
	         paramClass.setRe_step(0);
	         paramClass.setRe_level(0);
	      }
	      else{
	         paramClass.setRef(getRef());
	         paramClass.setRe_step(getRe_step());
	         sqlMapper.update("FAQ-updateReplyStep", paramClass);
	         
	         paramClass.setRe_step(getRe_step() + 1);
	         paramClass.setRe_level(getRe_level() + 1);
	         paramClass.setRef(getRef());
	         
	      }
	      
		//
		paramClass.setSubject(getSubject());
		paramClass.setId(getId());
		paramClass.setContent(getContent());
		paramClass.setRegdate(today.getTime());
		
		//
		  if(ref == 0)
		         sqlMapper.insert("FAQ-insertBoard", paramClass);
		      else
		         sqlMapper.insert("FAQ-insertBoardReply", paramClass);
		
	}
	
	public void recipeWrite() throws SQLException, IOException{

	      recipeVO paramClass=new recipeVO();
	      recipeVO resultClass=new recipeVO();
	      
	      
	    	if (getUpload() != null) {
				if(no == 0 ) {
					no = (int) sqlMapper.queryForObject("RecipeSeqNo");
				} else {
					no = getNo();
				}
				img = uploadImg();
			}
	    	
	      //등록할 항목 설정
	      paramClass.setThumbnail(img.split(",")[0]);
	      paramClass.setSubject(getSubject());
	      paramClass.setId(getId());
	      paramClass.setContent(getContent());
	      
	      paramClass.setText1(getText1());
	      paramClass.setText2(getText2());
	      paramClass.setText3(getText3());
	      paramClass.setText4(getText4());
	      paramClass.setText5(getText5());
	      paramClass.setText6(getText6());
	      paramClass.setText7(getText7());
	      paramClass.setText8(getText8());
	      paramClass.setText9(getText9());
	      
	      paramClass.setImg(img);
	      paramClass.setRegdate(regdate);
	      
	      //등록 쿼리 수행
	      
	      sqlMapper.insert("insertRecipe", paramClass);
	      
	      //------------카테고리 시작-------------------//
	      resultClass = (recipeVO) sqlMapper.queryForObject("RecipeSeqNo");
	      	      
	      r_category = getCategory().get(0) + ",";
	      for(int i = 1; i<category.size(); i++){
	         r_category = r_category + getCategory().get(i) + ",";
	      }
	      index2 = r_category.lastIndexOf(',');
	      r_category = r_category.substring(0, index2);
	      paramClass.setNo(resultClass.getNo());
	      
	      paramClass.setR_category(r_category);
	      
	      sqlMapper.update("updateCategory", paramClass);
	      

	      //-------------카테고리 끝------------------//
	      
	      /*--------------재료 시작--------------------*/
	      r_ingr = getIngr().get(0)+",";
	      for(int i = 1; i<ingr.size(); i++) {
	    	  r_ingr = r_ingr + getIngr().get(i) + ",";
	      }
	      index3 = r_ingr.lastIndexOf(',');
	      r_ingr = r_ingr.substring(0, index3);
	      
	      paramClass.setNo(resultClass.getNo());
	      paramClass.setR_ingr(r_ingr);
	      
	      sqlMapper.update("updateIngr", paramClass);
	      /*-----------------재료 끝--------------------*/
	}

	
	
	public String getR_ingr() {
		return r_ingr;
	}

	public void setR_ingr(String r_ingr) {
		this.r_ingr = r_ingr;
	}

	public String getSession_ID() {
		return session_ID;
	}

	public void setSession_ID(String session_ID) {
		this.session_ID = session_ID;
	}

	public List<String> getIngr() {
		return ingr;
	}

	public List<File> getUploads() {
		return uploads;
	}

	public void setUploads(List<File> uploads) {
		this.uploads = uploads;
	}

	public List<String> getUploadsContentType() {
		return uploadsContentType;
	}

	public void setUploadsContentType(List<String> uploadsContentType) {
		this.uploadsContentType = uploadsContentType;
	}

	public List<String> getUploadsFileName() {
		return uploadsFileName;
	}

	public void setUploadsFileName(List<String> uploadsFileName) {
		this.uploadsFileName = uploadsFileName;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public Calendar getToday() {
		return today;
	}

	public void setToday(Calendar today) {
		this.today = today;
	}

	public String getUserReq() {
		return userReq;
	}

	public void setUserReq(String userReq) {
		this.userReq = userReq;
	}

	public String getModifyReq() {
		return modifyReq;
	}

	public void setModifyReq(String modifyReq) {
		this.modifyReq = modifyReq;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public int getRef() {
		return ref;
	}

	public void setRef(int ref) {
		this.ref = ref;
	}

	public int getRe_step() {
		return re_step;
	}

	public void setRe_step(int re_step) {
		this.re_step = re_step;
	}

	public int getRe_level() {
		return re_level;
	}

	public void setRe_level(int re_level) {
		this.re_level = re_level;
	}

	public boolean isReply() {
		return reply;
	}

	public void setReply(boolean reply) {
		this.reply = reply;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getFileUploadPath() {
		return fileUploadPath;
	}

	public void setFileUploadPath(String fileUploadPath) {
		this.fileUploadPath = fileUploadPath;
	}

	public String getR_category() {
		return r_category;
	}

	public void setR_category(String r_category) {
		this.r_category = r_category;
	}

	public List<String> getCategory() {
		return category;
	}

	public void setCategory(List<String> category) {
		this.category = category;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
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

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getIndex2() {
		return index2;
	}

	public void setIndex2(int index2) {
		this.index2 = index2;
	}

	public int getIndex3() {
		return index3;
	}

	public void setIndex3(int index3) {
		this.index3 = index3;
	}

	public void setIngr(List<String> ingr) {
		this.ingr = ingr;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		
	}

	
	
}























