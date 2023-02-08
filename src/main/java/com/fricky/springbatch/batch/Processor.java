package com.fricky.springbatch.batch;

import com.fricky.springbatch.model.User;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Processor implements ItemProcessor<User, User> {

    private static final Map<String, String> DEPT_NAME = new HashMap<>();

    public Processor() {
        DEPT_NAME.put("001", "Dept 1");
        DEPT_NAME.put("002", "Dept 2");
        DEPT_NAME.put("003", "Dept 3");
    }

    @Override
    public User process(User user) throws Exception {

//        Get a user data and manipulate the data and give an output
//        The Input and Output to the processor can be different

        String deptCode = user.getDept();
        String deptName = DEPT_NAME.get(deptCode);
        user.setDept(deptName);
        return user;
    }
}
