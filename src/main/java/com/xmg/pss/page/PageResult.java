package com.xmg.pss.page;

import java.util.List;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageResult {
	private List<?> listData;
	private Integer totalCount;
	private Integer currentPage;
	private Integer pageSize;
	private Integer totalPage;
	private Integer prePage;
	private Integer nextPage;

	public PageResult(List<?> listData, Integer totalCount,
			Integer currentPage, Integer pageSize) {
		this.listData = listData;
		this.totalCount = totalCount;
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.totalPage = totalCount % pageSize == 0 ? totalCount / pageSize
				: totalCount / pageSize + 1;
		this.prePage = currentPage > 1 ? currentPage - 1 : 1;
		this.nextPage = currentPage < totalPage ? currentPage + 1 : totalPage;
	}

}
