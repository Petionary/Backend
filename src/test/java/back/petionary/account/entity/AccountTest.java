package back.petionary.account.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import back.petionary.domain.account.entity.Account;
import back.petionary.domain.account.entity.Address;
import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AccountTest {

    @Test
    @DisplayName("유저의 정보 중 빈값이 존재하지 않으면 유저가 생성이 된다.")
    void createAccount() {
        //given
        String email = "test@test.com";
        String name = "테스트";
        LocalDate birth = LocalDate.of(1998, 10, 15);
        String zipcode = "1111";
        String detail = "상세주소";
        String phone = "01011111111";
    }

    @Test
    @DisplayName("유저의 정보 중 이메일이 null일 경우 예외가 발생한다.")
    void isNullEmail() {
        //given
        String email = null;
        String name = "테스트";
        LocalDate birth = LocalDate.of(1998, 10, 15);
        String zipcode = "1111";
        String detail = "상세주소";
        String phone = "01011111111";
    }

    @Test
    @DisplayName("유저의 정보 중 이름이 null일 경우 예외가 발생한다.")
    void isNullName() {
        //given
        String email = "test@test.com";
        String name = null;
        LocalDate birth = LocalDate.of(1998, 10, 15);
        String zipcode = "1111";
        String detail = "상세주소";
        String phone = "01011111111";
    }

    @Test
    @DisplayName("유저의 정보 중 생년월일이 null일 경우 예외가 발생한다.")
    void isNullBirth() {
        //given
        String email = "test@test.com";
        String name = "test";
        LocalDate birth = null;
        String zipcode = "1111";
        String detail = "상세주소";
        String phone = "01011111111";
    }

    @Test
    @DisplayName("유저의 정보 중 핸드폰번호가 null일 경우 예외가 발생한다.")
    void isNullPhone() {
        //given
        String email = "test@test.com";
        String name = "테스트";
        LocalDate birth = LocalDate.of(1998, 10, 15);
        String zipcode = "1111";
        String detail = "상세주소";
        String phone = null;
    }

    @Test
    @DisplayName("유저의 정보 중 상세주소가 null일 경우 예외가 발생한다.")
    void isNullDetail() {
        String zipcode = "1111";
        String detail = null;
    }


    @Test
    @DisplayName("유저의 정보 중 우편번호가 존재하면 예외가 발생한다.")
    void isNullZipcode() {
        //given
        String zipcode = null;
        String detail = "상세주소";
    }

    @Test
    @DisplayName("전화번호의 크기가 11을 넘으면 예외가 발생한다.")
    void exceedPhoneLength11() {
        String email = "test@test.com";
        String name = "테스트";
        LocalDate birth = LocalDate.of(1998, 10, 15);
        String zipcode = "1111";
        String detail = "상세주소";
        String phone = "0".repeat(12);
    }

    @Test
    @DisplayName("이름의 크기가 30을 넘으면 예외가 발생한다.")
    void exceedNameLength30() {
        String email = "test@test.com";
        String name = "가".repeat(31);
        LocalDate birth = LocalDate.of(1998, 10, 15);
        String zipcode = "1111";
        String detail = "상세주소";
        String phone = "01012345678";
    }
}