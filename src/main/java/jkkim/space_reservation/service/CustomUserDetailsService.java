package jkkim.space_reservation.service;

import jkkim.space_reservation.entity.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepositoryIf memberRepositoryIf; // MemberRepository는 JPA 리포지토리 또는 데이터 접근 레이어

    public CustomUserDetailsService(MemberRepositoryIf memberRepositoryIf) {
        this.memberRepositoryIf = memberRepositoryIf;
    }

    @Override
    public UserDetails loadUserByUsername(String memberName) throws UsernameNotFoundException {
        Member member = memberRepositoryIf.findByMemberName(memberName)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with memberName: " + memberName));

        // memberRole 값에 따라 권한을 결정
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (Boolean.TRUE.equals(member.getMemberRole())) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else {
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }

        return new org.springframework.security.core.userdetails.User(member.getMemberName(), member.getMemberPassword(), authorities);
    }
}