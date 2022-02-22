package com.sp.fc.web.config;

import com.sp.fc.web.service.Paper;
import lombok.Getter;
import lombok.Setter;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;

/*
* MethodSecurityExpressionRoot는 public이 아님
* SecurityExpressionRoot를 상속받고 MethodSecurityExpressionOperations 를 구현한다*/

@Getter
@Setter
public class CustomMethodSecurityExpressionRoot extends SecurityExpressionRoot
        implements MethodSecurityExpressionOperations {


    MethodInvocation invocation;
    public CustomMethodSecurityExpressionRoot(Authentication authentication, MethodInvocation invocation) {
        super(authentication);
        this.invocation = invocation;
    }

    private Object filterObject;
    private Object returnObject;

    /* context root로 등록된 객체이기 때문에 root에서 isStudent로 바로 접근할 수 있음*/
    public boolean isStudent(){
        return getAuthentication().getAuthorities().stream()
                .filter(a-> a.getAuthority().equals("ROLE_STUDENT"))
                .findAny().isPresent();
    }

    public boolean notPrepareState(Paper paper){
        return paper.getState() != Paper.State.PREPARE;
    }

    @Override
    public Object getThis() {
        return this;
    }

/*
    @Override
    public void setFilterObject(Object filterObject) {

    }

    @Override
    public Object getFilterObject() {
        return null;
    }

    @Override
    public void setReturnObject(Object returnObject) {

    }

    @Override
    public Object getReturnObject() {
        return null;
    }*/

}
