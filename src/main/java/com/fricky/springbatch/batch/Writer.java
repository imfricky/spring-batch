package com.fricky.springbatch.batch;

import com.fricky.springbatch.model.User;
import com.fricky.springbatch.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class Writer implements ItemWriter<User> {

    @Autowired
    UserRepository userRepository;

    @Override
    public void write(List<? extends User> list) throws Exception {

        // Writer is used to write the processed data into the DB

        log.info("Writing users to DB, users: {}", list);
        userRepository.saveAll(list);
    }

}
