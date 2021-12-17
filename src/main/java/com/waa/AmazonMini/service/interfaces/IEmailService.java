package com.waa.AmazonMini.service.interfaces;

import com.waa.AmazonMini.domain.OrderLine;

public interface IEmailService {
    void sendOrderLineStatusChangeEmail(OrderLine reservation);
    public void sendDumpEmail();;
}
