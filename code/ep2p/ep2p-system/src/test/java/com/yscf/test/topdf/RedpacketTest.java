package com.yscf.test.topdf;

import java.math.BigDecimal;

import com.yscf.common.util.ArithmeticUtil;

/**
 * 
 * @ClassName : RedpacketTest
 * @Description : 红包详情生成规则测试类
 * @Author : Qing.Cai
 * @Date : 2015年11月24日 下午3:04:08
 */
public class RedpacketTest {
	public static void main(String[] args) {
	/*	double totalMoney = 100;
		int personNum = 10;
		double[] allocationMoney = new double[personNum];
		for (int i = personNum; i > 1; i--) {
			// 安全线
			double surplusMoney = (totalMoney - (i - 1) * 0.01) / i * 3;
			if (i == 2) {
				surplusMoney = (totalMoney - (i - 1) * 0.01);
			}
			System.out.println("第" + i + "个\t安全值=" + surplusMoney);
			double randomlyAssignedMoney = ((int) (Math.random() * surplusMoney * 100) + 1) * 0.01;
			// 截取小数点后两位输出
			BigDecimal aBigDecimal = new BigDecimal(randomlyAssignedMoney);
			double moneyOfOnePerson = aBigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			totalMoney = ArithmeticUtil.sub(totalMoney, moneyOfOnePerson);
			System.out.println("第" + i + "个随机值" + moneyOfOnePerson + "\t剩余金额=" + totalMoney + "\n");
			allocationMoney[i - 1] = moneyOfOnePerson;
		}
		BigDecimal bg = new BigDecimal(totalMoney);
		double remainingMoney = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		System.out.println("最后一个值" + remainingMoney);
		allocationMoney[0] = remainingMoney;

		// 验证金额是否相等
		double checkTotalMoney = 0;
		for (int i = 0; i < personNum; i++) {
			checkTotalMoney = ArithmeticUtil.add(checkTotalMoney, allocationMoney[i]);
			System.out.println(allocationMoney[i] + "  " + checkTotalMoney);
		}
		System.out.println("总金额=" + checkTotalMoney);*/
	}
}
