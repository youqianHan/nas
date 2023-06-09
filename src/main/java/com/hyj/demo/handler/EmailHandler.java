package com.hyj.demo.handler;

import com.hyj.demo.common.entity.EmailInfo;

public interface EmailHandler {
   void handler(String directoryPath, String directorySize,EmailInfo emailInfo);
}
