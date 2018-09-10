package cc.modules.util;

import java.math.BigInteger;

/**
 * 
 * String类型的ip地址转换为Long
 * 
 * @author Lich Li
 *
 */

public class IpTool {
	
	public static Long convert(String ip){
		
		long res = -1L;
		
		if (ip.replaceAll("\\d", "").length() == 3){
			res = ipv4tolong(ip);
		}else{
			res = ipv6toInt(ip).longValue();
		}
		return res;
			
	}
	
	private static long ipv4tolong(String ipv4){
		String[] splits = ipv4.split("\\.");
		long l = 0;
		l = l + (Long.valueOf(splits[0], 10)) << 24;
		l = l + (Long.valueOf(splits[1], 10) << 16);
		l = l + (Long.valueOf(splits[2], 10) << 8);
		l = l + (Long.valueOf(splits[3], 10));
		return l;
	}
	
	private static BigInteger ipv6toInt(String ipv6){

		int compressIndex = ipv6.indexOf("::");
		if (compressIndex != -1){
			String part1s = ipv6.substring(0, compressIndex);
			String part2s = ipv6.substring(compressIndex + 1);
			BigInteger part1 = ipv6toInt(part1s);
			BigInteger part2 = ipv6toInt(part2s);
			int part1hasDot = 0;
			char ch[] = part1s.toCharArray();
			for (char c : ch){
				if (c == ':'){
					part1hasDot++;
				}
			}
			// ipv6 has most 7 dot
			return part1.shiftLeft(16 * (7 - part1hasDot )).add(part2);
		}
		String[] str = ipv6.split(":");
		BigInteger big = BigInteger.ZERO;
		for (int i = 0; i < str.length; i++)
		{
			//::1
			if (str[i].isEmpty())
			{
				str[i] = "0";
			}
			big = big.add(BigInteger.valueOf(Long.valueOf(str[i], 16))
			        .shiftLeft(16 * (str.length - i - 1)));
		}
		return big;
	}
}
