/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eLibrary.Handlers;

import eLibrary.Domain.UserRole;
import eLibrary.Repos.UserRepo;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
/**
 *
 * @author Brian
 */
public class UserRoleHandler implements UserDetailsService {
    private UserRepo userRepo;
    @Autowired
    private eLibrary.Domain.User currentUser;
    
    public UserRoleHandler(){}
    
    public UserRepo getUserRepo() { return userRepo; }
    public void setUserRepo(UserRepo r) { userRepo = r; }
    
    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        eLibrary.Domain.User user = userRepo.findByUserName(username);
        currentUser.setEmail(user.getEmail());
        currentUser.setFirstName(user.getFirstName());
        currentUser.setLastName(user.getLastName());
        currentUser.setKey(user.getKey());
        currentUser.setCheckedOut(user.getCheckedOut());
	List<GrantedAuthority> authorities = buildUserAuthority(user.getUserRole());
	return buildUserForAuthentication(user, authorities);
    }
        
    private User buildUserForAuthentication(eLibrary.Domain.User user, 
	List<GrantedAuthority> authorities) {
	return new User(user.getEmail(),user.getPassword(), user.isEnabled(), 
                        true, true, true, authorities);
    }
 
    private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {
 
	Set<GrantedAuthority> setAuths = new HashSet<>();
        
	for (UserRole userRole : userRoles) {
            setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
	}
	List<GrantedAuthority> Result = new ArrayList<>(setAuths);
	return Result;
    }
}
