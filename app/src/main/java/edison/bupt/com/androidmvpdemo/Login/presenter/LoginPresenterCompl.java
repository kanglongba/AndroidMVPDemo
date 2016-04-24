package edison.bupt.com.androidmvpdemo.Login.presenter;

import android.os.Handler;
import android.os.Looper;

import edison.bupt.com.androidmvpdemo.Login.model.IUser;
import edison.bupt.com.androidmvpdemo.Login.model.UserModel;
import edison.bupt.com.androidmvpdemo.Login.view.ILoginView;

/**
 * Created by unclelong on 16/4/23.
 * 持有ILoginView和IUser的引用，分别用于更新UI和数据。
 */
public class LoginPresenterCompl implements ILoginPresenter {
    ILoginView iLoginView;
    IUser iUser;

    Handler handler;

    public LoginPresenterCompl(ILoginView iLoginView) {
        this.iLoginView = iLoginView;

        handler = new Handler(Looper.getMainLooper());
        initUser();
    }

    private void initUser() {
        iUser = new UserModel("mvp", "mvp");
    }

    /**
     * 对业务逻辑的处理，一般都会有两种结果：更新数据和反馈回UI。所以，业务逻辑方法中，多数会调用IView和IModel接口中的相应方法。
     * 本例中，处理完业务逻辑，调用IView的方法顺便更新了UI。抽象出来的视图逻辑，在这里派上了用场。
     */
    @Override
    public void clear() {
        iLoginView.onClearText();
    }

    @Override
    public void doLogin(String username, String password) {
        final boolean isLoginSuccess = iUser.checkUserValidity(username, password);
        final int code = isLoginSuccess ? 0 : -1;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                iLoginView.onLoginResult(isLoginSuccess, code);
            }
        }, 5000);
    }

    @Override
    public void setProgressBarVisibility(int visibility) {
        iLoginView.onSetProgressBarVisibility(visibility);
    }
}
