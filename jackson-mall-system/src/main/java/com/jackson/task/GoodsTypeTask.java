package com.jackson.task;

import com.jackson.entity.ShopGood;
import com.jackson.repository.GoodsRepository;
import jakarta.annotation.Resource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class GoodsTypeTask {

    @Resource
    private GoodsRepository goodsRepository;

    /**
     * 设置定时任务更新商品是否为新品或者热品
     */
    @Scheduled(cron = "0 0 0 * * *")
    public void listenGoodsIsNewOrIsHot() {
        // 新品在一个月前还是新品,过了一个月就不是新品了
        List<ShopGood> oneMonthNewGoodsList = goodsRepository.findAllByIsNewAndCreateTimeBefore(true, LocalDateTime.now().plusMonths(-1L));
        oneMonthNewGoodsList.forEach(good -> {
            good.setIsNew(false);
        });
        goodsRepository.saveAllAndFlush(oneMonthNewGoodsList);
        // 热卖的标准是超过100个实际售卖量
        List<ShopGood> noHotGoodsList = goodsRepository.findAllByIsHotAndActualSales(false, 100);
        noHotGoodsList.forEach(good -> {
            good.setIsHot(true);
        });
        goodsRepository.saveAllAndFlush(noHotGoodsList);
    }
}
