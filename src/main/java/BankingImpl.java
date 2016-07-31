import com.sun.istack.internal.NotNull;
import org.apache.log4j.Logger;

import java.math.BigDecimal;

public class BankingImpl implements Banking {

    final static Logger logger = Logger.getLogger(BankingImpl.class);

    /**
     * Calculate balance {@code BigDecimal} of saving account in a bank with earn.
     * Saving account in bank earns a different rate of interest depending on the
     * {@code BigDecimal} balance in the account and a {@code int} period of deposit.
     * Earn calculated with capitalization of the deposit.
     *
     * @param initialMonths calculated period.
     * @param initialBalance initial balance of the saving bank account.
     * @return {@code BigDecimal} calculated account balance with earn.
     * @throws Exception if months or (and) initialBalance value is not positive.
     */
    public BigDecimal calculateBalance(@NotNull final int initialMonths, @NotNull final BigDecimal initialBalance) throws Exception {
        int months = initialMonths;
        if (months < 0 || initialBalance.compareTo(BigDecimal.valueOf(0)) < 0) {
            throw new Exception("Initial value should be not negative number");
        }
        logger.debug(String.format("Calculating balance for initial value [%s] for period of [%s] month", initialBalance, months));
        BigDecimal balance = initialBalance;
        int years = months / 12 + (months % 12 == 0 ? 0 : 1);
        logger.debug(String.format("Transferring [%s] month gives us [%s] of years", months, years));
        for (int i = 1; i <= years; i++) {
            //            balance = balance + balance * (months > 12 ? 12 : months) / 12 * getInterestRate(balance) / 100;
            balance = balance.add(
                    balance.multiply(BigDecimal.valueOf((months > 12) ? 12 : months)).
                            divide(BigDecimal.valueOf(12))
                            .multiply(BigDecimal.valueOf(getInterestRate(balance)))
                            .divide(BigDecimal.valueOf(100), 2, BigDecimal.ROUND_UP)) ;
            logger.debug(String.format("fot [%s] period of year the balance is [%s]", i, balance));
            months = months - 12;
        }
        logger.debug(String.format("The final balance is [%s]", balance));
        return balance;
    }

    /**
    * Returns an {@code int} interest rate depending on the {@code balance} balance in the account.
     * The balance in the range up to $100 has a 3% interest rate.
     * The balance over $100 and up to $1000 has 5% interest rate.
     * The balance of $1000 and over have a 7% rate.
     *
     * @param balance a {@code BigDecimal} bank account balance.
     * @return {@code int} rate of interest depending on the balance in the account.
     * @throws Exception if balance value is not positive
    */
    public int getInterestRate(final BigDecimal balance) throws Exception{
        if (balance.compareTo(BigDecimal.valueOf(0)) < 0){
            throw new Exception("Balance value should be not negative number");
        }
        int interestRate;
        if (balance.compareTo(BigDecimal.valueOf(100)) < 1) {
            interestRate = 3;
        } else if (balance.compareTo(BigDecimal.valueOf(1000)) < 0) {
            interestRate = 5;
        } else {
            interestRate = 7;
        }
        return interestRate;
    }
}
