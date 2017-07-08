package com.xmg.pss.test;

import com.alibaba.druid.filter.config.ConfigTools;
import org.junit.Test;

public class Druidtest {
    @Test
    public void testDruid() throws Exception {
        String config = ConfigTools.encrypt("admin");
        System.out.println(config);
    }
}
