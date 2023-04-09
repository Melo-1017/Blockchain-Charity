package com.neu.mybatisdemo1.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neu.mybatisdemo1.entity.Helplist;
import com.neu.mybatisdemo1.mapper.HelplistMapper;
import com.neu.mybatisdemo1.service.HelplistService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class HelplistServiceImpl implements HelplistService {
    @Resource
    private HelplistMapper helplistMapper;

    @Override
    public PageInfo<Helplist> getPaged(int pageNum, int pageSize, String donationDemand, Integer donationState) {
        PageHelper.startPage(pageNum, pageSize);
        List<Helplist> list = helplistMapper.search("%"+donationDemand+"%",donationState);
        return new PageInfo<>(list);

    }

    @Override
    public Helplist getById(Long id) {
        return helplistMapper.selectByPrimaryKey(id);
    }

    @Override
    public int insert(Helplist helplist) {
        return helplistMapper.insert(helplist);
    }

    @Override
    public int update(Helplist helplist) {
        return helplistMapper.updateByPrimaryKey(helplist);
    }

    @Override
    public int delete(Long id) {
        return helplistMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer getNewId(){
        List<Helplist> a = new ArrayList<>();
        a = helplistMapper.getNewId();
        Helplist ob = a.get(0);
        return ob.getListId();
    }

    @Override
    public PageInfo<Helplist> getPagedByGiverId(int pageNum, int pageSize, int giverId) {
        PageHelper.startPage(pageNum, pageSize);
        List<Helplist> list = helplistMapper.searchByGiverId(giverId);
        return new PageInfo<>(list);
    }
    @Override
    public PageInfo<Helplist> getPagedByRecipientId(int pageNum, int pageSize, int recipientId) {
        PageHelper.startPage(pageNum, pageSize);
        List<Helplist> list = helplistMapper.searchByRecipientId(recipientId);
        return new PageInfo<>(list);
    }
}
