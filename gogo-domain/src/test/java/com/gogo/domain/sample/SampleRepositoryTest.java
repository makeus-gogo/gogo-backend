package com.gogo.domain.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SampleRepositoryTest {

    @Autowired
    private SampleRepository sampleRepository;

    @AfterEach
    void cleanUp() {
        sampleRepository.deleteAll();
    }

    @Test
    void test() {
        sampleRepository.save(new Sample("test"));

        List<Sample> sampleList = sampleRepository.findAll();
        assertThat(sampleList).hasSize(1);
    }

}
