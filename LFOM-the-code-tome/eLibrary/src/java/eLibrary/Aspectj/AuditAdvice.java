/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eLibrary.Aspectj;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;

import eLibrary.Handlers.AuditService;

/**
 * @author Brian
 * 
 */
@Aspect
public class AuditAdvice {

	@Autowired
	private AuditService auditService;

	
	@Before("execution(* eLibrary.web.controllers.*Controller.*(..)) && @annotation(auditAnnotation) ")
	public void auditScreen(Audit auditAnnotation) {
		auditService.audit(auditAnnotation.value());
	}

}
