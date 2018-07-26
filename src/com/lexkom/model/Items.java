package com.lexkom.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Model class for a Person.
 *
 * @author Marco Jakob
 */
public class Items {

    private final StringProperty article;
    private final StringProperty Name;
    private final StringProperty quantity;
    private final StringProperty description;
    private final StringProperty costUSD;
    private final StringProperty costUAH;
    private final StringProperty price;
    private final StringProperty income;
    private final StringProperty efficiency;
    private final StringProperty availability;
    private final StringProperty imageSource;


//    private final ObjectProperty<LocalDate> birthday;

    /**
     * Default constructor.
     */
//    public ItemsModel() {
//        this(null, null, 0, null,0.0, 0.0, 0.0, 0.0, 0.0);
//    }

    /**
     * Constructor with some initial data.
     *
     //     * @param article
     //     * @param Name
     */
    public Items(String article, String name, String quantity,
                 String descript, String costUSD, String costUAH,
                 String price, String income, String efficiency,
                 String availability, String imageSource) {
        this.article = new SimpleStringProperty(article);
        this.Name = new SimpleStringProperty(name);
        this.quantity = new SimpleStringProperty(quantity);
        this.description = new SimpleStringProperty(descript);
        this.costUSD = new SimpleStringProperty(costUSD);
        this.costUAH = new SimpleStringProperty(costUAH);
        this.price = new SimpleStringProperty(price);
        this.income = new SimpleStringProperty(income);
        this.efficiency = new SimpleStringProperty(efficiency);
        this.availability = new SimpleStringProperty(availability);
        this.imageSource = new SimpleStringProperty(imageSource);
    }



    public String getArticle() {
        return article.get();
    }

    public StringProperty articleProperty() {
        return article;
    }

    public void setArticle(String article) {
        this.article.set(article);
    }

    public String getName() {
        return Name.get();
    }

    public StringProperty nameProperty() {
        return Name;
    }

    public void setName(String name) {
        this.Name.set(name);
    }

    public String getQuantity() {
        return quantity.get();
    }

    public StringProperty quantityProperty() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity.set(quantity);
    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public String getCostUSD() {
        return costUSD.get();
    }

    public StringProperty costUSDProperty() {
        return costUSD;
    }

    public void setCostUSD(String costUSD) {
        this.costUSD.set(costUSD);
    }

    public String getCostUAH() {
        return costUAH.get();
    }

    public StringProperty costUAHProperty() {
        return costUAH;
    }

    public void setCostUAH(String costUAH) {
        this.costUAH.set(costUAH);
    }

    public String getPrice() {
        return price.get();
    }

    public StringProperty priceProperty() {
        return price;
    }

    public void setPrice(String price) {
        this.price.set(price);
    }

    public String getIncome() {
        return income.get();
    }

    public StringProperty incomeProperty() {
        return income;
    }

    public void setIncome(String income) {
        this.income.set(income);
    }

    public String getEfficiency() {
        return efficiency.get();
    }

    public StringProperty efficiencyProperty() {
        return efficiency;
    }

    public void setEfficiency(String efficiency) {
        this.efficiency.set(efficiency);
    }

    public String getAvailability() {
        return availability.get();
    }

    public StringProperty availabilityProperty() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability.set(availability);
    }

    public String getImageSource() {
        return imageSource.get();
    }

    public StringProperty imageSourceProperty() {
        return imageSource;
    }

    public void setImageSource(String imageSource) {
        this.imageSource.set(imageSource);
    }
//    public LocalDate getBirthday() {
//        return birthday.get();
//    }
//
//    public void setBirthday(LocalDate birthday) {
//        this.birthday.set(birthday);
//    }
//
//    public ObjectProperty<LocalDate> birthdayProperty() {
//        return birthday;
//    }
}