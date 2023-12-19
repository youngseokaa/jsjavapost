package com.example.post.Service;

import com.example.post.Repository. MemberRepository;
import com.example.post.common.CustomException;

import com.example.post.common.JwtTokenUtil;
import com.example.post.common.ResponseDto;
import com.example.post.common.SecurityConfig;
import com.example.post.dto.LoginRequestDto;
import com.example.post.dto.SignUpResponseDto;
import com.example.post.dto.SignupRequestDto;
import com.example.post.entity.Member;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

import static com.example.post.common.ExceptionMessage.DUPLICATE_EMAIL;
import static com.example.post.common.ExceptionMessage.ID_PASSWORDS_INCORRECT;
import static com.example.post.common.SuccessMessage.LOGIN_SUCCESS;
import static com.example.post.common.SuccessMessage.SIGN_UP_SUCCESS;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final JwtTokenUtil jwtTokenUtil;

    public ResponseEntity<ResponseDto> signup(SignupRequestDto signupRequestDto) {
        if (memberRepository.findByEmail(signupRequestDto.getEmail()).isPresent()) {
            throw new CustomException(DUPLICATE_EMAIL);
        }

        Member member = Member.builder()
                .loginid(signupRequestDto.getLoginid())
                .pwsd1(signupRequestDto.getPswd1())
                .name(signupRequestDto.getName())
                .birthdayInput(signupRequestDto.getBirthdayInput())
                .gender(signupRequestDto.getGender())
                .email(signupRequestDto.getEmail())
                .mobile(signupRequestDto.getMobile())
                .build();

        memberRepository.save(member);

        return ResponseDto.toResponseEntity(SIGN_UP_SUCCESS, new SignUpResponseDto(member));
    }


    public Member getLoginUserByLoginId(String loginId) {
        if(loginId == null) return null;

        Optional<Member> optionalUser = memberRepository.findByLoginid(loginId);
        if(optionalUser.isEmpty()) return null;

        return optionalUser.get();
    }

    public ResponseEntity<ResponseDto> Login(LoginRequestDto loginRequestDto ,HttpServletResponse response) {
        String loginid = loginRequestDto.getLoginid();

        Member member = memberRepository.findByLoginid(loginid).orElseThrow(
                () -> new CustomException(ID_PASSWORDS_INCORRECT)
        );
        if(!Objects.equals(member.getPwsd1(), loginRequestDto.getPwsd1())) {
            throw new CustomException(ID_PASSWORDS_INCORRECT);
        }

        jwtTokenUtil.createToken(member.getLoginid(), response);

        return ResponseDto.toResponseEntity(LOGIN_SUCCESS, new SignUpResponseDto(member));
    }
}
