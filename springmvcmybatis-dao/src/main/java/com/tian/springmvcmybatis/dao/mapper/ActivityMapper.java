package com.tian.springmvcmybatis.dao.mapper;

import com.tian.common.other.PageParam;
import com.tian.springmvcmybatis.dao.dto.ActivityDto;
import com.tian.springmvcmybatis.dao.entity.Activity;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface ActivityMapper {
    int deleteByPrimaryKey(Long id);

    /**
     * CachePut注解,不会在缓存中查找匹配,方法总是会被调用,然后将返回的结果加入到缓存中
     * @param record
     * @return
     */
    @CachePut
    int insertSelective(Activity record);

    /**
     * Cacheable注解会,首先在缓存中找, 找到直接返回,不再调用方法,找不到调用方法,而且会把结果加
     * 入到缓存中去.
     * @param id
     * @return
     */
    @Cacheable("activityCache")
    Activity selectByPrimaryKey(Long id);

    /**
     * CacheEvict注解不会往缓存中添加任何东西,而是从缓存中移除数据一个或者多个
     *
     * value表示条目
     * key表示该条目下的缓存的key值
     * #root.args[0].id 这个spel表达式,表示该访求第一个参数的id属性
     * @param record
     * @return
     */
    @CacheEvict(value = "activityCache",key = "#root.args[0].id")
    int updateByPrimaryKeySelective(Activity record);

    int updateByPrimaryKey(Activity record);

    /**
     * 查询出所有需要进行状态修改的数据
     * @return
     */
    List<ActivityDto> queryNeedUpdateStatus(@Param("startTime") String startTime, @Param("endTime") String endTime);

    /**
     * 分页查询活动列表(结果集)
     * @param pageParam
     * @return
     */
    List<Activity> queryByPage(PageParam<Activity> pageParam);

}