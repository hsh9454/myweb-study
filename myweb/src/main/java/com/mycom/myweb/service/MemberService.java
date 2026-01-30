package com.mycom.myweb.service;

public interface MemberService {
   
    String getDbName();
    
    void insertTest(int id, String name);
    void updateTest(int id, String name);
    void deleteTest(int id);
}