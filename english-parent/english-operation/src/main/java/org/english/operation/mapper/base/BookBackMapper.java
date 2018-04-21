package org.english.operation.mapper.base;

import java.util.List;

import org.english.operation.mapper.inter.Mapper;
import org.english.operation.model.po.BookBack;

public interface BookBackMapper extends Mapper<BookBack> {
	List<BookBack> go();
}