package com.dlg.reader.service;

import com.dlg.reader.entity.Member;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MemberServiceTest {
    @Resource
    private MemberService memberService;

    @Test
    public void createMember1() {
        Member member = memberService.createMember("imooc_1", "123456", "imooc_1");
        System.out.println(member);
    }

     @Test
    public void createMember2() {
        Member member = memberService.createMember("imooc_1000", "123456", "imooc_1000");
        System.out.println(member);
    }

    @Test
    public void checkLogin1() {
        Member member = memberService.checkLogin("imooc_1", "123456");
        System.out.println(member);
    }

    @Test
    public void checkLogin2() {
         Member member = memberService.checkLogin("imooc_1x", "123456");
        System.out.println(member);
    }

    @Test
    public void checkLogin3() {
         Member member = memberService.checkLogin("imooc_1", "12345");
        System.out.println(member);
    }

}