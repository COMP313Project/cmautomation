package com.cmautomation.spring.config;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {
	public static final String DEFAULT_ERROR_VIEW = "database-error";
	public static final String SQL_ERROR_VIEW = "sql-error";

	@ExceptionHandler(value = DataIntegrityViolationException.class)
	public ModelAndView sqlExceptionHandler(HttpServletRequest req, Exception e) throws Exception {

		if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
			throw e;
		} else {
			//setup and send the user to a default error-view.
			ModelAndView mav = new ModelAndView();
			mav.addObject("message", e);
			mav.addObject("url", req.getRequestURL());
			mav.setViewName(SQL_ERROR_VIEW);
			return mav;
		}
	}
	/*@ExceptionHandler(value=DataIntegrityViolationException.class)
	public String dataIntegrityExceptionHandler(HttpServletRequest req, Exception ex) throws DataIntegrityViolationException {

		return "sql-error";
	}*/
	@ExceptionHandler(value = Exception.class)
	public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {

		if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
			throw e;
		} else {
			//setup and send the user to a default error-view.
			ModelAndView mav = new ModelAndView();
			mav.addObject("exception", e);
			mav.addObject("url", req.getRequestURL());
			mav.setViewName(DEFAULT_ERROR_VIEW);
			return mav;
		}
	}
	
}
