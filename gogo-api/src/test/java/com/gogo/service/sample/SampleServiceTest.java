package com.gogo.service.sample;

import com.gogo.domain.sample.Sample;
import com.gogo.domain.sample.SampleRepository;
import com.gogo.service.sample.dto.SampleRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SampleServiceTest {

    @Autowired
    private SampleService sampleService;

    @Autowired
    private SampleRepository sampleRepository;

    @AfterEach
    void cleanUp() {
        sampleRepository.deleteAll();
    }

    @Test
    void 샘플_테스트() {
        // given
        String name = "프로젝트 셋업을 위한 샘플 코드";

        // when
        sampleService.test(new SampleRequest(name));

        // then
        List<Sample> sampleList = sampleRepository.findAll();
        assertThat(sampleList).hasSize(1);
    }

}
