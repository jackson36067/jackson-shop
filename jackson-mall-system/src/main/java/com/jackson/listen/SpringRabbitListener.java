package com.jackson.listen;

import com.jackson.constant.BrowseHistoryConstant;
import com.jackson.constant.RabbitMQConstant;
import com.jackson.entity.ShopMemberBrowseHistory;
import com.jackson.entity.ShopMemberFollowStore;
import com.jackson.repository.MemberBrowseHistoryRepository;
import com.jackson.repository.MemberFollowStoreRepository;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class SpringRabbitListener {

    @Resource
    private MemberFollowStoreRepository memberFollowStoreRepository;
    @Resource
    private MemberBrowseHistoryRepository memberBrowseHistoryRepository;

    /**
     * 监听队列shop_queue的信息,将信息添加到数据库中,用户关注店铺信息
     *
     * @param info userId,storeId
     */
    @RabbitListener(queues = RabbitMQConstant.QUEUE_KEY)
    private void listenMemberFollowStore(Map<String, Long> info) {
        Long userId = info.get("userId");
        Long storeId = info.get("storeId");
        ShopMemberFollowStore shopMemberFollowStore = new ShopMemberFollowStore(null, userId, storeId, null);
        memberFollowStoreRepository.save(shopMemberFollowStore);
    }

    @RabbitListener(queues = RabbitMQConstant.BROWSE_QUEUE_KEY)
    private void listenMemberBrowseStore(Map<String, Long> info) {
        Long userId = info.get("memberId");
        Long storeId = info.get("storeId");
        Long goodsId = info.get("goodsId");
        Long commentId = info.get("commentId");
        Long type = info.get("type");
        ShopMemberBrowseHistory shopMemberBrowseHistory = new ShopMemberBrowseHistory();
        shopMemberBrowseHistory.setMemberId(userId);
        if (storeId != null) {
            shopMemberBrowseHistory.setStoreId(storeId);
        }
        if (goodsId != null) {
            shopMemberBrowseHistory.setGoodsId(goodsId);
        }
        if (commentId != null) {
            shopMemberBrowseHistory.setCommentId(commentId);
        }
        shopMemberBrowseHistory.setType(Short.parseShort(type.toString()));
        memberBrowseHistoryRepository.save(shopMemberBrowseHistory);
    }
}
