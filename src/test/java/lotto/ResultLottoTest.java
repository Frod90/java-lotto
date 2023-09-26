package lotto;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ResultLottoTest extends Application {
	final Prize[] PRIZE_ARR_TEST = Prize.values();
	List<String> winningHitListTest = new ArrayList<>();
	ResultLotto resultLotto = new ResultLotto();
	List<Integer> lottoResultCountTest = new ArrayList<>();
	String inputPurchaseAmountTest = "5000";

	@DisplayName("구입한 전체 로또의 각 등수가 몇개 있는지를 정확히 세주는지 확인한다.")
	@Test
	void lottoResultCountTest() {

		winningHitListTest.add("3개 일치");
		winningHitListTest.add("3개 일치");
		winningHitListTest.add("6개 일치");
		winningHitListTest.add("5개 일치, 보너스 볼 일치");
		winningHitListTest.add("0개 일치");

		lottoResultCountTest.add(2);
		lottoResultCountTest.add(0);
		lottoResultCountTest.add(0);
		lottoResultCountTest.add(1);
		lottoResultCountTest.add(1);

		assertThat(resultLotto.lottoResultCount(winningHitListTest, PRIZE_ARR_TEST)).isEqualTo(lottoResultCountTest);
	}

	@DisplayName("수익률을 정확히 계산하는지 확인한다.")
	@Test
	void lottoEarningRateTest() {

		lottoResultCountTest.add(2);
		lottoResultCountTest.add(0);
		lottoResultCountTest.add(0);
		lottoResultCountTest.add(1);
		lottoResultCountTest.add(1);

		final double LOTTO_EARNING_RATE_TEST =
			(Prize.FIRST.money + Prize.SECOND.money + Prize.FIFTH.money * 2) / (double)5000;

		assertThat(
			resultLotto.lottoEarningRate(lottoResultCountTest, PRIZE_ARR_TEST, inputPurchaseAmountTest)).isEqualTo(
			LOTTO_EARNING_RATE_TEST);

	}

}
