package wechat.qiye;

import org.junit.Test;
import wechat.common.entity.BaseParamsEntity;
import wechat.common.utils.AccessTokenUtil;

public class AccessToeknTest {

    @Test
    public void getAccessToken(){
        BaseParamsEntity baseParamsEntity = new BaseParamsEntity("", "", "");
        String accessToken = AccessTokenUtil.getAccessToken(baseParamsEntity);
        System.out.println(accessToken);
    }
}
