package bankproduct.model.dictionary;

public enum Currency {
    USD ("Доллар США"),
    RUB ("Рубль российский"),
    EUR ("Евро");
    private String title;

    Currency(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
