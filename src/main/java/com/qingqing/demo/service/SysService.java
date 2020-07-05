//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.qingqing.demo.service;

import com.qingqing.demo.entity.SysLog;
import com.qingqing.demo.entity.SysView;
import java.util.List;

public interface SysService {
 void addLog(SysLog sysLog);

 void addView(SysView sysView);

 int getLogCount();

 int getViewCount();

 List<SysLog> listAllLog();

 List<SysView> listAllView();
}
