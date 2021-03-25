public class Product {
    /**
     * Product id (used in database operations)
     */
    int id;

    String name, description;

    double price;

    /**
     * Contact constructor
     *
     * @param id the new product id
     */
    Product(int id) {
        this.id = id;
    }

    /**
     * Contact constructor
     *
     * @param id   the new contact it
     * @param name contact name
     */
    Product(int id, String name, double price, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    @Override
    public String toString(){
        return id + " ; " + name + " ; " + price + " ; " + description;
    }
}
