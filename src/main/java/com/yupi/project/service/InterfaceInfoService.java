package com.yupi.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yupi.project.model.entity.InterfaceInfo;
import com.yupi.project.model.entity.Post;

/**
* @author 徐思捷
* @description 针对表【interface_info(接口信息)】的数据库操作Service
* @createDate 2023-11-25 11:28:56
*/
public interface InterfaceInfoService extends IService<InterfaceInfo> {

    /**
     * 校验
     *
     * @param interfaceinfo
     * @param add 是否为创建校验
     */
    void validInterfaceInfo(InterfaceInfo interfaceinfo, boolean add);
}
