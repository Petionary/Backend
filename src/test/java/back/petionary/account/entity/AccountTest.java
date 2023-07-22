package back.petionary.account.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AccountTest {

    @Test
    @DisplayName("이메일, 번호, 주소, 이름, 생년월일 빈값이 없으면 유저가 생성이 된다.")
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
    @DisplayName("이메일, 번호, 주소, 이름, 생년월일 빈값 하나라도 존재하면 예외가 발생한다.")
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
        String message = assertThrows(NullPointerException.class,
            () -> new Account(email, phone, name, birth, address)).getMessage();
        assertThat(message).isEqualTo("값이 존재하지 않습니다.");
    }

    @Test
    @DisplayName("주소 중 하나라도 빈 값이 있으면 예외가 발생한다.")
    void test3() {
        //given
        String email = null;
        String name = "테스트";
        LocalDate birth = LocalDate.of(1998,10,15);
        String zipcode = null;
        String detail = "상세주소";
        String phone = "01011111111";

        //when && then
        String message = assertThrows(NullPointerException.class,
            () ->  new Address(zipcode, detail)).getMessage();
        assertThat(message).isEqualTo("값이 존재하지 않습니다.");
    }

}