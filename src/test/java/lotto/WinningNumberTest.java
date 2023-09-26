package lotto;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinningNumberTest {
	final String ERROR_MESSAGE = "[ERROR]";
	WinningNumber winningNumber = new WinningNumber();

	@DisplayName("\",\"이외의 구분자로 당첨 번호를 입력하면 예외가 발생한다.")
	@Test
	void inputWinningNumberByWrongDelimiter() {
		assertThatThrownBy(() -> winningNumber.winningNumber("1.2.3.4.5.6")).isInstanceOf(
			IllegalArgumentException.class).hasMessageContaining(ERROR_MESSAGE);
	}

	@DisplayName("당첨 번호에 문자를 입력시 예외가 발생한다.")
	@Test
	void createWinningNumberByString() {
		assertThatThrownBy(() -> winningNumber.winningNumber("50j,1,2,3,4,5")).isInstanceOf(
			IllegalArgumentException.class).hasMessageContaining(ERROR_MESSAGE);
	}

	@DisplayName("당첨 번호에 1~45 범위 밖의 숫자를 입력시 예외가 발생한다.")
	@Test
	void createWinningNumberByOutRange() {
		assertThatThrownBy(() -> winningNumber.winningNumber("50,1,2,3,4,5")).isInstanceOf(
			IllegalArgumentException.class).hasMessageContaining(ERROR_MESSAGE);
	}

	@DisplayName("당첨 번호의 개수가 6개가 넘어가면 예외가 발생한다.")
	@Test
	void createWinningNumberByOverSIze() {
		assertThatThrownBy(() -> winningNumber.winningNumber("1,2,3,4,5,6,7")).isInstanceOf(
			IllegalArgumentException.class).hasMessageContaining(ERROR_MESSAGE);
	}

	@DisplayName("당첨 번호에 중복된 숫자가 있으면 예외가 발생한다.")
	@Test
	void createWinningNumberByDuplicatedNumber() {
		assertThatThrownBy(() -> winningNumber.winningNumber("1,2,3,4,5,5")).isInstanceOf(
			IllegalArgumentException.class).hasMessageContaining(ERROR_MESSAGE);
	}

	@DisplayName("당첨 번호와 보너스 번호 사이에 중복이 있으면 예외가 발생한다.")
	@Test
	void createWinningNumberContainBonusNumber() {
		List<Integer> winningNumberTest = winningNumber.winningNumber("1,2,3,4,5,6");
		int bonusNumberTest = 6;
		assertThatThrownBy(() -> winningNumber.bonusNumberTest(winningNumberTest, bonusNumberTest)).isInstanceOf(
			IllegalArgumentException.class).hasMessageContaining(ERROR_MESSAGE);
	}

}
