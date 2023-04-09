package com.neu.mybatisdemo1.service;

import com.github.pagehelper.PageInfo;
import com.neu.mybatisdemo1.entity.Helplist;

public interface HelplistService {
    PageInfo<Helplist> getPaged(int pageNum, int pageSize, String donationDemand, Integer donationState);

    Helplist getById(Long id);

    int insert(Helplist helplist);

    int update(Helplist helplist);

    int delete(Long id);

    Integer getNewId();

    PageInfo<Helplist> getPagedByGiverId(int pageNum, int pageSize, int giverId);
    public PageInfo<Helplist> getPagedByRecipientId(int pageNum, int pageSize, int recipientId);
}
