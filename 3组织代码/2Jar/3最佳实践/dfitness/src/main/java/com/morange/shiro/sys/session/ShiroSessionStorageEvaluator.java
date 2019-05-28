package com.morange.shiro.sys.session;

import org.apache.shiro.mgt.SessionStorageEvaluator;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : zhenyun.su
 * @since : 2018/10/10
 */

public class ShiroSessionStorageEvaluator implements SessionStorageEvaluator {
    @Override
    public boolean isSessionStorageEnabled(Subject subject) {
        boolean enabled = false;
        if (WebUtils.isWeb(subject)) {
            HttpServletRequest request = WebUtils.getHttpRequest(subject);
            //set 'enabled' based on the current request.
            enabled = true;
        } else {
            //not a common request - maybe a RMI or daemon invocation?
            //set 'enabled' another way...
        }

        return enabled;
    }
}
