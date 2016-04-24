package edison.bupt.com.androidmvpdemo.Login.model;

/**
 * Created by unclelong on 16/4/23.
 */
public class UserModel implements IUser {
    String name;
    String passwd;

    public UserModel(String name,String passwd){
        this.name = name;
        this.passwd = passwd;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getPassword() {
        return this.passwd;
    }

    @Override
    public boolean checkUserValidity(String name, String passwd) {
        if(null==name||null==passwd||!name.equals(getName())||!passwd.equals(getPassword())){
            return false;
        }
        return true;
    }
}
