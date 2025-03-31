local userId = ARGV[1]
local couponId = ARGV[2]

local stockKey = 'coupon:stock:' .. couponId
-- 用户领取优惠卷key
local orderKey = 'member:coupon:' .. couponId

-- 判断该优惠卷是否还有库存
if(tonumber(redis.call('get',stockKey)) <= 0) then
    return 1
end

-- 判断用户是否领取过优惠卷
if(redis.call('sismember',orderKey,userId) == 1) then
    return 2
end

-- 用户领取了优惠卷且库存充足
redis.call('incrby',stockKey,-1)
-- 添加用户到优惠卷领取列表中
redis.call('sadd',orderKey,userId)
return 0