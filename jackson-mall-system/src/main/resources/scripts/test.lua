--local couponId = ARGV[1]  -- Lua 索引从 1 开始
local mysql = require("luasql.mysql")

-- 创建数据库连接
local env = mysql.mysql()
local conn = env:connect("jackson_mall", "root", "1234", "127.0.0.1", 3306)

if not conn then
    print("无法连接到数据库")
    return
end

-- 执行 SQL 查询
local sql = string.format("SELECT * FROM shop_coupon WHERE id = 1")
local cursor = conn:execute(sql)  -- `cursor` 是查询结果

if cursor then
    local row = cursor:fetch({}, "a")  -- 获取查询结果

    if row and tonumber(row.num) > 0 then
        print("还有库存")
    else
        print("没有库存了")
    end

    cursor:close()  -- 关闭游标
else
    print("查询失败")
end

-- 关闭连接
conn:close()
env:close()
