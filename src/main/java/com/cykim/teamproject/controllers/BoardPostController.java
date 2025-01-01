//package com.cykim.teamproject.controllers;
//
//import com.cykim.teamproject.entities.UserEntity;
//import com.cykim.teamproject.results.LikedResult;
//import com.cykim.teamproject.services.BoardPostService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//import jakarta.servlet.http.HttpSession;
//
//import java.util.HashSet;
//import java.util.Set;
//
//@RestController
//@RequestMapping("/board")
//@RequiredArgsConstructor
//public class BoardPostController {
//
//    private final BoardPostService boardPostService;
//
//    // 좋아요 증가
//    @PostMapping("/like/{postId}")
//    public LikedResult postLike(@PathVariable("postId") int postId, HttpSession session) {
//        Set<Integer> likedPosts = (Set<Integer>) session.getAttribute("likedPosts");
//        if (likedPosts == null) {
//            likedPosts = new HashSet<>();
//            session.setAttribute("likedPosts", likedPosts);
//        }
//
//        if (likedPosts.contains(postId)) {
//            return LikedResult.ALREADY_LIKED;
//        }
//
//        boolean result = boardPostService.incrementLike(postId);
//        if (result) {
//            likedPosts.add(postId);
//            return LikedResult.SUCCESS;
//        } else {
//            return LikedResult.FAILURE;
//        }
//    }
//
//    // 좋아요 감소
//    @PostMapping("/unlike/{postId}")
//    public LikedResult postUnlike(@PathVariable("postId") int postId, HttpSession session) {
//        Set<Integer> likedPosts = (Set<Integer>) session.getAttribute("likedPosts");
//        if (likedPosts == null || !likedPosts.contains(postId)) {
//            return LikedResult.NOT_LIKED;
//        }
//
//        boolean result = boardPostService.decrementLike(postId);
//        if (result) {
//            likedPosts.remove(postId);
//            return LikedResult.SUCCESS;
//        } else {
//            return LikedResult.FAILURE;
//        }
//    }
//}

package com.cykim.teamproject.controllers;

import com.cykim.teamproject.results.LikedResult;
import com.cykim.teamproject.services.BoardPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardPostController {

    private final BoardPostService boardPostService;

    // 좋아요 추가
    @PostMapping("/like/{postId}")
    public LikedResult likePost(@PathVariable("postId") int postId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName(); // 로그인된 사용자의 이메일
        if (userEmail == null) {
            return LikedResult.NOT_LOGGED_IN;
        }
        boolean result = boardPostService.addLike(postId); // 서비스가 로그인된 사용자 이메일을 처리
        return result ? LikedResult.SUCCESS : LikedResult.ALREADY_LIKED;
    }

    // 좋아요 삭제
    @PostMapping("/unlike/{postId}")
    public LikedResult unlikePost(@PathVariable("postId") int postId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName(); // 로그인된 사용자의 이메일
        if (userEmail == null) {
            return LikedResult.NOT_LOGGED_IN;
        }
        boolean result = boardPostService.removeLike(postId); // 서비스가 로그인된 사용자 이메일을 처리
        return result ? LikedResult.SUCCESS : LikedResult.NOT_LIKED;
    }
}
