package com.hw.mapper;

import java.util.List;

import com.hw.vo.Correlation;

public interface CorrelationMapper {
	public void insert(Correlation obj);
	public void delete(Integer id);
	public void update(Correlation obj);
	public Correlation select(Integer id);
	public List<Correlation> selectall();
}
