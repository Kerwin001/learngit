package com.xmg.pss.mapper;

import java.util.List;

public interface BaseMapper<T> {
	void save(T t);

	void update(T t);

	void delete(Long id);

	T get(Long id);

	List<T> list();

	void batchDelete(List<Long> ids);
}
