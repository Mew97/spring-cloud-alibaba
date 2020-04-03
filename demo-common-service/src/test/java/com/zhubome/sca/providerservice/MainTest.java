package com.zhubome.sca.providerservice;

import com.zhubome.sca.providerservice.myenum.PersonDest;
import com.zhubome.sca.providerservice.myenum.PersonSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
//@SpringBootTest
public class MainTest {
    @Test
    public void test(){
        PersonSource personSource = new PersonSource(1, "pjmike", "12345", 21);
        PersonDest personDest = new PersonDest();
        BeanUtils.copyProperties(personSource,personDest);
        System.out.println("persondest: "+personDest);
    }

}
