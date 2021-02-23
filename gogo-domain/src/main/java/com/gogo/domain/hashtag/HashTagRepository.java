package com.gogo.domain.hashtag;

import com.gogo.domain.hashtag.repository.HashTagRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HashTagRepository extends JpaRepository<HashTag, Long>, HashTagRepositoryCustom {

}
