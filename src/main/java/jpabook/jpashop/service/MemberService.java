package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    //memberRepository 는 한번 생성되고 변경할 필요없으니 final로 설정해준다.
    private final MemberRepository memberRepository;

    /*
    //생성자가 하나만 있는 경우 스프링이 @Autowired 자동으로 해준다.
    //==@AllArgsConstructor 을 class 위에 작성해도 동일함.
    //==@RequiredArgsConstructor 은 final 붙어있는 필드만 대상으로 생성자를 만들어줌. (best)
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    */

    /**
     * 회원 가입
     * @param member 가입하는 회원 정보
     * @return
     */
    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);

        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());

        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    //회원 단건 조회
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

    @Transactional
    public void update(Long id, String name) {
        Member member = memberRepository.findOne(id);
        member.setName(name);


    }
}
