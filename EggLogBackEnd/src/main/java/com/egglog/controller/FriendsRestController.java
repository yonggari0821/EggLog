package com.egglog.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.egglog.dto.Friends;
import com.egglog.dto.Request;
import com.egglog.service.FriendsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(tags="친구 컨트롤러")
public class FriendsRestController {
    @Autowired
    FriendsService friendsService;
    
    
    @GetMapping("/friends/{myId}")
    @ApiOperation(value = "친구들 아이디 리스트를 가져온다.", response = Friends.class)
    public ResponseEntity<?> selectAll(@PathVariable String myId) {
        try {
            List<String> friends = friendsService.selectAll(myId);
            if (friends != null && friends.size() > 0) return new ResponseEntity<List<String>>(friends, HttpStatus.OK);
            else return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }
    
    @PostMapping("/friends")
    @ApiOperation(value = "친구가 된다.", response = Integer.class)
    public ResponseEntity<?> insert (@RequestBody Request request)
    {
        try {
            boolean result = friendsService.insert(request);
            if (result) {
                // 이 부분에 요청 삭제 요청 메서드 추가해야 함
                return new ResponseEntity<String>("Success to be friend with user " + request.getTo(), HttpStatus.OK);
            }
            else return new ResponseEntity<String>("Fail to be new friends.", HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
    }
    
    
    @DeleteMapping("/friends")
    @ApiOperation(value = "친구 관계를 끊는다.", response = Integer.class)
    public ResponseEntity<?> delete (@RequestBody Friends friends)
    {
        System.out.println(friends);
        try {
            boolean result = friendsService.delete(friends);
            if (result) return new ResponseEntity<String>("Success to break off with friend", HttpStatus.OK);
            else return new ResponseEntity<String>("Fail to break off with friend", HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
    }
    
    
    private ResponseEntity<String> exceptionHandling(Exception e) {
        e.printStackTrace();
        return new ResponseEntity<String>("Sorry : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
