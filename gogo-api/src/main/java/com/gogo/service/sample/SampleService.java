package com.gogo.service.sample;

import com.gogo.domain.sample.Sample;
import com.gogo.domain.sample.SampleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class SampleService {

    private final SampleRepository sampleRepository;

    @Transactional
    public void test(String test) {
        sampleRepository.save(new Sample(test));
    }

}
