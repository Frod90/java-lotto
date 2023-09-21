package lotto;

import camp.nextstep.edu.missionutils.Console;

public class Application {
	public static void main(String[] args) {
		System.out.printf("구입금액을 입력해 주세요.%n");
		String purchaseAmount = Console.readLine();

		Input userInput = new Input();

		int userPurchaseTime = userInput.purchaseTime(purchaseAmount);

	}
}

class Input {
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