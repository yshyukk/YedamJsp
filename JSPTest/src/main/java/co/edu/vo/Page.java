package co.edu.vo;

public class Page {
	private int startPage; // 첫번째. 150page(11~20)
	private int endPage; // 마지막.
	private boolean prev;// 이전페이지
	private boolean next; // 다음페이지
	
	private int total; //data의 전체 건수
	private Criteria cri;//
	
	public Page(Criteria cri, int total) {
		this.cri = cri;
		this.total = total;
		this.endPage = (int)(Math.ceil(cri.getPageNum()/10.0) *10); //Math.ceil: 현재 값 
		this.startPage = this.endPage - 9;
		int realEnd = (int)(Math.ceil(total * 1.0)/cri.getAmount()); //175건의 데이터값이 있으면 18page가 마지막 페이지

		if(this.endPage > realEnd) 
			this.endPage = realEnd;
		
		this.prev = this.startPage >1;
		this.next = this.endPage < realEnd;
		
		
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

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Criteria getCri() {
		return cri;
	}

	public void setCri(Criteria cri) {
		this.cri = cri;
	}
}
