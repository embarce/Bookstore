package com.embrace.demo.goods.service;

import com.embrace.demo.goods.dao.GoodsDao;
import com.embrace.demo.goods.entity.goodsInfo;
import com.embrace.demo.goods.util.AppResponse;
import com.embrace.demo.goods.util.AuthUtils;
import com.embrace.demo.goods.util.StringUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @Description 实现类
 * @author 72937
 * @Date 2020-03-23
 */
@Service
public class GoodsService {
    @Resource
    private GoodsDao goodsDao;

    /**
     * goods 新增商品
     * @param goodsInfo
     * @return
     * @author 72937
     * @Date 2020-03-23
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse addGoods(goodsInfo goodsInfo) {
        // 校验商品是否存在
        int countgoodsBybookId = goodsDao.countgoodsBybookId(goodsInfo.getGoodId());
        if(0 !=countgoodsBybookId ) {
            return AppResponse.bizError("商品账号已存在，请重新输入！");
        }
        goodsInfo.setBookId(StringUtil.getCommonCode(2));
        goodsInfo.setIsDeleted(0);
        // 新增商品
        int count = goodsDao.addGoods(goodsInfo);
        if(0 == count) {
            return AppResponse.bizError("新增失败，请重试！");
        }
        return AppResponse.success("新增成功！");
    }

    /**
     * 查询用户列表
     * @param goodsInfo
     * @return
     * @Author 7293747
     * @Date 2020-03-25
     */
    public AppResponse listGoods(goodsInfo goodsInfo){
        PageHelper.startPage(goodsInfo.getPageNum(), goodsInfo.getPageSize());
        List<goodsInfo> goodsInfoList = goodsDao.listGoodsByPage(goodsInfo);
        // 包装Page对象
        PageInfo<goodsInfo> pageData = new PageInfo<goodsInfo>(goodsInfoList);
        return AppResponse.success("查询成功！",pageData);
    }

    /**
     * 删除商品
     * @pram goodsId
     * @author 72937
     * @date 2020-3-25
     * @return AppResponse
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse deleteGoods(String goodsId,String userId) {
        List<String> listCode = Arrays.asList(goodsId.split(","));
        AppResponse appResponse = AppResponse.success("删除成功！");
        // 删除用户
        int count = goodsDao.deleteGoods(listCode,userId);
        if(0 == count) {
            appResponse = AppResponse.bizError("删除失败，请重试！");
        }
        return appResponse;
    }
}
