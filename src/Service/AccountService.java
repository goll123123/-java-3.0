package Service;


import Dao.AccountDao;
import entity.User;
import utils.R;
import utils.Staus;

public class AccountService {
  private AccountDao accountDao;

    public AccountService(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public AccountService() {

    }

    /**
     * 登录
     * 参数：用户账号(String)
     * */
    public R login(String id,String password){
        User user;
        try{
        user =  accountDao.selectById(id);
        }catch (Exception e){
        return    R.err(Staus.Login_Lost.getCode(),Staus.Login_Lost.getMessage());
        }
        if ( !user.getPassword().toString().equals(password )){
         return R.err(Staus.Login_Lost2.getCode(),Staus.Login_Lost2.getMessage());
        }
        return R.ok(user);
    }

    /**
     * 注册
     * 参数:用户(User类)
     * */
    public R register(User user){
        try {
            accountDao.insert(user);
        }catch (Exception e){
            return R.err(Staus.Account_Exist.getCode(),Staus.Account_Exist.getMessage());
        }
        return R.ok(null);
    }
}
