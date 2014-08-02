package com.fastek.mailinbox;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.mail.AuthenticationFailedException;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;

public class GetInboxData {

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMMM-yyyy");

    public Object getDesc(String dateVal, String subject, String email, String pass) {
        Properties props = System.getProperties();
        Object desc = "No, email corresponding to this date and subject .";
        System.out.println("starts===" + new Date());//6525
        props.setProperty("mail.store.protocol", "imaps");
        int totalRec = 29;
        Date d1 = new Date();
        Date d2 = null;
        try {
            d2 = dateFormat.parse(dateVal);
        } catch (ParseException ex) {
            ex.printStackTrace();
            //Logger.getLogger(GetInboxData.class.getName()).log(Level.SEVERE, null, ex);
        }
        long diff = d1.getTime() - d2.getTime();
        System.out.println("difference days===" + diff / (24 * 60 * 60 * 1000));
        totalRec = (int) diff / (24 * 60 * 60 * 1000);
        if (totalRec <= 2) {
            totalRec = 10;
        }
        try {
            Session session = Session.getDefaultInstance(props, null);
            Store store = session.getStore("imaps");
            store.connect("imap.gmail.com", email, pass);
            Folder inbox = store.getFolder("INBOX");
            int msgCount = inbox.getMessageCount();
            System.out.println("total message===" + msgCount);
            inbox.open(Folder.READ_ONLY);//https://github.com/Sibaram/MailApplication.git
            Message messages[] = inbox.getMessages(msgCount - totalRec, msgCount);
            //Message messages[] = inbox.getMessages();
            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("sibaram");
            System.out.println("");
            List<Message> list = Arrays.asList(messages);
            System.out.println("total records==" + list.size());
            System.out.println("Please wait while getting message body content ...");
            for (Message message : messages) {
                Date dt = message.getReceivedDate();
                String dt1 = dateFormat.format(dt);
                //System.out.println("dt1=="+dt1+"  dateVal=="+ dateVal);
                if (dt1.equals(dateVal)) {
                    System.out.println("sub===" + message.getSubject().trim());
                    if (subject.replace("Re: ", "").trim().equals(message.getSubject().replace("Re: ", "").trim())) {
                        Multipart multipart1 = (Multipart) message.getContent();
                        for (int j = 0; j < multipart1.getCount(); j += 2) {
                            BodyPart bodyPart = multipart1.getBodyPart(j);
                            String disposition = bodyPart.getDisposition();
                            System.out.println("Body: " + j + "   \n" + bodyPart.getContent());
                            desc = bodyPart.getContent();
                            if (disposition != null && (disposition.equalsIgnoreCase("ATTACHMENT"))) { // BodyPart.ATTACHMENT doesn't work for gmail
                                DataHandler handler = bodyPart.getDataHandler();
                                System.out.println("file name : " + handler.getName());
                            } else {
                            }
                        }
                    }
                    break;
                }
            }
            System.out.println("find message body content ...");
        } catch (AuthenticationFailedException e) {
            desc = "Incorrect Email id or Password .";
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
            desc = "Some error occored while getting email body .";
        } catch (MessagingException e) {
            e.printStackTrace();
            desc = "Some error occored while getting email body .";
        } catch (IOException e) {
            e.printStackTrace();
            desc = "Some error occored while getting email body .";
        } catch (Exception e) {
            e.printStackTrace();
            desc = "Some error occored while getting email body .";
        }
        System.out.println("ends===" + new Date());
        return desc;
    }

    public Object getCurrentDateDesc(String dateVal, String subject, String email, String pass) {
        Properties props = System.getProperties();
        Object desc = "No, email corresponding to current date .";
        System.out.println("starts===" + new Date());
        props.setProperty("mail.store.protocol", "imaps");
        try {
            Session session = Session.getDefaultInstance(props, null);
            Store store = session.getStore("imaps");
            store.connect("imap.gmail.com", email, pass);
            Folder inbox = store.getFolder("INBOX");
            int msgCount = inbox.getMessageCount();
            System.out.println("total message===" + msgCount);
            inbox.open(Folder.READ_ONLY);
            Message messages[] = inbox.getMessages(msgCount - 5, msgCount);
            List<Message> list = Arrays.asList(messages);
            System.out.println("size=======" + list.size());
            System.out.println("Please wait while getting message body content ...");
            dateVal = dateFormat.format(new Date());
            System.out.println("contains method===");
            for (Message message : messages) {
                Date dt = message.getReceivedDate();
                String dt1 = dateFormat.format(dt);
                System.out.println("dt1==" + dt1 + "  dateVal==" + dateVal);
                System.out.println("sub===before=" + message.getSubject().trim());
                if (dt1.equals(dateVal)) {
                    System.out.println("sub===" + message.getSubject().trim());
                    System.out.println(subject.replace("Re: ", "").trim() + "   " + message.getSubject().replace("Re: ", "").trim());
                    if (subject.replace("Re: ", "").trim().equals(message.getSubject().replace("Re: ", "").trim())) {
                        System.out.println("i am inside....");
                        Multipart multipart1 = (Multipart) message.getContent();
                        for (int j = 0; j < multipart1.getCount(); j += 2) {
                            BodyPart bodyPart = multipart1.getBodyPart(j);
                            String disposition = bodyPart.getDisposition();
                            System.out.println("Body: " + j + "   \n" + bodyPart.getContent());
                            desc = bodyPart.getContent();
                            if (disposition != null && (disposition.equalsIgnoreCase("ATTACHMENT"))) { // BodyPart.ATTACHMENT doesn't work for gmail
                                DataHandler handler = bodyPart.getDataHandler();
                                System.out.println("file name : " + handler.getName());
                            } else {
                            }
                        }
                    }
                    //break;
                }
            }
            System.out.println("find message body content ...");
        } catch (AuthenticationFailedException e) {
            desc = "Incorrect Email id or Password .";
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
            desc = "Some error occored while getting email body .";
        } catch (MessagingException e) {
            e.printStackTrace();
            desc = "Some error occored while getting email body .";
        } catch (IOException e) {
            e.printStackTrace();
            desc = "Some error occored while getting email body .";
        } catch (Exception e) {
            e.printStackTrace();
            desc = "Some error occored while getting email body .";
        }
        System.out.println("ends===" + new Date());
        return desc;
    }
}