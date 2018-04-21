package org.english.operation.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.english.operation.exception.BussinessException;
import org.english.operation.mapper.base.BookMapper;
import org.english.operation.mapper.base.CourseMapper;
import org.english.operation.mapper.base.OpenBookRecordMapper;
import org.english.operation.mapper.base.RechargCardBookMapper;
import org.english.operation.mapper.base.RechargCardMapper;
import org.english.operation.mapper.base.TokenMapper;
import org.english.operation.mapper.base.UserBookMapper;
import org.english.operation.mapper.base.UserMapper;
import org.english.operation.model.dto.CourseTreeDto;
import org.english.operation.model.dto.UserBookDto;
import org.english.operation.model.po.Book;
import org.english.operation.model.po.Course;
import org.english.operation.model.po.OpenBookRecord;
import org.english.operation.model.po.RechargCard;
import org.english.operation.model.po.RechargCardBook;
import org.english.operation.model.po.Token;
import org.english.operation.model.po.User;
import org.english.operation.model.po.UserBook;
import org.english.operation.service.CourseService;
import org.english.operation.utils.DateUtils;
import org.english.operation.utils.IdGen;
import org.english.operation.utils.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;


@Service
@Transactional(rollbackFor = Exception.class)
public class CourseServiceImpl implements CourseService  {
	
	@Autowired
	private CourseMapper courseMapper;
	@Autowired
	private TokenMapper tokenMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserBookMapper userBookMapper;
	@Autowired
	private BookMapper bookMapper;
	@Autowired
	private RechargCardMapper rechargCardMapper;
	@Autowired
	private RechargCardBookMapper rechargCardBookMapper;
	@Autowired
	private OpenBookRecordMapper openBookRecordMapper;
	//得到所有的课程列表
	@Override
	public List<Course> getCourseModule(int i) throws BussinessException {
		try {
			Example example = new Example(Course.class);
			Criteria criteria = example.createCriteria();
			criteria.andEqualTo("parentId", i);
			List<Course> courseList = courseMapper.selectByExample(example);
			return courseList;
		} catch (RuntimeException  e) {
			throw new BussinessException(e);
		}
	}
	//得到所有的课程列表
	@Override
	public CourseTreeDto getCourseList() throws BussinessException {
		try {
			Example example = new Example(Course.class);
			Criteria criteria = example.createCriteria();
			criteria.andEqualTo("parentId", 1);
			List<Course> courseList = courseMapper.selectByExample(example);
			CourseTreeDto courseTreeDto = new CourseTreeDto();
			courseTreeDto.setParentCourse(courseList);
			Example example2 = new Example(Course.class);
			Criteria criteria2 = example2.createCriteria();
			criteria2.andEqualTo("parentId", 2);
			List<Course> courseList2 = courseMapper.selectByExample(example2);
			courseTreeDto.setSecondLevelCourse(courseList2);
			Example example3 = new Example(Course.class);
			Criteria criteria3 = example3.createCriteria();
			criteria3.andEqualTo("parentId", 3);
			List<Course> courseList3 = courseMapper.selectByExample(example3);
			courseTreeDto.setChildrenCourse(courseList3);
//			System.out.println(courseTreeDto);
			return courseTreeDto;
			
			
			
		} catch (RuntimeException  e) {
			throw new BussinessException(e);
		}
	}
	/**
	 * 选择课程
	 * @param id 课程id
	 * 
	 */
	@Override
	public void chooseFreeCourse(int courseId, String tokenStr) throws BussinessException {
		try {
			//根据token串查询用户id,用户状态(0 不可用  1未付费  2已付费 3 已使用)
			Example example = new Example(Token.class);
			Criteria criteria = example.createCriteria();
			criteria.andEqualTo("token", tokenStr);
			List<Token> list = tokenMapper.selectByExample(example);
			if(list== null || list.isEmpty()) {
				throw new BussinessException("用户token查询失败");
			}
			Token token = list.get(0);
			//得到用户的ID
			String userId = token.getId();
			//判断用户是否为未付费用户
			User user = userMapper.selectByPrimaryKey(userId);
			Integer status = user.getStatus();
			if(status!=1) {
				throw new BussinessException("用户已选过免费课程");
			}
			//根据课程id得到书本id
			Example bookExample = new Example(Book.class);
			Criteria bookeCriteria = bookExample.createCriteria();
			bookeCriteria.andEqualTo("courseId", courseId);
			List<Book> bookList= bookMapper.selectByExample(bookExample);
			Book book = bookList.get(0);
			Integer bookId = book.getId();
			//将用户免费选课信息插入到选课表中
			UserBook userBook = new UserBook();	
			userBook.setId(IdGen.getDefaultPrimaryKey());
			userBook.setUserId(userId);
			userBook.setCourseId(courseId);
			userBook.setBookId(bookId);
			//书本状态  1已开通  2已过期
			userBook.setStatus(1);
			//开通类型 1免费开通（此类型只返回2个单元可用）2付费开通
			userBook.setOpenType(1);
			userBook.setCreateTime(new Date());
			userBook.setActiveTime(7);
			userBook.setOverdueTime(DateUtils.addTime(new Date(), "7d"));
			userBookMapper.insert(userBook);
			//再更改user的状态 变为
			user.setStatus(3);
			userMapper.updateByPrimaryKeySelective(user);	
			
		} catch (RuntimeException  e) {
			throw new BussinessException(e);
		}
	}
	
	/**
	 * 用户已订阅课程
	 */
	@Override
	public List<UserBookDto> subCourse(String tokenStr) throws BussinessException {
		try {
			//根据token串查询用户id,用户状态(0 不可用  1未付费  2已付费 3 已使用)
			Example example = new Example(Token.class);
			Criteria criteria = example.createCriteria();
			criteria.andEqualTo("token", tokenStr);
			List<Token> list = tokenMapper.selectByExample(example);
			if(list== null || list.isEmpty()) {
				throw new BussinessException("用户token查询失败");
			}
			Token token = list.get(0);
			//得到用户的ID
			String userId = token.getId();
			//用户未过期课程
			List<UserBook> userBooklist = userBookMapper.checkoverdue(userId);
			List<UserBookDto> arrayList = Lists.newArrayList();
			for(UserBook userBook:userBooklist) {
				UserBookDto bookDto = new UserBookDto();
				bookDto.setUserBook(userBook);
				//根据过期时间和现在的时间得到还有多少天过期
				int days = DateUtils.days(new Date(), userBook.getOverdueTime());
				bookDto.setDay(days);
				arrayList.add(bookDto);
			}	
			return arrayList;
			
		} catch (RuntimeException  e) {
			throw new BussinessException(e);
		}
		
	}
	
	/**
	 * 用户订阅却已过期课程
	 */
	@Override
	public List<UserBookDto> subOverCourse(String tokenStr) throws BussinessException {
		try {
			//根据token串查询用户id,用户状态(0 不可用  1未付费  2已付费 3 已使用)
			Example example = new Example(Token.class);
			Criteria criteria = example.createCriteria();
			criteria.andEqualTo("token", tokenStr);
			List<Token> list = tokenMapper.selectByExample(example);
			if(list== null || list.isEmpty()) {
				throw new BussinessException("用户token查询失败");
			}
			Token token = list.get(0);
			//得到用户的ID
			String userId = token.getId();
			//用户过期课程
			List<UserBook> userBooklist = userBookMapper.overdue(userId);
			List<UserBookDto> arrayList = Lists.newArrayList();
			for(UserBook userBook:userBooklist) {
				UserBookDto bookDto = new UserBookDto();
				bookDto.setUserBook(userBook);
				//根据过期时间和现在的时间得到还有多少天过期
				int days = DateUtils.days(userBook.getOverdueTime(), new Date());
				bookDto.setDay(days);
				arrayList.add(bookDto);
				userBook.setStatus(2);
				userBookMapper.updateByPrimaryKeySelective(userBook);
			}	
			return arrayList;
				
		} catch (RuntimeException  e) {
			throw new BussinessException(e);
		}
		
	}
	
	/**
	 * 用户卡号开通课程
	 */
	@Override
	public void openCourse(String tokenStr, int courseId, String cardNo, String password) throws BussinessException {
		try {
			//根据token串查询用户id
			Example example = new Example(Token.class);
			Criteria criteria = example.createCriteria();
			criteria.andEqualTo("token", tokenStr);
			List<Token> list = tokenMapper.selectByExample(example);
			if(list== null || list.isEmpty()) {
				throw new BussinessException("用户token查询失败");
			}
			Token token = list.get(0);
			//得到用户的ID
			String userId = token.getId();
			
			Example cardExample = new Example(RechargCard.class);
			Criteria cardCriteria = cardExample.createCriteria();
			cardCriteria.andEqualTo("cardNo", cardNo);
			List<RechargCard> cardList = rechargCardMapper.selectByExample(cardExample);
			if(cardList ==null ||cardList.isEmpty()) {
				throw new BussinessException("卡号不存在");
			}
			RechargCard card = cardList.get(0);
			//status为1 代表此卡从前从未启用 没有绑定用户ID
			if(card.getStatus()==1) {
				Example cardExample1 = new Example(RechargCard.class);
				Criteria cardCriteria1 = cardExample1.createCriteria();
				//状态为1只比较卡号密码
				cardCriteria1.andEqualTo("cardNo", cardNo).andEqualTo("password", password);
				List<RechargCard> cardList1 = rechargCardMapper.selectByExample(cardExample1);
				if(cardList1 ==null || cardList1.isEmpty()) {
					throw new BussinessException("卡号密码不符");
				}
				RechargCard card1 = cardList1.get(0);
				card1.setStatus(2);
				card1.setUserId(userId);
				card1.setUserTime(new Date());
				rechargCardMapper.updateByPrimaryKey(card1);
				//判断卡号 type类型 (1.普通单门卡选A即A 2.普通多门卡 选ABC即ABC 3.vip卡可以从一定范围内选几门)
				Integer type = card1.getCardType();
				//得到充值卡Id去RechargCardBook查询 课程id
				Integer cardId = card1.getId();
				//开通天数
				Integer day = card1.getDay();
				//交易卡卡号
				String cardNo2 = card1.getCardNo();
				//卡号绑定的可使用课程门数(只针对vip卡  主要是需求太过奇葩，后期强行加的字段)
				Integer courseNumber = card1.getCourseNo();
				//根据前端传过来的课程id判断是否在RechargCardBook里切是未开通的
				if(type==1) {
					Example bookExample = new Example(RechargCardBook.class);
					Criteria bookCriteria = bookExample.createCriteria();
					bookCriteria.andEqualTo("rechargCardId", cardId).andEqualTo("rechargCardId",cardId);
					List<RechargCardBook> bookList = rechargCardBookMapper.selectByExample(bookExample);
					RechargCardBook rechargCardBook = bookList.get(0);
					Integer courseId2 = rechargCardBook.getCourseId();
					Integer courseType = rechargCardBook.getCourseType();
					if(courseId2==courseId && courseType==1) {
						//开通成功,并更改课程type为已开通
//						RechargCardBook rechargCardBook2 = new RechargCardBook();
						rechargCardBook.setCourseType(2);
//						rechargCardBook2.setCourseType(2);
						rechargCardBookMapper.updateByPrimaryKeySelective(rechargCardBook);
						//再将用户开通课程插入用户课程表
						//判断之前user_book表有没有开通过这个课程
						Example example15 = new Example(UserBook.class);
						Criteria criteria15 = example15.createCriteria();
						criteria15.andEqualTo("courseId", courseId).andEqualTo("userId", userId);
						List<UserBook> userBookList15 = userBookMapper.selectByExample(example15);
						if(userBookList15==null || userBookList15.isEmpty()) {
							//把开通成功的课程插入user_book,open_book_reecord表
							UserBook userBook = new UserBook();
							userBook.setId(IdGen.getDefaultPrimaryKey());
							userBook.setCourseId(courseId);
							userBook.setCreateTime(new Date());
							userBook.setOpenType(2);
							userBook.setActiveTime(day);
							userBook.setUserId(userId);
							userBook.setStatus(1);
							String days = Integer.toString(day)+"d";	
							userBook.setOverdueTime(DateUtils.addTime(new Date(), days));
							//根据课程id得到书本id
							Example bookExample4 = new Example(Book.class);
							Criteria bookeCriteria = bookExample4.createCriteria();
							bookeCriteria.andEqualTo("courseId", courseId);
							List<Book> bookList4= bookMapper.selectByExample(bookExample4);
							Book book = bookList4.get(0);
							Integer bookId = book.getId();
							userBook.setBookId(bookId);
							userBookMapper.insertSelective(userBook);
						}else {
							UserBook userBook = userBookList15.get(0);
							userBook.setStatus(1);
							userBook.setOpenType(2);
							userBook.setActiveTime(day);
							userBook.setCreateTime(new Date());
							String days = Integer.toString(day)+"d";				
							userBook.setOverdueTime(DateUtils.addTime(new Date(), days));
							userBookMapper.updateByPrimaryKey(userBook);
						
						}
						//再讲课程插入交易记录表
						OpenBookRecord openBookRecord = new OpenBookRecord();
						openBookRecord.setId(NumberUtils.createData(25));
						openBookRecord.setUserId(userId);
						openBookRecord.setPayType(1);
						openBookRecord.setTradeId(cardNo2);
						openBookRecord.setBusType(1);
						openBookRecord.setDayCount(day);
						openBookRecord.setStatus(2);
						openBookRecord.setPayTime(new Date());
						openBookRecord.setCreateTime(new Date());
						openBookRecordMapper.insertSelective(openBookRecord);
						
					}else {
						throw new BussinessException("开通失败，卡号已使用或者不包含此课程");
					}
				}
				//状态为2 代表ABC只能从ABC选
				if(type==2) {
					Example bookExample = new Example(RechargCardBook.class);
					Criteria bookCriteria = bookExample.createCriteria();
					bookCriteria.andEqualTo("rechargCardId", cardId);
					List<RechargCardBook> bookList = rechargCardBookMapper.selectByExample(bookExample);
					
					List<Integer> courseIdList=Lists.newArrayList();
					//把course_id提成一个新list来判断 传入得courseId是否在里面
					for(RechargCardBook book:bookList) {
						if(book.getCourseType()==1) {
							courseIdList.add(book.getCourseId());
						}
					}
					if(courseIdList.contains(courseId)) {
						
						Example bookExample1 = new Example(RechargCardBook.class);
						Criteria bookCriteria1 = bookExample1.createCriteria();
						bookCriteria1.andEqualTo("courseId", courseId);
						List<RechargCardBook> bookList1 = rechargCardBookMapper.selectByExample(bookExample1);
						RechargCardBook rechargCardBook = bookList1.get(0);
						rechargCardBook.setCourseType(2);
						rechargCardBookMapper.updateByPrimaryKeySelective(rechargCardBook);
						//判断之前user_book表有没有开通过这个课程
						Example example15 = new Example(UserBook.class);
						Criteria criteria15 = example15.createCriteria();
						criteria15.andEqualTo("courseId", courseId).andEqualTo("userId", userId);
						List<UserBook> userBookList15 = userBookMapper.selectByExample(example15);
						if(userBookList15==null || userBookList15.isEmpty()) {
							//把开通成功的课程插入user_book,open_book_reecord表
							UserBook userBook = new UserBook();
							userBook.setId(IdGen.getDefaultPrimaryKey());
							userBook.setCourseId(courseId);
							userBook.setCreateTime(new Date());
							userBook.setOpenType(2);
							userBook.setActiveTime(day);
							userBook.setUserId(userId);
							userBook.setStatus(1);
							String days = Integer.toString(day)+"d";	
							userBook.setOverdueTime(DateUtils.addTime(new Date(), days));
							//根据课程id得到书本id
							Example bookExample4 = new Example(Book.class);
							Criteria bookeCriteria = bookExample4.createCriteria();
							bookeCriteria.andEqualTo("courseId", courseId);
							List<Book> bookList4= bookMapper.selectByExample(bookExample4);
							Book book = bookList4.get(0);
							Integer bookId = book.getId();
							userBook.setBookId(bookId);
							userBookMapper.insertSelective(userBook);
						}else {
							UserBook userBook = userBookList15.get(0);
							userBook.setStatus(1);
							userBook.setOpenType(2);
							userBook.setActiveTime(day);
							userBook.setCreateTime(new Date());
							String days = Integer.toString(day)+"d";				
							userBook.setOverdueTime(DateUtils.addTime(new Date(), days));
							userBookMapper.updateByPrimaryKey(userBook);
						
						}
						//再讲课程插入交易记录表
						OpenBookRecord openBookRecord = new OpenBookRecord();
						openBookRecord.setId(NumberUtils.createData(25));
						openBookRecord.setUserId(userId);
						openBookRecord.setPayType(1);
						openBookRecord.setTradeId(cardNo2);
						openBookRecord.setBusType(1);
						openBookRecord.setDayCount(day);
						openBookRecord.setStatus(2);
						openBookRecord.setPayTime(new Date());
						openBookRecord.setCreateTime(new Date());
						openBookRecordMapper.insertSelective(openBookRecord);					
					}else {
						throw new BussinessException("开通失败，卡号已使用或者不包含此课程");
					}			
					
				}
				//代表为vip卡
				if(type==3) {
					//得到卡号所绑定的课程
					Example bookExample = new Example(RechargCardBook.class);
					Criteria bookCriteria = bookExample.createCriteria();
					bookCriteria.andEqualTo("rechargCardId", cardId).andEqualTo("rechargCardId",cardId);
					List<RechargCardBook> bookList = rechargCardBookMapper.selectByExample(bookExample);
					//得到目前已经开通的课程
					List<RechargCardBook> notUseCourseList=Lists.newArrayList();
					List<Integer> courseIdList=Lists.newArrayList();
					for(RechargCardBook book:bookList) {
						if(book.getCourseType()==2) {
							notUseCourseList.add(book);
						}
					}
					if(notUseCourseList.size()<courseNumber) {
						for(RechargCardBook book:bookList) {
							if(book.getCourseType()==1) {
								courseIdList.add(book.getCourseId());
							}	
						}
						if(courseIdList.contains(courseId)) {
							Example bookExample1 = new Example(RechargCardBook.class);
							Criteria bookCriteria1 = bookExample1.createCriteria();
							bookCriteria1.andEqualTo("courseId", courseId);
							List<RechargCardBook> bookList1 = rechargCardBookMapper.selectByExample(bookExample1);
							RechargCardBook rechargCardBook = bookList1.get(0);
							rechargCardBook.setCourseType(2);
							rechargCardBookMapper.updateByPrimaryKeySelective(rechargCardBook);
							//判断之前user_book表有没有开通过这个课程
							Example example15 = new Example(UserBook.class);
							Criteria criteria15 = example15.createCriteria();
							criteria15.andEqualTo("courseId", courseId).andEqualTo("userId", userId);
							List<UserBook> userBookList15 = userBookMapper.selectByExample(example15);
							if(userBookList15==null || userBookList15.isEmpty()) {
								//把开通成功的课程插入user_book,open_book_reecord表
								UserBook userBook = new UserBook();
								userBook.setId(IdGen.getDefaultPrimaryKey());
								userBook.setCourseId(courseId);
								userBook.setCreateTime(new Date());
								userBook.setOpenType(2);
								userBook.setActiveTime(day);
								userBook.setUserId(userId);
								userBook.setStatus(1);
								String days = Integer.toString(day)+"d";	
								userBook.setOverdueTime(DateUtils.addTime(new Date(), days));
								//根据课程id得到书本id
								Example bookExample4 = new Example(Book.class);
								Criteria bookeCriteria = bookExample4.createCriteria();
								bookeCriteria.andEqualTo("courseId", courseId);
								List<Book> bookList4= bookMapper.selectByExample(bookExample4);
								Book book = bookList4.get(0);
								Integer bookId = book.getId();
								userBook.setBookId(bookId);
								userBookMapper.insertSelective(userBook);
							}else {
								UserBook userBook = userBookList15.get(0);
								userBook.setStatus(1);
								userBook.setOpenType(2);
								userBook.setActiveTime(day);
								userBook.setCreateTime(new Date());
								String days = Integer.toString(day)+"d";				
								userBook.setOverdueTime(DateUtils.addTime(new Date(), days));
								userBookMapper.updateByPrimaryKey(userBook);
							
							}
							//再讲课程插入交易记录表
							OpenBookRecord openBookRecord = new OpenBookRecord();
							openBookRecord.setId(NumberUtils.createData(25));
							openBookRecord.setUserId(userId);
							openBookRecord.setPayType(1);
							openBookRecord.setTradeId(cardNo2);
							openBookRecord.setBusType(1);
							openBookRecord.setDayCount(day);
							openBookRecord.setStatus(2);
							openBookRecord.setPayTime(new Date());
							openBookRecord.setCreateTime(new Date());
							openBookRecordMapper.insertSelective(openBookRecord);		
						}else {
							throw new BussinessException("开通失败，卡号已使用或者不包含此课程");
						}
						
					}else {
						throw new BussinessException("可开通门数超限");
					}
					
					
				}
				
		
			}
			//状态为2
			if(card.getStatus()==2) {
				Example cardExample2 = new Example(RechargCard.class);
				Criteria cardCriteria2 = cardExample2.createCriteria();
				//状态为2比较卡号密码用户id
				cardCriteria2.andEqualTo("cardNo", cardNo).andEqualTo("password", password).andEqualTo("userId", userId);
				List<RechargCard> cardList2 = rechargCardMapper.selectByExample(cardExample2);
				if(cardList2 ==null || cardList2.isEmpty()) {
					throw new BussinessException("卡号密码不正确");
				}
				RechargCard card1 = cardList2.get(0);
				card1.setStatus(2);
				card1.setUserId(userId);
				card1.setUserTime(new Date());
				rechargCardMapper.updateByPrimaryKey(card1);
				//判断卡号类型 (1.普通单门卡选A即A 2.普通多门卡 选ABC即ABC 3.vip卡可以从一定范围内选几门)
				Integer type = card1.getCardType();
				//得到充值卡Id去RechargCardBook查询 课程id
				Integer cardId = card1.getId();
				//开通天数
				Integer day = card1.getDay();
				//交易卡卡号
				String cardNo2 = card1.getCardNo();
				//卡号绑定的可使用课程门数(只针对vip卡  主要是需求太过奇葩，后期强行加的字段)
				Integer courseNumber = card1.getCourseNo();
				//根据前端传过来的课程id判断是否在RechargCardBook里切是未开通的
				if(type==1) {
					Example bookExample = new Example(RechargCardBook.class);
					Criteria bookCriteria = bookExample.createCriteria();
					bookCriteria.andEqualTo("rechargCardId", cardId);
					List<RechargCardBook> bookList = rechargCardBookMapper.selectByExample(bookExample);
					RechargCardBook rechargCardBook = bookList.get(0);
					Integer courseId2 = rechargCardBook.getCourseId();
					Integer courseType = rechargCardBook.getCourseType();
					if(courseId2==courseId && courseType==1) {
						//开通成功,并更改课程type为已开通
//						RechargCardBook rechargCardBook2 = new RechargCardBook();
						rechargCardBook.setCourseType(2);
//						rechargCardBook2.setCourseType(2);
						rechargCardBookMapper.updateByPrimaryKeySelective(rechargCardBook);
						//判断之前user_book表有没有开通过这个课程
						Example example15 = new Example(UserBook.class);
						Criteria criteria15 = example15.createCriteria();
						criteria15.andEqualTo("courseId", courseId).andEqualTo("userId", userId);
						List<UserBook> userBookList15 = userBookMapper.selectByExample(example15);
						if(userBookList15==null || userBookList15.isEmpty()) {
							//把开通成功的课程插入user_book,open_book_reecord表
							UserBook userBook = new UserBook();
							userBook.setId(IdGen.getDefaultPrimaryKey());
							userBook.setCourseId(courseId);
							userBook.setCreateTime(new Date());
							userBook.setOpenType(2);
							userBook.setActiveTime(day);
							userBook.setUserId(userId);
							userBook.setStatus(1);
							String days = Integer.toString(day)+"d";	
							userBook.setOverdueTime(DateUtils.addTime(new Date(), days));
							//根据课程id得到书本id
							Example bookExample4 = new Example(Book.class);
							Criteria bookeCriteria = bookExample4.createCriteria();
							bookeCriteria.andEqualTo("courseId", courseId);
							List<Book> bookList4= bookMapper.selectByExample(bookExample4);
							Book book = bookList4.get(0);
							Integer bookId = book.getId();
							userBook.setBookId(bookId);
							userBookMapper.insertSelective(userBook);
						}else {
							UserBook userBook = userBookList15.get(0);
							userBook.setStatus(1);
							userBook.setOpenType(2);
							userBook.setActiveTime(day);
							userBook.setCreateTime(new Date());
							String days = Integer.toString(day)+"d";				
							userBook.setOverdueTime(DateUtils.addTime(new Date(), days));
							userBookMapper.updateByPrimaryKey(userBook);
						
						}
						//再讲课程插入交易记录表
						OpenBookRecord openBookRecord = new OpenBookRecord();
						openBookRecord.setId(NumberUtils.createData(25));
						openBookRecord.setUserId(userId);
						openBookRecord.setPayType(1);
						openBookRecord.setTradeId(cardNo2);
						openBookRecord.setBusType(1);
						openBookRecord.setDayCount(day);
						openBookRecord.setStatus(2);
						openBookRecord.setPayTime(new Date());
						openBookRecord.setCreateTime(new Date());
						openBookRecordMapper.insertSelective(openBookRecord);					
					}else {
						throw new BussinessException("开通失败，卡号已使用或者不包含此课程");
					}
					
				}
				
				if(type==2) {
					Example bookExample = new Example(RechargCardBook.class);
					Criteria bookCriteria = bookExample.createCriteria();
					bookCriteria.andEqualTo("rechargCardId", cardId);
					List<RechargCardBook> bookList = rechargCardBookMapper.selectByExample(bookExample);
					
					List<Integer> courseIdList=Lists.newArrayList();
					//把course_id提成一个新list来判断 传入得courseId是否在里面
					for(RechargCardBook book:bookList) {
						if(book.getCourseType()==1) {
							courseIdList.add(book.getCourseId());
						}
					}
					if(courseIdList.contains(courseId)) {
						Example bookExample1 = new Example(RechargCardBook.class);
						Criteria bookCriteria1 = bookExample1.createCriteria();
						bookCriteria1.andEqualTo("courseId", courseId).andEqualTo("rechargCardId",cardId);
						List<RechargCardBook> bookList1 = rechargCardBookMapper.selectByExample(bookExample1);
						RechargCardBook rechargCardBook = bookList1.get(0);
						rechargCardBook.setCourseType(2);
						rechargCardBookMapper.updateByPrimaryKeySelective(rechargCardBook);
						//判断之前user_book表有没有开通过这个课程
						Example example15 = new Example(UserBook.class);
						Criteria criteria15 = example15.createCriteria();
						criteria15.andEqualTo("courseId", courseId).andEqualTo("userId", userId);
						List<UserBook> userBookList15 = userBookMapper.selectByExample(example15);
						if(userBookList15==null || userBookList15.isEmpty()) {
							//把开通成功的课程插入user_book,open_book_reecord表
							UserBook userBook = new UserBook();
							userBook.setId(IdGen.getDefaultPrimaryKey());
							userBook.setCourseId(courseId);
							userBook.setCreateTime(new Date());
							userBook.setOpenType(2);
							userBook.setActiveTime(day);
							userBook.setUserId(userId);
							userBook.setStatus(1);
							String days = Integer.toString(day)+"d";	
							userBook.setOverdueTime(DateUtils.addTime(new Date(), days));
							//根据课程id得到书本id
							Example bookExample4 = new Example(Book.class);
							Criteria bookeCriteria = bookExample4.createCriteria();
							bookeCriteria.andEqualTo("courseId", courseId);
							List<Book> bookList4= bookMapper.selectByExample(bookExample4);
							Book book = bookList4.get(0);
							Integer bookId = book.getId();
							userBook.setBookId(bookId);
							userBookMapper.insertSelective(userBook);
						}else {
							UserBook userBook = userBookList15.get(0);
							userBook.setStatus(1);
							userBook.setOpenType(2);
							userBook.setActiveTime(day);
							userBook.setCreateTime(new Date());
							String days = Integer.toString(day)+"d";				
							userBook.setOverdueTime(DateUtils.addTime(new Date(), days));
							userBookMapper.updateByPrimaryKey(userBook);
						
						}
						//再讲课程插入交易记录表
						OpenBookRecord openBookRecord = new OpenBookRecord();
						openBookRecord.setId(NumberUtils.createData(25));
						openBookRecord.setUserId(userId);
						openBookRecord.setPayType(1);
						openBookRecord.setTradeId(cardNo2);
						openBookRecord.setBusType(1);
						openBookRecord.setDayCount(day);
						openBookRecord.setStatus(2);
						openBookRecord.setPayTime(new Date());
						openBookRecord.setCreateTime(new Date());
						openBookRecordMapper.insertSelective(openBookRecord);					
					}else {
						throw new BussinessException("开通失败，卡号已使用或者不包含此课程");
					}			
					
				}
				//代表为vip卡
				if(type==3) {
					//得到卡号所绑定的课程
					Example bookExample = new Example(RechargCardBook.class);
					Criteria bookCriteria = bookExample.createCriteria();
					bookCriteria.andEqualTo("rechargCardId", cardId);
					List<RechargCardBook> bookList = rechargCardBookMapper.selectByExample(bookExample);
					//得到目前已经开通的课程
					List<RechargCardBook> notUseCourseList=Lists.newArrayList();
					List<Integer> courseIdList=Lists.newArrayList();
					for(RechargCardBook book:bookList) {
						if(book.getCourseType()==2) {
							notUseCourseList.add(book);
						}
					}
					if(notUseCourseList.size()<courseNumber) {
						for(RechargCardBook book:bookList) {
							if(book.getCourseType()==1) {
								courseIdList.add(book.getCourseId());
							}	
						}
						if(courseIdList.contains(courseId)) {
							Example bookExample1 = new Example(RechargCardBook.class);
							Criteria bookCriteria1 = bookExample1.createCriteria();
							bookCriteria1.andEqualTo("courseId", courseId).andEqualTo("rechargCardId",cardId);
							List<RechargCardBook> bookList1 = rechargCardBookMapper.selectByExample(bookExample1);
							RechargCardBook rechargCardBook = bookList1.get(0);
							rechargCardBook.setCourseType(2);
							rechargCardBookMapper.updateByPrimaryKeySelective(rechargCardBook);
							//判断之前user_book表有没有开通过这个课程
							Example example15 = new Example(UserBook.class);
							Criteria criteria15 = example15.createCriteria();
							criteria15.andEqualTo("courseId", courseId).andEqualTo("userId", userId);
							List<UserBook> userBookList15 = userBookMapper.selectByExample(example15);
							if(userBookList15==null || userBookList15.isEmpty()) {
								//把开通成功的课程插入user_book,open_book_reecord表
								UserBook userBook = new UserBook();
								userBook.setId(IdGen.getDefaultPrimaryKey());
								userBook.setCourseId(courseId);
								userBook.setCreateTime(new Date());
								userBook.setOpenType(2);
								userBook.setActiveTime(day);
								userBook.setUserId(userId);
								userBook.setStatus(1);
								String days = Integer.toString(day)+"d";	
								userBook.setOverdueTime(DateUtils.addTime(new Date(), days));
								//根据课程id得到书本id
								Example bookExample4 = new Example(Book.class);
								Criteria bookeCriteria = bookExample4.createCriteria();
								bookeCriteria.andEqualTo("courseId", courseId);
								List<Book> bookList4= bookMapper.selectByExample(bookExample4);
								Book book = bookList4.get(0);
								Integer bookId = book.getId();
								userBook.setBookId(bookId);
								userBookMapper.insertSelective(userBook);
							}else {
								UserBook userBook = userBookList15.get(0);
								userBook.setStatus(1);
								userBook.setOpenType(2);
								userBook.setActiveTime(day);
								userBook.setCreateTime(new Date());
								String days = Integer.toString(day)+"d";				
								userBook.setOverdueTime(DateUtils.addTime(new Date(), days));
								userBookMapper.updateByPrimaryKey(userBook);
							
							}
							//再讲课程插入交易记录表
							OpenBookRecord openBookRecord = new OpenBookRecord();
							openBookRecord.setId(NumberUtils.createData(25));
							openBookRecord.setUserId(userId);
							openBookRecord.setPayType(1);
							openBookRecord.setTradeId(cardNo2);
							openBookRecord.setBusType(1);
							openBookRecord.setDayCount(day);
							openBookRecord.setStatus(2);
							openBookRecord.setPayTime(new Date());
							openBookRecord.setCreateTime(new Date());
							openBookRecordMapper.insertSelective(openBookRecord);		
						}else {
							throw new BussinessException("开通失败，卡号已使用或者不包含此课程");
						}
						
					}else {
						throw new BussinessException("可开通门数超限");
					}
	
				}
			
			}
	
		} catch (RuntimeException  e) {
			throw new BussinessException(e);
		}
		
	}
	
	
	
	
	
	
	

}
