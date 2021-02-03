package com.gogo.service.sample;

import com.gogo.domain.sample.Sample;
import com.gogo.domain.sample.SampleRepository;
import com.gogo.service.sample.dto.SampleRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class SampleService {

    private final SampleRepository sampleRepository;

    @Transactional
    public void test(SampleRequest request) {
        sampleRepository.save(new Sample(request.getSample()));
    }

}
