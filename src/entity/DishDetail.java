package entity;

public class DishDetail {
    String name;
    String price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public DishDetail(String name, String price) {
        this.name = name;
        this.price = price;

    }

    @Override
    public String toString() {
          return name +"\t\t" +price;
      //  return name + '\t' +'\t'+'\t'+price;
    }

    public String toString_1(){
        return name;
    }
    public String toString_2(){
        return price;
    }
}
