package edison.bupt.com.androidmvpdemo.Login.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edison.bupt.com.androidmvpdemo.HomeActivity;
import edison.bupt.com.androidmvpdemo.Login.presenter.ILoginPresenter;
import edison.bupt.com.androidmvpdemo.Login.presenter.LoginPresenterCompl;
import edison.bupt.com.androidmvpdemo.R;

/**
 * 源自 http://zhuanlan.zhihu.com/p/20312610?refer=kaede
 *
 * 个人认为MVP模式的主要思想，是把controller的逻辑从View（Activity或Fragment）中抽离，使View变得很简单，从而杜绝因View层臃肿而带来的各种问题。
 *
 * MVP相较MVC，P层很厚，M层和V层很薄。并且，M层和V层不能直接通信。
 *
 * MVP模式通过面向接口编程来实现，Presenter，Model和View各自有自己的接口，并且都会在本层实现它们。View层的接口提供更新UI的方法，Model层的接口
 * 提供数据的增删改查方法，Presenter层的接口提供所有业务逻辑相关的方法。
 * Model和View会把各自接口的引用（也就是自己）传递给Presenter，使Presenter可以根据业务逻辑的需要，通过调用Model接口和View接口中的方法更新数据和UI。
 * 而Presenter也会把自己接口的引用（Presenter本身）传递给Model和View，使Model和View可以根据自己状态的变化，通过Presenter更新对方。这样Presenter
 * 就把View和Model完全隔离开了，也把所有的逻辑操作，都抽象到了Presenter中。
 *
 * 如何设计接口：
 * 1.IModel, Model层的接口设计秉承一个原则：只提供对数据的增删改查方法。本例中，getName()，getPassword()和checkUserValidity()都是查询方法。
 * 2.IPresenter, 分析一共有多少种业务逻辑，设计出相应的方法。本例中，一共有两种逻辑：登陆和清除，对应clear()和doLogin()。
 * 3.IView，分析业务逻辑的变化可以使UI出现多少种情况，从而定义多少种接口方法。本例中，一共有两种业务逻辑（登陆成功和登陆失败，本质上都是登陆后的情况，
 * 可以归为一种），对应有两种UI的变化，所以设计出两个接口方法：onClearText()和onLoginResult()。
 */
public class LoginActivity extends AppCompatActivity implements ILoginView{

    @Bind(R.id.username)
    EditText username;
    @Bind(R.id.password)
    EditText password;
    @Bind(R.id.login)
    Button login;
    @Bind(R.id.clear)
    Button clear;
    @Bind(R.id.tips)
    TextView tips;
    @Bind(R.id.progressbar)
    ProgressBar progressbar;

    ILoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        loginPresenter = new LoginPresenterCompl(this);
    }

    /**
     * 本例在点击事件中（用户交互），调用了Presenter层的业务逻辑方法。
     * 抽象出来的业务逻辑，在这里派上了用场。
     */
    @OnClick(R.id.login)
    public void login() {
        loginPresenter.setProgressBarVisibility(View.VISIBLE); //我觉得这种纯UI操作，没必要抽象到Presenter层。而且在本例中，这个方法的存在也毫无意义。
        login.setEnabled(false);
        clear.setEnabled(false);
        loginPresenter.doLogin(username.getText().toString(),password.getText().toString()); //业务逻辑
    }

    @OnClick(R.id.clear)
    public void clear() {
        loginPresenter.clear(); //业务逻辑
    }

    @Override
    public void onClearText() {
        username.setText("");
        password.setText("");
        tips.setText("");
    }

    @Override
    public void onLoginResult(boolean result, int code) {
        loginPresenter.setProgressBarVisibility(View.GONE);
        login.setEnabled(true);
        clear.setEnabled(true);
        if(result){
            Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
            finish(); //关闭Login页面
        }else{
            Toast.makeText(LoginActivity.this, "Login Fail, code = "+code, Toast.LENGTH_SHORT).show();
            clear.performClick(); //清除输入内容
            tips.setText("invalid username or password");
        }
    }

    @Override
    public void onSetProgressBarVisibility(int visibility) {
        progressbar.setVisibility(visibility);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
