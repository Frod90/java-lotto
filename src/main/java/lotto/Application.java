package lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

enum Prize {
	FIFTH("5등", "3개 일치", 5000L), FOURTH("4등", "4개 일치", 50000L), THIRD("3등", "5개 일치", 1500000L), SECOND("2등",
		"5개 일치, 보너스 볼 일치", 30000000L), FIRST("1등", "6개 일치", 2000000000L);

	final String rank;
	final String hit;
	final long money;

	Prize(String rank, String hit, long money) {
		this.rank = rank;
		this.hit = hit;
		this.money = money;

	}

}

public class Application {
	public static void main(String[] args) {
		System.out.printf("구입금액을 입력해 주세요.%n");
		String inputPurchaseAmount = Console.readLine();

		Purchase purchase = new Purchase();

		int PurchaseTime;
		PurchaseTime = purchase.purchaseTime(inputPurchaseAmount);

		System.out.println();
		System.out.println(PurchaseTime + "개를 구매했습니다.");

		List<List<Integer>> lottoList = new ArrayList<>(lottoNumber(PurchaseTime));

		System.out.println();

		WinningNumber win = new WinningNumber();

		System.out.printf("당첨 번호를 입력해 주세요.%n");
		String inputWinningNumber = Console.readLine();
		List<Integer> winningNumber = win.winningNumber(inputWinningNumber);

		new Lotto(winningNumber);

		System.out.println();
		System.out.printf("보너스 번호를 입력해 주세요.%n");
		String inputBonusNumber = Console.readLine();
		int bonusNumber = Integer.parseInt(inputBonusNumber);
		win.bonusNumberTest(bonusNumber);

		Hit hit = new Hit();
		List<String> winningList = hit.winningHitList(lottoList, winningNumber, bonusNumber);

		Prize[] PRIZE_ARR = Prize.values();

		resultPrint(winningList, PRIZE_ARR, inputPurchaseAmount);

	}

	static List<List<Integer>> lottoNumber(int purchaseTime) {
		List<List<Integer>> lottoList = new ArrayList<>();
		for (int i = 0; i < purchaseTime; i++) {
			List<Integer> lottoNumber = new ArrayList<>(Randoms.pickUniqueNumbersInRange(1, 45, 6));

			Collections.sort(lottoNumber);
			lottoList.add(lottoNumber);
		}

		for (int i = 0; i < purchaseTime; i++) {
			System.out.println(lottoList.get(i));
		}

		return lottoList;
	}

	static int resultCount(List<String> winningList, Prize[] PRIZE_ARR, int winningIndex, int prizeIndex, int count) {

		if (winningList.get(winningIndex).equals(PRIZE_ARR[prizeIndex].hit)) {
			++count;
		}
		return count;
	}

	static void resultPrint(List<String> winningList, Prize[] PRIZE_ARR, String inputPurchaseAmount) {
		System.out.println();
		System.out.println("당첨 통계");
		System.out.println("---");

		double sum = 0;

		for (int i = 0; i < PRIZE_ARR.length; i++) {
			int resultCount = 0;
			for (int j = 0; j < winningList.size(); j++) {
				resultCount = resultCount(winningList, PRIZE_ARR, j, i, resultCount);
			}
			sum = sum + PRIZE_ARR[i].money * resultCount;
			System.out.printf("%s (%,d원) - %d개%n", PRIZE_ARR[i].hit, PRIZE_ARR[i].money, resultCount);

		}

		double purchaseAmount = Double.parseDouble(inputPurchaseAmount);
		double summary = sum / purchaseAmount;
		System.out.printf("총 수익률은 %.1f%%입니다.", summary * 100);

	}

}

class Purchase {
	final int LOTTO_PRICE = 1000;

	int purchaseTime(String purchaseAmount) {

		boolean errorTest = false;

		try {
			Integer.parseInt(purchaseAmount);
		} catch (IllegalArgumentException ill) {

			errorTest = true;

		}

		if (errorTest) {
			System.out.println("[ERROR] 숫자만 입력해주세요.");
			throw new NoSuchElementException("[ERROR] 숫자만 입력해주세요.");
		}

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

			if (eachSplitInputWinningNumber < 1 || 45 < eachSplitInputWinningNumber) {
				throw new IllegalArgumentException("[ERROR] 1~45 사이의 숫자만 입력해주세요.");
			}

			winningNumberTest.add(eachSplitInputWinningNumber);
		}

		return winningNumberTest;
	}

	void bonusNumberTest(int bonusNumber) {
		if (winningNumberTest.contains(bonusNumber)) {

			throw new IllegalArgumentException("[ERROR] 보너스 번호가 당첨 번호에 포함되어 있습니다.");
		}
	}

}

class Hit {
	int hitCount(List<Integer> lottoNumber, List<Integer> winningNumber) {
		int hitCount = 0;
		for (int i = 0; i < lottoNumber.size(); i++) {
			if (winningNumber.contains(lottoNumber.get(i))) {
				++hitCount;
			}
		}
		return hitCount;
	}

	List<String> winningHitList(List<List<Integer>> lottoList, List<Integer> winningNumber, int bonusNumber) {
		List<String> winningHitList = new ArrayList<>();
		for (int i = 0; i < lottoList.size(); i++) {
			int hitCount = hitCount(lottoList.get(i), winningNumber);

			if (hitCount == 5 && lottoList.get(i).contains(bonusNumber)) {
				winningHitList.add(hitCount + "개 일치, 보너스 볼 일치");
				continue;
			}

			winningHitList.add(hitCount + "개 일치");
		}
		return winningHitList;
	}

}



