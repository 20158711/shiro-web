package org.yanbing.filter;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

public class RolesOrFilter extends AuthorizationFilter {
    @Override
    protected boolean isAccessAllowed(javax.servlet.ServletRequest request, javax.servlet.ServletResponse response, Object o) throws Exception {
        Subject subject=getSubject(request, response);
        String[] roles= (String[]) o;
        if (roles==null || roles.length==0)
            return true;
        for (String role : roles) {
            if(subject.hasRole(role))
                return true;
        }
        return false;
    }
}
