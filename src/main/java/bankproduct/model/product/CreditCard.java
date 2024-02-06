package bankproduct.model.product;

import bankproduct.model.dictionary.Currency;
import bankproduct.model.impl.CardTag;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreditCard extends Product implements CardTag {
    private double percent;
    private double dept;

    public CreditCard(Currency currency, Double balance, String name) {
        super(currency, balance, name);
    }

}
