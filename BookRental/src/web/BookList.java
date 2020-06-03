package web;
import java.util.ArrayList;

public class BookList {
	private ArrayList<Integer> CodeList = new ArrayList<Integer>();
	private ArrayList<String> titleList = new ArrayList<String>();
	private ArrayList<String> writerList = new ArrayList<String>();
	private ArrayList<Integer> priceList = new ArrayList<Integer>();
	private ArrayList<Integer> rentList = new ArrayList<Integer>();
	private ArrayList<Integer> countList = new ArrayList<Integer>();
	private boolean firstPage = false;
	private boolean lastPage = false;
	private int pageNum;
	
	public BookList() {
	}
	
	public void setCode(int index, Integer Code) {
		this.CodeList.add(index, Code);
	}
	public void setTitle(int index, String title) {
		this.titleList.add(index, title);
	}
	public void setWriter(int index, String writer) {
		this.writerList.add(index, writer);
	}
	public void setPrice(int index, Integer price) {
		this.priceList.add(index, price);
	}
	public void setRent(int index, Integer rent) {
		this.rentList.add(index, rent);
	}
	public void setCount(int index, Integer count) {
		this.countList.add(index, count);
	}
	public void setFirstPage(boolean firstPage) {
		this.firstPage = firstPage;
	}
	public void setLastPage(boolean lastPage) {
		this.lastPage = lastPage;
	}
	public Integer[] getCode() {
		return CodeList.toArray(new Integer[CodeList.size()]);
	}
	public String[] getTitle() {
		return titleList.toArray(new String[titleList.size()]);
	}
	public String[] getWriter() {
		return writerList.toArray(new String[writerList.size()]);
	}
	public Integer[] getPrice() {
		return priceList.toArray(new Integer[priceList.size()]);
	}
	public Integer[] getRent() {
		return rentList.toArray(new Integer[rentList.size()]);
	}
	public Integer[] getCount() {
		return countList.toArray(new Integer[countList.size()]);
	}
	public boolean isFirstPage() {
		return firstPage;
	}
	public boolean isLastPage() {
		return lastPage;
	}
	public int getListSize() {
		return CodeList.size();
	}	
	public void setPageNum(int pageNum) { 
		this.pageNum = pageNum;
	}
	public int getPageNum() { 
		return pageNum;
	}
}
