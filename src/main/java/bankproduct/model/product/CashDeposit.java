package bankproduct.model.product;

import bankproduct.model.dictionary.Currency;

public class CashDeposit extends Product {
    private boolean isClosed;

    public CashDeposit(Currency currency, Double balance, String name) {
        super(currency, balance, name);
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }

}
