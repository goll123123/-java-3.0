package utils;

import entity.Dish;

public enum Staus {
    Success(200,"success!"),
    Account_Exist(201,"账号已存在，注册失败"),
    Login_Lost(202,"未找到账户,登录失败"),
    Login_Lost2(203,"密码错误,登录失败"),
    No_DishId(204,"未找到该菜，查询失败"),
    Dish_Exist(205,"已有相同id的菜品，添加失败"),
    Dish_DeleteNone(206,"未找到该食谱，删除失败"),
    Dish_SetNone(207,"未找到该食谱，设置失败"),
    Change_To(208,"输入格式错误");



    int code;
    String message;

    Staus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
