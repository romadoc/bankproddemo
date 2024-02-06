package bankproduct.model.product;

import bankproduct.model.dictionary.Currency;
import bankproduct.model.impl.CardTag;
import lombok.Getter;

@Getter
public class DebitCard extends Product implements CardTag {
    private final double percentPerYear;
    public DebitCard(Currency currency, Double balance, String name, double percentPerYear) {
        super(currency, balance, name);
        this.percentPerYear = percentPerYear;
    }

}
