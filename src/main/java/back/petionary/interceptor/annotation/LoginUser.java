package back.petionary.interceptor.annotation;

import static java.lang.annotation.ElementType.PARAMETER;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * packageName    : back.petionary.interceptor.enums
 * fileName       : LoginUser
 * author         : hoewoonjeong
 * date           : 3/2/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 3/2/24        hoewoonjeong               최초 생성
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(PARAMETER)
public @interface LoginUser {
}
