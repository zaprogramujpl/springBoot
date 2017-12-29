package pl.zaprogramuj.spring.boot.webapp.webapp.configuration;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import pl.zaprogramuj.spring.boot.webapp.webapp.util.SystemViewsName;

@ControllerAdvice
class GlobalDefaultExceptionHandler {
	
	  private static final Logger LOGGER = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);
	
	  @ExceptionHandler(value = IllegalArgumentException.class)
	  public ModelAndView defaultErrorHandler(HttpServletRequest request, Exception e) {
		  LOGGER.error("Request: " + request.getRequestURL() + " raised " + e);
		  return new ModelAndView(SystemViewsName.REDIRECT_TO_MAIN_PAGE);
	  }
}