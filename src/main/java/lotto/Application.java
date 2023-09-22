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

		WinningNumber win = new WinningNumber();

		System.out.printf("당첨 번호를 입력해 주세요.%n");
		String inputWinningNumber = Console.readLine();
		List<Integer> winningNumberTest = win.winningNumber(inputWinningNumber);
		Lotto winningNumber = new Lotto(winningNumberTest);

		System.out.printf("보너스 번호를 입력해 주세요.%n");
		String inputBonusNumber = Console.readLine();
		int bonusNumber = Integer.parseInt(inputBonusNumber);
		win.bonusNumberTest(bonusNumber);

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
	final int LOTTO_PRICE = 1000;

	int purchaseTime(String purchaseAmount) {
		int userPurchaseAmount = Integer.parseInt(purchaseAmount);
		int PurchaseTest = userPurchaseAmount % LOTTO_PRICE;

		if (!(PurchaseTest == 0)) {
			throw new IllegalArgumentException("[ERROR] 로또는 1000원 단위로 구매 가능합니다.");
		}
		return userPurchaseAmount / LOTTO_PRICE;
	}

}

class WinningNumber {

	List<Integer> winningNumberTest = new ArrayList<>();

	List<Integer> winningNumber(String inputWinningNumber) {
		String[] splitInputWinningNumber = inputWinningNumber.split(",");

		for (int i = 0; i < splitInputWinningNumber.length; i++) {
			int eachSplitInputWinningNumber = Integer.parseInt(splitInputWinningNumber[i]);
			if (!winningNumberTest.contains(eachSplitInputWinningNumber)) {
				winningNumberTest.add(eachSplitInputWinningNumber);
			}
		}

		return winningNumberTest;
	}

	void bonusNumberTest(int bonusNumber) {
		if (winningNumberTest.contains(bonusNumber)) {
			throw new IllegalArgumentException("[ERROR] 보너스 번호가 당첨 번호에 포함되어 있습니다.");
		}
	}

}