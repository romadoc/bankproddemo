package bankproduct.service;

import bankproduct.model.product.CashDeposit;

public class CashDepositService {
    private final CashDeposit cashDeposit;

    public CashDepositService(CashDeposit cashDeposit) {
        this.cashDeposit = cashDeposit;
    }

    public boolean addMoney(double money) {
        if (!cashDeposit.isClosed() && money > 0) {
            cashDeposit.setBalance(cashDeposit.getBalance() + money);
            System.out.println("депозит пополнен " + money);
             return true;
        } else {
            System.out.println("Отказано. Проверьте статус депозита или корректность суммы пополнения");
        }
        return false;
    }

    public double getBalance() {
        return this.cashDeposit.getBalance();
    }

    public void closeDeposit() {
        cashDeposit.setBalance(0.0);
        cashDeposit.setClosed(true);
    }

    public void getDepositInfo() {
        System.out.println("Текущий баланс: " + getBalance());
        System.out.println("Валюта " + cashDeposit.getCurrency());
        System.out.println("Название " + cashDeposit.getName());
        System.out.println("Состояние депозита: " + getDepositStatus());
    }

    public String getDepositStatus() {
        if (cashDeposit.isClosed()) {
            return "Закрыт";
        }
        return "Активен";
    }

}
