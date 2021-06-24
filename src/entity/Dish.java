package entity;

public class Dish {
    private  String id;
    int morning=0,afternoon=0,evening=0;
    private DishDetail dishDetail;

    public Dish(String id, int morning, int afternoon, int evening, DishDetail dishDetail) {
        this.id = id;
        this.morning = morning;
        this.afternoon = afternoon;
        this.evening = evening;
        this.dishDetail = dishDetail;
    }

    public boolean isMorning() {
        return morning == 1?true:false;
    }

    public void setMorning(int morning) {
        this.morning = morning;
    }

    public boolean isAfternoon() {
        return afternoon == 1?true:false;

    }

    public void setAfternoon(int afternoon) {
        this.afternoon = afternoon;
    }

    public boolean isEvening() {
           return evening == 1?true:false;

    }

    public void setEvening(int evening) {
        this.evening = evening;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DishDetail getDishDetail() {
        return dishDetail;
    }

    public void setDishDetail(DishDetail dishDetail) {
        this.dishDetail = dishDetail;
    }

    public Dish(String id,  DishDetail dishDetail) {
        this.id = id;
        this.morning = 0;
        this.afternoon = 0;
        this.evening = 0;
        this.dishDetail = dishDetail;
    }

    @Override
    public String toString() {
        return '\n'+"食谱id:"+ id + '\t' + dishDetail;
    }
}
