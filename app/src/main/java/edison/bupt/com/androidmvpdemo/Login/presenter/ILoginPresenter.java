package edison.bupt.com.androidmvpdemo.Login.presenter;

/**
 * Created by unclelong on 16/4/23.
 * 抽象业务逻辑，Presenter实现这个接口，View和Model调用这个接口。
 * 从而实现View通过Presenter更改Model，Model通过Presenter更新View。
 */
public interface ILoginPresenter {
    public void clear();
    public void doLogin(String username,String password);
    public void setProgressBarVisibility(int visibility);
}
