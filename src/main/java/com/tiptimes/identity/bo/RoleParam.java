package com.tiptimes.identity.bo;

import lombok.Data;
import java.util.List;

@Data
public class RoleParam extends BaseParam{

    private String roleId;

    private List<String> permissionIds;

}
