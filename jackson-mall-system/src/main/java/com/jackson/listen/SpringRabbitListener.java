package com.jackson.listen;

import com.jackson.constant.RabbitMQConstant;
import com.jackson.entity.ShopMemberFollowStore;
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

    /**
     * 监听队列shop_queue的信息,将信息添加到数据库中
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
}
