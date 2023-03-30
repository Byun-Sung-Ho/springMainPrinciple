package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {

    RateDiscountPolicy rateDiscountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10%할인이 되어야 한다.")
    void discount() {
        //given
        Member member = new Member(1L, "VIPmember", Grade.VIP);
        //when
        int discount = rateDiscountPolicy.discount(member, 10000);
        //then
        Assertions.assertThat(discount).isEqualTo(1000);
    }

    /*@Test
    @DisplayName("VIP가 아니면 10%할인이 되면 안된다.")
    void discountX() {
        //given
        Member member = new Member(2L, "Basicmember", Grade.BASIC);
        //when
        int discount = rateDiscountPolicy.discount(member, 10000);
        //then
        Assertions.assertThat(discount).isEqualTo(1000);
    }*/
}