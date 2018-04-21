package org.english.operation.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class IdGen {
	private static final String MACHINEIP = getMACHINEID();
	private static String CURRENT_YMDHMS = StringUtils.EMPTY;

	private static final Long defaultToken = 0L;
	private static AtomicLong TOKEN_YMDHMS = new AtomicLong(defaultToken);
	private static String TOKEN_ZERO_PREFIX = "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";

	@PostConstruct
	private void init() {
		updateCurrentTime();
	}

	@Scheduled(cron = "0/1 * *  * * ? ") // 每1秒执行一次
	private void updateCurrentTime() {
		String ymdhms = DateUtils.getYMDHMS();
		if (!ymdhms.equals(CURRENT_YMDHMS)) {
			CURRENT_YMDHMS = ymdhms;
			Long randomStart = Double.valueOf(Math.random() * 10).longValue();
			TOKEN_YMDHMS.getAndSet(randomStart);
		}
	}

	private static String getMACHINEID() {
		InetAddress addr = null;
		try {
			addr = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			return "00";
		}
		String ip = addr.getHostAddress();// 获得本机IP
		ip = ip.substring(ip.lastIndexOf(".") + 1);
		if (ip.length() > 2) {
			ip = ip.substring(1);
		}
		Integer dot = Integer.parseInt(ip);
		return StringUtils.reverse(String.format("%02d", dot));
	}

	/**
	 * 返回唯一id
	 * 
	 * @param prefix
	 *            id前缀
	 * @param width
	 *            id总宽度, 包括id前缀的长度
	 * @param onlyDigit
	 *            是否包含字母
	 * @return id 格式为 prefix + 160223151025 + reverse[ip.last2digit] + suffix
	 */
	public static String getUniqueId(String prefix, Integer width, boolean onlyDigit) {
		Integer prefixLength = StringUtils.isEmpty(prefix) ? 0 : prefix.length();
		Integer l = prefixLength + CURRENT_YMDHMS.length() + MACHINEIP.length();
		if (width <= l) {
			throw new IllegalArgumentException("width长度应大于" + l);
		}

		Integer suffixWidth = width - prefixLength - CURRENT_YMDHMS.length() - MACHINEIP.length();
		String suffix = StringUtils.EMPTY;
		boolean valid = false;

		while (!valid) {
			Long t = TOKEN_YMDHMS.incrementAndGet();
			if (onlyDigit) {
				suffix = String.format("%0" + suffixWidth + "d", t);
				valid = t.toString().length() <= suffixWidth;
			} else {
				suffix = Long.toString(t, 36);
				valid = suffix.length() <= suffixWidth;
				if (valid && suffix.length() < suffixWidth) {
					suffix = TOKEN_ZERO_PREFIX.substring(0, suffixWidth - suffix.length()) + suffix;
				}
			}
			if (!valid) {
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					// does nothing
				}
			}
		}

		return (prefixLength == 0 ? StringUtils.EMPTY : prefix) + CURRENT_YMDHMS + MACHINEIP + suffix;
	}

	/**
	 * 获取默认长度为36的主键
	 * @return 主键
	 */
	public static String getDefaultPrimaryKey(){
		return IdGen.getUniqueId(null,36,false);
	}

}
