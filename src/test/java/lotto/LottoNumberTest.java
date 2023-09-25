package lotto;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoNumberTest extends Application {
	final int PURCHASE_TIME = 5;
	final int LOTTO_SIZE = 6;
	List<List<Integer>> lottoList = new ArrayList<>(lottoNumber(PURCHASE_TIME));

	@DisplayName("기입한 횟수만큼 로또가 구매해졌는지 확인한다.")
	@Test
	void lottoTimeCheck() {
		assertThat(lottoList.size()).isEqualTo(PURCHASE_TIME);
	}

	@DisplayName("구매한 로또 번호의 개수가 맞는지 확인한다.")
	@Test
	void lottoNumberSizeCheck() {
		for (int i = 0; i < lottoList.size(); i++) {
			assertThat(lottoList.get(i).size()).isEqualTo(LOTTO_SIZE);
		}
	}

	@DisplayName("구매한 로또 번호에 중복이 있는지 확인한다.")
	@Test
	void lottoDuplicatedNumberCheck() {
		for (int i = 0; i < lottoList.size(); i++) {
			assertThat(Set.copyOf(lottoList.get(i)).size()).isEqualTo(LOTTO_SIZE);
		}
	}

}
