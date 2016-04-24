package edison.bupt.com.androidmvpdemo.Login.view;

/**
 * Created by unclelong on 16/4/23.
 * 抽象视图逻辑，View实现这个接口，Presenter调用这个接口.从而通过Presenter控制UI的更新。
 * 不要把视图逻辑简单地理解成是所有在View层可能被用到的更新UI的方法的集合，它应该是在根据业务逻辑的处理结果，需要对UI做出相应改变的情况下，而设计出来的方法的集合。
 * 仅包含业务逻辑需要的方法，而不是所有的。本例中onSetProgressBarVisibility()方法，就是冗余的。
 * 视图逻辑的抽象，是MVP模式的核心之一。
 */
public interface ILoginView {
    public void onClearText();
    public void onLoginResult(boolean result,int code);
    public void onSetProgressBarVisibility(int visibility);
}
