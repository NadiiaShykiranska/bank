import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.fest.assertions.api.Assertions.*;

/**
 * Created by Nadiia on 31.07.2016.
 */
public class BankingTest {

    private Banking banking;

    @Before
    public void init() {
        banking = new BankingImpl();
    }

    @Test
    public void testCalculateZeroBalance() throws  Exception{
        BigDecimal value = banking.calculateBalance(5, BigDecimal.valueOf(0));
        assertThat(value).isEqualTo(new BigDecimal("0.00"));
    }

    @Test
    public void testCalculateZeroPeriod() throws  Exception{
        BigDecimal value = banking.calculateBalance(0, new BigDecimal("10.00"));
        assertThat(value).isEqualTo(new BigDecimal("10.00"));
    }

    @Test
    public void testCalculateBalanceWith100Balance() throws  Exception{
        BigDecimal value = banking.calculateBalance(12, BigDecimal.valueOf(100));
        assertThat(value).isEqualTo(new BigDecimal("103.00"));
    }

    @Test
    public void testCalculateBalanceWith1000Balance() throws  Exception{
        BigDecimal value = banking.calculateBalance(12, BigDecimal.valueOf(1000));
        assertThat(value).isEqualTo(new BigDecimal("1070.00"));
    }

    @Test
    public void testCalculateBalanceWithLowRate() throws  Exception{
        BigDecimal value = banking.calculateBalance(12, BigDecimal.valueOf(50));
        assertThat(value).isEqualTo(new BigDecimal("51.50"));
    }

    @Test
    public void testCalculateBalanceWithMiddleRate() throws  Exception{
        BigDecimal value = banking.calculateBalance(6, BigDecimal.valueOf(200));
        assertThat(value).isEqualTo(new BigDecimal("205.00"));
    }

    @Test
    public void testCalculateBalanceWithHigh() throws  Exception{
        BigDecimal value = banking.calculateBalance(12, BigDecimal.valueOf(10000));
        assertThat(value).isEqualTo(new BigDecimal("10700.00"));
    }

    @Test
    public void testCalculateBalanceWithCapitalization() throws  Exception{
        BigDecimal value = banking.calculateBalance(18, BigDecimal.valueOf(99));
        assertThat(value).isEqualTo(new BigDecimal("104.52"));
    }
}
