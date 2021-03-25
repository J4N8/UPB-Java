import java.sql.Date;

public class ShoppingCart {
    int id;
    Product product;
    //int user_id;
    Date date;
    double current_price;

    ShoppingCart(int id, Date date, double current_price, Product product){
        //shopping cart properties
        this.id = id;
        this.date = date;
        this.current_price = current_price;

        //product properties
        this.product = product;
    }


    @Override
    public String toString(){
        return id + " ; " + product.name + " ; " + current_price + " ; " + date;
    }

}
