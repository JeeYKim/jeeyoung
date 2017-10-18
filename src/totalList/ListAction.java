package totalList;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import com.opensymphony.xwork2.ActionSupport;

import model.cmnVO;
import model.memberVO;
import model.noticeVO;
import model.qnaVO;
import model.recipeVO;
import model.tipVO;

public class ListAction extends ActionSupport{

	public static Reader reader;
	public static SqlMapClient sqlMapper;
	
	private List list;
	
	private int currentPage = 1;	//현재페이지
	private int totalCount;			//총 게시물의 수
	private int blockCount;			//한 페이지의 게시물의 수
	private int blockPage = 5;		//한 화면에 보여줄 페이지 수
	private String pagingHtml;		//페이징을 구현한 HTML
	private pagingAction page;		//페이징 클래스
	private String find = "";		//검색 내용
	private String userReq = "";	//사용자가 요청하는 리스트 이름;
	private String userType = "";	//유저 유형(일반, 관리자)
	private int mem_lev = 0;
	String mem_id = "";
	
	public ListAction() throws IOException {
		
		reader = Resources.getResourceAsReader("sqlMapConfig.xml");
		sqlMapper = SqlMapClientBuilder.buildSqlMapClient(reader);
		
		reader.close();
	}
	
	//사용자의 요청을 파악해서 원하는 메서드를 호출하는 메서드
	public String execute() throws Exception{
		
		if(getUserReq().equals("member")){
			list = complete(memberList());
		} else if(getUserReq().equals("Recipe")){
			list = complete(RecipeList());
		} else if(getUserReq().equals("cmnList")){
			list = complete(cmnList());
		} else if(getUserReq().equals("tipList")){
			list = complete(tipList());
		} else if(getUserReq().equals("qnaList")){
			list = complete(qnaList());
		} else if(getUserReq().equals("noticeList")){
			list = complete(noticeList());
		}
		
		return SUCCESS;
	}
	
	public List memberList() throws Exception{
		
		blockCount = 10;	//회원 목록은 10개씩
		list = new ArrayList<memberVO>();
		list = sqlMapper.queryForList("selectMemAll");
		
		return list;
	}
	
	public List qnaList() throws Exception{
		
		blockCount = 10;	//qna리스트는 한페이지에 10개씩
		list = new ArrayList<qnaVO>();
		
		if (find==null || find.equals("")) {
			list = sqlMapper.queryForList("selectNoticeAll");
		} else {
			list = sqlMapper.queryForList("NoticeFindSelectAll", "%"+getFind()+"%"); //검색 했을때.
		}
		
		return list;
		
	}
	
	public List RecipeList() throws Exception{
		blockCount = 9;
		list = new ArrayList<recipeVO>();
		
		list = sqlMapper.queryForList("selectRecipeAll");
		
		return list;
		
	}
	
	public List cmnList() throws Exception{
		blockCount = 10;
		list = new ArrayList<cmnVO>();

		if (find==null || find.equals("")) {
			list = sqlMapper.queryForList("selectCmnAll");
		} else {
			list = sqlMapper.queryForList("CmnFindSelectAll", "%"+getFind()+"%"); //검색 했을때.
		}
		
		return list;
	}
	
	public List tipList() throws Exception{
		blockCount = 10;
		list = new ArrayList<tipVO>();

		if (find==null || find.equals("")) {
			list = sqlMapper.queryForList("selectTipAll");
		} else {
			list = sqlMapper.queryForList("TipFindSelectAll", "%"+getFind()+"%"); //검색 했을때.
		}
		
		return list;
	}
	
	public List noticeList() throws Exception{
		blockCount = 10;
		list = new ArrayList<noticeVO>();

		if (find==null || find.equals("")) {
			list = sqlMapper.queryForList("selectNoticeAll");
		} else {
			list = sqlMapper.queryForList("NoticeFindSelectAll", "%"+getFind()+"%"); //검색 했을때.
		}
		
		return list;
	}
	
	public List complete(List list) throws Exception {
		
		totalCount = list.size(); 
		page = new pagingAction(currentPage, totalCount, blockCount, blockPage, userType, userReq , getFind());
		
		pagingHtml = page.getPagingHtml().toString(); 
		int lastCount = totalCount;

		if (page.getEndCount() < totalCount) {
			lastCount = page.getEndCount();
		}
		
		list = list.subList(page.getStartCount(), lastCount); 
		
		return list;
	}
	
	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getBlockCount() {
		return blockCount;
	}

	public void setBlockCount(int blockCount) {
		this.blockCount = blockCount;
	}

	public int getBlockPage() {
		return blockPage;
	}

	public void setBlockPage(int blockPage) {
		this.blockPage = blockPage;
	}

	public String getPagingHtml() {
		return pagingHtml;
	}

	public void setPagingHtml(String pagingHtml) {
		this.pagingHtml = pagingHtml;
	}

	public pagingAction getPage() {
		return page;
	}

	public void setPage(pagingAction page) {
		this.page = page;
	}

	public String getFind() {
		return find;
	}

	public void setFind(String find) {
		this.find = find;
	}

	public String getUserReq() {
		return userReq;
	}

	public void setUserReq(String userReq) {
		this.userReq = userReq;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public int getMem_lev() {
		return mem_lev;
	}

	public void setMem_lev(int mem_lev) {
		this.mem_lev = mem_lev;
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	
	
	
	
	
}
