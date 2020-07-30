package wechat.qiye;

import org.junit.Test;
import wechat.qiye.common.entity.BaseParamsEntity;
import wechat.qiye.utils.AccessTokenUtil;

public class AccessToeknTest {

    @Test
    public void getAccessToken(){
        BaseParamsEntity baseParamsEntity = new BaseParamsEntity("", "", "");
        String accessToken = AccessTokenUtil.getAccessToken(baseParamsEntity);
        System.out.println(accessToken);
    }
}
