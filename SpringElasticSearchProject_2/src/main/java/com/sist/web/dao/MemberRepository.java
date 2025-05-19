package com.sist.web.dao;

import java.util.*;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.sist.web.vo.*;

@Repository
public interface MemberRepository extends ElasticsearchRepository<Member, String>{

}
