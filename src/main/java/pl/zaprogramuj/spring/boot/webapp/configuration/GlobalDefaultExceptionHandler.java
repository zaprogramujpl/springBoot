package pl.zaprogramuj.spring.boot.webapp.configuration;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import pl.zaprogramuj.spring.boot.webapp.excepotion.post.PostNotFoundException;
import pl.zaprogramuj.spring.boot.webapp.util.SystemViewsName;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);

	@ExceptionHandler(value = IllegalArgumentException.class)
	public ModelAndView defaultErrorHandler(HttpServletRequest request, Exception e) {
		LOGGER.error("Request: " + request.getRequestURL() + " raised " + e);
		return new ModelAndView(SystemViewsName.REDIRECT_TO_MAIN_PAGE);
	}

	@ExceptionHandler(value = PostNotFoundException.class)
	public ModelAndView postNotFoundExceptionHandler(HttpServletRequest request, Exception e) {
		LOGGER.error("Request: " + request.getRequestURL() + " raised " + e);
		return new ModelAndView(SystemViewsName.REDIRECT_TO_MAIN_PAGE);
	}
}