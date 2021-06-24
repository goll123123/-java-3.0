package View;

import Service.AccountService;
import Service.MenuService;
import entity.Dish;
import entity.DishDetail;
import entity.User;
import utils.R;

import java.util.ArrayList;
import java.util.Scanner;

public class StartPanel {
    Scanner sc;
    private AccountService accountService;
    private MenuService menuService;

    public StartPanel(AccountService accountService, MenuService menuService) {
        this.accountService = accountService;
        this.menuService = menuService;
        sc = new Scanner(System.in);
    }

    public void startPanel() {
        System.out.println("======================================================开始界面========================================================");
        System.out.println("                                             1.登录    2.注册    3.退出          ");
        System.out.println("=====================================================================================================================");
        int index = sc.nextInt();
        if (index == 1) {
            this.loginPanel();
        } else if (index == 2) {
            this.registerPanel();
        } else if (index == 3) {
            System.exit(0);
        }

    }

    public void loginPanel() {
        System.out.println("           ============登录============");
        System.out.print("            请输入账号："         );
        String id = sc.next();
        System.out.print("            请输入密码：");
        String password = sc.next();
        R R = accountService.login(id, password);
        if (R.getCode() != 200) {
            System.out.println(R.getMessage());
            startPanel();
        } else {
            System.out.println("           =====欢迎"+(((User)R.getData()).getName()+"使用该系统!====="));
            masterPanel();
        }
    }

    public void registerPanel() {
        System.out.println("           ============注册============");
        System.out.print("            请输入账号:");
        String id = sc.next();
        System.out.print("            请输入密码:");
        String password = sc.next();
        System.out.print("            请输入名称:");
        String name = sc.next();
        User user = new User(id, name, password);
        R R = accountService.register(user);
        if (R.getCode() != 200) {
            System.out.println(R.getMessage());
            startPanel();
        } else {
            System.out.println("           ==========注册成功!==========");
            startPanel();
        }
    }

    public void masterPanel() {
        System.out.println("======================================================管理员界面======================================================");
        System.out.println("                                   1.查看所有的食谱     2.添加食谱          3.删除食谱    " + '\n' + "                                   4.设置早中晚餐食谱   5.公布早中晚餐食谱   6.注销");
        System.out.println("=====================================================================================================================");
        int index = sc.nextInt();
        switch (index) {
            case 1:
                ArrayList arrayList = (ArrayList) menuService.queryAll().getData();
                System.out.println("                                   ===================总食谱===================");
                System.out.printf("                                   %6s %8s \t%10s \n", " 食谱id  ", "  食谱名称", "食谱价格(单价元)");
                System.out.println("                                   ===========================================");

                for (Object dish : arrayList) {
                    Dish dish1 = (Dish) dish;
                    System.out.printf("                                   %6s %12s \t%9s \n", dish1.getId(), dish1.getDishDetail().toString_1(), dish1.getDishDetail().toString_2());
                    //                    System.out.println('\t' + dish1.getId() + '\t' + dish1.getDishDetail().toString());
                    //System.out.println(dish1.getId() + "\t" + dish1.getDishDetail().toString());
                }
                masterPanel();
                break;
            case 2:
                System.out.print("请设置食谱id:");
                String id = sc.next();
                System.out.print("请设置食谱名称:");
                String name = sc.next();
                System.out.print("请设置食谱价格:");
                String price = sc.next();
                DishDetail dishDetail = new DishDetail(name, price);
                Dish dish = new Dish(id, dishDetail);
                R R = menuService.addDish(dish);
                if (R.getCode() != 200) {
                    System.out.println(R.getMessage());
                    masterPanel();
                } else {
                    System.out.println("添加成功");
                    masterPanel();
                }
                break;
            case 3:
                System.out.print("请输入您要删除食谱的id:");
                String Dishid = sc.next();
                R R2 = menuService.deleteDish(Dishid);
                if (R2.getCode() != 200) {
                    System.out.println(R2.getMessage());
                    masterPanel();
                } else {
                    System.out.println("删除成功");
                    masterPanel();
                }
                break;
            case 4:
                ArrayList arrayList_1 = (ArrayList) menuService.queryAll().getData();
                //System.out.println(arrayList);
                System.out.printf("                                   %6s %8s \t%10s \n", " 食谱id  ", "  食谱名称", "食谱价格(单价元)");
                for (Object dish_1 : arrayList_1) {
                    Dish dish1 = (Dish) dish_1;
                    System.out.println("                                   "+'\t' + dish1.getId() + '\t' + dish1.getDishDetail().toString());
                }
                //===================================================
                System.out.print("请选择想加入早餐中的id:");
                String id_1 = sc.next();
                R = menuService.setMorning(id_1);
                if (R.getCode() == 200) {
                    System.out.println("设置成功");
                    add_morning();

                } else {
                    System.out.println(R.getMessage());
                    add_morning();
                }
                //====================================================
                System.out.print("请选择想加入午餐中的id:");
                String id_2 = sc.next();
                R = menuService.setAfternoon(id_2);
                if (R.getCode() == 200) {
                    System.out.println("设置成功");
                    add_afternoon();

                } else {
                    System.out.println(R.getMessage());
                    add_afternoon();
                }
                //====================================================
                System.out.print("请选择想加入晚餐中的id:");
                String id_3 = sc.next();
                R = menuService.setEvening(id_3);
                if (R.getCode() == 200) {
                    System.out.println("设置成功");
                    add_Evening();

                } else {
                    System.out.println(R.getMessage());
                    add_Evening();
                }
                masterPanel();
                break;
            case 5:
                System.out.println("                                   ==================早餐食谱==================");
                ArrayList arrayList_1_1 = (ArrayList) menuService.queryAll().getData();
                System.out.printf("                                   %6s %8s \t%10s \n", " 食谱id  ", "  食谱名称", "食谱价格(单价元)");
                //System.out.println("食谱id" + "\t" + "食谱名称" + "\t" + "食谱价格(单价元)");

                for (Object dish_1_1 : arrayList_1_1) {
                    Dish dish1 = (Dish) dish_1_1;
                    if (dish1.isMorning()) {
                        System.out.printf("                                   %6s %12s \t%9s \n", dish1.getId(), dish1.getDishDetail().toString_1(), dish1.getDishDetail().toString_2());
                        //                    System.out.println('\t' + dish1.getId() + '\t' + dish1.getDishDetail().toString());
                        //System.out.println(dish1.getId() + "\t" + dish1.getDishDetail().toString());
                    }
                }
                System.out.println("                                   ==================午餐食谱==================");
                ArrayList arrayList_1_2 = (ArrayList) menuService.queryAll().getData();
                System.out.printf("                                   %6s %8s \t%10s \n", " 食谱id  ", "  食谱名称", "食谱价格(单价元)");
                //System.out.println("食谱id" + "\t" + "食谱名称" + "\t" + "食谱价格(单价元)");

                for (Object dish_1_1 : arrayList_1_2) {
                    Dish dish1 = (Dish) dish_1_1;
                    if (dish1.isAfternoon()) {
                        System.out.printf("                                   %6s %12s \t%9s \n", dish1.getId(), dish1.getDishDetail().toString_1(), dish1.getDishDetail().toString_2());
                        //                    System.out.println('\t' + dish1.getId() + '\t' + dish1.getDishDetail().toString());
                        //System.out.println(dish1.getId() + "\t" + dish1.getDishDetail().toString());
                    }
                }
                System.out.println("                                   ==================晚餐食谱==================");
                ArrayList arrayList_1_3 = (ArrayList) menuService.queryAll().getData();
                System.out.printf("                                   %6s %8s \t%10s \n", " 食谱id  ", "  食谱名称", "食谱价格(单价元)");
                //System.out.println("食谱id" + "\t" + "食谱名称" + "\t" + "食谱价格(单价元)");

                for (Object dish_1_1 : arrayList_1_3) {
                    Dish dish1 = (Dish) dish_1_1;
                    if (dish1.isEvening()) {
                        System.out.printf("                                   %6s %12s \t%9s \n", dish1.getId(), dish1.getDishDetail().toString_1(), dish1.getDishDetail().toString_2());
                        //                    System.out.println('\t' + dish1.getId() + '\t' + dish1.getDishDetail().toString());
                        //System.out.println(dish1.getId() + "\t" + dish1.getDishDetail().toString());
                    }
                }
                masterPanel();
                break;

            case 6:
//                System.out.println("是否注销？（y/n）");
                System.out.println("注销成功");
                startPanel();
                break;
        }
    }

    private void add_morning() {
        R R;
        for (; ; ) {
            System.out.print("是否继续设置[Y/N]:");
            String flag = sc.next();
            if (flag.equals("Y")) {
                System.out.print("请选择想加入早餐中的id:");
                String id_1_1 = sc.next();
                R = menuService.setMorning(id_1_1);
                if (R.getCode() == 200) {
                    System.out.println("设置成功");
                } else {
                    System.out.println(R.getMessage());
                }
            } else break;
        }
    }

    private void add_afternoon() {
        R R;
        for (; ; ) {
            System.out.print("是否继续设置[Y/N]:");
            String flag = sc.next();
            if (flag.equals("Y")) {
                System.out.print("请选择想加入午餐中的id:");
                String id_1_1 = sc.next();
                R = menuService.setAfternoon(id_1_1);
                if (R.getCode() == 200) {
                    System.out.println("设置成功");
                } else {
                    System.out.println(R.getMessage());
                }
            } else break;
        }
    }

    private void add_Evening() {
        R R;
        for (; ; ) {
            System.out.print("是否继续设置[Y/N]:");
            String flag = sc.next();
            if (flag.equals("Y")) {
                System.out.print("请选择想加入晚餐中的id:");
                String id_3_1 = sc.next();
                R = menuService.setEvening(id_3_1);
                if (R.getCode() == 200) {
                    System.out.println("设置成功");
                } else {
                    System.out.println(R.getMessage());
                }
            } else break;
        }
    }


}
