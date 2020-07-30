package wechat.qiye;

import org.junit.Test;
import wechat.common.utils.AccessTokenUtil;
import wechat.qiye.common.entity.BaseParamsEntity;

public class AccessToeknTest {

    @Test
    public void getAccessToken(){
        BaseParamsEntity baseParamsEntity = new BaseParamsEntity("", "", "");
        String accessToken = AccessTokenUtil.getAccessToken(baseParamsEntity);
        System.out.println(accessToken);
    }
}
