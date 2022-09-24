package com.junitproject.util;

public class MailSenderAdapter implements MailSender{
    
    private Mail mail;

    public MailSenderAdapter(Mail mail) {
        this.mail = mail;
    }

    @Override
    public boolean send() {
        // TODO Auto-generated method stub
        // return mail.send();
        return true;
    }
}
