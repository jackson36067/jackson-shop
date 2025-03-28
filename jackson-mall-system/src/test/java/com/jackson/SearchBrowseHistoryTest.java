package com.jackson;

import com.jackson.constant.BrowseHistoryConstant;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

//@SpringBootTest
public class SearchBrowseHistoryTest {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void test() {
        for (int i = 0; i < 5; i++) {
            LocalDate localDate = LocalDate.now().minusDays(i);
            stringRedisTemplate.opsForZSet().add(BrowseHistoryConstant.BROWSE_SEARCH_KEY_PREFIX + "93:" + localDate, "x", System.currentTimeMillis() - i * 24 * 60 * 60 * 1000);
            stringRedisTemplate.expire(BrowseHistoryConstant.BROWSE_SEARCH_KEY_PREFIX + "93:" + localDate.toString(), (7L - i), TimeUnit.DAYS);
        }
    }

    @Test
    public void test2() {
        Set<String> keys = stringRedisTemplate.keys(BrowseHistoryConstant.BROWSE_SEARCH_KEY_PREFIX + "93:*");
        List<String> list = keys.stream().sorted((o1, o2) -> {
            String[] split1 = o1.split(":");
            String[] split2 = o2.split(":");
//            System.out.println(split1[split1.length - 1]);
//            LocalDate parse = LocalDate.parse(split1[split1.length - 1]);
//            LocalDate parse2 = LocalDate.parse(split2[split2.length - 1]);
//            return parse.compareTo(parse2);
            return split2[split2.length - 1].compareTo(split1[split1.length - 1]);
        }).toList();
        for (String key : list) {
            System.out.println(key);
            System.out.println(stringRedisTemplate.opsForZSet().range(key, 0, -1));
        }
    }
}
