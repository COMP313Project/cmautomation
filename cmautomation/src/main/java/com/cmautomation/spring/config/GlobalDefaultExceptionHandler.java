package com.cmautomation.spring.config;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


/*
 * This class handles all the exceptions of the application. Any error raised by the 
 * app will be covered by this class
 * 
 * */
@ControllerAdvice
public class GlobalDefaultExceptionHandler {
	public static final String DEFAULT_ERROR_VIEW = "database-error";
	public static final String SQL_ERROR_VIEW = "sql-error";
	public static final String GENERIC_ERROR_VIEW = "error-500";
	
	// handles data integrity violation
	@ExceptionHandler(value = DataIntegrityViolationException.class)
	public ModelAndView sqlExceptionHandler(HttpServletRequest req, Exception e) throws Exception {

		if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
			throw e;
		} else {
			// setup and send the user to a default error-view.
			ModelAndView mav = new ModelAndView();
			mav.addObject("message", e);
			mav.addObject("url", req.getRequestURL());
			mav.setViewName(SQL_ERROR_VIEW);
			return mav;
		}
	}// handles null pointer exception
//	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//	@ExceptionHandler(value = Exception.class)
//	public ModelAndView nullPointerExceptionHandler(HttpServletRequest req, Exception e) throws Exception {
//
//		if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
//			throw e;
//		} else {
//			// setup and send the user to a default error-view.
//			ModelAndView mav = new ModelAndView();
//			mav.addObject("message", e);
//			mav.addObject("url", req.getRequestURL());
//			mav.setViewName(GENERIC_ERROR_VIEW);
//			return mav;
//		}
//	}
	// generic handler for any errors
	@ExceptionHandler(value = Exception.class)
	public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {

		if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
			throw e;
		} else { // setup and send the user to a default error-view.
			ModelAndView mav = new ModelAndView();
			mav.addObject("exception", e);
			mav.addObject("url", req.getRequestURL());
			mav.setViewName(DEFAULT_ERROR_VIEW);
			return mav;
		}
	}
	


}
