package com.sujan.sajiloshopping.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ModelAndView handlerNoHandlerFoundException() {
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("errorTitle","No such Page Found!");
		mv.addObject("errorDescription","The page you are looking for is not available!");
		mv.addObject("title","404 Error Page");
		return mv;
	}
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ModelAndView handlerProductNotFoundException() {
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("errorTitle","Product Unavailable!");
		mv.addObject("errorDescription","The product you are searching for is not available in the store!");
		mv.addObject("title","Product Unavailable");
		return mv;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView handlerException(Exception ex) {
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("errorTitle","Contact Administrator!");
		
		/*
		 * Only for debugging application but not for production
		 * */
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		ex.printStackTrace(pw);
		mv.addObject("errorDescription", sw.toString());
		
		/*
		 * for production
		 * */
//		mv.addObject("errorDescription", ex.toString());
		mv.addObject("title","admin support");
		return mv;
	}
}
