package com.cykim.teamproject.services;//package com.yhkim.fave.services;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
//import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
//import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//import org.springframework.stereotype.Service;
//
//import java.lang.reflect.Member;
//import java.util.Map;
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//public class OAuth2MemberService extends DefaultOAuth2UserService {
//    private final BCryptPasswordEncoder encoder;
//    private final MemberRepository memberRepository;
//
//    @Override
//    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
//        OAuth2User oAuth2User = super.loadUser(userRequest);
//        OAuth2MemberInfo memberInfo = null;
//        System.out.println(oAuth2User.getAttributes());
//        System.out.println(userRequest.getClientRegistration().getRegistrationId());
//
//        String registrationId = userRequest.getClientRegistration().getRegistrationId();
//        if (registrationId.equals("google")) {
//            memberInfo = new GoogleMemberInfo(oAuth2User.getAttributes());
//        } else if (registrationId.equals("kakao")) {
//            memberInfo = new KakaoMemberInfo(oAuth2User.getAttributes());
//        } else if (registrationId.equals("naver")) {
//            memberInfo = new NaverMemberInfo((Map)oAuth2User.getAttributes().get("response"));
//        } else {
//            System.out.println("로그인 실패");
//        }
//        String provider = memberInfo.getProvider();
//        String providerId = memberInfo.getProviderId();
//        String username = provider + "_" + providerId; //중복이 발생하지 않도록 provider와 providerId를 조합
//        String email = memberInfo.getEmail();
//        String role = "ROLE_ADMIN"; //일반 유저
//        System.out.println(oAuth2User.getAttributes());
//        Optional<Member> findMember = memberRepository.findByName(username);
//        Member member=null;
//        if (findMember.isEmpty()) { //찾지 못했다면
//            member = Member.builder()
//                    .name(username)
//                    .email(email)
//                    .password(encoder.encode("password"))
//                    .role(role)
//                    .provider(provider)
//                    .providerId(providerId).build();
//            memberRepository.save(member);
//        }
//        else{
//            member=findMember.get();
//        }
//        return new PrincipalDetails(member, oAuth2User.getAttributes());
//
//    }
//}