# wechatutils
企业微信/微信 接口工具包

一、使用方式

    1.企业微信控制器QiYeWeChatUtil
    
        QiYeWechatUtil ctrl = new QiYeWechatUtil();
        // 初始化基本参数
        BaseParamsEntity params = new BaseParamsEntity(corpId, secret, agentId);
        // 配置文件方式
        BaseParamsEntity params = ctrl.loadProps(path);
        BaseParamsEntity params = new BaseParamsEntity(properties);
        // 开启全局通讯录模式
        boolean isOpenGlobalAddressBookSecret = true;
        BaseParamsEntity params = new BaseParamsEntity(corpId, addressBookSecret, isOpenGlobalAddressBookSecret);
        
        <1> 打开人员控制器
         ctrl.openPersonnelCtrl(params);
         // 调用人员相关操作（以创建为例）
         PersonnelEntity personnel = new PersonnelEntity();
         personnel.setUserId("xxxxxx");
         personnel.setName("钓鱼小号");
         personnel.setMobile("xxxxxx");
         personnel.setDepartment(new String[]{"xxx"});
         personnel.setOrder(new Integer[]{0});        
         boolean flag = ctrl.createPersonnel(personnel);
         
        <2> 打开部门控制器（上同）
         ctrl.openDepartmentCtrl(params);
         
        <3> 打开认证控制器（上同）
         ctrl.openLoginAuthCtrl(params);
         
        <4> 打开消息控制器（上同）
         ctrl.openMessageCtrl(params);
         
        <5> 获取jssdkconfig
         JsSdkConfigEntity config = ctrl.getJsSdkConfig(params);
         
        <6> 关闭单个应用控制器
         ctrl.closeCtrlByAgentId(agentId);
         
        <7> 关闭所有控制器
         ctrl.closeAllCtrl();

    2.开启Redis缓存模式(默认使用ehcache)
             
        <1> 示例
         RedisConfig redisConfig = new RedisConfig();
         redisConfig.setAddress("ip");
         redisConfig.setPort("port");
         redisConfig.setAuth("password");没有则不设置
         BaseParamsEntity params = new BaseParamsEntity(corpId, secret, agentId);
         params.openRedisCache(redisConfig);

    2.配置详解
             
        <1> 配置文件方式详解
         // 注意：key必须保持一致
         #企业微信配置
         corpId=xxx
         secret=xxx
         agentId=xxx
         encodingAESKey=xxx
         addressBookSecret=xxx
         isOpenGlobalAddressBookSecret=false
         #Redis配置(具体配置参照JedisPoolConfig配置)
         isOpenRedisCache=false
         redis.address=0.0.0.0
         redis.port=6379
         redis.auth=123456
         redis.maxTotal=50
         redis.maxIdle=50
         redis.minIdle=50
         redis.maxWaitMillis=50
         redis.testOnBorrow=false
         redis.testOnReturn=false
         redis.timeBetweenEvictionRunsMillis=1000
         redis.testWhileIdle=false
         redis.numTestsPerEvictionRun=1000