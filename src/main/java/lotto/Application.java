package lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

public class Application {
	public static void main(String[] args) {
		System.out.printf("구입금액을 입력해 주세요.%n");
		String purchaseAmount = Console.readLine();

		Purchase purchase = new Purchase();

		int userPurchaseTime = purchase.purchaseTime(purchaseAmount);
		List<List<Integer>> lottoList = lottoNumber(userPurchaseTime);

		for (int i = 0; i < userPurchaseTime; i++) {
			System.out.println(lottoList.get(i));
		}

	}

	static List<List<Integer>> lottoNumber(int purchaseTime) {
		List<List<Integer>> lottoList = new ArrayList<>();
		for (int i = 0; i < purchaseTime; i++) {
			List<Integer> lottoNumber = Randoms.pickUniqueNumbersInRange(1, 45, 6);
			Collections.sort(lottoNumber);
			lottoList.add(lottoNumber);
		}
		return lottoList;
	}
}

class Purchase {
	final int lottoPrice = 1000;

	int purchaseTime(String purchaseAmount) {
		int userPurchaseAmount = Integer.parseInt(purchaseAmount);
		int PurchaseTest = userPurchaseAmount % lottoPrice;

		if (!(PurchaseTest == 0)) {
			throw new IllegalArgumentException("[ERROR] 로또는 1000원 단위로 구매 가능합니다.");
		}
		return userPurchaseAmount / lottoPrice;
	}

}

