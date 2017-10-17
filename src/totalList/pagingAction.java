package totalList;

public class pagingAction {

	private int currentPage;	//현재 페이지
	private int totalCount;		//전체 게시물 수
	private int totalPage;		//전체 페이지 수
	private int blockCount;		//한 페이지의 게시물의 수
	private int blockPage;		//한 화면에 보여줄 페이지의 수
	private int startCount;		//한 페이지에서 보여줄 게시글의 시작 번호
	private int endCount;		//한 페이지에서 보여줄 게시글의 끝 번호
	private int startPage;		//한 화면에 보여줄 시작페이지
	private int endPage;		//한 화면에 보여줄 마지막 페이지
	
	private String userType;	
	private String userReq;
	private String find;
	
	private StringBuffer pagingHtml;
	
	public pagingAction(int currentPage, int totalCount, int blockCount, int blockPage, 
						String userType, String userReq, String find) {
		
		this.blockCount = blockCount;
		this.blockPage = blockPage;
		this.currentPage = currentPage;
		this.totalCount = totalCount;
		this.find = find;
		
		if (find == null || find.equals("null")){
			find = "";
		}
		
		totalPage = (int)Math.ceil((double)totalCount / blockCount);
		
		if (totalPage == 0){
			totalPage = 1;
		}
		
		if (currentPage > totalPage){
			currentPage = totalPage;
		}
		
		startCount = (currentPage - 1) * blockCount;
		endCount = (startCount + blockCount);
		
		startPage = (int)((currentPage - 1) / blockPage) * blockPage + 1;
		endPage = startPage + blockPage -1;
		
		if (endPage > totalPage) {
			endPage = totalPage;
		}
		
		pagingHtml = new StringBuffer();
		
		if (currentPage > blockPage) {
			pagingHtml.append("<a href=" + userType + userReq + ".action?currentPage=" + (startPage -1) + "&find=" + find + ">");
			pagingHtml.append("이전");
			pagingHtml.append("</a>");
		}
		
		pagingHtml.append("&nbsp;|%nbsp;");
		
		for (int i = startPage; i <= endPage; i++){
			if (i > totalPage){
				break;
			}
			if (i == currentPage){
				pagingHtml.append("&nbsp;<b><font color='red'>");
				pagingHtml.append(i);
				pagingHtml.append("</font></b>");
			}else{
				pagingHtml.append("&nbsp;<a href='" + userType + userReq + ".action?currentPage=");
				pagingHtml.append(i);
				pagingHtml.append("&find=" + find + "'>");
				pagingHtml.append(i);
				pagingHtml.append("</a>");
			}
			pagingHtml.append("&nbsp;");
		}
		pagingHtml.append("&nbsp;&nbsp;|&nbsp;&nbsp;");
		
		if (totalPage - startPage >= blockPage){
			pagingHtml.append("<a href=" + userType + userReq + ".action?currentPage=" + (endPage + 1) + "&find" + find + ">");
			pagingHtml.append("다음");
			pagingHtml.append("</a>");
		}
		
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

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
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

	public int getStartCount() {
		return startCount;
	}

	public void setStartCount(int startCount) {
		this.startCount = startCount;
	}

	public int getEndCount() {
		return endCount;
	}

	public void setEndCount(int endCount) {
		this.endCount = endCount;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}



	public StringBuffer getPagingHtml() {
		return pagingHtml;
	}

	public void setPagingHtml(StringBuffer pagingHtml) {
		this.pagingHtml = pagingHtml;
	}
	
}
















