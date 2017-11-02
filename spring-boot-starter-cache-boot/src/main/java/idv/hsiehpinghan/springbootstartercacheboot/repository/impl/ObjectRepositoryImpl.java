package idv.hsiehpinghan.springbootstartercacheboot.repository.impl;

import org.springframework.stereotype.Repository;

import idv.hsiehpinghan.springbootstartercacheboot.repository.ObjectRepository;

@Repository
public class ObjectRepositoryImpl implements ObjectRepository {
	public int newStringCount = 0;

	@Override
	public String getString(String string) {
		++newStringCount;
		return new String(string);
	}

	@Override
	public int getNewStringCount() {
		return newStringCount;
	}

}
