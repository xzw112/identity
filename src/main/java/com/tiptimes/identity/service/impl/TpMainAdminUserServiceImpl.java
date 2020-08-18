package com.tiptimes.identity.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tiptimes.identity.bo.AdminUserParam;
import com.tiptimes.identity.common.PageResult;
import com.tiptimes.identity.dao.*;
import com.tiptimes.identity.entity.*;
import com.tiptimes.identity.enums.DataStatus;
import com.tiptimes.identity.qo.*;
import com.tiptimes.identity.service.TpMainAdminUserService;
import com.tiptimes.identity.utils.*;
import com.tiptimes.identity.vo.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 后台人员信息模块 Service层
 */
@Service
@Transactional
public class TpMainAdminUserServiceImpl implements TpMainAdminUserService {

    @Resource
    private TpMainAdminUserMapper tpMainAdminUserMapper;
    @Resource
    private UserPostMapper userPostMapper;
    @Resource
    private UserDepartmentMapper userDepartmentMapper;
    @Resource
    private UserGroupMapper userGroupMapper;
    @Resource
    private HatProvinceMapper hatProvinceMapper;
    @Resource
    private HatCityMapper hatCityMapper;
    @Resource
    private HatAreaMapper hatAreaMapper;


    @Override
    public PageResult<TpMainAdminUserVO> selectPageList(AdminUserParam adminUserParam) {
        if (adminUserParam.getPageNumber() != null && adminUserParam.getPageSize() != null) {
            PageHelper.startPage(adminUserParam.getPageNumber(), adminUserParam.getPageSize());
        }
        PageInfo<TpMainAdminUserVO> pageInfo = new PageInfo<>(tpMainAdminUserMapper.selectList(adminUserParam));
        List<TpMainAdminUserVO> list = pageInfo.getList();
        if (list.size() > 0) {
            for (TpMainAdminUserVO tpMainAdminUserVO : list) {
                //转换时间
                if (tpMainAdminUserVO.getUpdateTime() == null) {
                    tpMainAdminUserVO.setUpdateTimeStr(DateUtil.dateTimeToStr(tpMainAdminUserVO.getCreateTime()));
                } else {
                    tpMainAdminUserVO.setUpdateTimeStr(DateUtil.dateTimeToStr(tpMainAdminUserVO.getUpdateTime()));
                }
            }
        }
        PageResult<TpMainAdminUserVO> pageResult = new PageResult<>();
        pageResult.setTotal(pageInfo.getTotal());
        pageResult.setRows(list);
        return pageResult;
    }

    @Override
    public PageResult<OutUserVo> selectOutUserList(OutUserRequest outUserRequest) {
        PageHelper.startPage(outUserRequest.getPageNumber(), outUserRequest.getPageSize());
        List<OutUserVo> list = tpMainAdminUserMapper.selectOutUserList(outUserRequest);
        PageInfo<OutUserVo> pageInfo = new PageInfo<>(list);
        PageResult<OutUserVo> pageResult = new PageResult<>();
        pageResult.setTotal(pageInfo.getTotal());
        pageResult.setRows(list);
        return pageResult;
    }

    @Override
    public ClientUserVo selectUserById(String id) {
        return tpMainAdminUserMapper.selectUserById(id);
    }

    @Override
    @Transactional
    public int add(TpMainAdminUser tpMainAdminUser) {
        int num = tpMainAdminUserMapper.insert(tpMainAdminUser);
        // 添加所属部门
        if (StringUtils.isNotEmpty(tpMainAdminUser.getDepartment())) {
            String[] department = tpMainAdminUser.getDepartment().split(",");
            if (department.length > 0) {
                for (int i = 0; i < department.length; i++) {
                    UserDepartment userDepartment = new UserDepartment();
                    userDepartment.setDepartmentId(Integer.valueOf(department[i]));
                    userDepartment.setUserId(tpMainAdminUser.getId());
                    num += userDepartmentMapper.insert(userDepartment);
                }
            }
        }

        // 添加 副岗
        if (StringUtils.isNotEmpty(tpMainAdminUser.getDeputyPost())) {
            String[] deputyPost = tpMainAdminUser.getDeputyPost().split(",");
            if (deputyPost.length > 0) {
                for (int i = 0; i < deputyPost.length; i++) {
                    UserPost userDeputyPost = new UserPost();
                    userDeputyPost.setPostId(Integer.valueOf(deputyPost[i]));
                    userDeputyPost.setUserId(tpMainAdminUser.getId());
                    num += userPostMapper.insert(userDeputyPost);
                }
            }
        }
        // 添加分组
        if (StringUtils.isNotEmpty(tpMainAdminUser.getGroupId())) {
            String[] ids = tpMainAdminUser.getGroupId().split(",");
            for (int i = 0; i < ids.length; i++) {
                UserGroup userGroup = new UserGroup();
                userGroup.setGroupId(Integer.valueOf(ids[i]));
                userGroup.setUserId(tpMainAdminUser.getId());
                num += userGroupMapper.insert(userGroup);
            }
        }
        return num;
    }

    @Override
    @Transactional
    public int update(TpMainAdminUser tpMainAdminUser) {
        int num = 0;
        num += tpMainAdminUserMapper.updateByPrimaryKeySelective(tpMainAdminUser);

        // 更新所属部门
        String[] department = tpMainAdminUser.getDepartment().split(",");
        if (department.length > 0) {
            num += userDepartmentMapper.delByUserId(tpMainAdminUser.getId());
            for (int i = 0; i < department.length; i++) {
                UserDepartment userDepartment = new UserDepartment();
                userDepartment.setDepartmentId(Integer.valueOf(department[i]));
                userDepartment.setUserId(tpMainAdminUser.getId());
                num += userDepartmentMapper.insert(userDepartment);
            }
        }

        // 更新所属岗位
        String[] deputyPost = tpMainAdminUser.getDeputyPost().split(",");
        if (deputyPost.length > 0) {
            num += userPostMapper.delByUserId(tpMainAdminUser.getId());
            for (int i = 0; i < deputyPost.length; i++) {
                UserPost userDeputyPost = new UserPost();
                userDeputyPost.setPostId(Integer.valueOf(deputyPost[i]));
                userDeputyPost.setUserId(tpMainAdminUser.getId());
                num += userPostMapper.insert(userDeputyPost);
            }
        }

        // 更新用户所属分组
        if (StringUtils.isNotEmpty(tpMainAdminUser.getGroupId())) {
            String[] ids = tpMainAdminUser.getGroupId().split(",");
            num += userGroupMapper.delByUserId(tpMainAdminUser.getId());
            for (int i = 0; i < ids.length; i++) {
                UserGroup userGroup = new UserGroup();
                userGroup.setGroupId(Integer.valueOf(ids[i]));
                userGroup.setUserId(tpMainAdminUser.getId());
                num += userGroupMapper.insert(userGroup);
            }
        }
        return num;
    }

    @Override
    public int batchDel(String[] idArr) {
        for (String id : idArr) {
            TpMainAdminUser tpMainAdminUser = new TpMainAdminUser();
            tpMainAdminUser.setId(id);
            tpMainAdminUser.setIsDelete(DataStatus.DELETED.getCode());
            tpMainAdminUserMapper.updateByPrimaryKeySelective(tpMainAdminUser);
        }
        return 1;
    }

    @Override
    public int updateUserLeave(String[] id) {
        int num = tpMainAdminUserMapper.updateUserLeave(id);
        return num;
    }

    @Override
    public int updateUserUnLeave(String[] id) {
        int num = tpMainAdminUserMapper.updateUserUnLeave(id);
        return num;
    }

    @Override
    public int updateOutUser(OutUser outUser) {
        if (StringUtils.isNotEmpty(outUser.getLoginPassword())) {
            outUser.setLoginPassword(BCrypt.hashpw(BASE64Util.getFromBase64(outUser.getLoginPassword()), BCrypt.gensalt()));
        }
        outUser.setUpdateTime(new Date());
        outUser.setUpdateUser(CurrentUserUtil.getCurrentUserId());
        int num = tpMainAdminUserMapper.updateOutUser(outUser);
        return num;
    }

    @Override
    public int insertOutUser(OutUser outUser) {
        outUser.setId(UUIDUtil.getUUID());
        outUser.setLoginPassword(BCrypt.hashpw(BASE64Util.getFromBase64(outUser.getLoginPassword()), BCrypt.gensalt()));
        outUser.setCreateTime(new Date());
        outUser.setCreateUser(CurrentUserUtil.getCurrentUserId());
        outUser.setIsDelete(0);
        outUser.setStatus(0);
        int num = tpMainAdminUserMapper.insertOutUser(outUser);
        return num;
    }

    @Override
    public int insertClientOutUser(RegisterOutUserRequest registerOutUserRequest) {
        registerOutUserRequest.setId(UUIDUtil.getUUID());
        registerOutUserRequest.setLoginPassword(BCrypt.hashpw(BASE64Util.getFromBase64(registerOutUserRequest.getLoginPassword()), BCrypt.gensalt()));
        return tpMainAdminUserMapper.insertClientOutUser(registerOutUserRequest);
    }

    @Override
    public int updateUserUse(String id) {
        return tpMainAdminUserMapper.updateUserUse(id);
    }

    @Override
    public int updateUserUnUse(String id) {
        return tpMainAdminUserMapper.updateUserUnUse(id);
    }

    @Override
    public int updateUserHead(UserHeadRequest userHeadRequest) {
        return tpMainAdminUserMapper.updateUserHead(userHeadRequest);
    }

    @Override
    public List<ProvinceVo> cities() {

        // 返回的结果
        List<ProvinceVo> list = new ArrayList<>();
        // 省数据
        List<HatProvince> provinceList = hatProvinceMapper.selectProvinceList();
        // 城市数据
        List<HatCity> cityList = hatCityMapper.selectCityList();
        // 区县数据
        List<HatArea> areaList = hatAreaMapper.selectAreaList();
        // 处理省数据
        if (provinceList.size() > 0) {
            for (int i = 0; i < provinceList.size(); i++) {
                ProvinceVo provinceVo = new ProvinceVo();
                HatProvince hatProvince = provinceList.get(i);
                provinceVo.setLable(hatProvince.getProvince());
                provinceVo.setValue(hatProvince.getProvinceId());
                list.add(provinceVo);
            }
        }

        // 处理城市数据
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                List<CityVo> cityVoList = new ArrayList<>();
                ProvinceVo provinceVo = list.get(i);
                for (int j = 0; j < cityList.size(); j++) {
                    HatCity hatCity = cityList.get(j);
                    if (provinceVo.getValue().equals(hatCity.getFather())) {
                        CityVo cityVo = new CityVo();
                        cityVo.setLable(hatCity.getCity());
                        cityVo.setValue(hatCity.getCityId());
                        cityVoList.add(cityVo);
                    }
                }
                list.get(i).setChildren(cityVoList);
            }
        }

        // 处理区县数据
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                List<CityVo> cityChildrenList = list.get(i).getChildren();
                for (int j = 0; j < cityChildrenList.size(); j++) {
                    List<AreaVo> areaVoList = new ArrayList<>();
                    CityVo cityVo = cityChildrenList.get(j);
                    for (int k = 0; k < areaList.size(); k++) {
                        HatArea hatArea = areaList.get(k);
                        if (cityVo.getValue().equals(hatArea.getFather())) {
                            AreaVo areaVo = new AreaVo();
                            areaVo.setValue(hatArea.getAreaId());
                            areaVo.setLable(hatArea.getArea());
                            areaVoList.add(areaVo);
                        }
                    }
                    list.get(i).getChildren().get(j).setChildren(areaVoList);
                }
            }
        }
        return list;
    }

    @Override
    public int updateOutUserInfo(OutUserInfoRequest outUserInfoRequest) {
        return tpMainAdminUserMapper.updateOutUserInfo(outUserInfoRequest);
    }

    @Override
    public int updateUserPwd(UserPwdRequest userPwdRequest) {
        if (StringUtils.isNotEmpty(userPwdRequest.getId())) {
            String pwd = tpMainAdminUserMapper.selectUserPwd(userPwdRequest.getId());
            if (StringUtils.isNotEmpty(pwd)) {
                if (userPwdRequest.getNewPassword().equals(userPwdRequest.getReportPassword())) {
                    String newPwd = BASE64Util.getFromBase64(userPwdRequest.getNewPassword());
                    boolean flag = BCrypt.checkpw(newPwd, pwd);
                    if (flag) {
                        int num = tpMainAdminUserMapper.updateUserPwd(userPwdRequest);
                        return num;
                    } else {
                        return -2;
                    }
                } else {
                    return -1;
                }
            }
        }
        return 0;
    }
}
