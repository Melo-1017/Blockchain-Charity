package com.neu.mybatisdemo1;
import com.neu.mybatisdemo1.entity.User;
import com.neu.mybatisdemo1.service.HelplistService;
import com.neu.mybatisdemo1.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class Mybatisdemo1ApplicationTests {
    HelplistService helplistService;
    void test(){
        System.out.println(helplistService.getById((long)1));
    }
}