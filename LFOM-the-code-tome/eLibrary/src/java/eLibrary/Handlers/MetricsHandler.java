/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eLibrary.Handlers;

import eLibrary.Repos.MetricsRepo;
import java.util.List;

/**
 *
 * @author Brian
 */
public class MetricsHandler {
    private MetricsRepo metricsRepo;
    
    
    public void setMetricsRepo(MetricsRepo repo) { metricsRepo = repo; }
    public MetricsRepo getMetricsRepo() { return metricsRepo; }
    
    
    public List getPageData(){
        return metricsRepo.getPageData();
    }
    
    public List getSearchData()
    {
        return metricsRepo.getAvgSearchTimeData();
    }
    
    public List getCheckoutData(){
        return metricsRepo.getCheckoutData();
    }
    
    public List getTickets(){
        return metricsRepo.getTickets();
    }
    
    public int resolveTicket(int key){
        return metricsRepo.resolveTicket(key);
    }
    
    
}
