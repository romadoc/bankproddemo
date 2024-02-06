package bankproduct.model.product;

import bankproduct.model.dictionary.Currency;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Product {
    private String name;
    private String currency;
    private double balance; //в реале нужно использовать BigDecimal

    public Product(Currency currency, Double balance, String name) {
        this.currency = String.valueOf(currency);
        this.name = name;
        this.balance = balance;
    }

}
