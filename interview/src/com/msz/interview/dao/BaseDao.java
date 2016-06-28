package com.msz.interview.dao;

import java.util.List;
import java.util.Map;

public interface BaseDao {

	public int deleteAll();

	public int setAllUnavailable();

	public int deleteAllUnavailable();

	public int setRecordAvailable(Map<String, String> map);
	
}
