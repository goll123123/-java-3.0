import Dao.AccountDao;
import Dao.MenuDao;
import Service.AccountService;
import Service.MenuService;
import View.StartPanel;

public class Start {

    MenuDao menuDao;
    AccountDao accountDao;
    MenuService menuService;
    AccountService accountService;
    StartPanel startPanel;

    public Start() {
        this.menuDao = new MenuDao();
        this.accountDao = new AccountDao();
        this.menuService = new MenuService(this.menuDao);
        this.accountService = new AccountService(this.accountDao);
        this.startPanel = new StartPanel(this.accountService,this.menuService);
    }

    public static void main(String[] args) {
       Start start = new Start();
       start.startPanel.startPanel();
    }
}
