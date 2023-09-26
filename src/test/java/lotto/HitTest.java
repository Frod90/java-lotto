package lotto;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HitTest {
	Hit hit = new Hit();
	WinningNumber winningNumber = new WinningNumber();

	@DisplayName("로또 번호와 당첨 번호 사이에 일치한 번호의 개수를 정확하게 세주는지 확인한다.")
	@Test
	void winningHitListTest() {
		List<List<Integer>> lottoListTest = new ArrayList<>();
		List<Integer> lottoListTest1 = List.of(1, 2, 3, 4, 5, 6);
		List<Integer> lottoListTest2 = List.of(1, 2, 3, 7, 8, 9);
		List<Integer> lottoListTest3 = List.of(1, 2, 3, 7, 8, 10);
		List<Integer> lottoListTest4 = List.of(45, 44, 43, 42, 41, 10);
		lottoListTest.add(lottoListTest1);
		lottoListTest.add(lottoListTest2);
		lottoListTest.add(lottoListTest3);
		lottoListTest.add(lottoListTest4);

		List<Integer> winningNumberTest = new ArrayList<>(winningNumber.winningNumber("1,2,3,7,8,9"));
		int bonusNumberTest = 10;

		List<String> winningHitListTest = new ArrayList<>();
		winningHitListTest.add("3개 일치");
		winningHitListTest.add("6개 일치");
		winningHitListTest.add("5개 일치, 보너스 볼 일치");
		winningHitListTest.add("0개 일치");

		assertThat(hit.winningHitList(lottoListTest, winningNumberTest, bonusNumberTest)).isEqualTo(winningHitListTest);

	}
}
