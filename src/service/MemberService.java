package service;

import data.Member;
import infra.Container;
import repository.MemberRepository;

import java.util.Scanner;

public class MemberService {

    private Scanner sc;

    private MemberRepository memberRepository;

    public MemberService(){
        this.sc = Container.sc;
        this.memberRepository = Container.memberRepository;

    }

    public int saveMember(String loginId, String password, String name){
        return memberRepository.saveMember(loginId, password, name);
    }

    public boolean isExistsByLoginId(String loginId){
        return memberRepository.isExistByLoginId(loginId);
    }

    public boolean isCorrectInfo(String loginId, String password) {

        Member findMember = memberRepository.getMemberByLoginId(loginId);

        if(findMember == null){
            return false;
        }

        if(findMember.getPassword().equals(password)){
            return true;
        }
        return false;
    }

}
