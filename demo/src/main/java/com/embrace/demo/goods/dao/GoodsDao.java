package com.embrace.demo.goods.dao;

import com.embrace.demo.goods.entity.goodsInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName GoodsDao
 * @Description goods
 * @author 72937
 * @Date 2020-03-24
 */
@Mapper
public interface GoodsDao {
    /**
     * 查看是否存在商品
     * @param goodsId 商品信息
     * @return
     */
    int countgoodsBybookId(@Param("goodsId") int goodsId);
    /**
     * 新增商品
     * @param  goodsInfo
     * @return
     */
    int addGoods(goodsInfo goodsInfo);
    /**
     * 查询商品列表
     * @Param goodsInfo 商品信息
     * @return 商品信息列表
     */
    List<goodsInfo> listGoodsByPage(goodsInfo goodsInfo);

    /**
     *
     * @param listCode
     * @param goodsId
     * @return
     */
    int deleteGoods(List<String> listCode,@Param("goodId") String goodsId);

}
