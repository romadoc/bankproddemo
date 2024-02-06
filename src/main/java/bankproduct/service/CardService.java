package bankproduct.service;

import bankproduct.model.impl.CardTag;
import bankproduct.model.product.DebitCard;
import bankproduct.model.product.Product;

public class CardService<T extends Product & CardTag> {
    private T card;
    private double credit;

    public CardService(T card) {
        this.card = card;
    }

    public boolean addMoney(double money) {
        if (money > 0) {
            card.setBalance(card.getBalance() + money);
            System.out.println("Зачислено на карту " + money + " (" + card.getCurrency() + ")");
            return true;
        } else {
            System.out.println("Проверьте пополняемую сумму!");
        }
        return false;
    }

    public boolean takeOutMoney(double money) {
        if (money <= card.getBalance() && money >= 0) {
            double delta = card.getBalance() - money;
            card.setBalance(delta);
            if (card instanceof DebitCard) {
                credit = delta;
            }
            return true;
        } else {
            System.out.println("Проверьте сумму снятия!");
        }
        return false;
    }

    public double getCardBalance() {
        return card.getBalance();
    }

    public double getFullDept(DebitCard debitCard) {
        double percent = debitCard.getPercentPerYear();
        return credit + ((credit/100) * percent);
    }

}
