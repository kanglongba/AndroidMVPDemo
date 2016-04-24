package edison.bupt.com.androidmvpdemo.Login.model;

/**
 * Created by unclelong on 16/4/23.
 * 这个接口在分类上，应该也属于抽象业务逻辑，但是在作用上，它与IView是一类的。
 * 这个接口，只是用来操作Model，而对数据的操作，无外乎：增删改查。所以这个接口定义的方法，也是该是这四种。
 * 在这四种之外的操作数据的方法，应该尽量放在IPresenter中。
 * Model实现这个接口，Presenter调用这个接口。
 */
public interface IUser {
    public String getName();
    public String getPassword();
    public boolean checkUserValidity(String name,String passwd);
}
