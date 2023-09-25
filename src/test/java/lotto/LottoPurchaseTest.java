package lotto;

import static org.assertj.core.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoPurchaseTest {
	@DisplayName("구매 금액에 숫자 이외의 것을 넣으면 오류 발생")
	@Test
	void inputPriceByString() {
		LottoPurchase lottoPurchase = new LottoPurchase();
		assertThatThrownBy(() -> lottoPurchase.purchaseTime("1000j")).isInstanceOf(NoSuchElementException.class);

	}

	@DisplayName("로또 구매 금액이 1000원 단위가 아니면 예외가 발생한다.")
	@Test
	void inputPriceByUnit() {
		LottoPurchase lottoPurchase = new LottoPurchase();
		assertThatThrownBy(() -> lottoPurchase.purchaseTime("5500")).isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("기입한 금액에 맞는 횟수만큼 로또 개수가 계산되는지 확인한다.")
	@Test
	void purchaseTimeCheck() {
		LottoPurchase lottoPurchase = new LottoPurchase();
		assertThat(lottoPurchase.purchaseTime("8000")).isEqualTo(8);
	}

}
