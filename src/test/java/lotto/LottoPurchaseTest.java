package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class LottoPurchaseTest {
	@DisplayName("구매 금액에 숫자 이외의 것을 넣으면 오류 발생")
	@Test
	void inputPriceByString() {
		LottoPurchase lottoPurchase = new LottoPurchase();
		assertThatThrownBy(() -> lottoPurchase.purchaseTime("1000j") )
			.isInstanceOf(NoSuchElementException.class);

	}

	@DisplayName("로또 구매 금액이 1000원 단위가 아니면 예외가 발생한다.")
	@Test
	void inputPriceByUnit() {
		LottoPurchase lottoPurchase = new LottoPurchase();
		assertThatThrownBy(() -> lottoPurchase.purchaseTime("5500"))
			.isInstanceOf(IllegalArgumentException.class);
	}



}
