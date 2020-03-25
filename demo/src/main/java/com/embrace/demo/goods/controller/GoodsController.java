package com.embrace.demo.goods.controller;

import com.embrace.demo.goods.entity.goodsInfo;
import com.embrace.demo.goods.service.GoodsService;
import com.embrace.demo.goods.util.AppResponse;
import com.embrace.demo.goods.util.AuthUtils;
import com.embrace.demo.goods.util.GoodsUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 72937
 * @time 2020年3月24日 11:31:48
 *
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {
    private static final Logger logger = LoggerFactory.getLogger(GoodsController.class);

    @Resource
    private GoodsService goodsService;

    /**
     * 新增商品
     * @param goodsInfo
     * @return AppResponse
     * @author 72937
     * @Date 2020年3月24日 22:16:34
     */
    @PostMapping("addGoods")
    public AppResponse saveUser(goodsInfo goodsInfo) {
        try {
            //获取用户id 用于修改后的记录
            String userId = AuthUtils.getCurrentUserId();
            goodsInfo.setCreateBy(userId);
            AppResponse appResponse = goodsService.addGoods(goodsInfo);
            return appResponse;
        } catch (Exception e) {
            logger.error("商品新增失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }
    /**
     * 查询商品列表（分页）
     * @Param goodsInfo
     * @return AppResponse
     * @author 72937
     * @Date 2020-2-25
     */
    @RequestMapping(value="listGoods")
    public AppResponse listGoods(goodsInfo goodsInfo){
        try {
            return goodsService.listGoods(goodsInfo);
        } catch (Exception e){
            logger.error("查询商品列表异常", e);
            System.out.println(e.toString());
            throw e;
        }
    }
    /**
     * 删除商品
     * @author 72937
     * @Date 2020-3-25
     * @Param goodsId   商品id
     * @return AppResponse
     */
    @PostMapping("deleteGoods")
    public AppResponse deleteGoods(String goodsId){
        try {
            String userId = AuthUtils.getCurrentUserId();
            return goodsService.deleteGoods(goodsId,userId);
        }catch (Exception e){
            logger.error("商品删除错误", e);
            System.out.println(e.toString());
            throw e;
        }
    }

}
