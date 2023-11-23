package com.egglog.controller;

import java.util.Date;
import java.util.List;

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

import com.egglog.dto.Request;
import com.egglog.service.FriendsService;
import com.egglog.service.RequestService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api")
@Api(tags="요청 컨트롤러")
public class RequestRestController {
    
    @Autowired
    FriendsService friendsService;
    
    @Autowired
    RequestService requestService;
    
    
    // Goal : 전체 요청리스트 가져오기
    // Parameter : 
    // Return : 요청리스트
    @GetMapping("/request")
    public ResponseEntity<List<Request>> getRequestList() {
        List<Request> list = requestService.getRequestList();
        return new ResponseEntity<List<Request>>(list, HttpStatus.OK);
    }
    
    
    // Goal : 나에게 온 요청 가져오기
    // Parameter : to(누가 친구 요청 보냈는지)
    // Return : 있다면 요청 반환, 없다면 NO_CONTENT
    @GetMapping("/request/{to}")
    public ResponseEntity<?> getRequest(@PathVariable String to) {
//        System.out.println("to = " + to);
        List<Request> list = requestService.getRequest(to);
//        System.out.println(list.toString());
        if(list == null || list.size() == 0) 
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<List<Request>>(list, HttpStatus.OK);
    }
    
    
    // Goal : 해당 요청 등록
    // Parameter : request
    // Return : 요청 등록 완료 반환
    @PostMapping("/request")
    public ResponseEntity<?> sendRequest(@RequestBody Request request) {
        System.out.println("요청요청요청요청");
        System.out.println(request);
        if(requestService.sendRequest(request)) return new ResponseEntity<Boolean>(true, HttpStatus.OK);
        return new ResponseEntity<Boolean>(false, HttpStatus.NOT_ACCEPTABLE);
    }
    
    
    // Goal : 해당 요청 삭제
    // Parameter : request
    // Return : 요청 삭제 완료 반환
    @DeleteMapping("/request")
    public ResponseEntity<Boolean> deleteRequest(@RequestBody Request request) {
        if(requestService.deleteRequest(request))
            return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
        return new ResponseEntity<Boolean>(false, HttpStatus.NOT_ACCEPTABLE);
    }


}