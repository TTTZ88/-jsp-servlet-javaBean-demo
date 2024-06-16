package Filter;

import javax.servlet.*;
import java.io.IOException;

public class MyFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 初始化方法，可以在此处进行一些初始化操作
        System.out.println("Filter init");
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // 在此处编写过滤逻辑

        // 对请求进行处理...

        // 将请求传递给下一个过滤器或目标资源
        chain.doFilter(request, response);
    }
    @Override
    public void destroy() {
        // 销毁方法，可以在此处进行一些清理操作
    }
}