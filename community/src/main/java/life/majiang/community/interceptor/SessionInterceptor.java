package life.majiang.community.interceptor;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.User;

@Service
public class SessionInterceptor implements HandlerInterceptor {
	
	@Autowired
	private UserMapper userMapper;
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		Cookie[] cookies=request.getCookies();
		if(cookies!=null&&cookies.length!=0) {
		for(Cookie cookie:request.getCookies()) {
			if(cookie.getName().equals("token")) {
				List<User> users=userMapper.findUserByToken(cookie.getValue());
				if(users.size()!=0) {
					request.getSession().setAttribute("user", users.get(0));
				}
				break;
			}
		}
		}
		
		

		return true;
	}
	
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable ModelAndView modelAndView) throws Exception {
	}
	
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable Exception ex) throws Exception {
	}

	

}
