import bankproduct.model.dictionary.Currency;
import bankproduct.model.product.CashDeposit;
import bankproduct.model.product.CreditCard;
import bankproduct.model.product.DebitCard;
import bankproduct.service.CardService;
import bankproduct.service.CashDepositService;
import io.qameta.allure.Feature;
import logger.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

@Feature("Smoke тестирование. Проект продукты банка, карты и депозиты")
//Примеры возможных тестов продуктов: карты и депозиты

public class BankProductTest {
    @Test(description = "проверка внесения денег на депозит")
    public void isDepositIncreasedCorrectly() {
        CashDeposit cashDeposit = new CashDeposit(Currency.EUR, 50.0, "EuroDeposit");
        CashDepositService depositService = new CashDepositService(cashDeposit);
        Assert.assertEquals(depositService.getBalance(), 50.0, "Депозит должен составить 50.0");
    }

    @Test(description = "проверка функции закрытия депозита")
    public void isDepositCloseFunctionOk() {
        CashDeposit cashDeposit = new CashDeposit(Currency.EUR, 50.0, "EuroDeposit");
        CashDepositService depositService = new CashDepositService(cashDeposit);
        depositService.closeDeposit();
        Assert.assertEquals(depositService.getDepositStatus(), "Закрыт", "Депозит должен быть закрыт");
    }

    @Test(description = "проверка возможности проведения операций с закрытым депозитом")
    public void isClosedDepositNotWorkable() {
        CashDeposit cashDeposit = new CashDeposit(Currency.RUB, 150.0, "Deposit");
        CashDepositService depositService = new CashDepositService(cashDeposit);
        Assert.assertEquals(depositService.getBalance(), 150.0);
        depositService.closeDeposit();
        Assert.assertNotEquals(depositService.addMoney(50), true,
                "Закрытый депозит не должен быть доступен для операции со счетом");
    }

    @Test(description = "защита от внесения некорректной суммы")
    public void isNegativeSumNotWorkable() {
        CashDeposit cashDeposit = new CashDeposit(Currency.USD, 150.0, "Deposit USD");
        CashDepositService depositService = new CashDepositService(cashDeposit);
        depositService.addMoney(-10);
        Assert.assertEquals(depositService.getBalance(), 150.0, "Сумма депозита не должна измениться");
    }

    @Test(description = "проверка внесения денег на карту")
    public void isMoneyAddedToCardCorrectly() {
        CreditCard creditCard = new CreditCard(Currency.RUB, 100.0, "CommonCard");
        CardService service = new CardService(creditCard);
        service.addMoney(50.0);
        Assert.assertEquals(service.getCardBalance(), 150.0, "Зачисление прошло успешно");
    }

    @Test(description = "проверка внесения денег на валютную карту")
    public void isMoneyCurrencyAddedToCardCorrectly() {
        CreditCard creditCard = new CreditCard(Currency.USD, 100.0, "Currency USD");
        CardService service = new CardService(creditCard);
        service.addMoney(50.0);
        Assert.assertEquals(service.getCardBalance(), 150.0, "Зачисление прошло успешно");
    }

    @Test(description = "проверка защиты от некорректного (over) вывода денег с карты, рублевая")
    public void isMoneyTakenOffCorrectly() {
        CreditCard creditCard = new CreditCard(Currency.RUB, 100.0, "CommonCard");
        CardService service = new CardService(creditCard);
        service.addMoney(50.0);
        Assert.assertEquals(service.getCardBalance(), 150.0);
        Assert.assertFalse(service.takeOutMoney(200.0), "отказ совершения операции");
    }

    @Test(description = "проверка защиты от некорректного (over) вывода денег с карты, валютная")
    public void isMoneyCurrencyTakenOffCorrectly() {
        CreditCard creditCard = new CreditCard(Currency.USD, 100.0, "Currency USD");
        CardService service = new CardService(creditCard);
        service.addMoney(50.0);
        Logger.info("Добавлено 50 долларов на счет. - (Это пример работы логгера, см. папку /logs)");
        Assert.assertEquals(service.getCardBalance(), 150.0);
        Assert.assertFalse(service.takeOutMoney(200.0), "отказ совершения операции");
    }

    @Test(description = "проверка защиты от снятия денег с карты с изначально отрицательным баллансом")
    public void isCardSystemReactsCorrectlyToError() {
        CreditCard creditCard = new CreditCard(Currency.RUB, -100.0, "CommonCard");
        CardService service = new CardService(creditCard);
        Assert.assertFalse(service.takeOutMoney(5.0), "отказ совершения операции");
    }

    @Test(description = "проверка корректного начисления процентов. кретидная карта")
    public void debitCardCountPercentCorrectly() {
        DebitCard debitCard = new DebitCard(Currency.RUB, 100.0, "Карта Быстрые деньги", 10.0);
        CardService cardService = new CardService<>(debitCard);
        cardService.takeOutMoney(50);
        Assert.assertEquals(cardService.getFullDept(debitCard), 55.0,
                "сумма долга с учетом процента д.б. 55.0");
    }
}
