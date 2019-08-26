package com.leemon.mall.service.impl;

import com.leemon.mall.nosql.mongodb.document.MemberReadHistory;
import com.leemon.mall.nosql.mongodb.repository.MemberReadHistoryRepository;
import com.leemon.mall.service.MemberReadHistoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author limenglong
 * @create 2019-08-26 11:24
 * @desc MemberReadHistoryService实现类
 **/
@Service
public class MemberReadHistoryServiceImpl implements MemberReadHistoryService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MemberReadHistoryServiceImpl.class);

    @Autowired
    private MemberReadHistoryRepository repository;

    @Override
    public int create(MemberReadHistory memberReadHistory) {
        memberReadHistory.setId(null);
        memberReadHistory.setCreateTime(new Date());
        repository.save(memberReadHistory);
        return 1;
    }

    @Override
    public int delete(List<String> ids) {
        List<MemberReadHistory> deleteList = new ArrayList<>();
        for (String id : ids) {
            MemberReadHistory memberReadHistory = new MemberReadHistory();
            memberReadHistory.setId(id);
            deleteList.add(memberReadHistory);
        }

        repository.deleteAll(deleteList);
        return ids.size();
    }

    @Override
    public List<MemberReadHistory> list(Long memberId) {
        return repository.findByMemberIdOrderByCreateTimeDesc(memberId);
    }
}
