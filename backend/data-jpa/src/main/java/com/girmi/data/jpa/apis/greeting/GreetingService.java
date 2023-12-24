package com.girmi.data.jpa.apis.greeting;

import com.girmi.models.GreetingVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GreetingService {
    @Autowired
    GreetingRepository greetingRepository;

    public Iterable<GreetingVO> getGreeting() throws Exception {
        return greetingRepository.findAll();
    }

    public void insertGreeting(GreetingVO greetingVO) throws Exception {
        greetingRepository.save(greetingVO);
    }

    public void deleteGreeting(String id) throws Exception {
        greetingRepository.deleteById(id);
    }

    public List<GreetingVO> getDateByCode(String code) throws Exception {
        return greetingRepository.findAllByCode(code);
    }

}
