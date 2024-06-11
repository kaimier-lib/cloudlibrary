package com.ybvtc.common;
import java.io.Serializable;
import java.util.List;
/**
 * 分页结果的实体类
 */
public class PageResult implements Serializable{
	private long total; // 总数
	private List records; // 返回的数据集合
	
	public PageResult(long total, List records) {
		super();
		this.total = total;
		this.records = records;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public List getRecords() {
		return records;
	}
	public void setRecords(List records) {
		this.records = records;
	}
}
