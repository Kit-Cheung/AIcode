import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.cef.*;
import org.cef.browser.*;
import org.cef.callback.*;
import org.cef.handler.*;

public class WebViewDemo extends JFrame {
public WebViewDemo() {
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setSize(800, 600);
setLocationRelativeTo(null);

// 初始化 CEF
CefApp.addAppHandler(new AppHandlerAdapter());
CefApp.addMessageHandler(new MessageHandler() {
@Override
public boolean onQuery(CefBrowser browser, long query_id, String request, boolean persistent,
Callback callback) {
callback.success("Hello from Java!");
return true;
}
});
CefSettings settings = new CefSettings();
CefApp cefApp = CefApp.getInstance(settings);
CefClient client = cefApp.createClient();

// 创建浏览器
CefBrowser browser = client.createBrowser("http://www.baidu.com", true, false);

// 将浏览器添加到窗口
Component browserUI = browser.getUIComponent();
getContentPane().add(browserUI, BorderLayout.CENTER);
}

public static void main(String[] args) {
SwingUtilities.invokeLater(() -> {
new WebViewDemo().setVisible(true);
});
}

private static class AppHandlerAdapter extends CefAppHandlerAdapter {
@Override
public void stateHasChanged(CefAppState state) {
if (state == CefAppState.TERMINATED) {
System.exit(0);
}
}
}
}
