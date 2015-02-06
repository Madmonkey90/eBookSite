/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eLibrary.Handlers;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import eLibrary.Repos.MetricsRepo;
import org.springframework.stereotype.Service;

/**
 * 
 * 
 * @author Brian
 * 
 */
@Service
public class AuditService {
    private MetricsRepo metricsRepo;
    
    public void setMetricsRepo(MetricsRepo repo) { metricsRepo = repo; }
    public MetricsRepo getMetricsRepo() { return metricsRepo; }
    
	public void audit(String pageName) {

                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();
                int err = 0;
                try{err = metricsRepo.addPageView(userName, pageName);}catch(Exception e) { e.printStackTrace(); }
                if(!(err==1)){ System.out.println("ERROR");}

        }

	

}
