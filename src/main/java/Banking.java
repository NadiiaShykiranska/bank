import java.math.BigDecimal;

public interface Banking {
    /**
     * Calculate balance {@code BigDecimal} of saving account in a bank with earn.
     * Saving account in bank earns a different rate of interest depending on the
     * {@code BigDecimal} balance in the account and a {@code int} period of deposit.
     */
    BigDecimal calculateBalance(final int months, final BigDecimal initialBalance) throws Exception;

    /**
     * Returns an {@code int} interest rate depending on the {@code balance} balance in the account.
     *
     * @param balance a {@code BigDecimal} bank account balance.
     * @return {@code int} rate of interest depending on the balance in the account.
     * @throws Exception if balance value is
     */
    int getInterestRate(final BigDecimal balance) throws Exception;
}
