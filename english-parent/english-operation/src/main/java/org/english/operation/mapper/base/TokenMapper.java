package org.english.operation.mapper.base;

import org.english.operation.mapper.inter.Mapper;
import org.english.operation.model.po.Token;

public interface TokenMapper extends Mapper<Token> {
	void deleteToken();
}