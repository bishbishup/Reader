package com.dlg.reader.service;

import com.dlg.reader.entity.Member;
import com.dlg.reader.entity.MemberReadState;

public interface MemberService {
    public Member createMember(String username,String password,String nickname);
    public Member checkLogin(String username,String password);
    public Member selectById(Long memberId);
    public MemberReadState selectMemberReadState(Long memberId,Long bookId);
    public MemberReadState updateMemberReadState(Long memberId,Long bookId,Integer readState);
}
