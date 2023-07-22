package back.petionary.account.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import back.petionary.common.exception.CustomException;
import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AccountTest {

    @Test
    @DisplayName("유저의 정보 중 빈값이 존재하지 않으면 유저가 생성이 된다.")
    void test1() {
        //given
        String email = "test@test.com";
        String name = "테스트";
        LocalDate birth = LocalDate.of(1998,10,15);
        String zipcode = "1111";
        String detail = "상세주소";
        String phone = "01011111111";
        Address address = new Address(zipcode, detail);

        //when
        Account account = new Account(email, phone, name, birth, address);

        //then
        assertThat(account.getEmail()).isEqualTo(email);
        assertThat(account.getName()).isEqualTo(name);
        assertThat(account.getBirth()).isEqualTo(birth);
        assertThat(account.getAddress().getZipCode()).isEqualTo(zipcode);
        assertThat(account.getAddress().getDetail()).isEqualTo(detail);
        assertThat(account.getPhone()).isEqualTo(phone);
    }

    @Test
    @DisplayName("유저의 정보 중 이메일이 null일 경우 예외가 발생한다.")
    void test2() {
        //given
        String email = null;
        String name = "테스트";
        LocalDate birth = LocalDate.of(1998,10,15);
        String zipcode = "1111";
        String detail = "상세주소";
        String phone = "01011111111";
        Address address = new Address(zipcode, detail);

        //when && then
        assertThrows(CustomException.class,() -> new Account(email, phone, name, birth, address));
    }
    @Test
    @DisplayName("유저의 정보 중 이름이 null일 경우 예외가 발생한다.")
    void test3() {
        //given
        String email = "test@test.com";
        String name = null;
        LocalDate birth = LocalDate.of(1998,10,15);
        String zipcode = "1111";
        String detail = "상세주소";
        String phone = "01011111111";
        Address address = new Address(zipcode, detail);

        //when && then
        assertThrows(CustomException.class,() -> new Account(email, phone, name, birth, address));
    }
    @Test
    @DisplayName("유저의 정보 중 생년월일이 null일 경우 예외가 발생한다.")
    void test4() {
        //given
        String email = "test@test.com";
        String name = "test";
        LocalDate birth = null;
        String zipcode = "1111";
        String detail = "상세주소";
        String phone = "01011111111";
        Address address = new Address(zipcode, detail);

        //when && then
        assertThrows(CustomException.class,() -> new Account(email, phone, name, birth, address));
    }

    @Test
    @DisplayName("유저의 정보 중 핸드폰번호가 null일 경우 예외가 발생한다.")
    void test5() {
        //given
        String email = "test@test.com";
        String name = "테스트";
        LocalDate birth = LocalDate.of(1998,10,15);
        String zipcode = "1111";
        String detail = "상세주소";
        String phone = null;
        Address address = new Address(zipcode, detail);

        //when && then
        assertThrows(CustomException.class,() -> new Account(email, phone, name, birth, address));
    }

    @Test
    @DisplayName("유저의 정보 중 상세주소가 null일 경우 예외가 발생한다.")
    void test6() {
        //given
        String zipcode = "1111";
        String detail = null;

        //when && then
        assertThrows(CustomException.class,() -> new Address(zipcode, detail));
    }


    @Test
    @DisplayName("주유저의 정보 중 우편번호가 존재하면 예외가 발생한다.")
    void test7() {
        //given
        String zipcode = null;
        String detail = "상세주소";

        //when && then
        assertThrows(CustomException.class,
            () ->  new Address(zipcode, detail));
    }

}